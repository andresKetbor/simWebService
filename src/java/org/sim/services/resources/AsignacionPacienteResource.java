/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Rol;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.AsignacionPacienteDto;
import org.sim.services.entities.dtos.PacienteDto;
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
    private UsuarioDto getDtoFromEntite(Usuario usuario) {

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setDni(usuario.getDni());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRol(new RolDto(usuario.getRol().getIdRol()));

        
        Iterator<Paciente> iter =usuario.getPacientes().iterator();
        
        while(iter.hasNext()){
            
            Paciente paciente = iter.next();
            PacienteDto pacienteDto = new PacienteDto();
            
            pacienteDto.setIdPaciente(paciente.getIdPaciente());
            pacienteDto.setNombre(paciente.getNombre());
            pacienteDto.setApellido(paciente.getApellido());
            pacienteDto.setDni(paciente.getDni());
            pacienteDto.setEdad(paciente.getEdad());
            pacienteDto.setAltura(paciente.getAltura());
            pacienteDto.setPeso(paciente.getPeso());
            
            usuarioDto.getPacientes().add(pacienteDto);
            
        }
        
        
        
        return usuarioDto;

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
            
             paciente.setUsuarios(usuarios);
 
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

    @DELETE
    public void deleteUsuario(@QueryParam("id") int id) {

        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Usuario usuario = usuarioDao.findById(id);

            usuarioDao.delete(usuario);

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
        }

    }

    @PUT
    public void updateUsuario(String usuarioRequest) {

        try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Gson gson = new Gson();

            UsuarioDto usuarioDto = gson.fromJson(usuarioRequest, UsuarioDto.class);

            usuarioDao.merge(getEntitieFromDto(usuarioDto));

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
    public String getUsuario(@QueryParam("id") int id) {

        String usuarioResponse = "";
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Gson gson = new Gson();

            Usuario usuario = usuarioDao.findById(id);

            usuarioResponse = gson.toJson(getDtoFromEntite(usuario));

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return usuarioResponse;
        }

    }

}