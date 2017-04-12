/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.ui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.itla.sistemacomisiones.database.controlador.VentaControlador;
import edu.itla.sistemacomisiones.database.model.Venta;
import io.datafx.controller.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ariel
 */
@ViewController(value = "/fxml/Ventas.fxml", title = "Sistema Comisiones")
public class VentasController implements Initializable {
    @FXML
    private StackPane root;

    @FXML
    private Label editableTreeTableViewCount;

    @FXML
    private JFXTextField searchField2;

    @FXML
    private JFXTreeTableView<VentasData> editableTreeTableView;

    @FXML
    private JFXTreeTableColumn<VentasData, String> inmueble;

    @FXML
    private JFXTreeTableColumn<VentasData, String> precio;

    @FXML
    private JFXTreeTableColumn<VentasData, String> comision;

    @FXML
    private JFXTreeTableColumn<VentasData, String> comprador;

    @FXML
    private JFXTreeTableColumn<VentasData, String> vendedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inmueble.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<VentasData, String> param) -> 
                param.getValue().getValue().inmueble);
        
        precio.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<VentasData, String> param) -> 
                param.getValue().getValue().precio);
    
       comision.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<VentasData, String> param) -> 
                param.getValue().getValue().comision);
       
       comprador.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<VentasData, String> param) -> 
                param.getValue().getValue().comprador);
       
       vendedor.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<VentasData, String> param) -> 
                param.getValue().getValue().vendedor);
       
       ObservableList<VentasData> ventaData = FXCollections.observableArrayList();

         for (Venta venta : VentaControlador.getInstancia().obtenerTodos(1000)) {
             ventaData.add(new VentasData(venta));
         }
                 // builUd tree
         final TreeItem<VentasData> root = new RecursiveTreeItem<VentasData>(
                 ventaData, 
                 RecursiveTreeObject::getChildren);

         editableTreeTableView.setRoot(root);
         editableTreeTableView.setShowRoot(false);
    }    
       /*
     * data class
     */
    static final class VentasData extends RecursiveTreeObject<VentasData> {
        final StringProperty inmueble;
        final StringProperty precio;
        final StringProperty comision;
        final StringProperty comprador;
        final StringProperty vendedor;
        
        public VentasData(Venta venta) {
            this.inmueble = new SimpleStringProperty(venta.getInmueble().getId()+"");
            this.precio = new SimpleStringProperty(venta.getPrecioFormateado());
            this.comision = new SimpleStringProperty(venta.getComisionFormateada());
            this.comprador = new SimpleStringProperty(venta.getComprador().getNombreCompleto());
            this.vendedor = new SimpleStringProperty(venta.getVendedor().getNombreCompleto());
                   
        }
    }
}
