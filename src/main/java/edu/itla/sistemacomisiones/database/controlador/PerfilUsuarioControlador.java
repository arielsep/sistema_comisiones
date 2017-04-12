/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.PerfilUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilmanpc
 */
public class PerfilUsuarioControlador extends Controlador<PerfilUsuario> {
    private static PerfilUsuarioControlador controlador;
    
    public static PerfilUsuarioControlador getInstancia (){
           if (controlador == null){
               controlador = new PerfilUsuarioControlador();
           }
           return controlador;
    }
    
    private PerfilUsuarioControlador() {
        super("perfil_usuarios");
    }
    @Override
    public PerfilUsuario crear(PerfilUsuario obj) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "  (`nombre`, `descripcion`) VALUES (?, ?); " );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getDescripcion());
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
    public PerfilUsuario actualizar(PerfilUsuario obj) {
    try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `nombre`=?, `descripcion`=? WHERE `id`=?;" );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getDescripcion());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;  
    }

    @Override
    public PerfilUsuario buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PerfilUsuario crearDeResultSet(ResultSet rs) throws SQLException {
      return new PerfilUsuario(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("descripcion"));
  
    }

   
}
