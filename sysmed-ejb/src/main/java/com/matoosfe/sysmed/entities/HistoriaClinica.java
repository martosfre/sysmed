/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Setter
@NoArgsConstructor

@Entity
@Table(name = "historia_clinica")
@NamedQueries({
    @NamedQuery(name = "HistoriaClinica.findAll", query = "SELECT h FROM HistoriaClinica h"),
    @NamedQuery(name = "HistoriaClinica.findByIdHisCli", query = "SELECT h FROM HistoriaClinica h WHERE h.idHisCli = :idHisCli"),
    @NamedQuery(name = "HistoriaClinica.findByFechaAperturaHiscli", query = "SELECT h FROM HistoriaClinica h WHERE h.fechaAperturaHiscli = :fechaAperturaHiscli"),
    @NamedQuery(name = "HistoriaClinica.findByNumeroHistoriaClinica", query = "SELECT h FROM HistoriaClinica h WHERE h.numeroHistoriaClinica = :numeroHistoriaClinica")})
public class HistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_his_cli")
    private Integer idHisCli;

    @Getter
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_apertura_hiscli")
    @Temporal(TemporalType.DATE)
    private Date fechaAperturaHiscli;

    @Getter
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numero_historia_clinica")
    private String numeroHistoriaClinica;

    @Getter
    @JoinColumn(name = "id_pac", referencedColumnName = "id_pac")
    @ManyToOne(optional = false)
    private Paciente idPac;

    @Getter(onMethod_ = {@XmlTransient, @JsonbTransient})
    @OneToMany(mappedBy = "historiaClinica", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DetalleHistoriaClinica> detallesHistoria;

    public HistoriaClinica(Integer idHisCli) {
        this.idHisCli = idHisCli;
    }

    public HistoriaClinica(Integer idHisCli, Date fechaAperturaHiscli, String numeroHistoriaClinica) {
        this.idHisCli = idHisCli;
        this.fechaAperturaHiscli = fechaAperturaHiscli;
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHisCli != null ? idHisCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaClinica)) {
            return false;
        }
        HistoriaClinica other = (HistoriaClinica) object;
        if ((this.idHisCli == null && other.idHisCli != null) || (this.idHisCli != null && !this.idHisCli.equals(other.idHisCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.sysmed.entities.HistoriaClinica[ idHisCli=" + idHisCli + " ]";
    }

}
