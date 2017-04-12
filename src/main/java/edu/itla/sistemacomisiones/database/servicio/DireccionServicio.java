/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.servicio;

import com.mysql.cj.api.jdbc.Statement;
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
public class DireccionServicio extends Servicio<Direccion> {
    private static DireccionServicio controlador;
    
    public static DireccionServicio getInstancia (){
           if (controlador == null){
               controlador = new DireccionServicio();
           }
           return controlador;
    }
    
    private DireccionServicio() {
        super("direccion");
    }

    @Override
    public Direccion crear(Direccion obj) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "  (`calle`, `sector`, `ciudad`, `provincia`) VALUES (?, ?, ?, ?); " );
            st.setString(1, obj.getCalle());
            st.setString(2, obj.getSector());
            st.setString(3, obj.getCiudad());
            st.setString(4, obj.getProvincia());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            
            if(rs.next()){
                obj.setId(rs.getInt(1));
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj;
    }

    @Override
    public Direccion actualizar(Direccion obj) {
       try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `calle`=?, `sector`=?, `ciudad`=?, `provincia`=? WHERE `id`=?;" );
            st.setString(1, obj.getCalle());
            st.setString(2, obj.getSector());
            st.setString(3, obj.getCiudad());
            st.setString(4, obj.getProvincia());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
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
