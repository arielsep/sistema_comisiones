/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Annelisse
 */
public interface Controlador<T> {
    public T crear (T obj);
    public T actualizar (T obj);
    public T eliminar (T obj);
    public T obtenerPorId (int id);
    public ArrayList<T> obtenerTodos (int limite);
    public T buscar (String term);
    public T crearDeResultSet (ResultSet rs) throws SQLException;
}
