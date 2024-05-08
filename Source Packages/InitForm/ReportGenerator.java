package InitForm;

import javax.swing.JOptionPane;
import java.util.List;

public class ReportGenerator {
    static final double cantidadOptima = 10; // Cantidad óptima de unidades vendidas
    static final double precioPromedio = 873.2; // Precio promedio

    public static void generarReporteVentasPorModelo(List<DatosCocina.Cocina> cocinas, double cuotaDiaria) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("VENTAS POR MODELO\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reporte.append("Modelo: ").append(cocina.getModelo()).append("\n");
            reporte.append("Cantidad de ventas: ").append(cocina.getVentas()).append("\n");
            reporte.append("Cantidad de unidades vendidas: ").append(cocina.getCantidadUnidadesVendidas()).append("\n");
            reporte.append("Importe total vendido: ").append(cocina.getImporteTotalVendido()).append("\n");
            double aporteCuotaDiaria = (cocina.getImporteTotalVendido() / cuotaDiaria) * 100;
            reporte.append("Aporte a la cuota diaria: ").append(String.format("%.2f", aporteCuotaDiaria)).append("%\n\n");
        }
        JOptionPane.showMessageDialog(null, reporte.toString(), "Ventas por Modelo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReporteVentasOptima(List<DatosCocina.Cocina> cocinas) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("VENTAS EN RELACIÓN A LA VENTA ÓPTIMA\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reporte.append("Modelo: ").append(cocina.getModelo()).append("\n");
            reporte.append("Cantidad de unidades vendidas: ").append(cocina.getCantidadUnidadesVendidas());
            int diferencia = (int) (cocina.getCantidadUnidadesVendidas() - cantidadOptima);
            if (diferencia == 0) {
                reporte.append("(igual a la cantidad óptima)\n");
            } else if (diferencia > 0) {
                reporte.append("(").append(diferencia).append(" más que la cantidad óptima)\n");
            } else {
                reporte.append("(").append(-diferencia).append(" menos que la cantidad óptima)\n");
            }
        }
        JOptionPane.showMessageDialog(null, reporte.toString(), "Ventas en Relación a la Venta Óptima", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReportePreciosPromedio(List<DatosCocina.Cocina> cocinas) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("PRECIOS EN RELACIÓN AL PRECIO PROMEDIO\n");
        for (DatosCocina.Cocina cocina : cocinas) {
            reporte.append("Modelo: ").append(cocina.getModelo()).append("\n");
            reporte.append("Precio: ").append(cocina.getPrecio()).append(" ");
            if (cocina.getPrecio() > precioPromedio) {
                reporte.append("(Mayor al promedio)\n");
            } else if (cocina.getPrecio() < precioPromedio) {
                reporte.append("(Menor al promedio)\n");
            } else {
                reporte.append("(Igual al promedio)\n");
            }
        }
        JOptionPane.showMessageDialog(null, reporte.toString(), "Precios en Relación al Precio Promedio", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReportePromediosMenoresMayores(List<DatosCocina.Cocina> cocinas) {
        double precioPromedio = calcularPrecioPromedio(cocinas);
        double precioMenor = calcularPrecioMenor(cocinas);
        double precioMayor = calcularPrecioMayor(cocinas);
        double anchoPromedio = calcularAnchoPromedio(cocinas);
        double anchoMenor = calcularAnchoMenor(cocinas);
        double anchoMayor = calcularAnchoMayor(cocinas);

        StringBuilder reporte = new StringBuilder();
        reporte.append("PROMEDIOS, MENORES Y MAYORES\n");
        reporte.append("Precio promedio: ").append(precioPromedio).append("\n");
        reporte.append("Precio menor: ").append(precioMenor).append("\n");
        reporte.append("Precio mayor: ").append(precioMayor).append("\n");
        reporte.append("Ancho promedio: ").append(anchoPromedio).append(" cm\n");
        reporte.append("Ancho menor: ").append(anchoMenor).append(" cm\n");
        reporte.append("Ancho mayor: ").append(anchoMayor).append(" cm\n");

        JOptionPane.showMessageDialog(null, reporte.toString(), "Promedios, Menores y Mayores", JOptionPane.INFORMATION_MESSAGE);
    }

    static double calcularPrecioPromedio(List<DatosCocina.Cocina> cocinas) {
        double sumaPrecios = 0;
        for (DatosCocina.Cocina cocina : cocinas) {
            sumaPrecios += cocina.getPrecio();
        }
        return sumaPrecios / cocinas.size();
    }

    static double calcularPrecioMenor(List<DatosCocina.Cocina> cocinas) {
        double precioMenor = Double.MAX_VALUE;
        for (DatosCocina.Cocina cocina : cocinas) {
            if (cocina.getPrecio() < precioMenor) {
                precioMenor = cocina.getPrecio();
            }
        }
        return precioMenor;
    }

    static double calcularPrecioMayor(List<DatosCocina.Cocina> cocinas) {
        double precioMayor = Double.MIN_VALUE;
        for (DatosCocina.Cocina cocina : cocinas) {
            if (cocina.getPrecio() > precioMayor) {
                precioMayor = cocina.getPrecio();
            }
        }
        return precioMayor;
    }

    static double calcularAnchoPromedio(List<DatosCocina.Cocina> cocinas) {
        double sumaAnchos = 0;
        for (DatosCocina.Cocina cocina : cocinas) {
            sumaAnchos += cocina.getAncho();
        }
        return sumaAnchos / cocinas.size();
    }

    static double calcularAnchoMenor(List<DatosCocina.Cocina> cocinas) {
        double anchoMenor = Double.MAX_VALUE;
        for (DatosCocina.Cocina cocina : cocinas) {
            if (cocina.getAncho() < anchoMenor) {
                anchoMenor = cocina.getAncho();
            }
        }
        return anchoMenor;
    }

    static double calcularAnchoMayor(List<DatosCocina.Cocina> cocinas) {
        double anchoMayor = Double.MIN_VALUE;
        for (DatosCocina.Cocina cocina : cocinas) {
            if (cocina.getAncho() > anchoMayor) {
                anchoMayor = cocina.getAncho();
            }
        }
        return anchoMayor;
    }
}
