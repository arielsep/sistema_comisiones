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
import edu.itla.sistemacomisiones.database.servicio.UsuarioServicio;
import edu.itla.sistemacomisiones.database.model.Usuario;
import io.datafx.controller.ViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ariel
 */
@ViewController(value = "/fxml/Usuarios.fxml", title = "Sistema Comisiones")
public class UsuariosController implements Initializable {
    @FXML
    private StackPane root;

    @FXML
    private Label editableTreeTableViewCount;

    @FXML
    private JFXTextField searchField2;

    @FXML
    private JFXTreeTableView<UsuarioData> editableTreeTableView;

    @FXML
    private JFXTreeTableColumn<UsuarioData, String> id;

    @FXML
    private JFXTreeTableColumn<UsuarioData, String> nombre;

    @FXML
    private JFXTreeTableColumn<UsuarioData, String> documentoIdentidad;

    @FXML
    private JFXTreeTableColumn<UsuarioData, String> perfil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<UsuarioData, String> param) -> 
                param.getValue().getValue().id);
        
        nombre.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<UsuarioData, String> param) -> 
                param.getValue().getValue().nombre);
        
        documentoIdentidad.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<UsuarioData, String> param) -> 
                param.getValue().getValue().documentoIdentidad);
        
        perfil.setCellValueFactory((TreeTableColumn
                .CellDataFeatures<UsuarioData, String> param) -> 
                param.getValue().getValue().perfil);
        
        
        ObservableList<UsuarioData> usuarios = FXCollections.observableArrayList();

         for (Usuario usuario : UsuarioServicio.getInstancia().obtenerTodos(1000)) {
             usuarios.add(new UsuarioData(usuario));
         }
                 // builUd tree
         final TreeItem<UsuarioData> root = new RecursiveTreeItem<UsuarioData>(
                 usuarios, 
                 RecursiveTreeObject::getChildren);

         editableTreeTableView.setRoot(root);
         editableTreeTableView.setShowRoot(false);
        
    }    
    
    @FXML
    void agregarUsuario(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FormularioUsuario.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            
            stage.setTitle("Agregar usuarios");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * data class
     */
    static final class UsuarioData extends RecursiveTreeObject<UsuarioData> {
        final StringProperty id;
        final StringProperty nombre;
        final StringProperty documentoIdentidad;
        final StringProperty perfil;
        
        
        public UsuarioData(Usuario usuario) {
            this.id = new SimpleStringProperty(usuario.getId()+"");
            this.nombre = new SimpleStringProperty(usuario.getNombreCompleto());
            this.documentoIdentidad = new SimpleStringProperty(usuario.getPersona()
                    .getDocumentoIdentidad()+"");
            this.perfil = new SimpleStringProperty(usuario
                    .getPerfilUsuario().getNombre());
        }
    }
}
