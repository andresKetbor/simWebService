/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.Visita;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.common.daos.VisitaDao;
import org.sim.services.entities.dtos.VisitaDto;
import org.sim.services.entities.dtos.VisitasDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/PacienteResource/visita") 
public class VisitaResource {
    
    private  VisitaDao visitaDao = new VisitaDao(); 
    private PacienteDao pacienteDao = new PacienteDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    
    
 
 
     private List<VisitaDto> getDtoFromEntite(List<Visita> visitas){
        
      List<VisitaDto> visitasDto = new ArrayList<VisitaDto>();
        
      Iterator<Visita> it = visitas.iterator();
      
      while(it.hasNext()){
          
      Visita visita = (Visita)it.next();
      VisitaDto visitaDto = new VisitaDto();
       
      visitaDto.setFechaVisita(visita.getFechaVisita().toString());
      visitaDto.setFrecuencia(visita.getFrecuencia());
      visitaDto.setIdPaciente(visita.getPaciente().getIdPaciente());
      visitaDto.setIdUsuario(visita.getUsuario().getIdUsuario());
      visitaDto.setIdVisita(visita.getIdVisita());
      
      visitasDto.add(visitaDto);
      }
      
      return visitasDto;
    } 
    
    
 
    @POST   
 public String addVisita(String visitaRequest){
     
     VisitaDto visitaDto = null;
     Gson gson = null;
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
       visitaDto = gson.fromJson(visitaRequest, VisitaDto.class);
      
      Usuario usuario = usuarioDao.findById(visitaDto.getIdUsuario());
      Paciente paciente = pacienteDao.findById(visitaDto.getIdPaciente());
      Visita visita = new Visita();
      
      visita.setPaciente(paciente);
      visita.setUsuario(usuario);
      visita.setFechaVisita(new Date(visitaDto.getFechaVisita()));
     
      visitaDao.persist(visita);
     
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     
      visitaDto.setError("OK");
      
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
        if(visitaDto == null){
            visitaDto = new VisitaDto();  
        }
      visitaDto.setError("Error de negocio al asignar una visita: " + e.getMessage());   
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(visitaDto == null){
            visitaDto = new VisitaDto();  
        }
      visitaDto.setError("Error de negocio al asignar una visita: " + e.getMessage());   
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();
         return gson.toJson(visitaDto);
     }
 }  
 
 
 @DELETE  
 public String deleteVisita(@QueryParam ("id") int id){
     
     VisitaDto visitaDto = new VisitaDto();
     Gson gson = new Gson();
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Visita visita = visitaDao.findById(id);
     
      visitaDao.delete(visita);
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
      visitaDto.setError("OK");
     }catch(HibernateException e){
         System.out.println(e.getMessage());
         visitaDto.setError("Error al eliminar la visita: " + e.getMessage());
     }finally{   
     HibernateUtil.getSessionFactory().getCurrentSession().close();
     return gson.toJson(visitaDto);
     }
 }     
 
 
 @PUT  
 public String updateVisita(String visitaRequest){
     
     VisitaDto visitaDto = null;
     Gson gson = null;
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
       visitaDto = gson.fromJson(visitaRequest, VisitaDto.class);
      
      Usuario usuario = usuarioDao.findById(visitaDto.getIdUsuario());
      Paciente paciente = pacienteDao.findById(visitaDto.getIdPaciente());
      Visita visita = new Visita();
      
      visita.setPaciente(paciente);
      visita.setUsuario(usuario);
      visita.setFechaVisita(new Date(visitaDto.getFechaVisita()));
     
      visitaDao.persist(visita);
     
        
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(visitaDto == null){
            visitaDto = new VisitaDto();  
        }
      visitaDto.setError("Error de negocio al modificar una visita: " + e.getMessage());   
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(visitaDto == null){
            visitaDto = new VisitaDto();  
        }
      visitaDto.setError("Error de negocio al modificar una visita: " + e.getMessage());   
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();     
          return gson.toJson(visitaDto);
     }
     
 }
 
 
  @GET
    public String getEcg(@QueryParam ("idPaciente") int idPaciente, @QueryParam ("idUsuario") int idUsuario){
    
     Gson gson = null;
     VisitasDto visitasDto = null;
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     gson = new Gson();
     
     Usuario usuario = usuarioDao.findById(idUsuario);
     Paciente paciente = pacienteDao.findById(idPaciente);
     
     List<Visita> visitas = visitaDao.findByUsuarioAndPaciente(usuario, paciente);
      
     List<VisitaDto> listVisitasDto = this.getDtoFromEntite(visitas);
     
     visitasDto = new VisitasDto();
                 
     visitasDto.setVisitas(listVisitasDto);
             
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     
     visitasDto.setError("OK");
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(visitasDto == null){
            visitasDto = new VisitasDto();  
        }
      visitasDto.setError("Error de negocio al traer la lista de visitas : " + e.getMessage());   
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(visitasDto == null){
            visitasDto = new VisitasDto();  
        }
      visitasDto.setError("Error de negocio al traer la lista de visitas : " + e.getMessage());   
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();
         
         return gson.toJson(visitasDto);
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
