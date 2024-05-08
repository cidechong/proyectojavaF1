package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarCantidadOptimaDialog extends JDialog {

    private JTextField cantidadOptimaTextField;

    public ConfigurarCantidadOptimaDialog(JFrame parentFrame) {
        super(parentFrame, "Configurar Cantidad Óptima", true);

        // Crear el JTextField para la cantidad óptima
        cantidadOptimaTextField = new JTextField(10);

        // Obtener el valor actual de la variable global cantidadOptima y mostrarlo en el JTextField
        cantidadOptimaTextField.setText(String.valueOf(DatosCocina.cantidadOptima));

        // Configurar el panel principal
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta y campo de texto para la cantidad óptima
        panel.add(new JLabel("Cantidad Óptima:"));
        panel.add(cantidadOptimaTextField);

        // Botones de Aceptar y Cancelar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor del JTextField y actualizar la variable global cantidadOptima
                String cantidadOptimaStr = cantidadOptimaTextField.getText();
                try {
                    int cantidadOptima = Integer.parseInt(cantidadOptimaStr);
                    DatosCocina.cantidadOptima = cantidadOptima;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ConfigurarCantidadOptimaDialog.this, "Ingrese un valor válido para la cantidad óptima.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
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
                parentFrame.setSize(400, 200);
                parentFrame.setVisible(true);

                ConfigurarCantidadOptimaDialog dialog = new ConfigurarCantidadOptimaDialog(parentFrame);
                dialog.setVisible(true);
            }
        });
    }
}
