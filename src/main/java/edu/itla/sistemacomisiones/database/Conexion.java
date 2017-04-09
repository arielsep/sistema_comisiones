/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annelisse
 */
public class Conexion {
    
    private static Conexion con;
    private Connection conn;
    
    public static Conexion getInstancia(){
        if (con == null ){
            try {
                con = new Conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }

    
    private Conexion() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");  
        conn = DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/sistema_comision?serverTimezone=AST","usuario_sistemas_comisiones","123456");  
    }
    
    public ResultSet executeQuery(String sql) throws SQLException{
        return conn.createStatement().executeQuery(sql); 
    }
    
} 

