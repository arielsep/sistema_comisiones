/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel
 */
public class PersonaControlador extends Controlador<Persona> {
    
    private static PersonaControlador controlador;
    
    public static PersonaControlador getInstancia (){
           if (controlador == null){
               controlador = new PersonaControlador();
           }
           return controlador;
    }

    private PersonaControlador() {
        super("persona");
    }
    
    

    @Override
    public Persona crear(Persona obj) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    " (`nombre`, `apellido`, `documento_identidad`, `sexo`, `correo`, `celular`, `direccion_id`) "
                            + "VALUES (?,?,?,?,?,?,?); " );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getApellido());
            st.setString(3, obj.getDocumentoIdentidad());
            st.setString(4, obj.getSexo());
            
            obj.setId(st.executeUpdate());
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj;
    }

    @Override
    public Persona actualizar(Persona obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona crearDeResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
