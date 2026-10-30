// package pruebas;

// import java.awt.Color;
import registros.imagen.ImageFileEdit;
// import utils.comunes.TipoRedimension;
import utils.comunes.TipoTemperatura;

public class PruebaImagen {
    public static void main(String[] args) {
        ImageFileEdit modificadorImagenes = new ImageFileEdit("src\\registros\\imagen\\morgan.jpg");
        // modificadorImagenes.pasarGris();
        // modificadorImagenes.modificarBrillo(50);
        // modificadorImagenes.invertirHorizontal();
        // modificadorImagenes.invertirVertical();
        // modificadorImagenes.transpuesta();
        // modificadorImagenes.rotar90();
        // modificadorImagenes.rotar180();
        // modificadorImagenes.rotar270();
        // modificadorImagenes.redimensionarXMetodo(TipoRedimension.CUADRUPLE);
        // modificadorImagenes.redimensionarImagen(1000, 1000);
        // modificadorImagenes.agregarMarco(Color.CYAN, 50);
        modificadorImagenes.colorXTemperatura(TipoTemperatura.FRIO);
        modificadorImagenes.guardarImagen("_Frio");
    }
}
