/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.beans.util.AbstractManagedBean;
import com.matoosfe.sysmed.controllers.PacienteFacade;
import com.matoosfe.sysmed.entities.HistoriaClinica;
import com.matoosfe.sysmed.entities.Paciente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class HistoriaClinicaBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private HistoriaClinica historiaClinica;
    @Getter
    @Setter
    private HistoriaClinica historiaClinicaSel;
    @Getter
    @Setter
    private List<HistoriaClinica> historiasClinicas;
    @Getter
    @Setter
    private boolean panelNuevo;

    @Inject
    private PacienteFacade adminPaciente;

    public HistoriaClinicaBean() {
        this.historiaClinica = new HistoriaClinica();
        this.historiasClinicas = new ArrayList<>();
    }

    /**
     * Método para buscar pacientes
     *
     * @param valorApellido
     * @return
     */
    public List<Paciente> completarPacientes(String valorApellido) {
        String queryLowerCase = valorApellido.toLowerCase();
        List<Paciente> pacientes = adminPaciente.buscarTodos();
        return pacientes.stream().filter(pac -> pac.getApellidoPaternoPac().toLowerCase()
                .contains(queryLowerCase)).collect(Collectors.toList());
    }
    
    /**
     * Método para activar el panel de nuevo
     */
    public void activarPanel(){
        this.panelNuevo = true;
    }
    /**
     * Método para desactivar el panel de nuevo
     */
     public void desactivarPanel(){
        this.panelNuevo = false;
    }

}
