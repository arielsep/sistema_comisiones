/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.model;

/**
 *
 * @author wilmanpc
 */
public class Moneda {
    private int id;
    private String nombre;
    private String simbolo;
    private double tasa;
    private boolean esPrincipal;

    public Moneda() {
    }

    public Moneda(int id, String nombre, String simbolo, double tasa, boolean esPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.tasa = tasa;
        this.esPrincipal = esPrincipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
    
}
