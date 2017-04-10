/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Moneda;
import edu.itla.sistemacomisiones.database.model.Persona;
import edu.itla.sistemacomisiones.database.model.Usuario;
import edu.itla.sistemacomisiones.database.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel
 */
public class VentaControlador extends Controlador<Venta> {
    
    private static VentaControlador controlador;
    
    public static VentaControlador getInstancia (){
           if (controlador == null){
               controlador = new VentaControlador();
           }
           return controlador;
    }

    private VentaControlador() {
        super("ventas");
    }

    @Override
    public Venta crear(Venta obj) {
         try {
           PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "(`usuarios_id`, `moneda_id`, `persona_id`, `precio`) "
                    + "VALUES (?,?,?,?')");
            st.setInt(1, obj.getUsuario().getId());
            st.setInt(2, obj.getMoneda().getId());
            st.setInt(3, obj.getPersona().getId());
            st.setDouble(4, obj.getPrecio());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    
            }

    @Override
    public Venta actualizar(Venta obj) {
         try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `usuarios_id`=?, `moneda_id`=?,"
                      + " `persona_id`=?,`precio`=? "
                      + "WHERE `id`=?;" );
            st.setInt(1, obj.getUsuario().getId());
            st.setInt(2, obj.getMoneda().getId());
            st.setInt(3, obj.getPersona().getId());
            st.setDouble(4, obj.getPrecio());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    }

    @Override
    public Venta buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta crearDeResultSet(ResultSet rs) throws SQLException {
        Usuario us = UsuarioControlador.getInstancia().obtenerPorId(rs.getInt("usuarios_id"));
        Moneda mn = MonedaControlador.getInstancia().obtenerPorId (rs.getInt("moneda_id"));
        Persona ps = PersonaControlador.getInstancia().obtenerPorId (rs.getInt("pesona_id"));
        return new Venta(rs.getInt("id"),us, mn, ps,rs.getDouble("precio")  );
        
            }
    
}
