package InitForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VenderCocinasDialog extends JDialog {

    private DatosCocina datosCocina;
    private JComboBox<String> modeloComboBox;
    private JTextField precioUnitarioTextField;
    private JTextField cantidadTextField;
    private JLabel totalLabel;

    private int numeroVentas = 0;
    private double importeTotalAcumulado = 0.0;
    private final int VENTAS_POR_MENSAJE = 5;
    private final double CUOTA_DIARIA = 23.0; // Ejemplo: Define aquí el valor de la cuota diaria

    public VenderCocinasDialog(JFrame frame, DatosCocina datosCocina) {
        super(frame, "Vender Cocinas", true);
        this.datosCocina = datosCocina;

        // Configurar panel principal
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Componentes para ingresar datos de la venta
        JLabel modeloLabel = new JLabel("Modelo de la cocina:");
        modeloComboBox = new JComboBox<>(datosCocina.getModelos());
        JLabel precioLabel = new JLabel("Precio unitario:");
        precioUnitarioTextField = new JTextField();
        precioUnitarioTextField.setEditable(false); // No editable
        JLabel cantidadLabel = new JLabel("Cantidad de unidades:");
        cantidadTextField = new JTextField();
        totalLabel = new JLabel("Total: $0.00");

        // Asociar ActionListener al JComboBox para actualizar el precio unitario
        modeloComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedModelo = (String) modeloComboBox.getSelectedItem();
                double precioUnitario = datosCocina.getPrecio(selectedModelo);
                precioUnitarioTextField.setText(String.valueOf(precioUnitario));
                
                
            }
        });

        // Botón para realizar la venta
        JButton venderButton = new JButton("Vender");
        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el modelo, la cantidad y calcular el total
                    String modelo = (String) modeloComboBox.getSelectedItem(); // Declaración correcta aquí
                    int cantidad = Integer.parseInt(cantidadTextField.getText());
                    double precioUnitario = Double.parseDouble(precioUnitarioTextField.getText());
                    double total = precioUnitario * cantidad;

                    // Calcular descuento y obsequio
                    double descuento = calcularDescuento(cantidad, total);
                    String obsequio = calcularObsequio(cantidad);

                    // Calcular importe a pagar
                    double importePagar = total - descuento;

                    // Llamada al método para registrar la venta
                    datosCocina.registrarVenta(modelo, cantidad, importePagar);

                    // Incrementar el número de ventas y el importe total acumulado
                    numeroVentas++;
                    importeTotalAcumulado += importePagar;

                    // Mostrar el mensaje cada cinco ventas
                    if (numeroVentas % VENTAS_POR_MENSAJE == 0) {
                        // Calcular el porcentaje de la cuota diaria
                        double porcentajeCuotaDiaria = (importeTotalAcumulado / CUOTA_DIARIA) * 100;
                        String porcentajeRedondeado = String.format("%.2f", porcentajeCuotaDiaria);

                        // Construir el mensaje
                        StringBuilder mensajeVentas = new StringBuilder();
                        mensajeVentas.append("Número de venta actual: ").append(numeroVentas).append("\n");
                        mensajeVentas.append("Importe total acumulado hasta el momento: $").append(importeTotalAcumulado).append("\n");
                        mensajeVentas.append("Porcentaje de la cuota diaria: ").append(porcentajeRedondeado).append("%");

                        // Mostrar el men	saje
                        JOptionPane.showMessageDialog(null, mensajeVentas.toString(), "Informe de Ventas", JOptionPane.INFORMATION_MESSAGE);
                    }

                    // Mostrar boleta de venta en un JOptionPane
                    String mensaje = "Modelo: " + modelo + "\n" +
                            "Precio unitario: $" + precioUnitario + "\n" +
                            "Cantidad: " + cantidad + "\n" +
                            "Total: $" + total + "\n" +
                            "Descuento: $" + descuento + "\n" +
                            "Importe a pagar: $" + importePagar + "\n" +
                            "Obsequio: " + obsequio;
                    JOptionPane.showMessageDialog(null, mensaje);

                    // Actualizar el mensaje del total si es necesario
                    totalLabel.setText("Total: $" + importePagar);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una cantidad válida.");
                }
            }

            // Método para calcular el descuento
            private double calcularDescuento(int cantidad, double total) {
                double descuento = 0.0;
                if (cantidad >= 1 && cantidad <= 5) {
                    descuento = total * DatosCocina.PORCENTAJE1 / 100;
                } else if (cantidad >= 6 && cantidad <= 10) {
                    descuento = total * DatosCocina.PORCENTAJE2 / 100;
                } else if (cantidad >= 11 && cantidad <= 15) {
                    descuento = total * DatosCocina.PORCENTAJE3 / 100;
                } else if (cantidad > 15) {
                    descuento = total * DatosCocina.PORCENTAJE4 / 100;
                }
                return descuento;
            }

            // Método para calcular el obsequio
            private String calcularObsequio(int cantidad) {
                if (cantidad == 1) {
                    return DatosCocina.OBSEQUIO1;
                } else if (cantidad >= 2 && cantidad <= 5) {
                    return DatosCocina.OBSEQUIO2;
                } else if (cantidad > 5) {
                    return DatosCocina.OBSEQUIO3;
                }
                return "";
            } 
        });

        // Agregar componentes al panel
        panel.add(modeloLabel);
        panel.add(modeloComboBox);
        panel.add(precioLabel);
        panel.add(precioUnitarioTextField);
        panel.add(cantidadLabel);
        panel.add(cantidadTextField);
        panel.add(totalLabel);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(venderButton);

        // Agregar panel al contenido del diálogo
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(frame);
    }

    // Método para calcular la cantidad óptima de cocinas a vender
    private double calcularCantidadOptima(List<DatosCocina.Cocina> cocinas) {
        double totalUnidadesVendidas = 0;
        for (DatosCocina.Cocina cocina : cocinas) {
            totalUnidadesVendidas += cocina.getCantidadUnidadesVendidas();
        }
        return totalUnidadesVendidas / cocinas.size();
    }

    // Método para calcular el precio promedio de las cocinas
    private double calcularPrecioPromedio(List<DatosCocina.Cocina> cocinas) {
        double totalPrecio = 0;
        for (DatosCocina.Cocina cocina : cocinas) {
            totalPrecio += cocina.getPrecio();
        }
        return totalPrecio / cocinas.size();
    }
}
