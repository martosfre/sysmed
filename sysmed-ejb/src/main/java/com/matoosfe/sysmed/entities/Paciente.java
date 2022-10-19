/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "paciente")
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdPac", query = "SELECT p FROM Paciente p WHERE p.idPac = :idPac"),
    @NamedQuery(name = "Paciente.findByNombrePac", query = "SELECT p FROM Paciente p WHERE p.nombrePac = :nombrePac"),
    @NamedQuery(name = "Paciente.findByApellidoPaternoPac", query = "SELECT p FROM Paciente p WHERE p.apellidoPaternoPac = :apellidoPaternoPac"),
    @NamedQuery(name = "Paciente.findByApellidoMaternoPac", query = "SELECT p FROM Paciente p WHERE p.apellidoMaternoPac = :apellidoMaternoPac"),
    @NamedQuery(name = "Paciente.findByIdentificacionPac", query = "SELECT p FROM Paciente p WHERE p.identificacionPac = :identificacionPac"),
    @NamedQuery(name = "Paciente.findByTelefonoCelularPac", query = "SELECT p FROM Paciente p WHERE p.telefonoCelularPac = :telefonoCelularPac"),
    @NamedQuery(name = "Paciente.findByDireccionPac", query = "SELECT p FROM Paciente p WHERE p.direccionPac = :direccionPac"),
    @NamedQuery(name = "Paciente.findByCorreoPac", query = "SELECT p FROM Paciente p WHERE p.correoPac = :correoPac"),
    @NamedQuery(name = "Paciente.findByFechaNacimientoPac", query = "SELECT p FROM Paciente p WHERE p.fechaNacimientoPac = :fechaNacimientoPac")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pac")
    private Integer idPac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre_pac")
    private String nombrePac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellido_paterno_pac")
    private String apellidoPaternoPac;
    @Size(max = 25)
    @Column(name = "apellido_materno_pac")
    private String apellidoMaternoPac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "identificacion_pac")
    private String identificacionPac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono_celular_pac")
    private String telefonoCelularPac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "direccion_pac")
    private String direccionPac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_pac")
    private String correoPac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento_pac")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoPac;
    @Lob
    @Column(name = "foto_pac")
    private byte[] fotoPac;
    //Identifica el nombre de la columna de la relación.
    @JoinColumn(name = "id_tippac", referencedColumnName = "id_tippac")
    @ManyToOne(optional = false)
    private TipoPaciente idTippac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPac")
    private List<HistoriaClinica> historiaClinicaList;

    public Paciente(Integer idPac) {
        this.idPac = idPac;
    }

    public Paciente(Integer idPac, String nombrePac, String apellidoPaternoPac, String identificacionPac, String telefonoCelularPac, String direccionPac, String correoPac, Date fechaNacimientoPac) {
        this.idPac = idPac;
        this.nombrePac = nombrePac;
        this.apellidoPaternoPac = apellidoPaternoPac;
        this.identificacionPac = identificacionPac;
        this.telefonoCelularPac = telefonoCelularPac;
        this.direccionPac = direccionPac;
        this.correoPac = correoPac;
        this.fechaNacimientoPac = fechaNacimientoPac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPac != null ? idPac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPac == null && other.idPac != null) || (this.idPac != null && !this.idPac.equals(other.idPac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombrePac + " " + this.apellidoPaternoPac + " " + this.apellidoMaternoPac;
    }

}
