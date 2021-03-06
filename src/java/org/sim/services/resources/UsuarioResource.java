/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.entities.dtos.RolDto;
import org.sim.services.entities.dtos.UsuarioDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */
@Path("/UsuarioResource")
public class UsuarioResource {

    UsuarioDao usuarioDao = new UsuarioDao();

    private UsuarioDto getDtoFromEntite(Usuario usuario) {

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setDni(usuario.getDni());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRol(new RolDto(usuario.getRol().getIdRol(), usuario.getRol().getNombreRol()));
        usuarioDto.setMail(usuario.getMail());
        usuarioDto.setMensajeRegId(usuario.getMensajeRegId());
        if(usuario.getPacientes()!=null){
        
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
        usuario.setMail(usuarioDto.getMail());
        usuario.setMensajeRegId(usuarioDto.getMensajeRegId());
        return usuario;

    }

    @POST
    public String addUsuario(String usuarioRequest) {

        UsuarioDto usuarioDto = null;
        Gson gson = null;
        
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            gson = new Gson();

            usuarioDto = gson.fromJson(usuarioRequest, UsuarioDto.class);

            usuarioDao.persist(getEntitieFromDto(usuarioDto));

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            usuarioDto.setError("OK");
        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
            if(usuarioDto == null){
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al agregar Usuario : " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
             if(usuarioDto == null){
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al agregar Usuario : " + e.getMessage());
            
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return gson.toJson(usuarioDto);
        }
    }

    @DELETE
    public String deleteUsuario(@QueryParam("id") int id) {

        UsuarioDto usuarioDto = new UsuarioDto();
        Gson gson = new Gson();
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Usuario usuario = usuarioDao.findById(id);

            usuarioDao.delete(usuario);

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            usuarioDto.setError("OK");
        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
            usuarioDto.setError("Error al dar de baja el usuario : " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            usuarioDto.setError("Error al dar de baja el usuario : " + e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return gson.toJson(usuarioDto);
        }

    }

    @PUT
    public String updateUsuario(String usuarioRequest) {

        UsuarioDto usuarioDto = null;
        Gson gson = null;
        
        try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            gson = new Gson();

            usuarioDto = gson.fromJson(usuarioRequest, UsuarioDto.class);

            usuarioDao.merge(getEntitieFromDto(usuarioDto));

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            
            usuarioDto.setError("OK");
        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
            if(usuarioDto == null){
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al modificar Usuario : " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(usuarioDto == null){
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al modificar Usuario : " + e.getMessage());
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return gson.toJson(usuarioDto);
        }

    }

    @GET
    public String getUsuario(@QueryParam("usuario") String usuarioRequest) {

        String usuarioResponse = "";
        UsuarioDto usuarioDto = null;
        Gson gson = null;
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            gson = new Gson();

            Usuario usuario = usuarioDao.findUsuarioByUsr(usuarioRequest);

            usuarioDto = getDtoFromEntite(usuario);
            
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            
            usuarioDto.setError("OK");
        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
             if(usuarioDto == null){    
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al consultar usuario : " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
             if(usuarioDto == null){
              usuarioDto = new UsuarioDto();  
            }
         
         usuarioDto.setError("Error al consultar usuario : " + e.getMessage());
        } finally {
            HibernateUtil.getSessionFactory().getCurrentSession().close();
           return gson.toJson(usuarioDto);
        }

    }

}
