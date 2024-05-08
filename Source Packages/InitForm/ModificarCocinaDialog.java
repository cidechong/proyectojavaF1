package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InitForm.DatosCocina;

public class ModificarCocinaDialog extends JDialog {

    private JComboBox<String> modelosComboBox;
    private JLabel modeloLabel;
    private JLabel precioLabel;
    private JLabel anchoLabel;
    private JLabel altoLabel;
    private JLabel fondoLabel;
    private JLabel quemadoresLabel;
    private JTextField precioField;
    private JTextField anchoField;
    private JTextField altoField;
    private JTextField fondoField;
    private JTextField quemadoresField;
    private JButton guardarButton;
    private JButton cerrarButton;

    private DatosCocina datosCompartidos; // Objeto para almacenar los datos de la cocina

    public ModificarCocinaDialog(JFrame frame, DatosCocina datosCompartidos) {
        super(frame, "Modificar Cocina", true);

        this.datosCompartidos = datosCompartidos; // Guarda la referencia al objeto compartido

        modelosComboBox = new JComboBox<>();
        modeloLabel = new JLabel("Modelo:");
        precioLabel = new JLabel("Precio:");
        anchoLabel = new JLabel("Ancho:");
        altoLabel = new JLabel("Alto:");
        fondoLabel = new JLabel("Fondo:");
        quemadoresLabel = new JLabel("Quemadores:");
        precioField = new JTextField(10);
        anchoField = new JTextField(10);
        altoField = new JTextField(10);
        fondoField = new JTextField(10);
        quemadoresField = new JTextField(10);
        guardarButton = new JButton("Guardar");
        cerrarButton = new JButton("Cerrar");

        String[] modelos = datosCompartidos.getModelos();
        for (String modelo : modelos) {
            modelosComboBox.addItem(modelo);
        }

        modelosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modeloSeleccionado = (String) modelosComboBox.getSelectedItem();
                mostrarDatosCocina(modeloSeleccionado);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });

        JPanel contentPane = new JPanel(new GridLayout(0, 2));
        contentPane.add(modeloLabel);
        contentPane.add(modelosComboBox);
        contentPane.add(precioLabel);
        contentPane.add(precioField);
        contentPane.add(anchoLabel);
        contentPane.add(anchoField);
        contentPane.add(altoLabel);
        contentPane.add(altoField);
        contentPane.add(fondoLabel);
        contentPane.add(fondoField);
        contentPane.add(quemadoresLabel);
        contentPane.add(quemadoresField);
        contentPane.add(guardarButton);
        contentPane.add(cerrarButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(frame);
    }

    private void mostrarDatosCocina(String modelo) {
        precioField.setText(String.valueOf(datosCompartidos.getPrecio(modelo)));
        anchoField.setText(String.valueOf(datosCompartidos.getAncho(modelo)));
        altoField.setText(String.valueOf(datosCompartidos.getAlto(modelo)));
        fondoField.setText(String.valueOf(datosCompartidos.getFondo(modelo)));
        quemadoresField.setText(String.valueOf(datosCompartidos.getQuemadores(modelo)));
    }

    private void guardarCambios() {
        String modeloSeleccionado = (String) modelosComboBox.getSelectedItem();
        // Actualiza los datos en el objeto compartido
        datosCompartidos.setPrecio(modeloSeleccionado, Double.parseDouble(precioField.getText()));
        datosCompartidos.setAncho(modeloSeleccionado, Double.parseDouble(anchoField.getText()));
        datosCompartidos.setAlto(modeloSeleccionado, Double.parseDouble(altoField.getText()));
        datosCompartidos.setFondo(modeloSeleccionado, Double.parseDouble(fondoField.getText()));
        datosCompartidos.setQuemadores(modeloSeleccionado, Integer.parseInt(quemadoresField.getText()));
        dispose(); // Cierra la ventana
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                DatosCocina datosCompartidos = new DatosCocina();
                datosCompartidos.addModelo("Mabe EMP6120PG0", 949.0, 60.0, 91.0, 58.6, 4);
                datosCompartidos.addModelo("Indurama Parma", 1089.0, 80.0, 94.0, 67.5, 6);
                datosCompartidos.addModelo("Sole COSOL027", 850.0, 60.0, 90.0, 50.0, 4);
                datosCompartidos.addModelo("Coldex CX602", 629.0, 61.6, 95.0, 51.5, 5);
                datosCompartidos.addModelo("Reco Dakota", 849.0, 75.4, 94.5, 66.0, 5);
                ModificarCocinaDialog dialog = new ModificarCocinaDialog(frame, datosCompartidos);
                dialog.setVisible(true);
            }
        });
    }
}
