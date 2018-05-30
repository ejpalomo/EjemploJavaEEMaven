/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backingbeans;

import com.mycompany.entities.Usuario2;
import com.mycompany.model.ContraseniaInvalidaException;
import com.mycompany.model.CuentaInexistenteException;
import com.mycompany.model.MyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.mycompany.model.Negocio;

/**
 *
 * @author estebanpalomoferrer
 */
@Named(value = "login")
@RequestScoped
public class Login {

    @EJB
    Negocio negocio;

    @Inject
    Sesion sesion;
    
    private Usuario2 usuario;
               
    public Usuario2 getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario2 usuario) {
        this.usuario = usuario;
    }
    
    
    /**
     * Creates a new instance of Login
     */
    public Login() {
        usuario = new Usuario2();
    }
    
    public String autenticar(){
                
        try {            
            negocio.compruebaLogin(usuario);            
            sesion.setUsuario(negocio.refrescarUsuario(usuario));            
            return "hola.xhtml"; 
        } catch (CuentaInexistenteException e) {
            FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (ContraseniaInvalidaException e) {
            FacesMessage fm = new FacesMessage("La contraseña no es correcta");
            FacesContext.getCurrentInstance().addMessage("login:pass", fm);        
        } catch (MyException ex) {            
            FacesMessage fm = new FacesMessage("Error: " + ex);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        return null;
    }
    
}
