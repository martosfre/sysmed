/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.controllers;

import com.matoosfe.sysmed.entities.Especialidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author martosfre
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class EspecialidadFacade extends AbstractFacade<Especialidad> {

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadFacade() {
        super(Especialidad.class);
    }

    /**
     * Método para cargar las especialidades por SP
     *
     * @return
     */
    public List<Especialidad> cargarEspecialidadesSP() {
        return em.createNamedStoredProcedureQuery("obtenerEspecialidades").getResultList();
    }

}
