package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarCocinasDialog extends JDialog {

    private DatosCocina datosCocina;

    public ListarCocinasDialog(JFrame frame, DatosCocina datosCocina) {
        super(frame, "Listar Cocinas", true);
        this.datosCocina = datosCocina;

        // Configurar panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un JTextArea para mostrar los datos de las cocinas
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Para que el usuario no pueda editar el texto
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fuente monoespaciada para alinear correctamente los datos

        // Construir el texto con los datos de las cocinas
        StringBuilder builder = new StringBuilder();
        builder.append("LISTADO DE COCINAS\n\n");
        List<DatosCocina.Cocina> cocinas = datosCocina.getCocinas();
        for (DatosCocina.Cocina cocina : cocinas) {
            builder.append("Modelo: ").append(cocina.getModelo()).append("\n");
            builder.append("Precio: ").append(cocina.getPrecio()).append("\n");
            builder.append("Ancho: ").append(cocina.getAncho()).append("\n");
            builder.append("Alto: ").append(cocina.getAlto()).append("\n");
            builder.append("Fondo: ").append(cocina.getFondo()).append("\n");
            builder.append("Quemadores: ").append(cocina.getQuemadores()).append("\n\n");
        }
        textArea.setText(builder.toString());

        // Agregar el JTextArea a un JScrollPane para permitir el scroll
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para cerrar
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la caja de diálogo
                dispose();
            }
        });
        panel.add(cerrarButton, BorderLayout.SOUTH);

        // Agregar panel al contenido del diálogo
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(frame);
    }
}
