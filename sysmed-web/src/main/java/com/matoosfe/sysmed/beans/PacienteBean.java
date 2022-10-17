/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.beans.util.AbstractManagedBean;
import com.matoosfe.sysmed.controllers.PacienteFacade;
import com.matoosfe.sysmed.controllers.TipoPacienteFacade;
import com.matoosfe.sysmed.entities.Paciente;
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
        this.tipoIden = "CED";
        this.mascaraIden = "9999999999";
        this.pathImagen = "/resources/img/usuario.webp";
    }

    /**
     * Método para buscar pacientes
     */
    public void buscarPacientes() {

    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargarTipoPacientes() {
        this.listaTipoPacientes.clear();
        adminTipoPaciente.buscarTodos().forEach(tipPac -> {
            this.listaTipoPacientes.add(new SelectItem(tipPac.getIdTippac(), tipPac.getNombreTippac()));
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

    public void guardar() {

    }

    @PostConstruct
    public void inicializar() {
        cargarTipoPacientes();
    }

}
