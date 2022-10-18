/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.converters;

import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.TipoPaciente;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convTipPac") //Soporte la @Inject
@RequestScoped
public class ConvTipoPaciente implements Converter<TipoPaciente> {

    @Inject
    private TipoPacienteFacade adminTipoPaciente;
    
    //Pantalla a la Bdd
    @Override
    public TipoPaciente getAsObject(FacesContext fc, UIComponent uic, String idTipPac) {
        TipoPaciente tipoPaciente = null;
        if(idTipPac != null){
            tipoPaciente = adminTipoPaciente.buscarPorId(Integer.parseInt(idTipPac));
        }
        return tipoPaciente;
    }

    //Bdd a la Pantalla
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, TipoPaciente tipoPac) {
        String idTipPac = "";
        if (tipoPac != null) {
            idTipPac = tipoPac.getIdTippac().toString();
        }
        return idTipPac;
    }

}
