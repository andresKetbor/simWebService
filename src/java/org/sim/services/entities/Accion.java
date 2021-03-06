package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Accion generated by hbm2java
 */
@Entity
@Table(name="accion"
    ,catalog="sim2"
)
public class Accion  implements java.io.Serializable {


     private Integer idAccion;
     private String accion;
     private Set<Rolaccion> rolaccions = new HashSet<Rolaccion>(0);

    public Accion() {
    }

	
    public Accion(String accion) {
        this.accion = accion;
    }
    public Accion(String accion, Set<Rolaccion> rolaccions) {
       this.accion = accion;
       this.rolaccions = rolaccions;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idAccion", unique=true, nullable=false)
    public Integer getIdAccion() {
        return this.idAccion;
    }
    
    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    
    @Column(name="Accion", nullable=false, length=30)
    public String getAccion() {
        return this.accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="accion")
    public Set<Rolaccion> getRolaccions() {
        return this.rolaccions;
    }
    
    public void setRolaccions(Set<Rolaccion> rolaccions) {
        this.rolaccions = rolaccions;
    }




}


