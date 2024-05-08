package InitForm;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReportDialog extends JDialog {

    public ReportDialog(JFrame parent, List<DatosCocina.Cocina> cocinas, double cantidadOptima, double precioPromedio) {
        super(parent, "Generar Reportes", true);

        // Crear el área de texto para mostrar los reportes
        JTextArea reportTextArea = new JTextArea();
        reportTextArea.setEditable(false); // No editable
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Tamaño del área de texto
        

        // Generar los distintos reportes
        generarReporteVentasPorModelo(cocinas, reportTextArea);
        generarReporteVentasEnRelacion(cocinas, cantidadOptima, reportTextArea);
        generarReportePreciosEnRelacion(cocinas, precioPromedio, reportTextArea);
        generarReportePromediosMenoresMayores(cocinas, reportTextArea);

        // Configurar el diálogo
        getContentPane().add(scrollPane);
        pack();
        setLocationRelativeTo(parent);
    }

    // Método para generar el reporte de Ventas por Modelo
    private void generarReporteVentasPorModelo(List<DatosCocina.Cocina> cocinas, JTextArea reportTextArea) {
        reportTextArea.append("VENTAS POR MODELO\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reportTextArea.append("Modelo : " + cocina.getModelo() + "\n");
            reportTextArea.append("Cantidad de ventas : " + cocina.getVentas() + "\n");
            reportTextArea.append("Cantidad de unidades vendidas : " + cocina.getCantidadUnidadesVendidas() + "\n");
            reportTextArea.append("Importe total vendido : S/." + cocina.getImporteTotalVendido() + "\n");
            double aporteCuotaDiaria = (cocina.getImporteTotalVendido() / DatosCocina.Cocina.CUOTA_DIARIA) * 100;
            reportTextArea.append("Aporte a la cuota diaria : " + String.format("%.2f", aporteCuotaDiaria) + "%\n\n");
        }
    }

    // Método para generar el reporte de Ventas en Relación a la Venta Óptima
    private void generarReporteVentasEnRelacion(List<DatosCocina.Cocina> cocinas, double cantidadOptima, JTextArea reportTextArea) {
        reportTextArea.append("VENTAS EN RELACIÓN A LA VENTA ÓPTIMA\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reportTextArea.append("Modelo : " + cocina.getModelo() + "\n");
            reportTextArea.append("Cantidad de unidades vendidas : " + cocina.getCantidadUnidadesVendidas());
            if (cocina.getCantidadUnidadesVendidas() < cantidadOptima) {
                reportTextArea.append("(" + (cantidadOptima - cocina.getCantidadUnidadesVendidas()) + " menos que la cantidad óptima)\n\n");
            } else if (cocina.getCantidadUnidadesVendidas() > cantidadOptima) {
                reportTextArea.append("(" + (cocina.getCantidadUnidadesVendidas() - cantidadOptima) + " más que la cantidad óptima)\n\n");
            } else {
                reportTextArea.append("(igual a la cantidad óptima)\n\n");
            }
        }
    }

    // Método para generar el reporte de Precios en Relación al Precio Promedio
    private void generarReportePreciosEnRelacion(List<DatosCocina.Cocina> cocinas, double precioPromedio, JTextArea reportTextArea) {
        reportTextArea.append("PRECIOS EN RELACIÓN AL PRECIO PROMEDIO\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reportTextArea.append("Modelo : " + cocina.getModelo() + "\n");
            reportTextArea.append("Precio : S/." + cocina.getPrecio());
            if (cocina.getPrecio() > precioPromedio) {
                reportTextArea.append("(Mayor al promedio)\n\n");
            } else if (cocina.getPrecio() < precioPromedio) {
                reportTextArea.append("(Menor al promedio)\n\n");
            } else {
                reportTextArea.append("(Igual al promedio)\n\n");
            }
        }
    }

    // Método para generar el reporte de Promedios de Precios (Menores y Mayores)
    private void generarReportePromediosMenoresMayores(List<DatosCocina.Cocina> cocinas, JTextArea reportTextArea) {
        reportTextArea.append("PROMEDIOS, MENORES Y MAYORES\n");
        double totalPrecios = 0;
        double totalAnchos = 0; // Declarar una variable para almacenar la suma de los anchos
        int contador = 0;        
        double precioMayor = Double.MIN_VALUE;
        double precioMenor = Double.MAX_VALUE;
        double anchoMayor = Double.MIN_VALUE;
        double anchoMenor = Double.MAX_VALUE;
        for (DatosCocina.Cocina cocina : cocinas) {
            totalPrecios += cocina.getPrecio();
            totalAnchos += cocina.getAncho(); // Sumar los anchos
            contador++;
            if (cocina.getPrecio() > precioMayor) {
                precioMayor = cocina.getPrecio();
            }
            if (cocina.getPrecio() < precioMenor) {
                precioMenor = cocina.getPrecio();
            }
            if (cocina.getAncho() > anchoMayor) {
                anchoMayor = cocina.getAncho();
            }
            if (cocina.getAncho() < anchoMenor) {
                anchoMenor = cocina.getAncho();
            }
        }
        double precioPromedio = totalPrecios / contador;
        double anchoPromedio = totalAnchos / contador; // Calcular el ancho promedio
        reportTextArea.append("Precio promedio : S/." + precioPromedio + "\n");
        reportTextArea.append("Precio menor : S/." + precioMenor + "\n");
        reportTextArea.append("Precio mayor : S/." + precioMayor + "\n");
        reportTextArea.append("Ancho promedio : " + anchoPromedio + " cm\n"); // Utilizar la variable calculada
        reportTextArea.append("Ancho menor : " + anchoMenor + " cm\n");
        reportTextArea.append("Ancho mayor : " + anchoMayor + " cm\n");
    }
}
