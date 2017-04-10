/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

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
public abstract class Controlador<T> {

    protected String tablaBaseDeDatos;
    protected Conexion con; 
    
    public Controlador(String tablaBaseDeDatos) {
        this.tablaBaseDeDatos = tablaBaseDeDatos;
        con = Conexion.getInstancia();
    }
    
    public abstract T crear (T obj);
    public abstract T actualizar (T obj);
    public boolean eliminar (int id){
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM " + tablaBaseDeDatos + " where id = ?");
            st.setInt(1, id);
            return st.execute();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"eliminar" , ex);
        }
         return false;
    }
    
    public T obtenerPorId (int id){
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tablaBaseDeDatos + " where id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return crearDeResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"obtenerPorId" , ex);
        }
         return null;
    }

    
    
    public ArrayList<T> obtenerTodos (int limite){
        
        ArrayList<T> lista = new ArrayList();
        if (limite == 0){
            limite = 20;
        }
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tablaBaseDeDatos + " limit ?");
            st.setInt(1, limite);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                lista.add(crearDeResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"obtenerTodos", ex);
        }
        return lista;
    }
    public abstract T buscar (String term);
    public abstract T crearDeResultSet (ResultSet rs) throws SQLException;
}
