package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurarCuotaDiariaDialog extends JDialog {

    private JTextField cuotaTextField;

    public ConfigurarCuotaDiariaDialog(JFrame parentFrame) {
        super(parentFrame, "Configurar Cuota Diaria", true);

        // Obtener el valor actual de la cuota diaria
        double cuotaActual = DatosCocina.CUOTA_DIARIA;

        // Crear JTextField para la cuota diaria
        cuotaTextField = new JTextField(String.valueOf(cuotaActual), 10);

        // Configurar el panel principal
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta y campo de texto para la cuota diaria
        panel.add(new JLabel("Cuota Diaria:"));
        panel.add(cuotaTextField);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(new JLabel()); // Espacio en blanco

        // Botones de Aceptar y Cancelar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el nuevo valor de la cuota diaria desde el JTextField
                    double nuevaCuota = Double.parseDouble(cuotaTextField.getText());
                    // Actualizar la variable global cuotaDiaria con el nuevo valor
                    DatosCocina.CUOTA_DIARIA = nuevaCuota;
                    dispose(); // Cerrar la caja de diálogo
                } catch (NumberFormatException ex) {
                    // Mostrar mensaje de error si el usuario ingresa un valor no válido
                    JOptionPane.showMessageDialog(ConfigurarCuotaDiariaDialog.this,
                            "Ingrese un valor numérico válido para la cuota diaria.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la caja de diálogo
            }
        });

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
                // Crear y configurar la ventana principal
                JFrame parentFrame = new JFrame("Main Frame");
                parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                parentFrame.setSize(400, 300);
                parentFrame.setVisible(true);

                // Crear y mostrar el diálogo de configuración de la cuota diaria
                ConfigurarCuotaDiariaDialog dialog = new ConfigurarCuotaDiariaDialog(parentFrame);
                dialog.setVisible(true);
            }
        });
    }
}
