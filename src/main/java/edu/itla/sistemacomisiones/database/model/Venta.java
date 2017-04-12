/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.model;

import java.text.NumberFormat;

/**
 *
 * @author ariel
 */
public class Venta {
    private int id;
    private Usuario vendedor;
    private Moneda moneda;
    private Persona comprador;
    private double precio;
    private Inmueble inmueble;

    public Venta() {
    }

    public Venta(int id, Usuario usuario, Moneda moneda, 
            Persona persona, double precio, Inmueble inmueble) {
        this.id = id;
        this.vendedor = usuario;
        this.moneda = moneda;
        this.comprador = persona;
        this.precio = precio;
        this.inmueble = inmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Persona getComprador() {
        return comprador;
    }

    public void setComprador(Persona comprador) {
        this.comprador = comprador;
    }

    public double getPrecio() {
        return precio;
    }
    
    
    public String getPrecioFormateado() {
        return moneda.getSimbolo() +  NumberFormat.getInstance().format(precio);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    
    public double getComisionVenta(){
        double comision = this.getInmueble().getComision();
        return (comision / 100) * precio;
    }
    
    public String getComisionFormateada(){
        return moneda.getSimbolo() +  NumberFormat.getInstance().format(getComisionVenta());
    }
    
    

   

   
    
    
}
