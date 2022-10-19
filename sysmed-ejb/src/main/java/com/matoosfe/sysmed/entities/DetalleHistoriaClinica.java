/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detalle_historia_clinica")
@NamedQueries({
    @NamedQuery(name = "DetalleHistoriaClinica.findAll", query = "SELECT d FROM DetalleHistoriaClinica d"),
    @NamedQuery(name = "DetalleHistoriaClinica.findByIdDethiscli", query = "SELECT d FROM DetalleHistoriaClinica d WHERE d.idDethiscli = :idDethiscli"),
    @NamedQuery(name = "DetalleHistoriaClinica.findByFechaAtencionDethiscli", query = "SELECT d FROM DetalleHistoriaClinica d WHERE d.fechaAtencionDethiscli = :fechaAtencionDethiscli"),
    @NamedQuery(name = "DetalleHistoriaClinica.findByObservacionesDethiscli", query = "SELECT d FROM DetalleHistoriaClinica d WHERE d.observacionesDethiscli = :observacionesDethiscli"),
    @NamedQuery(name = "DetalleHistoriaClinica.findByPrescripcionDethiscli", query = "SELECT d FROM DetalleHistoriaClinica d WHERE d.prescripcionDethiscli = :prescripcionDethiscli")})
public class DetalleHistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dethiscli")
    private Integer idDethiscli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_atencion_dethiscli")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencionDethiscli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observaciones_dethiscli")
    private String observacionesDethiscli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "prescripcion_dethiscli")
    private String prescripcionDethiscli;
    
    @ManyToOne
    @JoinColumn(name = "id_his_cli", referencedColumnName = "id_his_cli")
    private HistoriaClinica historiaClinica;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDethiscli != null ? idDethiscli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleHistoriaClinica)) {
            return false;
        }
        DetalleHistoriaClinica other = (DetalleHistoriaClinica) object;
        if ((this.idDethiscli == null && other.idDethiscli != null) || (this.idDethiscli != null && !this.idDethiscli.equals(other.idDethiscli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.sysmed.entities.DetalleHistoriaClinica[ idDethiscli=" + idDethiscli + " ]";
    }
    
}
