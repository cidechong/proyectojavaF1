package InitForm;

import javax.swing.*;
import java.awt.*;

public class AcercaDeTiendaDialog extends JDialog {

    public AcercaDeTiendaDialog(JFrame parentFrame) {
        super(parentFrame, "Acerca de Tienda", true);

        // Configurar el panel principal
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        // Agregar información sobre la tienda
        panel.add(new JLabel("Tienda 1.0"));
        panel.add(new JLabel("-----------------------------------------------"));
        panel.add(new JLabel("Autores:"));
        panel.add(new JLabel("- Aarón Zegarra Rivera "));
        panel.add(new JLabel("- Carlos Brayan Canchanya Lázaro"));
        panel.add(new JLabel("- Deyvit Misael Quispe Labra"));
        panel.add(new JLabel("- Solorzano Enriquez Luis Aldair"));
        panel.add(new JLabel("- Edgar Melendez Gutierrez"));

        // Configurar el diálogo
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parentFrame);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear y configurar la ventana principal (en este caso, JFrame)
                JFrame parentFrame = new JFrame("Main Frame");
                parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parentFrame.setSize(400, 300);
                parentFrame.setVisible(true);

                // Crear y mostrar el diálogo Acerca de Tienda
                AcercaDeTiendaDialog dialog = new AcercaDeTiendaDialog(parentFrame);
                dialog.setVisible(true);
            }
        });
    }
}
