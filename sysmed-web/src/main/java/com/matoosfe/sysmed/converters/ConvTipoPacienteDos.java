/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.converters;

import com.matoosfe.sysmed.controllers.AbstractFacade;
import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.TipoPaciente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convTipPacDos") //Soporte la @Inject
@RequestScoped
public class ConvTipoPacienteDos extends ConvGenerico<TipoPaciente> {

    @Inject
    private TipoPacienteFacade adminTipoPaciente;

    @Override
    protected AbstractFacade<TipoPaciente> getAdminGenerico() {
       return adminTipoPaciente;
    }

}
