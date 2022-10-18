/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.beans.util.AbstractManagedBean;
import com.matoosfe.sysmed.controllers.PacienteFacade;
import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.Paciente;
import com.matoosfe.sysmed.entities.TipoPaciente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Named
@ViewScoped
public class PacienteBean extends AbstractManagedBean implements Serializable {
    
    @Getter
    @Setter
    private Paciente paciente;
    @Getter
    @Setter
    private Paciente pacienteSel;
    @Getter
    @Setter
    private List<Paciente> listaPacientes;
    @Getter
    @Setter
    private String identificacionApellido;
    @Getter
    @Setter
    private List<SelectItem> listaTipoPacientes;
    @Getter
    @Setter
    private List<TipoPaciente> listaTipoPacientesObj;
    @Getter
    @Setter
    private int idTipPac;
    @Getter
    @Setter
    private String tipoIden;
    @Getter
    @Setter
    private String mascaraIden;
    @Getter
    @Setter
    private String pathImagen;
    
    @Inject
    private PacienteFacade adminPaciente;
    @Inject
    private TipoPacienteFacade adminTipoPaciente;
    
    public PacienteBean() {
        this.paciente = new Paciente();
        this.listaPacientes = new ArrayList<>();
        this.listaTipoPacientes = new ArrayList<>();
        this.listaTipoPacientesObj = new ArrayList<>();
        this.tipoIden = "CED";
        this.mascaraIden = "9999999999";
        this.pathImagen = "/resources/img/usuario.webp";
    }

    /**
     * Método para buscar pacientes
     */
    public void buscarPacientes() {
        try {
            this.listaPacientes = adminPaciente.buscarIdentificacionApellido(identificacionApellido);
            if(listaPacientes.isEmpty()){
                anadirInfo("No existen pacientes con ese criterio");
            }
        } catch (Exception e) {
            anadirError("Error al buscar los pacientes con ese criterio:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargarTipoPacientes() {
        this.listaTipoPacientes.clear();
        this.listaTipoPacientesObj.clear();
        adminTipoPaciente.buscarTodos().forEach(tipPac -> {
            this.listaTipoPacientes.add(new SelectItem(tipPac.getIdTippac(), tipPac.getNombreTippac()));
            this.listaTipoPacientesObj.add(tipPac);
        });

//        for(TipoPaciente tipPac:adminTipoPaciente.buscarTodos()){
//             this.listaTipoPacientes.add(new SelectItem(tipPac.getIdTippac(), tipPac.getNombreTippac()));
//        }
    }

    /**
     * Método para actualizar la máscara según tipo de identificación
     */
    public void actualizarMascaraIdentificacion() {
        switch (tipoIden) {
            case "CED":
                this.mascaraIden = "9999999999";
                break;
            case "RUC":
                this.mascaraIden = "9999999999999";
                break;
            default:
                this.mascaraIden = "999999";
                break;
        }
    }

    /**
     * Método para guardar o actualizar un paciente
     */
    public void guardar() {
        try {
            //Recuperar el tipo de paciente y seteandole a paciente
            TipoPaciente tipoPaciente = adminTipoPaciente.buscarPorId(idTipPac);
            paciente.setIdTippac(tipoPaciente);
            
            if (paciente.getIdPac() != null) {
                adminPaciente.actualizar(paciente);
                anadirInfo("Paciente actualizado correctamente");
            } else {
                adminPaciente.guardar(paciente);
                anadirInfo("Paciente guardado correctamente");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un paciente
     */
    public void guardarConvertidor() {
        try {
            if (paciente.getIdPac() != null) {
                adminPaciente.actualizar(paciente);
                anadirInfo("Paciente actualizado correctamente");
            } else {
                adminPaciente.guardar(paciente);
                anadirInfo("Paciente guardado correctamente");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para resetear el formulario
     */
    public void resetearFormulario() {
        this.paciente = new Paciente();
        this.pacienteSel = null;
        this.idTipPac = 0;
    }
    
    @PostConstruct
    public void inicializar() {
        cargarTipoPacientes();
    }
    
}
