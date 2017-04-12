/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.itla.sistemacomisiones.database.controlador.InmuebleControlador;
import edu.itla.sistemacomisiones.database.controlador.VentaControlador;
import edu.itla.sistemacomisiones.database.model.Venta;
import io.datafx.controller.ViewController;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ariel
 */
@ViewController(value = "/fxml/Inicio.fxml", title = "Sistema Comisiones")
public class InicioController implements Initializable {

    @FXML
    private Pane paneCasa;

    @FXML
    private FontAwesomeIconView home;

    @FXML
    private Label casa;

    @FXML
    private Label precioCasa;

    @FXML
    private Pane paneSolares;

    @FXML
    private Label solares;

    @FXML
    private Label precioSolar;

    @FXML
    private Pane panelApartamento;

    @FXML
    private Label apartamento;

    @FXML
    private Label precioApartamento;

    @FXML
    private Pane paneVentas;

    @FXML
    private Label ventas;

    @FXML
    private Label precioVentas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        int solares = InmuebleControlador.getInstancia().obtenerSolares(1000).size();
        int casas = InmuebleControlador.getInstancia().obtenerCasas(1000).size();
        int apartamentos = InmuebleControlador.getInstancia().obtenerApartamentos(1000).size();
        precioVentas.setText("RD$" + NumberFormat.getInstance()
                .format(getTotalVentas()));
        precioApartamento.setText(apartamentos + "");
        precioCasa.setText(casas + "");
        precioSolar.setText(solares + "");
    }    

    private double getTotalVentas() {
        double ventas = 0;
        ArrayList<Venta>  todasLasVentas = VentaControlador
                .getInstancia().obtenerTodos(1000);
        for (Venta venta : todasLasVentas) {
            double tasa = venta.getMoneda().getTasa();
            // Calcula el valor segun la tasa, si es dolar lo convierte a pesos
            ventas += tasa * venta.getPrecio();
        }
        return ventas;
    }    
    
}
