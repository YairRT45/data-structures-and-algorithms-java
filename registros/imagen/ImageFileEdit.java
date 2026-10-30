package registros.imagen;

import javax.imageio.ImageIO;
import estructurasnolineales.MatrizInfo2Numerica;
import utils.comunes.TipoRedimension;
import utils.comunes.TipoTemperatura;
import java.awt.*;
import java.awt.image.BufferedImage;
// import java.io.ByteArrayOutputStream;
import java.io.File;
// import java.io.FileInputStream;
import java.io.IOException;

public class ImageFileEdit {
    protected MatrizInfo2Numerica matrizPixeles;
    protected BufferedImage imagen;
    protected BufferedImage imagen2;
    protected int alto;
    protected int ancho;

    public ImageFileEdit(String nomArchivo) {
        try {
            imagen = ImageIO.read(new File(nomArchivo));
            ancho = imagen.getWidth();
            alto = imagen.getHeight();
            imagen2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            matrizPixeles = new MatrizInfo2Numerica(ancho, alto);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean pasarGris() {
        //Iterar sobre cada pixel en la imagen
        for (int anchoActual = 0; anchoActual < ancho ; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) { 
                //Obtener el color del pixel en la coordenada actual
                int pixel = imagen.getRGB(anchoActual, altoActual);
                // Extraer los componentes de color: alfa, rojo, verde y azul
                int alpha = (pixel >> 24) & 0xFF;
                int rojo = (pixel >> 16) & 0xFF;
                int verde = (pixel >> 8) & 0xFF;
                int azul = pixel & 0xFF;
                //Calcular el promedio de los componentes de color par aobtener su escala de gris
                int gris = (rojo + verde + azul) / 3;
                //Crear un nuevo pixel con el valor de gris calculado
                int nuevoPixel = (alpha << 24) | (gris << 16) | (gris << 8) | gris;
                matrizPixeles.cambiar(anchoActual, altoActual, nuevoPixel);
            }
        }
        return true;
    }

    public boolean modificarBrillo(int brillo){
        //Iterar sobre cada pixel en la imagen
        for (int anchoActual = 0; anchoActual < ancho; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) { 
                //Obtener el color del pixel en la coordenada actual
                int pixel = imagen.getRGB(anchoActual, altoActual);
                // Extraer los componentes de color: alfa, rojo, verde y azul
                int alpha = (pixel >> 24) & 0xFF;
                int rojo = (pixel >> 16) & 0xFF;
                int verde = (pixel >> 8) & 0xFF;
                int azul = pixel & 0xFF;
                // Calcular la escala de grises y luego modificarla según el brillo proporcionado
                int gris = ((rojo + verde + azul) / 3) + brillo;
                // Ajustar el valor de gris si está fuera del rango (0, 255)
                if (gris < 0) {
                    gris = 0;
                }else if (gris > 255) {
                    gris = 255;
                }
                // Crear un nuevo píxel con el valor de gris modificado
                int nuevoPixel = (alpha << 24) | (gris << 16) | (gris << 8) | gris;
                matrizPixeles.cambiar(anchoActual, altoActual, nuevoPixel);
            }
        }
        return true;
    }

    public boolean invertirHorizontal(){
        // Se lee la imagen y se invierte de forma vertical, ya que los valores en la matriz se guardan diferente a como lo percibimos visualmente
        leerImagen();
        matrizPixeles.invertirVertical();
        return true;
    }

    public boolean invertirVertical(){
        // Se lee la imagen y se invierte de forma horizontal, ya que los valores en la matriz se guardan diferente a como lo percibimos visualmente
        leerImagen();
        matrizPixeles.invertirHorizontal();
        return true;
    }

    public boolean transpuesta(){
        // Leer la imagen y luego obtener su transpuesta intercambiando filas y columnas
        leerImagen();
        // Actualizar el ancho y alto de la imagen intercambiando sus dimensiones
        ancho = matrizPixeles.obtenerColumnas();
        alto = matrizPixeles.obtenerRenglones();
        matrizPixeles.transpuesta();
        return true;
    }

