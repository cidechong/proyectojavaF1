package InitForm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import InitForm.ReportGenerator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import InitForm.ConsultarCocinaDialog;
import InitForm.ModificarCocinaDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class initFormView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private DatosCocina datosCocina;


    // Menu Principal
    static JMenuBar barra = new JMenuBar();
    static JMenu menu1 = new JMenu("Archivo");
    static JMenu menu2 = new JMenu("Mantenimiento");
    static JMenu menu3 = new JMenu("Ventas");
    static JMenu menu4 = new JMenu("Configuracion");
    static JMenu menu5 = new JMenu("Ayuda");
    private double cantidadOptima;
    private double precioPromedio;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    initFormView frame = new initFormView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public initFormView() {
        setTitle("Tienda 1.0");
                
        setBounds(100, 100, 650, 382);
        setLocationRelativeTo(null); // Centra el panel en la pantalla
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Agregando Menu
        barra.add(menu1);
        barra.add(menu2);
        barra.add(menu3);
        barra.add(menu4);
        barra.add(menu5);
        setJMenuBar(barra);
        
        datosCocina = new DatosCocina();
        datosCocina.addModelo("Mabe EMP6120PG0", 949.0, 60.0, 91.0, 58.6, 4);
        datosCocina.addModelo("Indurama Parma", 1089.0, 80.0, 94.0, 67.5, 6);
        datosCocina.addModelo("Sole COSOL027", 850.0, 60.0, 90.0, 50.0, 4);
        datosCocina.addModelo("Coldex CX602", 629.0, 61.6, 95.0, 51.5, 5);
        datosCocina.addModelo("Reco Dakota", 849.0, 75.4, 94.5, 66.0, 5);
        
        
        // Opciones del menú "Archivo"
        JMenuItem salirMenuItem = new JMenuItem("Salir");
        salirMenuItem.addActionListener(e -> salir());
        menu1.add(salirMenuItem);
        
        
        
        
        
        
        // Opciones del menú "Mantenimiento"
        JMenuItem consultarMenuItem = new JMenuItem("Consultar cocina");
        consultarMenuItem.addActionListener(e -> consultarCocina()); // Agregar ActionListener
        

        
        JMenuItem modificarMenuItem = new JMenuItem("Modificar cocina");
        modificarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamar al diálogo de modificación de cocina
                ModificarCocinaDialog dialog = new ModificarCocinaDialog(initFormView.this, datosCocina);
                dialog.setVisible(true);
            }
        });
        
        
        
        JMenuItem listarMenuItem = new JMenuItem("Listar cocinas");
        listarMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el diálogo de listar cocinas
                ListarCocinasDialog dialog = new ListarCocinasDialog(initFormView.this, datosCocina);
                dialog.setVisible(true);
            }
        });
        
        
        menu2.add(consultarMenuItem);
        menu2.add(modificarMenuItem);
        menu2.add(listarMenuItem);
        
        
        
        
        
        
        

        // Opciones del menú "Ventas"
        JMenuItem venderMenuItem = new JMenuItem("Vender");
        venderMenuItem.addActionListener(e -> abrirDialogoVender());
        menu3.add(venderMenuItem);


        
        
        
        JMenuItem reportesMenuItem = new JMenuItem("Generar reportes");
        reportesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar el diálogo para generar reportes
            	ReportDialog dialog = new ReportDialog(initFormView.this, datosCocina.getCocinas(), cantidadOptima, precioPromedio);
                dialog.setVisible(true);
            }
        });
        
        menu3.add(venderMenuItem);
        menu3.add(reportesMenuItem);

        // Opciones del menú "Configuración"
        JMenuItem descuentosMenuItem = new JMenuItem("Configurar descuentos");
        descuentosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurarDescuentosDialog dialog = new ConfigurarDescuentosDialog(initFormView.this);
                dialog.setVisible(true);
            }
        });
        
        
        
        JMenuItem obsequiosMenuItem = new JMenuItem("Configurar obsequios");
        obsequiosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfigurarObsequiosDialog dialog = new ConfigurarObsequiosDialog(initFormView.this);
                dialog.setVisible(true);
            }
        });
        
        JMenuItem cantidadMenuItem = new JMenuItem("Configurar cantidad óptima");
        cantidadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar el diálogo para configurar la cantidad óptima
                ConfigurarCantidadOptimaDialog dialog = new ConfigurarCantidadOptimaDialog(initFormView.this);
                dialog.setVisible(true);
            }
        });

        
        JMenuItem cuotaMenuItem = new JMenuItem("Configurar cuota diaria");
        cuotaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes llamar al método para mostrar el diálogo de configuración de la cuota diaria
                ConfigurarCuotaDiariaDialog dialog = new ConfigurarCuotaDiariaDialog(initFormView.this);
                dialog.setVisible(true);

            }
        });
        
        
        menu4.add(descuentosMenuItem);
        menu4.add(obsequiosMenuItem);
        menu4.add(cantidadMenuItem);
        menu4.add(cuotaMenuItem);
        
        // Opciones del menu "Ayuda"
        JMenuItem acercaTienda = new JMenuItem("Acerda de Tienda");
        acercaTienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí debes llamar al método para mostrar el diálogo de configuración de la cuota diaria
                AcercaDeTiendaDialog dialog = new AcercaDeTiendaDialog(initFormView.this);
                dialog.setVisible(true);

            }
        });
        menu5.add(acercaTienda);
        
        
        // Mensaje de bienvenida
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "¡Bienvenido a Tienda 1.0!", "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
        });        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Método para salir de la aplicación
    private void salir() {
        int option = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir?", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    

    
    // Método para abrir el diálogo de consulta de cocina
    private void consultarCocina() {
        ConsultarCocinaDialog dialog = new ConsultarCocinaDialog(this, datosCocina);
        dialog.setVisible(true);
    }
    
    // Método para abrir el diálogo de venta de cocinas
    private void abrirDialogoVender() {
        VenderCocinasDialog dialog = new VenderCocinasDialog(this, datosCocina);
        dialog.setVisible(true);
    }
    

}
