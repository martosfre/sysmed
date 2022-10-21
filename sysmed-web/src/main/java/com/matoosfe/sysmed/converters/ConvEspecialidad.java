/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.converters;

import com.matoosfe.sysmed.controllers.AbstractFacade;
import com.matoosfe.sysmed.controllers.EspecialidadFacade;
import com.matoosfe.sysmed.entities.Especialidad;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convEspecialidad") //Soporte la @Inject
@RequestScoped
public class ConvEspecialidad extends ConvGenerico<Especialidad> {

    @Inject
    private EspecialidadFacade adminEspecialidad;

    @Override
    protected AbstractFacade<Especialidad> getAdminGenerico() {
       return adminEspecialidad;
    }

}
