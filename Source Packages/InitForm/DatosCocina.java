package InitForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatosCocina {
    private List<Cocina> cocinas;
    
    private Map<String, Integer> ventasPorModelo; // Mapa para almacenar las ventas por modelo
    private Map<String, Integer> cantidadUnidadesVendidasPorModelo; // Mapa para almacenar la cantidad de unidades vendidas por modelo
    private Map<String, Double> importeTotalVendidoPorModelo; // Mapa para almacenar el importe total vendido por modelo

    
    public static double PORCENTAJE1 = 5.0;
    public static double PORCENTAJE2 = 10.0;
    public static double PORCENTAJE3 = 15.0;
    public static double PORCENTAJE4 = 20.0;
    public static int cantidadOptima = 0;

    // Definición de constantes para obsequios
    public static String OBSEQUIO1 = "Obsequio 1";
    public static String OBSEQUIO2 = "Obsequio 2";
    public static String OBSEQUIO3 = "Obsequio 3";
    
    static double CUOTA_DIARIA = 23.0; // Cambia el valor según tus necesidades

    
    public DatosCocina() {
        cocinas = new ArrayList<>();
        ventasPorModelo = new HashMap<>();
        cantidadUnidadesVendidasPorModelo = new HashMap<>();
        importeTotalVendidoPorModelo = new HashMap<>();
    }

    public void addModelo(String modelo, double precio, double ancho, double alto, double fondo, int quemadores) {
        cocinas.add(new Cocina(modelo, precio, ancho, alto, fondo, quemadores));
    }

    public String[] getModelos() {
        String[] modelos = new String[cocinas.size()];
        for (int i = 0; i < cocinas.size(); i++) {
            modelos[i] = cocinas.get(i).getModelo();
        }
        return modelos;
    }
    
    public List<Cocina> getCocinas() {
        return cocinas;
    }

    public double getPrecio(String modelo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                return cocina.getPrecio();
            }
        }
        return 0.0; // Si el modelo no se encuentra, devuelve 0.0
    }

    public double getAncho(String modelo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                return cocina.getAncho();
            }
        }
        return 0.0; // Si el modelo no se encuentra, devuelve 0.0
    }

    public double getAlto(String modelo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                return cocina.getAlto();
            }
        }
        return 0.0; // Si el modelo no se encuentra, devuelve 0.0
    }

    public double getFondo(String modelo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                return cocina.getFondo();
            }
        }
        return 0.0; // Si el modelo no se encuentra, devuelve 0.0
    }

    public int getQuemadores(String modelo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                return cocina.getQuemadores();
            }
        }
        return 0; // Si el modelo no se encuentra, devuelve 0
    }

    public void setPrecio(String modelo, double precio) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                cocina.setPrecio(precio);
                break;
            }
        }
    }

    public void setAncho(String modelo, double ancho) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                cocina.setAncho(ancho);
                break;
            }
        }
    }

    public void setAlto(String modelo, double alto) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                cocina.setAlto(alto);
                break;
            }
        }
    }

    public void setFondo(String modelo, double fondo) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                cocina.setFondo(fondo);
                break;
            }
        }
    }

    public void setQuemadores(String modelo, int quemadores) {
        for (Cocina cocina : cocinas) {
            if (cocina.getModelo().equals(modelo)) {
                cocina.setQuemadores(quemadores);
                break;
            }
        }
    }

    // Clase interna para representar los datos de una cocina
    class Cocina {
        public static final double CUOTA_DIARIA = 0;
		private String modelo;
        private double precio;
        private double ancho;
        private double alto;
        private double fondo;
        private int quemadores;
        private int ventas;
        private int cantidadUnidadesVendidas;
        private double importeTotalVendido;

        public Cocina(String modelo, double precio, double ancho, double alto, double fondo, int quemadores) {
            this.modelo = modelo;
            this.precio = precio;
            this.ancho = ancho;
            this.alto = alto;
            this.fondo = fondo;
            this.quemadores = quemadores;
            this.ventas = 0;
            this.cantidadUnidadesVendidas = 0;
            this.importeTotalVendido = 0;
        }

        public String getModelo() {
            return modelo;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public double getAncho() {
            return ancho;
        }

        public void setAncho(double ancho) {
            this.ancho = ancho;
        }

        public double getAlto() {
            return alto;
        }

        public void setAlto(double alto) {
            this.alto = alto;
        }

        public double getFondo() {
            return fondo;
        }

        public void setFondo(double fondo) {
            this.fondo = fondo;
        }

        public int getQuemadores() {
            return quemadores;
        }

        public void setQuemadores(int quemadores) {
            this.quemadores = quemadores;
        }
        
        public int getVentas() {
            return ventas;
        }

        public int getCantidadUnidadesVendidas() {
            return cantidadUnidadesVendidas;
        }

        public double getImporteTotalVendido() {
            return importeTotalVendido;
        }
    }

    public void registrarVenta(String modelo, int cantidad, double importePagar) {
        // Aquí va la lógica para registrar la venta, como almacenarla en una base de datos,
        // actualizar archivos, etc. Por ahora, solo imprimimos los datos como ejemplo.
        System.out.println("Venta registrada:");
        System.out.println("Modelo: " + modelo);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Importe a pagar: " + importePagar);
    }
    
    
    
}
