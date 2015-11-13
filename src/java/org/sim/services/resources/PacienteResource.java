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
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.entities.dtos.RolDto;
import org.sim.services.entities.dtos.UsuarioDto;
import org.sim.services.util.HibernateUtil;


/**
 *
 * @author adengra
 */
 @Path("/PacienteResource")
public class PacienteResource{
    
     PacienteDao pacienteDao = new PacienteDao();
     
 private PacienteDto getDtoFromEntite(Paciente paciente){
        
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setIdPaciente(paciente.getIdPaciente());
        pacienteDto.setDni(paciente.getDni());
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setAltura(paciente.getAltura());
        paciente.setPeso(paciente.getPeso());
        paciente.setEdad(paciente.getEdad());
        
        
        if(paciente.getUsuarios()!=null){
        
        Set<UsuarioDto> usuariosDto = new HashSet();
        
        Iterator<Usuario> it = paciente.getUsuarios().iterator();
        
        while(it.hasNext()){
            
            Usuario usuario = it.next();
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setIdUsuario(usuario.getIdUsuario());
            usuarioDto.setDni(usuario.getDni());
            usuarioDto.setIdUsuario(usuario.getIdUsuario());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setUsuario(usuario.getUsuario());
            usuarioDto.setPassword(usuario.getPassword());
            usuarioDto.setRol(new RolDto(usuario.getRol().getIdRol()));
            
            
            usuariosDto.add(usuarioDto);
            
        }
        
        pacienteDto.setUsuariosAsignados(usuariosDto);
        
        }
        
        return pacienteDto;
        
    }    
     
 
 private Paciente getEntitieFromDto(PacienteDto pacienteDto){
        
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(pacienteDto.getIdPaciente());
        paciente.setDni(pacienteDto.getDni());
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setEdad(pacienteDto.getEdad());
        paciente.setPeso(pacienteDto.getPeso());
        paciente.setAltura(pacienteDto.getAltura());
        return paciente;
        
    }
 
 
 
 
    
    @POST   
 public String addPaciente(String pacienteRequest){
     Gson gson = null;
     PacienteDto pacienteDto = null;
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
      pacienteDto = gson.fromJson(pacienteRequest, PacienteDto.class);
     
      pacienteDao.persist(getEntitieFromDto(pacienteDto));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
      
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
            if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
            if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }
     finally{
         
     HibernateUtil.getSessionFactory().getCurrentSession().close();                       
     return gson.toJson(pacienteDto);
     }
 }  
 
 
 
 @DELETE  
 public void deletePaciente(@QueryParam ("id") int id){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Paciente paciente = pacienteDao.findById(id);
     
      pacienteDao.delete(paciente);
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException e){
         System.out.println(e.getMessage());
     }finally{   
         
     HibernateUtil.getSessionFactory().getCurrentSession().close();                  
          
     }
 }     
 
 
 @PUT  
 public String updatePaciente(String pacienteRequest){
     
     Gson gson = null;
     PacienteDto pacienteDto = null;
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
      pacienteDto = gson.fromJson(pacienteRequest, PacienteDto.class);
     
     pacienteDao.merge(getEntitieFromDto(pacienteDto));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();                       
         return gson.toJson(pacienteDto);
     }
     
 }
 
 
  @GET
    public String getPaciente(@QueryParam ("id") int id){
    
     Gson gson = null;
     PacienteDto pacienteDto = null;   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     gson = new Gson();
     
     Paciente paciente = pacienteDao.findById(id);
     
     pacienteDto = getDtoFromEntite(paciente);
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(pacienteDto == null){
              pacienteDto = new PacienteDto();  
            }
         
         pacienteDto.setError(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();                       
         return gson.toJson(pacienteDto);
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
