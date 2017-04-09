/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.MainApp;
import edu.itla.sistemacomisiones.database.Conexion;
import edu.itla.sistemacomisiones.database.model.Direccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annelisse
 */
public class DireccionControlador implements Controlador<Direccion> {
    private Conexion con; 
   public DireccionControlador(){
        con = Conexion.getInstancia();
        
    }

    @Override
    public Direccion crear(Direccion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direccion actualizar(Direccion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direccion eliminar(Direccion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direccion obtenerPorId(int id) {
         try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM direccion where id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                return crearDeResultSet(rs);
            }
            //launch(args);
        } catch (SQLException ex) {
            Logger.getLogger(DireccionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
         return new Direccion();
    }

    @Override
    public ArrayList<Direccion> obtenerTodos(int limite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direccion buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Direccion crearDeResultSet(ResultSet rs) throws SQLException {
        return new Direccion(rs.getInt("id"), rs.getString("calle"),
                    rs.getString("sector"),rs.getString("ciudad"),rs.getString("provincia"));
    }
}
