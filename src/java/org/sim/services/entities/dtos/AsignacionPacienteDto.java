package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;



public class AsignacionPacienteDto implements java.io.Serializable {


     private Integer idPaciente;
     
     private Set<Integer> idsUsuario = new HashSet();

    public AsignacionPacienteDto() {
    }

    
    public Integer getIdPaciente() {
        return idPaciente;
    }

    
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    public Set<Integer> getIdsUsuario() {
        return idsUsuario;
    }

    
    public void setIdsUsuario(Set<Integer> idsUsuario) {
        this.idsUsuario = idsUsuario;
    }

	  
}