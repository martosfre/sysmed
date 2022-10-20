/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.controllers;

import com.matoosfe.sysmed.entities.HistoriaClinica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author martosfre
 */
@Stateless
public class HistoriaClinicaFacade extends AbstractFacade<HistoriaClinica>{
    
    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriaClinicaFacade() {
        super(HistoriaClinica.class);
    }

    /**
     * Método para buscar las historias clínicas por su número.
     * Se utilizará JPQL (Sql de objetos)
     * @param numeroHistoria
     * @return 
     */
    public List<HistoriaClinica> buscarPorNumero(String numeroHistoria) {
        TypedQuery<HistoriaClinica> conHisNum = em.createQuery("Select his from HistoriaClinica his "
                + " where his.numeroHistoriaClinica like :numero", HistoriaClinica.class);
        conHisNum.setParameter("numero", numeroHistoria + "%");
        return conHisNum.getResultList();
    }
   
    
}
