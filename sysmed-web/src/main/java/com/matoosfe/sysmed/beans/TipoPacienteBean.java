/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.entities.TipoPaciente;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Named
@ViewScoped
public class TipoPacienteBean implements Serializable{
    @Getter @Setter
    private TipoPaciente tipoPaciente;

    public TipoPacienteBean() {
        this.tipoPaciente = new TipoPaciente();
    }
    
}
