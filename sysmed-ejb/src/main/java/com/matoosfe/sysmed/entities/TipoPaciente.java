/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Getter //Genera todos los getters de las propiedades privadas
@Setter //Genera todos los setters de las propiedades privadas
@NoArgsConstructor //Genera un constructor vacío

//Obligatoria
@Entity
// Opcional, se coloca cuando el nombre de la tabla es diferente al de clase
@Table(name = "tipo_paciente")
//Opcional, se utiliza para definir consultas precargadas, éstaws consultas deberían ser las que se utilizan con mayor frecuencia.
@NamedQueries({
    @NamedQuery(name = "TipoPaciente.findAll", query = "SELECT t FROM TipoPaciente t"),
    @NamedQuery(name = "TipoPaciente.findByIdTippac", query = "SELECT t FROM TipoPaciente t WHERE t.idTippac = :idTippac"),
    @NamedQuery(name = "TipoPaciente.findByNombreTippac", query = "SELECT t FROM TipoPaciente t WHERE t.nombreTippac = :nombreTippac"),
    @NamedQuery(name = "TipoPaciente.findByDescripcionTippac", query = "SELECT t FROM TipoPaciente t WHERE t.descripcionTippac = :descripcionTippac")})
public class TipoPaciente implements Serializable { //Marca para garantizar la integridad de la información

    private static final long serialVersionUID = 1L;
    //Obligatoria, define cual propiedad es la clave primaria
    @Id
    /*
     * Opcional y define la forma de generar la clave primaria lo cual depende
     * del motor de la base de datos. Se tiene algunos valores: AUTO, IDENTITY,
     * SEQUENCE y TABLE
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Opcional y define que la propiedad pued o no recibir valores nulos
    @Basic(optional = false)
    /**
     * Opcional y define a que columna de la tabla se mapeará o representa la
     * propiedad. Se coloca cuando el nombre de la columna es diferente de la
     * propiedad
     */
    @Column(name = "id_tippac")
    private Integer idTippac;
    @Basic(optional = false)
    //Opcional y determina que la propiedad no pueda ser nulo (Jakarta Bean Validation)
    @NotNull
    //Opcional y determina el tamaño  min y max de uns propiedad (Jakarta Bean Validation)
    @Size(min = 1, max = 25)
    @Column(name = "nombre_tippac")
    private String nombreTippac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion_tippac")
    private String descripcionTippac;
    /*
     * Relaciones en JPA son bidireccionales, que se mapea a los lados de la relación;
     * es decir, en cada clase. Existen tres tipos de relación:
     * @OneToMany / @ManyToOne
     * @ManyToMany
    
     * Para conocer como está mapeada la relación en el otro lado se utiliza el 
     * atributo mapppedBy, el cual referencia el nombre la propiedad del otro 
     * lado de la relación.
     *
     * Para poder realizar una operación en cascada se utiliza el atributo cascade
     * en el cual se define el tipo de operación(es) de CASCADE. Para lo cual 
     * se debe configurar el valor de CascadeType.ALL, CascadeType.PERSIST,
     * CascadeType.REMOVE y otros más
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTippac")
    private List<Paciente> pacienteList;

    public TipoPaciente(Integer idTippac) {
        this.idTippac = idTippac;
    }

    public TipoPaciente(Integer idTippac, String nombreTippac, String descripcionTippac) {
        this.idTippac = idTippac;
        this.nombreTippac = nombreTippac;
        this.descripcionTippac = descripcionTippac;
    }

    //Permiten diferenciar un objeto de otro.
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idTippac);
        hash = 89 * hash + Objects.hashCode(this.nombreTippac);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoPaciente other = (TipoPaciente) obj;
        if (!Objects.equals(this.nombreTippac, other.nombreTippac)) {
            return false;
        }
        return Objects.equals(this.idTippac, other.idTippac);
    }

    //Sobreescribir la salida estándar
    @Override
    public String toString() {
        return this.nombreTippac;
    }

}
