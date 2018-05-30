/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import com.mycompany.entities.Usuario2;
import javax.ejb.Local;

/**
 *
 * @author Esteban
 */
@Local
public interface Negocio {
    public void compruebaLogin(Usuario2 u) throws MyException;
    public Usuario2 refrescarUsuario(Usuario2 u) throws MyException;
}
