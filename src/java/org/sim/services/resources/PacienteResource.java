/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.dtos.PacienteDto;
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
 public void addPaciente(String pacienteRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      PacienteDto pacienteDto = gson.fromJson(pacienteRequest, PacienteDto.class);
     
      pacienteDao.persist(getEntitieFromDto(pacienteDto));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
      
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         
     HibernateUtil.getSessionFactory().getCurrentSession().close();                       
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
 public void updatePaciente(String pacienteRequest){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      PacienteDto pacienteDto = gson.fromJson(pacienteRequest, PacienteDto.class);
     
     pacienteDao.merge(getEntitieFromDto(pacienteDto));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();                       
     }
     
 }
 
 
  @GET
    public String getPaciente(@QueryParam ("id") int id){
    
     String pacienteResponse ="";   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Paciente paciente = pacienteDao.findById(id);
     
     pacienteResponse = gson.toJson(getDtoFromEntite(paciente));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();                       
         return pacienteResponse;
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
