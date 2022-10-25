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
import javax.ws.rs.core.MediaType;

/**
 * Servicio Web para administrar las operaciones de Tipo de Paciente
 *
 * @author martosfre
 */
@Path("/tipoPaciente")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class TipoPacienteService {

    @Inject
    private TipoPacienteFacade adminTipoPaciente;

    @GET
    @Path("/{id}")
    public TipoPaciente consultarPorId(@PathParam(value = "id") int idTipPac) {
        return adminTipoPaciente.buscarPorId(idTipPac);
    }

    @GET
    @Path("/consultarTodos")
    public List<TipoPaciente> consultarTodos() {
        return adminTipoPaciente.buscarTodos();
    }

    @POST
    public String guardar(TipoPaciente tipoPaciente) {
        try {
            adminTipoPaciente.guardar(tipoPaciente);
            return "Tipo Paciente guardado correctamente";
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    @PUT
    public String actualizar(TipoPaciente tipoPaciente) {
        try {
            adminTipoPaciente.actualizar(tipoPaciente);
            return "Tipo Paciente actualizado correctamente";
        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam(value = "id")int idTipPac) {
        try {
            TipoPaciente tipoPaciente = adminTipoPaciente.buscarPorId(idTipPac);
            adminTipoPaciente.eliminar(tipoPaciente);
            return "Tipo Paciente eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar:" + e.getMessage();
        }
    }

}
