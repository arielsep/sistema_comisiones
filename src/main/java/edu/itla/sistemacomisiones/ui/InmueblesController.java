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
import edu.itla.sistemacomisiones.database.servicio.InmuebleServicio;
import edu.itla.sistemacomisiones.database.model.Inmueble;
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
@ViewController(value = "/fxml/Inmuebles.fxml", title = "Sistema Comisiones")
public class InmueblesController implements Initializable {

     @FXML
    private StackPane root;

    @FXML
    private Label editableTreeTableViewCount;

    @FXML
    private JFXTextField searchField2;

    @FXML
    private JFXTreeTableView<InmuebleData> editableTreeTableView;

    @FXML
    private JFXTreeTableColumn<InmuebleData, String> detalles;

    @FXML
    private JFXTreeTableColumn<InmuebleData, String> superficie;

    @FXML
    private JFXTreeTableColumn<InmuebleData, String> precio;

    @FXML
    private JFXTreeTableColumn<InmuebleData, String> comision;

    @FXML
    private JFXTreeTableColumn<InmuebleData, String> direccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        detalles.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<InmuebleData, String> param) -> 
                param.getValue().getValue().detalles);
        
        superficie.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<InmuebleData, String> param) ->
                param.getValue().getValue().superficie);
        
        precio.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<InmuebleData, String> param) -> 
                param.getValue().getValue().precio);
        
        direccion.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<InmuebleData, String> param) -> 
                param.getValue().getValue().direccion);
        
        comision.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<InmuebleData, String> param) -> 
                param.getValue().getValue().comision);
       

        ObservableList<InmuebleData> inmbuebles = FXCollections.observableArrayList();

         for (Inmueble inmueble : InmuebleServicio.getInstancia().obtenerTodos(1000)) {
             inmbuebles.add(new InmuebleData(inmueble));
         }
                 // build tree
         final TreeItem<InmuebleData> root = new RecursiveTreeItem<InmuebleData>(
                 inmbuebles, 
                 RecursiveTreeObject::getChildren);

         editableTreeTableView.setRoot(root);
         editableTreeTableView.setShowRoot(false);
        
        
    }    
    
    
    /*
     * data class
     */
    static final class InmuebleData extends RecursiveTreeObject<InmuebleData> {
        final StringProperty detalles;
        final StringProperty superficie;
        final StringProperty precio;
        final StringProperty comision;
        final StringProperty direccion;
        
        public InmuebleData(Inmueble inmueble) {
            this.detalles = new SimpleStringProperty(inmueble.getDetalles());
            this.superficie = new SimpleStringProperty(inmueble.getSuperficie());
            this.precio = new SimpleStringProperty(inmueble.getPrecioFormateado());
            this.comision = new SimpleStringProperty(inmueble.getComisionFormateada());
            this.direccion = new SimpleStringProperty(inmueble.getDireccion().getFormateada());
        }
    }
}
