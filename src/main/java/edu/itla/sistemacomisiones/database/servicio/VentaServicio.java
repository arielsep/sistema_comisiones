/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.servicio;

import edu.itla.sistemacomisiones.database.model.Inmueble;
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
public class VentaServicio extends Servicio<Venta> {
    
    private static VentaServicio controlador;
    
    public static VentaServicio getInstancia (){
           if (controlador == null){
               controlador = new VentaServicio();
           }
           return controlador;
    }

    private VentaServicio() {
        super("ventas");
    }

    @Override
    public Venta crear(Venta obj) {
         try {
           PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "(`usuarios_id`, `moneda_id`, `persona_id`, `precio`) "
                    + "VALUES (?,?,?,?')");
            st.setInt(1, obj.getVendedor().getId());
            st.setInt(2, obj.getMoneda().getId());
            st.setInt(3, obj.getComprador().getId());
            st.setDouble(4, obj.getPrecio());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                obj.setId(rs.getInt(1));
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"actulizar" , ex);
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
            st.setInt(1, obj.getVendedor().getId());
            st.setInt(2, obj.getMoneda().getId());
            st.setInt(3, obj.getComprador().getId());
            st.setDouble(4, obj.getPrecio());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    }

    @Override
    public Venta buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venta crearDeResultSet(ResultSet rs) throws SQLException {
        Usuario us = UsuarioServicio.getInstancia().obtenerPorId(rs.getInt("usuarios_id"));
        Moneda mn = MonedaServicio.getInstancia().obtenerPorId (rs.getInt("moneda_id"));
        Persona ps = PersonaServicio.getInstancia().obtenerPorId (rs.getInt("persona_id"));
        Inmueble inm = InmuebleServicio.getInstancia().obtenerPorId(rs.getInt("inmuebles_id"));
        return new Venta(rs.getInt("id"),us, mn, ps,rs.getDouble("precio"), inm);
        
    }
    
}
