package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Permiso generated by hbm2java
 */
@Entity
@Table(name="permiso"
    ,catalog="sim2"
)
public class Permiso  implements java.io.Serializable {


     private Integer idPermiso;
     private String accion;

    public Permiso() {
    }

    public Permiso(String accion) {
       this.accion = accion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idPermiso", unique=true, nullable=false)
    public Integer getIdPermiso() {
        return this.idPermiso;
    }
    
    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    
    @Column(name="Accion", nullable=false, length=25)
    public String getAccion() {
        return this.accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }




}


