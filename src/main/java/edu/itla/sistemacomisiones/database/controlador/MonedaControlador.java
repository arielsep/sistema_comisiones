
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.Moneda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MonedaControlador extends Controlador<Moneda>  {
    private static MonedaControlador controlador;
    
    public static MonedaControlador getInstancia(){
           if (controlador == null){
               controlador = new MonedaControlador();
           }
           return controlador;
    }
    
     private MonedaControlador() {
        super("moneda");
    }
     
    @Override
    public Moneda crear(Moneda obj) {
    try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "  (`nombre`, `simbolo`, `tasa`, `es_principal`) VALUES (?, ?, ?, ?); " );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getSimbolo());
            st.setDouble(3, obj.getTasa());
            st.setBoolean(4,obj.isEsPrincipal());
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
    public Moneda actualizar(Moneda obj) {
       try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `nombre`=?, `simbolo`=?, `tasa`=?, `es_principal`=? WHERE `id`=?;" );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getSimbolo());
            st.setDouble(3, obj.getTasa());
            st.setBoolean(4, obj.isEsPrincipal());
            st.setInt(5, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    }

    @Override
    public Moneda buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Moneda crearDeResultSet(ResultSet rs) throws SQLException {
       return new Moneda(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("simbolo"),rs.getDouble("tasa"),rs.getBoolean("es_principal"));
    }
    
}
