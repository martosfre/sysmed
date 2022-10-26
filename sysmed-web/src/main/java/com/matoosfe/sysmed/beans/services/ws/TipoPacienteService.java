/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.matoosfe.sysmed.beans.services.ws;

import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.TipoPaciente;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 * Servicio Web con SOAP para administrar las operaciones de tipo de paciente
 *
 * @author martosfre
 */
@WebService(serviceName = "TipoPacienteService")
public class TipoPacienteService {

    @Resource
    private WebServiceContext wsctx;

    @Inject
    private TipoPacienteFacade adminTipoPaciente;

    /**
     * This is a sample web service operation
     *
     * @param id ID del paciente
     * @return Paciente
     */
    @WebMethod(operationName = "consultarTipPacientePorId")
    public TipoPaciente consultarPorId(@WebParam(name = "id") Integer id) {
        return adminTipoPaciente.buscarPorId(id);
    }

    @WebMethod(operationName = "consultarTodos")
    public List<TipoPaciente> consultarTodos() {
        return adminTipoPaciente.buscarTodos();
    }

    /**
     * Método para guardar un tipo de tipoPaciente
     *
     * @param tipoPaciente
     * @return
     */
    public String guardarTipoPaciente(TipoPaciente tipoPaciente) {
        try {
            MessageContext mctx = wsctx.getMessageContext();

            Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
            List<String> usuario = (List<String>) http_headers.get("usuario");
            List<String> clave = (List<String>) http_headers.get("clave");

            if (usuario != null && usuario.get(0).equals("admin")
                    && clave != null && clave.get(0).equals("1234")) {

                adminTipoPaciente.guardar(tipoPaciente);
                return "Tipo Paciente registrado correctamente";
            } else {
                return "Usuario no autorizado";
            }
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    /**
     * Método para actualizar el tipo de tipoPaciente
     *
     * @param tipoPaciente tipo de paciente que existe en la bdd
     * @return mensaje de confirmación
     */
    @WebMethod(operationName = "actualizarTipPac")
    public String actualizarTipoPaciente(TipoPaciente tipoPaciente) {
        try {
            if (adminTipoPaciente.buscarPorId(tipoPaciente.getIdTippac()) != null) {
                adminTipoPaciente.actualizar(tipoPaciente);
                return "Tipo Paciente actualizado correctamente";
            } else {
                return "Tipo de Paciente no existe";
            }

        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    /**
     * Método para eliminar el tipo de paciente
     *
     * @param idTipPac identificador de tipo de paciente
     * @return mensaje de confirmación
     */
    public String eliminarTipoPaciente(@WebParam(name = "identificadorTipPac") int idTipPac) {
        try {
            TipoPaciente tipoPaciente = adminTipoPaciente.buscarPorId(idTipPac);
            if (tipoPaciente != null) {
                adminTipoPaciente.eliminar(tipoPaciente);
                return "Tipo Paciente eliminado correctamente";
            } else {
                return "Tipo de Paciente no existe";
            }

        } catch (Exception e) {
            return "Error al Eliminar:" + e.getMessage();
        }
    }
}
