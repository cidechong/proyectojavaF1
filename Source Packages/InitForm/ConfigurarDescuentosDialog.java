package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarDescuentosDialog extends JDialog {

    private JTextField porcentaje1TextField;
    private JTextField porcentaje2TextField;
    private JTextField porcentaje3TextField;
    private JTextField porcentaje4TextField;

    public ConfigurarDescuentosDialog(JFrame parent) {
        super(parent, "Configurar Porcentajes de Descuento", true);

        // Configurar panel principal
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear etiquetas y campos de texto para los porcentajes de descuento
        JLabel porcentaje1Label = new JLabel("1 a 5 unidades:");
        porcentaje1TextField = new JTextField(String.valueOf(DatosCocina.PORCENTAJE1));
        JLabel porcentaje2Label = new JLabel("6 a 10 unidades:");
        porcentaje2TextField = new JTextField(String.valueOf(DatosCocina.PORCENTAJE2));
        JLabel porcentaje3Label = new JLabel("11 a 15 unidades:");
        porcentaje3TextField = new JTextField(String.valueOf(DatosCocina.PORCENTAJE3));
        JLabel porcentaje4Label = new JLabel("Mas de 15 unidades:");
        porcentaje4TextField = new JTextField(String.valueOf(DatosCocina.PORCENTAJE4));

        // Agregar componentes al panel
        panel.add(porcentaje1Label);
        panel.add(porcentaje1TextField);
        panel.add(porcentaje2Label);
        panel.add(porcentaje2TextField);
        panel.add(porcentaje3Label);
        panel.add(porcentaje3TextField);
        panel.add(porcentaje4Label);
        panel.add(porcentaje4TextField);

        // Crear botones Aceptar y Cancelar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los nuevos valores de los porcentajes y actualizar las variables globales
                DatosCocina.PORCENTAJE1 = Double.parseDouble(porcentaje1TextField.getText());
                DatosCocina.PORCENTAJE2 = Double.parseDouble(porcentaje2TextField.getText());
                DatosCocina.PORCENTAJE3 = Double.parseDouble(porcentaje3TextField.getText());
                DatosCocina.PORCENTAJE4 = Double.parseDouble(porcentaje4TextField.getText());
                // Cerrar la caja de diálogo
                dispose();
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la caja de diálogo sin hacer cambios
                dispose();
            }
        });

        // Agregar botones al panel
        panel.add(aceptarButton);
        panel.add(cancelarButton);

        // Agregar panel al contenido del diálogo
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}
