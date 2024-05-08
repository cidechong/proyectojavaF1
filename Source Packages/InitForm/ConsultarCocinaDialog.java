package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarCocinaDialog extends JDialog {

    private JComboBox<String> modelosComboBox;
    private JLabel modeloLabel;
    private JLabel precioLabel;
    private JLabel anchoLabel;
    private JLabel altoLabel;
    private JLabel fondoLabel;
    private JLabel quemadoresLabel;

    private DatosCocina datosCompartidos; // Objeto para almacenar los datos de las cocinas

    public ConsultarCocinaDialog(JFrame frame, DatosCocina datosCompartidos) {
        super(frame, "Consultar Cocina", true); // Ventana modal

        this.datosCompartidos = datosCompartidos; // Guarda la referencia al objeto compartido
        
        
        
        
        // Inicializar componentes
        modelosComboBox = new JComboBox<>();
        modeloLabel = new JLabel("Modelo:");
        precioLabel = new JLabel("Precio:");
        anchoLabel = new JLabel("Ancho: ");
        altoLabel = new JLabel("Alto: ");
        fondoLabel = new JLabel("Fondo: ");
        quemadoresLabel = new JLabel("Quemadores: ");

        // Agregar modelos al ComboBox
        for (String modelo : datosCompartidos.getModelos()) {
            modelosComboBox.addItem(modelo);
        }

        // Asignar escuchador de eventos al ComboBox
        modelosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el modelo seleccionado
                String modeloSeleccionado = (String) modelosComboBox.getSelectedItem();
                // Mostrar los datos correspondientes al modelo seleccionado
                mostrarDatosCocina(modeloSeleccionado);
            }
        });

        // Configurar diseño
        JPanel contentPane = new JPanel(new GridLayout(0, 2));
        contentPane.add(modeloLabel);
        contentPane.add(modelosComboBox);
        contentPane.add(precioLabel);
        contentPane.add(anchoLabel);
        contentPane.add(altoLabel);
        contentPane.add(fondoLabel);
        contentPane.add(quemadoresLabel);

        // Configurar ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(frame); // Centrar la ventana

        // Mostrar los datos de la primera cocina
        mostrarDatosCocina(datosCompartidos.getModelos()[0]);
    }

    // Método para mostrar los datos de la cocina seleccionada
    private void mostrarDatosCocina(String modelo) {
        precioLabel.setText("<html>Precio: " + datosCompartidos.getPrecio(modelo) + "<br></html>");
        anchoLabel.setText("<html>Ancho: " + datosCompartidos.getAncho(modelo) + "<br></html>");
        altoLabel.setText("<html>Alto: " + datosCompartidos.getAlto(modelo) + "<br></html>");
        fondoLabel.setText("<html>Fondo: " + datosCompartidos.getFondo(modelo) + "<br></html>");
        quemadoresLabel.setText("<html>Quemadores: " + datosCompartidos.getQuemadores(modelo) + "<br></html>");
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
                // Agregar más modelos según sea necesario
                ConsultarCocinaDialog dialog = new ConsultarCocinaDialog(frame, datosCompartidos);
                dialog.setVisible(true);
            }
        });
    }
}
