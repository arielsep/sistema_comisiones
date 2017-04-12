/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Direccion;
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
            st.setString(5, obj.getCorreo());
            st.setString(6, obj.getCelular());
            st.setInt(7, obj.getDireccion().getId());
            
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                obj.setId(rs.getInt(1));
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj;
    }

    @Override
    public Persona actualizar(Persona obj) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `nombre`= ?,apellido`=?,"
                     + "`documento_identidad`=?,`correo`=?,"
                     + "`celular`=? sexo=?,direccion_id`=? WHERE `id`=?;" );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getApellido());
            st.setString(3, obj.getDocumentoIdentidad());
            st.setString(4, obj.getCorreo());
            st.setString(5, obj.getCelular());
            st.setString(6, obj.getSexo());
            st.setInt(7, obj.getDireccion().getId());
            st.setInt(8, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
        
    }

    @Override
    public Persona buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona crearDeResultSet(ResultSet rs) throws SQLException {
        Direccion dr = DireccionControlador.getInstancia().obtenerPorId(rs.getInt("direccion_id"));
        return new Persona(rs.getInt("id"), rs.getString("nombre"), 
                rs.getString("apellido"), rs.getString("documento_identidad"),
                rs.getString("sexo"),rs.getString("correo"),rs.getString("celular"),
                dr);
    }
}
