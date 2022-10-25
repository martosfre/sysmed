/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@NoArgsConstructor
@Setter
@Entity
@Table(name = "especialidad")
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
    @NamedQuery(name = "Especialidad.findByIdEsp", query = "SELECT e FROM Especialidad e WHERE e.idEsp = :idEsp"),
    @NamedQuery(name = "Especialidad.findByNombreEsp", query = "SELECT e FROM Especialidad e WHERE e.nombreEsp = :nombreEsp"),
    @NamedQuery(name = "Especialidad.findByDescripcionEsp", query = "SELECT e FROM Especialidad e WHERE e.descripcionEsp = :descripcionEsp")})

@NamedStoredProcedureQuery(
        name = "obtenerEspecialidades",
        procedureName = "public.obtener_especialidades",
        resultClasses = Especialidad.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
        }
)
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_esp")
    private Integer idEsp;
    
    @Getter
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_esp")
    private String nombreEsp;
    
    @Getter
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion_esp")
    private String descripcionEsp;
    
    @Getter(onMethod_ = { @XmlTransient})
    @OneToMany(mappedBy = "especialidad")
    private List<DetalleHistoriaClinica> detallesHisCli;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEsp != null ? idEsp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.idEsp == null && other.idEsp != null) || (this.idEsp != null && !this.idEsp.equals(other.idEsp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.sysmed.entities.Especialidad[ idEsp=" + idEsp + " ]";
    }

}
