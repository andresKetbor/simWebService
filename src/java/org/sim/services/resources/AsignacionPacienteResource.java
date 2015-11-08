/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Rol;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.AsignacionPacienteDto;
import org.sim.services.entities.dtos.RolDto;
import org.sim.services.entities.dtos.UsuarioDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */
@Path("/PacienteResource/asignacion")
public class AsignacionPacienteResource {

    UsuarioDao usuarioDao = new UsuarioDao();
    PacienteDao pacienteDao = new PacienteDao();
    
    private Set<UsuarioDto> getDtoFromEntite(Set<Usuario> usuarios){
        
        Set<UsuarioDto> usuariosAsignados = new HashSet();
        
        Iterator<Usuario> it = usuarios.iterator();
        
        while(it.hasNext()){
        
         Usuario usuario = (Usuario)it.next();
            
         UsuarioDto usuarioDto = new UsuarioDto();
         usuarioDto.setIdUsuario(usuario.getIdUsuario());
         usuarioDto.setDni(usuario.getDni());
         usuarioDto.setIdUsuario(usuario.getIdUsuario());
         usuarioDto.setNombre(usuario.getNombre());
         usuarioDto.setUsuario(usuario.getUsuario());
         usuarioDto.setPassword(usuario.getPassword());
         usuarioDto.setRol(new RolDto(usuario.getRol().getIdRol()));
         usuarioDto.setMail(usuario.getMail());
         usuariosAsignados.add(usuarioDto);

    }    
        
        
        return usuariosAsignados;   
    }   

    private Usuario getEntitieFromDto(UsuarioDto usuarioDto) {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDto.getIdUsuario());
        usuario.setDni(usuarioDto.getDni());
        usuario.setIdUsuario(usuarioDto.getIdUsuario());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(new Rol(usuarioDto.getRol().getIdRol()));

        return usuario;

    }

    @POST
    public void asignarPaciente(String usuarioRequest) {

        try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Gson gson = new Gson();

             AsignacionPacienteDto asignacionPacienteDto = gson.fromJson(usuarioRequest, AsignacionPacienteDto.class);
             
            Set<Usuario> usuarios =  usuarioDao.findByListIds(asignacionPacienteDto.getIdsUsuario());
            
            Paciente paciente = pacienteDao.findById(asignacionPacienteDto.getIdPaciente());
            
            paciente.getUsuarios().addAll((Set)usuarios);
             //paciente.setUsuarios(usuarios);
 
             pacienteDao.persist(paciente);
             
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();

        }
    }

    @GET
    public String getPacientesAsignados(@QueryParam("id") int id) {

        String pacientesAsignadosResponse = "";
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Gson gson = new Gson();

            Paciente paciente = pacienteDao.findById(id);
            
            Set<Usuario> pacientes = paciente.getUsuarios();
                        
            Set<UsuarioDto> usuariosAsignadosDto = getDtoFromEntite(paciente.getUsuarios());
            
            List<Usuario> usuariosNoAsignados = usuarioDao.findByNotPaciente(paciente);
           
            Set<UsuarioDto> usuariosNoAsignadosDto = getDtoFromEntite(new HashSet<Usuario>(usuariosNoAsignados));
            
            AsignacionPacienteDto asignacionPacienteDto = new AsignacionPacienteDto();
            
            asignacionPacienteDto.setUsuariosNoAsignados(usuariosNoAsignadosDto);
            asignacionPacienteDto.setUsuariosAsignados(usuariosAsignadosDto);
            asignacionPacienteDto.setIdPaciente(id);
            
            
            pacientesAsignadosResponse = gson.toJson(asignacionPacienteDto);
            
           HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return pacientesAsignadosResponse;
        }

    }

    
    
    
    
    @DELETE
    public String desasignarPaciente(@QueryParam("idPaciente") int idPaciente, @QueryParam("idUsuario") int idUsuario ) {

        String pacientesAsignadosResponse = "";
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Paciente paciente = pacienteDao.findById(idPaciente);
            Usuario usuario = usuarioDao.findById(idUsuario);
            
            paciente.getUsuarios().remove(usuario);
            
            pacienteDao.persist(paciente);
            
           HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return pacientesAsignadosResponse;
        }
    
    }
}
