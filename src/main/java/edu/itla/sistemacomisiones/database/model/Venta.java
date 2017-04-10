/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.model;

/**
 *
 * @author ariel
 */
public class Venta {
    private int id;
    private Usuario usuario;
    private Moneda moneda;
    private Persona persona;
    private double precio;

    public Venta() {
    }

    public Venta(int id, Usuario usuario, Moneda moneda, Persona persona, double precio) {
        this.id = id;
        this.usuario = usuario;
        this.moneda = moneda;
        this.persona = persona;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

   

   
    
    
}
