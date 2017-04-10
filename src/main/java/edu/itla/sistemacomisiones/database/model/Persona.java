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
public class Persona {
   private int id;
   private String nombre;
   private String apellido;
   private String documentoIdentidad;       
   private String sexo;
   private String correo;
   private String celular;
   private Direccion direccion; 

    

    public Persona() {
    }

    public Persona(int id, String nombre, String apellido, String documentoIdentidad, 
            String sexo, String correo, String celular, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoIdentidad = documentoIdentidad;
        this.sexo = sexo;
        this.correo = correo;
        this.celular = celular;
        this.direccion = direccion;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombreCompleto(){
      return getNombre() + " " +  getApellido();
    }
    
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
   
       
}


