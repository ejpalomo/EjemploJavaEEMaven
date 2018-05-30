/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backingbeans;

import com.mycompany.entities.Usuario2;
import com.mycompany.model.Negocio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author estebanpalomoferrer
 */
@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {

    @Inject
    private Negocio negocio;
    private Usuario2 usuario;

    public Usuario2 getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario2 usuario) {
        this.usuario = usuario;
        System.out.println("Nombre USUARIO: " + usuario.getNombre());
    }
    /**
     * Creates a new instance of Sesion
     */
    public Sesion() {
    }
    
    public synchronized String invalidarSesion()
    {
        if (usuario != null)
        {            
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "index.xhtml";
    }
}
