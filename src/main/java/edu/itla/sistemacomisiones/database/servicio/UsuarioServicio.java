/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.servicio;

import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.PerfilUsuario;
import edu.itla.sistemacomisiones.database.model.Persona;
import edu.itla.sistemacomisiones.database.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilmanpc
 */
public class UsuarioServicio extends Servicio<Usuario> {
    private static UsuarioServicio controlador;
    
    public static UsuarioServicio getInstancia (){
           if (controlador == null){
               controlador = new UsuarioServicio();
           }
           return controlador;
    }
    
    private UsuarioServicio() {
        super("usuarios");
    }
    @Override
    public Usuario crear(Usuario obj) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "  (`contrasenia`, `desactivado`, `persona_id`, `perfil_usuarios_id`) VALUES (?, ?, ?, ?); " );
            st.setString(1, obj.getContrasenia());
            st.setBoolean(2, obj.isDesactivado());
            st.setInt(3, obj.getPersona().getId());
            st.setInt(4, obj.getPerfilUsuario().getId());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                obj.setId(rs.getInt(1));
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj; }

    @Override
    public Usuario actualizar(Usuario obj) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `contrasenia`=?, `desactivado`=?, `persona_id`=?, `perfil_usuarios_id`=? WHERE `id`=?;" );
            st.setString(1, obj.getContrasenia());
            st.setBoolean(2, obj.isDesactivado());
            st.setInt(3, obj.getPersona().getId());
            st.setInt(4, obj.getPerfilUsuario().getId());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;}

    @Override
    public Usuario buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario crearDeResultSet(ResultSet rs) throws SQLException {
        Persona per = PersonaServicio.getInstancia().obtenerPorId(rs.getInt("persona_id"));
        PerfilUsuario ps = PerfilUsuarioServicio.getInstancia().obtenerPorId(rs.getInt("perfil_usuarios_id"));
         return new Usuario(rs.getInt("id"), rs.getString("contrasenia"),
                    rs.getBoolean("desactivado"),per,ps);
    }
    
}
