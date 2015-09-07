/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.dtos.LibroreportDto;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/LibroReportResource") 
public class LibroReportResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private PacienteDao pacienteDao = new PacienteDao();
    
    
 private LibroreportDto getDtoFromEntite(Libroreport libroreport){
        
        LibroreportDto libroreportDto = new LibroreportDto();
        libroreportDto.setIdLibroReport(libroreport.getIdLibroReport());
        libroreportDto.setFechaAlta(libroreport.getFechaAlta());
        libroreportDto.setFechaBaja(libroreport.getFechaBaja());
        libroreportDto.setEstado(libroreport.getEstado());
        libroreportDto.setPaciente(new PacienteDto( libroreport.getPaciente().getIdPaciente(),
                                           libroreport.getPaciente().getNombre(),
                                           libroreport.getPaciente().getApellido(),
                                           libroreport.getPaciente().getDni(),
                                           libroreport.getPaciente().getEdad(),
                                           libroreport.getPaciente().getAltura(),
                                           libroreport.getPaciente().getPeso()));
        
        return libroreportDto;
        
    }    
     
 
 private Libroreport getEntitieFromDto(LibroreportDto libroreportDto){
        
        Libroreport libroreport = new Libroreport();
        libroreport.setIdLibroReport(libroreportDto.getIdLibroReport());
        libroreport.setFechaAlta(libroreportDto.getFechaAlta());
        libroreport.setFechaBaja(libroreportDto.getFechaBaja());
        libroreport.setEstado(libroreportDto.getEstado());
        
        libroreport.setPaciente(new Paciente( 
                                              libroreportDto.getPaciente().getNombre(),
                                              libroreportDto.getPaciente().getApellido(),
                                              libroreportDto.getPaciente().getDni(),
                                              libroreportDto.getPaciente().getEdad(),
                                              libroreportDto.getPaciente().getAltura(),
                                              libroreportDto.getPaciente().getPeso() ));
        
        return libroreport;
        
    }
 
 
 
    @POST   
 public void addLibroReport(String libroReportRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      LibroreportDto libroreportDto = gson.fromJson(libroReportRequest, LibroreportDto.class);
     
      Libroreport libroReport = getEntitieFromDto(libroreportDto);
     
      libroReportDao.persist(libroReport);
     
      libroReport.getPaciente().setIdPaciente(libroReport.getIdLibroReport());
        
      pacienteDao.persist(libroReport.getPaciente());
      
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
 public void deleteLibroReport(@QueryParam ("id") int id){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Libroreport libroreport = libroReportDao.findById(id);
     
      libroReportDao.delete(libroreport);
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     }catch(HibernateException e){
         System.out.println(e.getMessage());
     }finally{   
     HibernateUtil.getSessionFactory().getCurrentSession().close();    
     }
 }     
 
 
 @PUT  
 public void updateLibroResport(String libroReportRequest){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      LibroreportDto libroreportDto = gson.fromJson(libroReportRequest, LibroreportDto.class);
     
      Libroreport libroReport = getEntitieFromDto(libroreportDto);
      
      libroReportDao.merge(libroReport);
      
      libroReport.getPaciente().setIdPaciente(libroReport.getIdLibroReport());
      
      pacienteDao.merge(libroReport.getPaciente());
      
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
    public String getLibroReport(@QueryParam ("id") int id){
    
     String libroReportResponse ="";   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Libroreport libroreport = libroReportDao.findById(id);
     
     libroReportResponse = gson.toJson(getDtoFromEntite(libroreport));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();              
         return libroReportResponse;
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
