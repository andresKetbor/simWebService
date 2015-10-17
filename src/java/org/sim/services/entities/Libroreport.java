package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Libroreport generated by hbm2java
 */
@Entity
@Table(name="libroreport"
    ,catalog="sim2"
)
public class Libroreport  implements java.io.Serializable {


     private Integer idLibroReport;
     private Paciente paciente;
     private Date fechaAlta;
     private Date fechaBaja;
     private String estado;
     private Set<Medicion> medicions = new HashSet<Medicion>(0);
     private Set<Alerta> alertas = new HashSet<Alerta>(0);
     private Set<Administracionmedicamento> administracionmedicamentos = new HashSet<Administracionmedicamento>(0);
     private Set<Nota> notas = new HashSet<Nota>(0);
     private Set<Ecg> ecgs = new HashSet<Ecg>(0);

    public Libroreport() {
    }

	
    public Libroreport(Paciente paciente, Date fechaAlta, String estado) {
        this.paciente = paciente;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }
    
    public Libroreport(Integer idLibroReport,Paciente paciente, Date fechaAlta, Date fechaBaja, String estado) {
        this.idLibroReport = idLibroReport;
        this.paciente = paciente    ;
        this.fechaAlta = fechaAlta;
        this.fechaBaja= fechaBaja;
        this.estado = estado;
    }
    
    
    public Libroreport(Integer idLibroReport,Paciente paciente, Date fechaAlta,  String estado) {
        this.idLibroReport = idLibroReport;
        this.paciente = paciente    ;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }
    
    
    
    public Libroreport(Paciente paciente, Date fechaAlta, Date fechaBaja, String estado, Set<Medicion> medicions, Set<Alerta> alertas, Set<Administracionmedicamento> administracionmedicamentos, Set<Nota> notas, Set<Ecg> ecgs) {
       this.paciente = paciente;
       this.fechaAlta = fechaAlta;
       this.fechaBaja = fechaBaja;
       this.estado = estado;
       this.medicions = medicions;
       this.alertas = alertas;
       this.administracionmedicamentos = administracionmedicamentos;
       this.notas = notas;
       this.ecgs = ecgs;
    }
   
     //@GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="paciente"))@Id @GeneratedValue(generator="generator")

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idLibroReport", unique=true, nullable=false)
    public Integer getIdLibroReport() {
        return this.idLibroReport;
    }
    
    public void setIdLibroReport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Paciente getPaciente() {
        return this.paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fechaAlta", nullable=false, length=10)
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fechaBaja", length=10)
    public Date getFechaBaja() {
        return this.fechaBaja;
    }
    
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    
    @Column(name="Estado", nullable=false, length=20)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="libroreport")
    public Set<Medicion> getMedicions() {
        return this.medicions;
    }
    
    public void setMedicions(Set<Medicion> medicions) {
        this.medicions = medicions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="libroreport")
    public Set<Alerta> getAlertas() {
        return this.alertas;
    }
    
    public void setAlertas(Set<Alerta> alertas) {
        this.alertas = alertas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="libroreport")
    public Set<Administracionmedicamento> getAdministracionmedicamentos() {
        return this.administracionmedicamentos;
    }
    
    public void setAdministracionmedicamentos(Set<Administracionmedicamento> administracionmedicamentos) {
        this.administracionmedicamentos = administracionmedicamentos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="libroreport")
    public Set<Nota> getNotas() {
        return this.notas;
    }
    
    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="libroreport")
    public Set<Ecg> getEcgs() {
        return this.ecgs;
    }
    
    public void setEcgs(Set<Ecg> ecgs) {
        this.ecgs = ecgs;
    }




}


