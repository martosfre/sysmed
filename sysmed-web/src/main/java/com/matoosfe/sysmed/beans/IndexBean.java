/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase controladora de vista que permitirá gestionar la pantalla de
 * index.xhtml
 *
 * @author martosfre
 */
@Named //Se define que la clase IndexBean sera un controlador de vista
@SessionScoped //Alcance del Bean
public class IndexBean implements Serializable {

    //Campos para ingresar información
    @Getter
    @Setter
    private String txtNomUsu;
    @Getter
    @Setter
    private String txtClaUsu;
    //Campo dinámico
    @Getter
    @Setter
    private boolean banError;

    public IndexBean() {
        this.txtNomUsu = "admin";
    }

    /**
     * Método para validar el usuario
     *
     * @return
     */
    public String validarUsuario() {
        FacesMessage mensajeJSF = new FacesMessage();

        if (txtNomUsu.equals("admin") && txtClaUsu.equals("1234")) {
            //Mensaje de Información
            mensajeJSF.setSummary("Usuario correcto, bienvenido!!");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            banError = false;
            return null; //Null misma página.
        } else {
            //Mensaje de Error
            mensajeJSF.setSummary("Credenciales Incorrectas");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            banError = true;
            return null;
        }
    }

}
