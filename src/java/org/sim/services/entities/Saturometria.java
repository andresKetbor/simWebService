package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Saturometria generated by hbm2java
 */
@Entity
@Table(name="saturometria"
    ,catalog="sim2"
)
public class Saturometria  implements java.io.Serializable {


     private int idMedicion;
     private float oxigenoEnSangre;

    public Saturometria() {
    }

    public Saturometria(int idMedicion, float oxigenoEnSangre) {
       this.idMedicion = idMedicion;
       this.oxigenoEnSangre = oxigenoEnSangre;
    }
   
     @Id 

    
    @Column(name="idMedicion", unique=true, nullable=false)
    public int getIdMedicion() {
        return this.idMedicion;
    }
    
    public void setIdMedicion(int idMedicion) {
        this.idMedicion = idMedicion;
    }

    
    @Column(name="OxigenoEnSangre", nullable=false, precision=12, scale=0)
    public float getOxigenoEnSangre() {
        return this.oxigenoEnSangre;
    }
    
    public void setOxigenoEnSangre(float oxigenoEnSangre) {
        this.oxigenoEnSangre = oxigenoEnSangre;
    }




}


