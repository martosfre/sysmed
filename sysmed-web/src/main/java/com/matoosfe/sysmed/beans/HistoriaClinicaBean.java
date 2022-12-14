/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.beans.util.AbstractManagedBean;
import com.matoosfe.sysmed.controllers.EspecialidadFacade;
import com.matoosfe.sysmed.controllers.HistoriaClinicaFacade;
import com.matoosfe.sysmed.controllers.PacienteFacade;
import com.matoosfe.sysmed.entities.DetalleHistoriaClinica;
import com.matoosfe.sysmed.entities.Especialidad;
import com.matoosfe.sysmed.entities.HistoriaClinica;
import com.matoosfe.sysmed.entities.Paciente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Named("hisCliBean")
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
    private DetalleHistoriaClinica detHisClinica;
    @Getter
    @Setter
    private List<DetalleHistoriaClinica> detallesHistoriaClinica;
    @Getter
    @Setter
    private String numeroHistoria;
    @Getter
    @Setter
    private boolean bloquearHistoria;
    @Getter
    @Setter
    private boolean panelNuevo;
    @Getter
    @Setter
    private List<Especialidad> especialidades;

    @Inject
    private PacienteFacade adminPaciente;
    @Inject
    private HistoriaClinicaFacade adminHistoriaCli;
    @Inject
    private EspecialidadFacade adminEspecialidad;

    public HistoriaClinicaBean() {
        this.historiaClinica = new HistoriaClinica();
        this.historiasClinicas = new ArrayList<>();
        this.detHisClinica = new DetalleHistoriaClinica();
        this.detallesHistoriaClinica = new ArrayList<>();
        this.especialidades = new ArrayList<>();
    }

    /**
     * M??todo para buscar pacientes
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
     * M??todo para activar el panel de nuevo
     */
    public void activarPanel() {
        this.panelNuevo = true;
    }

    /**
     * M??todo para desactivar el panel de nuevo
     */
    public void desactivarPanel() {
        this.panelNuevo = false;
    }

    /**
     * M??todo para guardar o actualizar la historia con su atenci??n
     */
    public void guardar() {
        try {
            detHisClinica.setHistoriaClinica(historiaClinica); //Padre
            if (historiaClinica.getIdHisCli() != null) {
                historiaClinica.getDetallesHistoria().add(detHisClinica);//Hijos
                adminHistoriaCli.actualizar(historiaClinica);
                anadirInfo("Historia clinica actualizada correctamente");
            } else {
                List<DetalleHistoriaClinica> detalles = new ArrayList<>();
                detalles.add(detHisClinica);
                historiaClinica.setDetallesHistoria(detalles);
                adminHistoriaCli.guardar(historiaClinica);
                anadirInfo("Historia clinica registrada correctamente");
            }
            resetearFormulario();

        } catch (Exception e) {
            anadirError("Error al guardar historia cl??nica:" + e.getMessage());
        }
    }

    /**
     * M??todo para buscar las historia cl??nicas por su n??mero
     */
    public void buscar() {
        this.historiasClinicas = adminHistoriaCli.buscarPorNumero(numeroHistoria);
        if (historiasClinicas.isEmpty()) {
            anadirInfo("No existen historias cl??nicas con ese criterio");
        }
    }

    /**
     * M??todo para obtener el n??mero de atenciones de la historia cl??nica
     *
     * @param his
     * @return
     */
    public int obtenerAtenciones(HistoriaClinica his) {
        return his.getDetallesHistoria().size();
    }

    /**
     * M??todo para cargar las atenciones
     *
     * @param his
     */
    public void cargarHistoriaClinica(HistoriaClinica his) {
        this.historiaClinica = his;
        this.detallesHistoriaClinica = his.getDetallesHistoria();
        activarPanel();
        this.bloquearHistoria = true;
    }

    /**
     * M??todo para cargar las especialidades
     */
    private void cargarEspecialidades() {
//        this.especialidades = adminEspecialidad.buscarTodos();
        this.especialidades = adminEspecialidad.cargarEspecialidadesSP();
    }

    /**
     * M??todo para limpiar el formulario
     */
    public void resetearFormulario() {
        this.historiaClinica = new HistoriaClinica();
        this.detHisClinica = new DetalleHistoriaClinica();
        this.bloquearHistoria = false;
        this.numeroHistoria = null;
        this.detallesHistoriaClinica.clear();
        this.historiasClinicas.clear();
        desactivarPanel();
    }

    @PostConstruct
    public void inicializar() {
        cargarEspecialidades();
    }

}
