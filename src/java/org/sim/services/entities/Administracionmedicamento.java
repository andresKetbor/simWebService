package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Administracionmedicamento generated by hbm2java
 */
@Entity
@Table(name="administracionmedicamento"
    ,catalog="sim2"
)
public class Administracionmedicamento  implements java.io.Serializable {


     private Integer idAdminMedic;
     private Libroreport libroreport;
     private Date fecha;
     private String dosis;
     private String medicamento;
     private String administracion;

    public Administracionmedicamento() {
    }

    public Administracionmedicamento(Libroreport libroreport, Date fecha, String dosis, String medicamento, String administracion) {
       this.libroreport = libroreport;
       this.fecha = fecha;
       this.dosis = dosis;
       this.medicamento = medicamento;
       this.administracion = administracion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idAdminMedic", unique=true, nullable=false)
    public Integer getIdAdminMedic() {
        return this.idAdminMedic;
    }
    
    public void setIdAdminMedic(Integer idAdminMedic) {
        this.idAdminMedic = idAdminMedic;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idLibroReport", nullable=false)
    public Libroreport getLibroreport() {
        return this.libroreport;
    }
    
    public void setLibroreport(Libroreport libroreport) {
        this.libroreport = libroreport;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Fecha", nullable=false, length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="Dosis", nullable=false, length=20)
    public String getDosis() {
        return this.dosis;
    }
    
    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    
    @Column(name="Medicamento", nullable=false, length=50)
    public String getMedicamento() {
        return this.medicamento;
    }
    
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    
    @Column(name="Administracion", nullable=false, length=50)
    public String getAdministracion() {
        return this.administracion;
    }
    
    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }




}


