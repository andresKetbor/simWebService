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
import org.sim.services.entities.Ecg;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.EcgDao;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.dtos.EcgDto;
import org.sim.services.entities.dtos.LibroreportDto;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/EcgResource") 
public class EcgResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private EcgDao ecgDao = new EcgDao();
    
    
 private EcgDto getDtoFromEntite(Ecg ecg){
        
        EcgDto ecgDto = new EcgDto();
        ecgDto.setIdEcg(ecg.getIdEcg());
        ecgDto.setFecha(ecg.getFecha());
        ecgDto.setEstado(ecg.getEstado());
        ecgDto.setPpm(ecg.getPpm());
        ecgDto.setCaptura(ecg.getCaptura());
        ecgDto.setLibroreport(new LibroreportDto( ecg.getLibroreport().getIdLibroReport(),
                                           ecg.getLibroreport().getFechaAlta(),
                                           ecg.getLibroreport().getFechaBaja(),
                                           ecg.getLibroreport().getEstado(),
                                           new PacienteDto(ecg.getLibroreport().getPaciente().getIdPaciente(),
                                           ecg.getLibroreport().getPaciente().getNombre(),
                                           ecg.getLibroreport().getPaciente().getApellido(),
                                           ecg.getLibroreport().getPaciente().getDni(),
                                           ecg.getLibroreport().getPaciente().getEdad(),
                                           ecg.getLibroreport().getPaciente().getAltura(),
                                           ecg.getLibroreport().getPaciente().getPeso())
                                                   ));
        
        return ecgDto;
        
    }    
     
 
 private Ecg getEntitieFromDto(EcgDto ecgDto){
        
        Ecg ecg = new Ecg();
        ecg.setIdEcg(ecgDto.getIdEcg());
        ecg.setFecha(ecgDto.getFecha());
        ecg.setEstado(ecgDto.getEstado());
        ecg.setPpm(ecgDto.getPpm());
        ecg.setCaptura(ecgDto.getCaptura());
        ecg.setLibroreport(new Libroreport(ecgDto.getLibroreport().getIdLibroReport(),new Paciente(ecgDto.getLibroreport().getPaciente().getIdPaciente(),
                                                        ecgDto.getLibroreport().getPaciente().getNombre(),                            
                                                        ecgDto.getLibroreport().getPaciente().getApellido(),
                                                        ecgDto.getLibroreport().getPaciente().getDni(),
                                                        ecgDto.getLibroreport().getPaciente().getEdad(),
                                                        ecgDto.getLibroreport().getPaciente().getAltura(),
                                                        ecgDto.getLibroreport().getPaciente().getPeso()),
                                                        ecgDto.getLibroreport().getFechaAlta(),
                                                        ecgDto.getLibroreport().getFechaBaja(),
                                                        ecgDto.getLibroreport().getEstado()));
        
       
                                              
        
        return ecg;
        
    }
 
 
 
    @POST   
 public void addEcg(String ecgRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      EcgDto ecgDto = gson.fromJson(ecgRequest, EcgDto.class);
     
      Ecg ecg = getEntitieFromDto(ecgDto);
     
      //libroReportDao.refresh(ecg.getLibroreport());
      ecgDao.persist(ecg);
     
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
 public void deleteEcg(@QueryParam ("id") int id){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Ecg ecg = ecgDao.findById(id);
     
      ecgDao.delete(ecg);
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     }catch(HibernateException e){
         System.out.println(e.getMessage());
     }finally{   
     HibernateUtil.getSessionFactory().getCurrentSession().close();
     }
 }     
 
 
 @PUT  
 public void updateEcg(String ecgRequest){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      EcgDto ecgDto = gson.fromJson(ecgRequest, EcgDto.class);
     
      Ecg ecg = getEntitieFromDto(ecgDto);
      
      ecgDao.merge(ecg);
        
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
    public String getEcg(@QueryParam ("id") int id){
    
     String ecgResponse ="";   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Ecg ecg = ecgDao.findById(id);
     
     ecgResponse = gson.toJson(getDtoFromEntite(ecg));
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();
         return ecgResponse;
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
