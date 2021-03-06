package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Medicion generated by hbm2java
 */
@Entity
@Table(name="medicion"
    ,catalog="sim2"
)
@Inheritance(strategy=InheritanceType.JOINED)
public class Medicion  implements java.io.Serializable {


     private int idMedicion;
     private Libroreport libroreport;
     private Date fecha;
     private String descripcion;

    public Medicion() {
    }

	
    public Medicion( Date fecha, String descripcion ) {
  
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    public Medicion(int idMedicion, Libroreport libroreport, Date fecha, String descripcion) {
       this.idMedicion = idMedicion;
       this.libroreport = libroreport;
       this.fecha = fecha;
       this.descripcion = descripcion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idMedicion", unique=true, nullable=false)
    public int getIdMedicion() {
        return this.idMedicion;
    }
    
    public void setIdMedicion(int idMedicion) {
        this.idMedicion = idMedicion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idLibroReport", nullable=false)
    public Libroreport getLibroreport() {
        return this.libroreport;
    }
    
    public void setLibroreport(Libroreport libroreport) {
        this.libroreport = libroreport;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="Fecha", nullable=false, length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    @Column(name="Descripcion", length=100)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


