package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarObsequiosDialog extends JDialog {

    private JTextField obsequio1TextField;
    private JTextField obsequio2TextField;
    private JTextField obsequio3TextField;

    public ConfigurarObsequiosDialog(JFrame parentFrame) {
        super(parentFrame, "Configurar Obsequios", true);

        // Crear los JTextField para los obsequios
        obsequio1TextField = new JTextField(20);
        obsequio2TextField = new JTextField(20);
        obsequio3TextField = new JTextField(20);

        // Obtener los valores actuales de las variables globales y mostrarlos en los JTextField
        obsequio1TextField.setText(DatosCocina.OBSEQUIO1);
        obsequio2TextField.setText(DatosCocina.OBSEQUIO2);
        obsequio3TextField.setText(DatosCocina.OBSEQUIO3);

        // Configurar el panel principal
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiquetas y campos de texto para los obsequios
        panel.add(new JLabel("Obsequio 1:"));
        panel.add(obsequio1TextField);
        panel.add(new JLabel("Obsequio 2:"));
        panel.add(obsequio2TextField);
        panel.add(new JLabel("Obsequio 3:"));
        panel.add(obsequio3TextField);

        // Botones de Aceptar y Cancelar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los JTextField y actualizar las variables globales
                DatosCocina.OBSEQUIO1 = obsequio1TextField.getText();
                DatosCocina.OBSEQUIO2 = obsequio2TextField.getText();
                DatosCocina.OBSEQUIO3 = obsequio3TextField.getText();
                dispose(); // Cerrar la caja de diálogo
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la caja de diálogo
            }
        });

        // Agregar botones al panel
        panel.add(aceptarButton);
        panel.add(cancelarButton);

        // Configurar el diálogo
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parentFrame);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame parentFrame = new JFrame("Main Frame");
                parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parentFrame.setSize(400, 300);
                parentFrame.setVisible(true);

                ConfigurarObsequiosDialog dialog = new ConfigurarObsequiosDialog(parentFrame);
                dialog.setVisible(true);
            }
        });
    }
}
