
package edu.itla.sistemacomisiones.database.controlador;

import edu.itla.sistemacomisiones.database.model.Direccion;
import edu.itla.sistemacomisiones.database.model.Inmueble;
import edu.itla.sistemacomisiones.database.model.Moneda;
import edu.itla.sistemacomisiones.database.model.TipoInmueble;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InmuebleControlador extends Controlador<Inmueble>{
    private static InmuebleControlador controlador;
    
    public static InmuebleControlador getInstancia (){
           if (controlador == null){
               controlador = new InmuebleControlador();
               }
           return controlador;
}

    private InmuebleControlador() {
        super("inmuebles");
    }

    @Override
    public Inmueble crear(Inmueble obj) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO "+ tablaBaseDeDatos +
                    "  (`detalles`, `precio`, `superficie`, `dormitorios`, `tipo_inmuebles_id`,"
                            + " `direccion_id`, `moneda_id`, `comision`) VALUES (?, ?, ?, ?, ?, ?, ?, ?); " );
            st.setString(1, obj.getDetalles());
            st.setDouble(2, obj.getPrecio());
            st.setString(3, obj.getSuperficie());
            st.setInt(4, obj.getDormitorios());
            st.setInt(5, obj.getTipoInmueble().getId());
            st.setInt(6, obj.getDireccion().getId());
            st.setInt(7, obj.getMoneda().getId());
            st.setDouble(8, obj.getComision());
            obj.setId(st.executeUpdate());
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"crear" , ex);
        }
        return obj;
    }

    @Override
    public Inmueble actualizar(Inmueble obj) {
        try {
            PreparedStatement st = con.prepareStatement("UPDATE "+ tablaBaseDeDatos +
                    " SET `detalles`=?, `precio`=?, `superficie`=?, `dormitorios`=?,"
                            + " `tipo_inmuebles_id`=?, `direccion_id`=?, `moneda_id`=?,"
                            + " comision=? WHERE `id`=?;" );
            st.setString(1, obj.getDetalles());
            st.setDouble(2, obj.getPrecio());
            st.setString(3, obj.getSuperficie());
            st.setInt(4, obj.getDormitorios());
            st.setInt(5, obj.getTipoInmueble().getId());
            st.setInt(6, obj.getDireccion().getId());
            st.setInt(7, obj.getMoneda().getId());
            st.setDouble(8, obj.getComision());
            st.setInt(9, obj.getId());


            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE,"actulizar" , ex);
        }
        return obj;
    }

    @Override
    public Inmueble buscar(String term) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inmueble crearDeResultSet(ResultSet rs) throws SQLException {
        TipoInmueble tipoInmueble = TipoInmuebleControlador.getInstancia()
                .obtenerPorId(rs.getInt("tipo_inmuebles_id"));
        Direccion direccion = DireccionControlador.getInstancia()
                .obtenerPorId(rs.getInt("direccion_id"));
        Moneda moneda = MonedaControlador.getInstancia()
                .obtenerPorId(rs.getInt("moneda_id"));


        return new Inmueble(rs.getInt("id"), rs.getString("detalles"),
                    rs.getDouble("precio"),rs.getString("superficie"),
                    rs.getInt("dormitorios"),tipoInmueble,direccion,
                    moneda, rs.getDouble("comision_venta"));
    }
    
    
    public ArrayList<Inmueble> obtenerApartamentos(int limite) {
        ArrayList<Inmueble> apartamentos = new ArrayList<Inmueble>();
        ArrayList<Inmueble> inmuebles = this.obtenerTodos(limite);
        for (Inmueble inmueble : inmuebles) {
            if(inmueble.getTipoInmueble().getId() == 1) {
                apartamentos.add(inmueble);
            }
        }
        return apartamentos;
    }
    
    
    public ArrayList<Inmueble> obtenerCasas(int limite) {
        ArrayList<Inmueble> casas = new ArrayList<Inmueble>();
        ArrayList<Inmueble> inmuebles = this.obtenerTodos(limite);
        for (Inmueble inmueble : inmuebles) {
            if(inmueble.getTipoInmueble().getId() == 2) {
                casas.add(inmueble);
            }
        }
        return casas;
    }


    public ArrayList<Inmueble> obtenerSolares(int limite) {
        ArrayList<Inmueble> solares = new ArrayList<Inmueble>();
        ArrayList<Inmueble> inmuebles = this.obtenerTodos(limite);
        for (Inmueble inmueble : inmuebles) {
            if(inmueble.getTipoInmueble().getId() == 3) {
                solares.add(inmueble);
            }
        }
        return solares;
    }
        
}