    public boolean rotar90(){
        //Obtener la transpuesta de la imagen
        transpuesta();
        //Invertir la imagen verticalmente para completar la rotación
        matrizPixeles.invertirVertical();
        return true;
    }

    public boolean rotar180(){
        // Invertir la imagen horizontalmente
        invertirHorizontal();
        //Invertir la imagen horizontalmente nuevamente para completar la rotación
        matrizPixeles.invertirHorizontal();
        return true;
    }

    public boolean rotar270(){
        //Obtener la transpuesta de la imagen
        transpuesta();
        //Invertir la imagen horizontalmente para completar la rotación
        matrizPixeles.invertirHorizontal();
        return true;
    }

    public boolean redimensionarXMetodo(TipoRedimension metodo) {
        int nuevoAncho = 0;
        int nuevoAlto = 0;
    
        // Calcular las nuevas dimensiones según el tipo de redimensionamiento especificado
        switch (metodo) {
            case DOBLE:
                nuevoAncho = ancho * 2;
                nuevoAlto = alto * 2;
                break;
            case TRIPLE:
                nuevoAncho = ancho * 3;
                nuevoAlto = alto * 3;
                break;
            case CUADRUPLE:
                nuevoAncho = ancho * 4;
                nuevoAlto = alto * 4;
                break;
            case MITAD:
                nuevoAncho = ancho / 2;
                nuevoAlto = alto / 2;
                break;
            case TERCIO:
                nuevoAncho = ancho / 3;
                nuevoAlto = alto / 3;
                break;
        }
        // Llamar al método redimensionarImagen con las nuevas dimensiones calculadas
        return redimensionarImagen(nuevoAncho,nuevoAlto);
    }

    public boolean redimensionarImagen(int nuevoAncho, int nuevoAlto){
        leerImagen();
        // Crear una nueva matriz para almacenar los píxeles redimensionados
        MatrizInfo2Numerica nuevaMatriz = new MatrizInfo2Numerica(nuevoAncho, nuevoAlto);    
        // Iterar sobre la nueva matriz y mapear los píxeles de la imagen original a las nuevas dimensiones    
        for (int nuevoAltoActual = 0; nuevoAltoActual < nuevoAlto; nuevoAltoActual++){
            for (int nuevoAnchoActual = 0; nuevoAnchoActual < nuevoAncho; nuevoAnchoActual++) {
                int antiguoAncho = (int) (nuevoAnchoActual * (ancho / (double) nuevoAncho));
                int antiguoAlto = (int) (nuevoAltoActual * (alto / (double) nuevoAlto));
                // Obtener el píxel de la imagen original en las coordenadas redimensionadas
                int pixel = ((Double) matrizPixeles.obtener(antiguoAncho, antiguoAlto)).intValue();
                // Colocar el píxel en la nueva matriz en las coordenadas correspondientes
                nuevaMatriz.cambiar(nuevoAnchoActual, nuevoAltoActual, pixel);
            }
        }
        // Redefinir la matriz de píxeles con la nueva matriz redimensionada
        matrizPixeles.redefinir(nuevaMatriz);
        // Actualizar el ancho y alto de la imagen
        ancho = matrizPixeles.obtenerRenglones();
        alto = matrizPixeles.obtenerColumnas();
        return true;
    }

