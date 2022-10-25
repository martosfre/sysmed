/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.matoosfe.sysmed.beans.services.ws;

import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.TipoPaciente;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author martosfre
 */
@WebService(serviceName = "TipoPacienteService")
public class TipoPacienteService {

    @Inject
    private TipoPacienteFacade adminTipoPaciente;
    /**
     * This is a sample web service operation
     * @param id ID del paciente
     * @return Paciente
     */
    @WebMethod(operationName = "consultarTipPacientePorId")
    public TipoPaciente consultarPorId(@WebParam(name = "id") Integer id) {
        return adminTipoPaciente.buscarPorId(id);
    }
}
