/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.controllers;

import com.matoosfe.sysmed.entities.Paciente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author martosfre
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }

    /**
     * Método para buscar un paciente por identificación o su apellido paterno
     *
     * @param identificacionApellido
     * @return
     */
    public List<Paciente> buscarIdentificacionApellido(String identificacionApellido) {
        Query conPac = em.createNativeQuery("select p.*\n"
                + "from paciente p \n"
                + "where p.identificacion_pac like ?1 or \n"
                + "      p.apellido_paterno_pac  like ?1 or\n"
                + "      p.apellido_materno_pac like ?1", Paciente.class);
        conPac.setParameter(1, "%" + identificacionApellido + "%");
        return conPac.getResultList();
    }

}
