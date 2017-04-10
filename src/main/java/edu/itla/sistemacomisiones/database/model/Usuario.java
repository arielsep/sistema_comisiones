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
public class Usuario {
    private int id;
    private String contrasenia;
    private boolean desactivado;
    private Persona persona;
    private PerfilUsuario perfilUsuario;
    

    public Usuario() {
    }

    public Usuario(int id, String contrasenia, boolean desactivado, Persona persona, PerfilUsuario perfilUsuario) {
        this.id = id;
        this.contrasenia = contrasenia;
        this.desactivado = desactivado;
        this.persona = persona;
        this.perfilUsuario = perfilUsuario;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isDesactivado() {
        return desactivado;
    }

    public void setDesactivado(boolean desactivado) {
        this.desactivado = desactivado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }
    
    
}
