/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans.services.rs;

import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.TipoPaciente;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Servicio Web para administrar las operaciones de Tipo de Paciente
 *
 * @author martosfre
 */
@Path("/tipoPaciente")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class TipoPacienteService {

    @Context
    private HttpHeaders httpHeaders;

    @Inject
    private TipoPacienteFacade adminTipoPaciente;

    @GET
    @Path("/{id}") //http;//localhost:8080/sysmed/api/tipoPaciente/1
    public TipoPaciente consultarPorId(@PathParam(value = "id") int idTipPac) {
        return adminTipoPaciente.buscarPorId(idTipPac);
    }

    @GET
    @Path("/consultarTodos")//http;//localhost:8080/sysmed/api/tipoPaciente/consultarTodos
    public List<TipoPaciente> consultarTodos() {
        return adminTipoPaciente.buscarTodos();
    }

    @POST //Guardar
    public String guardar(TipoPaciente tipoPaciente) {
        String mensaje = null;
        try {
            MultivaluedMap<String, String> headers = httpHeaders.getRequestHeaders();
            if (headers.containsKey("usuario") && headers.containsKey("clave")) {
                if (headers.get("usuario").get(0).equals("admin")
                        && headers.get("clave").get(0).equals("1234")) {
                    adminTipoPaciente.guardar(tipoPaciente);
                    mensaje = "Tipo Paciente guardado correctamente";
                }
            } else {
                mensaje = "Usuario no autorizado";
            }
            return mensaje;

        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    @PUT //Actualizar
    public String actualizar(TipoPaciente tipoPaciente) {
        try {
            adminTipoPaciente.actualizar(tipoPaciente);
            return "Tipo Paciente actualizado correctamente";
        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    @DELETE//Eliminar
    @Path("/{id}")
    //@JWTTokenNeeded
    public String eliminar(@PathParam(value = "id") int idTipPac) {
        try {
            TipoPaciente tipoPaciente = adminTipoPaciente.buscarPorId(idTipPac);
            adminTipoPaciente.eliminar(tipoPaciente);
            return "Tipo Paciente eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar:" + e.getMessage();
        }
    }

}
