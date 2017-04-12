/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.itla.sistemacomisiones.database.controlador.DireccionControlador;
import edu.itla.sistemacomisiones.database.controlador.PerfilUsuarioControlador;
import edu.itla.sistemacomisiones.database.controlador.PersonaControlador;
import edu.itla.sistemacomisiones.database.controlador.UsuarioControlador;
import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.PerfilUsuario;
import edu.itla.sistemacomisiones.database.model.Persona;
import edu.itla.sistemacomisiones.database.model.Usuario;
import io.datafx.controller.ViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author ariel
 */
public class FormularioUsuarioController implements Initializable{
    
    @FXML
    private JFXTextField nombres;

    @FXML
    private JFXComboBox<Character> sexo;

    @FXML
    private Label lbdatospersonales;

    @FXML
    private JFXTextField apellidos;

    @FXML
    private JFXTextField Cedula;

    @FXML
    private JFXTextField correo;

    @FXML
    private JFXTextField movil;

    @FXML
    private JFXComboBox<PerfilUsuario> perfil;

    @FXML
    private JFXComboBox<Estado> estatus;

    @FXML
    private JFXTextField contrasenia;

    @FXML
    private JFXTextField Contrasenia;

    @FXML
    private Label lbdatospersonales1;

    @FXML
    private JFXTextField calle;

    @FXML
    private JFXTextField sector;

    @FXML
    private JFXTextField provincia;

    @FXML
    private JFXButton guardar;

    @FXML
    private JFXButton nuevo;

    @FXML
    private JFXButton cancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // agrega opciones al sexo
        sexo.getItems().add('F');
        sexo.getItems().add('M');
    
        // Agrega los perfiles al combobox
        PerfilUsuarioControlador.getInstancia()
                .obtenerTodos(1000)
                .forEach( perfil -> {
                    this.perfil.getItems().add(perfil);
                });
        
        // Agrega los estados al combobox
        estatus.getItems().add(new Estado("Activo", true));
        estatus.getItems().add(new Estado("Desactivado", false));
        
    }
    
    
    @FXML
    void crearUsuario(ActionEvent event) {
        Direccion d = new Direccion();
        d.setCalle(calle.getText());
        d.setSector(sector.getText());
        d.setProvincia(provincia.getText());
        DireccionControlador.getInstancia().crear(d);
        
        Persona p = new Persona();
        p.setNombre(nombres.getText());
        p.setApellido(apellidos.getText());
        p.setDocumentoIdentidad(Cedula.getText());
        p.setCorreo(correo.getText());
        p.setCelular(movil.getText());
        p.setSexo(sexo.getSelectionModel().getSelectedItem() + "");
        p.setDireccion(d);
        
        PersonaControlador.getInstancia().crear(p);
        
        Usuario us = new Usuario();
        us.setContrasenia(contrasenia.getText());
        us.setDesactivado(estatus.getSelectionModel().getSelectedItem().valor);
        us.setPerfilUsuario(perfil.getSelectionModel().getSelectedItem());
        us.setPersona(p);
        
        UsuarioControlador.getInstancia().crear(us);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Usuario Guardado");
        alert.showAndWait();
        
    }
    
    static class Estado {
        String nombre;
        boolean valor;

        public Estado(String nombre, boolean valor) {
            this.nombre = nombre;
            this.valor = valor;
        }
        
        @Override
        public String toString(){
            return nombre;
        }
    }
}