    public boolean agregarMarco(Color color, int grosor){
        leerImagen();
        // Crear una nueva matriz con el tamaño ampliado para el marco
        MatrizInfo2Numerica matrizConMarco = new MatrizInfo2Numerica(ancho + (2 * grosor), alto + (2 * grosor));
        // Rellenar la nueva matriz con el color del marco
        for (int anchoActual = 0; anchoActual < matrizConMarco.obtenerRenglones(); anchoActual++) {
            for (int altoActual = 0; altoActual < matrizConMarco.obtenerColumnas(); altoActual++) {
                matrizConMarco.cambiar(anchoActual, altoActual, color.getRGB());
            }
        }

        // Copiar los píxeles de la imagen original al centro de la matriz con el marco
        for (int anchoActual = 0; anchoActual < ancho; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) {
                int pixel = ((Double)matrizPixeles.obtener(anchoActual, altoActual)).intValue();
                matrizConMarco.cambiar(anchoActual + grosor, altoActual + grosor, pixel);
            }
        }
        // Redefinir la matriz de píxeles con la matriz que incluye el marco
        matrizPixeles.redefinir(matrizConMarco);
        // Actualizar el ancho y alto de la imagen
        ancho = matrizPixeles.obtenerRenglones();
        alto = matrizPixeles.obtenerColumnas();
        return true;
    }
    
    public boolean colorXTemperatura(TipoTemperatura tempe){
        // Factores de ajuste para los componentes rojo y azul del píxel
        double factorRojo;
        double factorAzul;
        // Determinar los factores según el tipo de temperatura especificada
        if (tempe == TipoTemperatura.CALIDO) {
            factorRojo = 1.2;
            factorAzul = 0.8;
        } else {
            factorRojo = 0.8;
            factorAzul = 1.2;
        }

        // Iterar sobre cada píxel en la imagen
        for (int anchoActual = 0; anchoActual < ancho ; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) { 
                //Obtener el color del pixel de acuerdo a asu coordenada acutual
                int pixel = imagen.getRGB(anchoActual, altoActual);
                // Extraer los componentes de color: alfa, rojo, verde y azul
                int alpha = (pixel >> 24) & 0xFF;
                int rojo = (pixel >> 16) & 0xFF;
                int verde = (pixel >> 8) & 0xFF;
                int azul = pixel & 0xFF;

                // Ajustar los componentes rojo y azul multiplicando por los factores de ajuste
                rojo = (int) (rojo * factorRojo);
                azul = (int) (azul * factorAzul);

                // Asegurarse de que los valores de rojo y azul estén dentro del rango (0, 255)
                if (rojo < 0) {
                    rojo = 0;
                }else if (rojo > 255) {
                    rojo = 255;
                }
                if (azul < 0) {
                    azul = 0;
                } else if (azul > 255){
                    azul = 255;
                }

                // Crear un nuevo píxel con los componentes ajustados y el mismo componente verde
                int nuevoPixel = (alpha << 24) | (rojo << 16) | (verde << 8) | azul;
                // Cambiar el píxel en la matriz de píxeles con el nuevo píxel
                matrizPixeles.cambiar(anchoActual, altoActual, nuevoPixel);
            }
        }
        return true;
    }
    
    private void editarImagen() {
        // Crear una nueva imagen (BufferedImage) con las dimensiones de ancho y alto
        imagen2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        // Iterar sobre cada píxel en la matriz de píxeles y establecer el color correspondiente en la imagen
        for (int anchoActual = 0; anchoActual < ancho; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) {
                int pixelCambiado = ((Double) matrizPixeles.obtener(anchoActual, altoActual)).intValue();
                imagen2.setRGB(anchoActual, altoActual, pixelCambiado);
            }
        }
    }
    
    public void guardarImagen(String MetodoUsado) {
        try {
            // Preparar la imagen para guardarla
            editarImagen();
            // Guardar la imagen en formato JPG en el directorio especificado
            ImageIO.write(imagen2, "JPG", new File("src\\registros\\imagen\\morgan" + MetodoUsado + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean leerImagen(){
        // Leer la imagen y almacenar sus píxeles en la matriz de píxeles
        for (int anchoActual = 0; anchoActual < ancho; anchoActual++) {
            for (int altoActual = 0; altoActual < alto; altoActual++) { 
                int pixel = imagen.getRGB(anchoActual, altoActual);
                matrizPixeles.cambiar(anchoActual, altoActual, pixel);
            }
        }
        return true;
    }
}
