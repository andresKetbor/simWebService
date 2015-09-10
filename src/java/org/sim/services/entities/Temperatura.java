package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Temperatura generated by hbm2java
 */
@Entity
@Table(name="temperatura"
    ,catalog="sim2"
)
@PrimaryKeyJoinColumn(name="idMedicion")
public class Temperatura  extends Medicion {


     private float temperatura;

    public Temperatura() {
    }

    public Temperatura(float temperatura) {
       this.temperatura = temperatura;
    }
     
    @Column(name="Temperatura", nullable=false, precision=12, scale=0)
    public float getTemperatura() {
        return this.temperatura;
    }
    
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }




}


