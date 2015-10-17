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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Freceunciarespiratoria;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Medicion;
import org.sim.services.entities.Nivelglucosa;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Saturometria;
import org.sim.services.entities.Temperatura;
import org.sim.services.entities.Tensionarterial;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.MedicionDao;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.dtos.LibroreportDto;
import org.sim.services.entities.dtos.MedicionDto;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/MedicionResource") 
public class MedicionResource {
    
    private LibroReportDao libroReportDao = new LibroReportDao(); 
    private MedicionDao medicionDao =  new MedicionDao();
    
 
     
 private Medicion getEntitieFromDto(MedicionDto medDto){
       
      Medicion medicion=null;     
          
         if( medDto.getTemperatura()!= null){
            
              Temperatura tempEnti = new Temperatura();
              tempEnti.setFecha(medDto.getFecha());
              tempEnti.setDescripcion(medDto.getDescripcion());
              tempEnti.setTemperatura(medDto.getTemperatura());
              
              medicion = tempEnti;
           }else{
               if((medDto.getGlucosa()!=null) && !(medDto.getGlucosa().isEmpty())) {
               
              Nivelglucosa ngEnti = new Nivelglucosa();
              ngEnti.setFecha(medDto.getFecha());
              ngEnti.setDescripcion(medDto.getDescripcion());
              ngEnti.setDosis(medDto.getDosis());
              ngEnti.setGlucosa(medDto.getGlucosa());
              
              medicion = ngEnti;
               }else{
                   if( (medDto.getFreceunciaRespiratoria()!=null) &&  !(medDto.getFreceunciaRespiratoria().isEmpty())){
                       
                       Freceunciarespiratoria freEnti = new Freceunciarespiratoria();
                       freEnti.setFecha(medDto.getFecha());
                       freEnti.setDescripcion(medDto.getDescripcion());
                       freEnti.setFreceunciaRespiratoria(medDto.getDescripcion());
              
                       medicion = freEnti;
                   }else{
                       
                       if(medDto.getOxigenoEnSangre()!=null){
                           
                           Saturometria saEnti = new Saturometria();
                           saEnti.setFecha(medDto.getFecha());
                           saEnti.setDescripcion(medDto.getDescripcion());  
                           saEnti.setOxigenoEnSangre(medDto.getOxigenoEnSangre());
                           
                           medicion = saEnti;
              
                       }else{
                           if(medDto.getTensionArterial()!=null){
                               
                             Tensionarterial tenEnti = new Tensionarterial();
                             tenEnti.setFecha(medDto.getFecha());
                             tenEnti.setDescripcion(medDto.getDescripcion());  
                             tenEnti.setTensionArterial(medDto.getTensionArterial());
                             
                             medicion = tenEnti;
                           }
                       
                   }
                   
               }
           }   
                
          }
         return medicion;
 } 
    
 
 @POST   
 public void addMedicion(String medicionRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      MedicionDto medicionDto = gson.fromJson(medicionRequest, MedicionDto.class);
      
      Medicion medicion = getEntitieFromDto(medicionDto);
     
      Libroreport libroReport = libroReportDao.findById(medicionDto.getIdLibroreport());
     
      medicion.setLibroreport(libroReport);
      
      medicionDao.persist(medicion);
      
      
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
 
  
}
