
package edu.itla.sistemacomisiones.database.servicio;

import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.Inmueble;
import edu.itla.sistemacomisiones.database.model.TipoInmueble;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoInmuebleServicio extends Servicio<TipoInmueble>{
    private static TipoInmuebleServicio controlador;
    
    public static TipoInmuebleServicio getInstancia (){
           if (controlador == null){
               controlador = new TipoInmuebleServicio();
               }
           return controlador;
}

    private TipoInmuebleServicio() {
        super("tipo_inmuebles");
    }

    @Override
    public TipoInmueble crear(TipoInmueble obj) {
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
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj;
    } 

    @Override
    public TipoInmueble actualizar(TipoInmueble obj) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `nombre`=?, `descripcion`=? WHERE `id`=?;" );
            st.setString(1, obj.getNombre());
            st.setString(2, obj.getDescripcion());
            st.setInt(3, obj.getId());
            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    }

    @Override
    public TipoInmueble buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoInmueble crearDeResultSet(ResultSet rs) throws SQLException {
        return new TipoInmueble(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("descripcion"));
    }
}
