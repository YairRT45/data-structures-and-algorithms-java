package registros.Markov;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class Imagen extends JFrame{

    public Imagen() {
        // Configurar la ventana
        setTitle("Grafo ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1518, 1029);
        setLocationRelativeTo(null);
        
        // Crear un panel para mostrar la imagen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cargar la imagen desde un archivo (asegúrate de que la imagen exista en la ruta especificada)
                ImageIcon icon = new ImageIcon("src\\registros\\Markov\\modelo.jpg");
                // Dibujar la imagen en el panel
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        // Añadir el panel a la ventana
        add(panel);
    }
}