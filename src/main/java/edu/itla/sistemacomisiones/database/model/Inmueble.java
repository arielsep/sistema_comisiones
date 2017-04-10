/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.itla.sistemacomisiones.database.model;

/**
 *
 * @author Annelisse
 */
public class Inmueble {
    private int id;
    private String detalles;
    private Double precio;
    private String superficie;
    private int dormitorios;
    private TipoInmueble tipoInmueble;
    private Direccion direccion;
    private Moneda moneda;
    private Double comision;

    public Inmueble() {

    }

    public Inmueble(int id, String detalles, Double precio, 
            String superficie,int dormitorios, TipoInmueble tipoInmueble,
            Direccion direccion, Moneda moneda, Double comision) {
        this.id = id;
        this.detalles = detalles;
        this.precio = precio;
        this.superficie = superficie;
        this.dormitorios = dormitorios;
        this.tipoInmueble = tipoInmueble;
        this.direccion = direccion;
        this.moneda = moneda;
        this.comision = comision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public int getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }
    
    
}