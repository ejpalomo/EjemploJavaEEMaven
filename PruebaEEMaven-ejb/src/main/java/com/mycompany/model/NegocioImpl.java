/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.entities.Usuario2;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Esteban
 */
@Stateless
public class NegocioImpl implements Negocio {

    @PersistenceContext(unitName = "com.mycompany_PruebaEEMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void compruebaLogin(Usuario2 u) throws MyException{
        Usuario2 user = em.find(Usuario2.class, u.getCuenta());
        if (user == null) {           
            throw new CuentaInexistenteException();           
        }

        if (!user.getContrasenia().equals(u.getContrasenia())) {            
            throw new ContraseniaInvalidaException();            
        }
    }
    
    public Usuario2 refrescarUsuario(Usuario2 u) throws MyException {
        compruebaLogin(u);
        Usuario2 user = em.find(Usuario2.class, u.getCuenta());
        em.refresh(user);
        return user;
    }

}
