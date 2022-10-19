/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.converters;

import com.matoosfe.sysmed.controllers.AbstractFacade;
import com.matoosfe.sysmed.controllers.PacienteFacade;
import com.matoosfe.sysmed.entities.Paciente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convPaciente") //Soporte la @Inject
@RequestScoped
public class ConvPaciente extends ConvGenerico<Paciente> {

    @Inject
    private PacienteFacade adminPaciente;

    @Override
    protected AbstractFacade<Paciente> getAdminGenerico() {
       return adminPaciente;
    }

}
