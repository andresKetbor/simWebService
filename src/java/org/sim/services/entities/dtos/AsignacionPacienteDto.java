package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import org.sim.services.entities.Usuario;



public class AsignacionPacienteDto extends CommonDto implements java.io.Serializable {


     private Integer idPaciente;
     
     private Set<Integer> idsUsuario = new HashSet();
     private Set<UsuarioDto> usuariosAsignados = new HashSet();
     private Set<UsuarioDto> usuariosNoAsignados = new HashSet();
     
     
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

    /**
     * @return the usuariosAsignados
     */
    public Set<UsuarioDto> getUsuariosAsignados() {
        return usuariosAsignados;
    }

    /**
     * @param usuariosAsignados the usuariosAsignados to set
     */
    public void setUsuariosAsignados(Set<UsuarioDto> usuariosAsignados) {
        this.usuariosAsignados = usuariosAsignados;
    }

    /**
     * @return the usuariosNoAsignados
     */
    public Set<UsuarioDto> getUsuariosNoAsignados() {
        return usuariosNoAsignados;
    }

    /**
     * @param usuariosNoAsignados the usuariosNoAsignados to set
     */
    public void setUsuariosNoAsignados(Set<UsuarioDto> usuariosNoAsignados) {
        this.usuariosNoAsignados = usuariosNoAsignados;
    }

    	  
}