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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author martosfre
 */
@Named
@SessionScoped
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
    @Getter
    @Setter
    private StreamedContent imagenBinaria;

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
            if (listaPacientes.isEmpty()) {
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
     * Método para seleccionar un paciente
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Paciente> ev) {
        this.pacienteSel = ev.getObject();
    }

    /**
     * Método para cargar un paciente
     */
    public void editar() {
        if (pacienteSel != null) {
            this.paciente = pacienteSel;
            this.idTipPac = paciente.getIdTippac().getIdTippac(); //cuando no se utiliza convertidor
            //Validar la máscara
            switch (paciente.getIdentificacionPac().length()) {
                case 10:
                    this.tipoIden = "CED";
                    break;
                case 13:
                    this.tipoIden = "RUC";
                    break;
                default:
                    this.tipoIden = "PAS";
                    break;
            }
            actualizarMascaraIdentificacion();

            //Actuaizar Imagen
            InputStream fis = new ByteArrayInputStream(paciente.getFotoPac());
            imagenBinaria = DefaultStreamedContent.builder().stream(() -> fis).build();
            
            PrimeFaces.current().executeScript("PF('diaNuePac').show();");
        } else {
            anadirError("Se debe seleccionar un paciente");
        }
    }

    /**
     * Método para eliminar un paciente
     */
    public void eliminar() {
        if (pacienteSel != null) {
            adminPaciente.eliminar(pacienteSel);
            anadirInfo("Paciente eliminado correctamente");
            buscarPacientes();
            resetearFormulario();
        } else {
            anadirError("Se debe seleccionar un paciente");
        }
    }

    /**
     * Método para subir una imagen
     */
    public void subirImagen(FileUploadEvent event) {
        InputStream fis = new ByteArrayInputStream(event.getFile().getContent());
        imagenBinaria = DefaultStreamedContent.builder().stream(() -> fis).build();
        this.paciente.setFotoPac(event.getFile().getContent());
    }

    /**
     * Método para resetear el formulario
     */
    public void resetearFormulario() {
        this.paciente = new Paciente();
        this.pacienteSel = null;
        this.idTipPac = 0;
        this.identificacionApellido = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarTipoPacientes();
    }

}
