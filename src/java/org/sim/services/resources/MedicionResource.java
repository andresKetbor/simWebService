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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.hibernate.HibernateException;
import org.sim.services.entities.Freceunciarespiratoria;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Medicion;
import org.sim.services.entities.Nivelglucosa;
import org.sim.services.entities.Saturometria;
import org.sim.services.entities.Temperatura;
import org.sim.services.entities.Tensionarterial;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.MedicionDao;
import org.sim.services.entities.dtos.MedicionDto;
import org.sim.services.entities.dtos.MedicionesDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/MedicionResource") 
public class MedicionResource {
    
    private LibroReportDao libroReportDao = new LibroReportDao(); 
    private MedicionDao medicionDao =  new MedicionDao();
    
 
     
 private Set<Medicion> getEntitieFromDto(Set<MedicionDto> medecionsDto){
       
      
      Set<Medicion> medicionsEnt = new HashSet<Medicion>(0);
      Iterator<MedicionDto> it = medecionsDto.iterator();
      
      
      while(it.hasNext()){
      
          MedicionDto medDto = (MedicionDto)it.next();
         
          if( medDto.getTemperatura()!= null){
            
              Temperatura tempEnti = new Temperatura();
              tempEnti.setFecha(medDto.getFecha());
              tempEnti.setDescripcion(medDto.getDescripcion());
              tempEnti.setTemperatura(medDto.getTemperatura());
              medicionsEnt.add(tempEnti);
           }else{
               if((medDto.getGlucosa()!=null) && !(medDto.getGlucosa().isEmpty())) {
               
              Nivelglucosa ngEnti = new Nivelglucosa();
              ngEnti.setFecha(medDto.getFecha());
              ngEnti.setDescripcion(medDto.getDescripcion());
              ngEnti.setDosis(medDto.getDosis());
              ngEnti.setGlucosa(medDto.getGlucosa());
              medicionsEnt.add(ngEnti);
               
               }else{
                   if( (medDto.getFreceunciaRespiratoria()!=null) &&  !(medDto.getFreceunciaRespiratoria().isEmpty())){
                       
                       Freceunciarespiratoria freEnti = new Freceunciarespiratoria();
                       freEnti.setFecha(medDto.getFecha());
                       freEnti.setDescripcion(medDto.getDescripcion());
                       freEnti.setFreceunciaRespiratoria(medDto.getDescripcion());
                       medicionsEnt.add(freEnti);
                   }else{
                       
                       if(medDto.getOxigenoEnSangre()!=null){
                           
                           Saturometria saEnti = new Saturometria();
                           saEnti.setFecha(medDto.getFecha());
                           saEnti.setDescripcion(medDto.getDescripcion());  
                           saEnti.setOxigenoEnSangre(medDto.getOxigenoEnSangre());
                           medicionsEnt.add(saEnti);
              
                       }else{
                           if(medDto.getTensionArterial()!=null){
                               
                             Tensionarterial tenEnti = new Tensionarterial();
                             tenEnti.setFecha(medDto.getFecha());
                             tenEnti.setDescripcion(medDto.getDescripcion());  
                             tenEnti.setTensionArterial(medDto.getTensionArterial());
                             medicionsEnt.add(tenEnti);
                           }
                       
                   }
                   
               }
           }   
                
          }
      }
         return medicionsEnt;
 } 
 
 
 
 
 private void setLibroReportToMediciones(Set<Medicion> mediciones, Libroreport libroreport){
     
     
     
     Iterator<Medicion> it = mediciones.iterator();
     
 
     while(it.hasNext()){
     
     Medicion medicion = (Medicion)it.next();
     
     medicion.setLibroreport(libroreport);
     
 }
 
 
 }
    
 
 @POST   
 public void addMedicion(String medicionRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      MedicionesDto medicionesDto = gson.fromJson(medicionRequest, MedicionesDto.class);
      
      Libroreport libroReport = libroReportDao.findById(medicionesDto.getIdLibroReport());
      
      Set<Medicion> mediciones = getEntitieFromDto(medicionesDto.getMediciones());
      setLibroReportToMediciones(mediciones, libroReport);
      
      medicionDao.persist(mediciones);
      
      
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
