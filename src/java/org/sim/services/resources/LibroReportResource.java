/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

@Path("/LibroReportResource") 
public class LibroReportResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private PacienteDao pacienteDao = new PacienteDao();
    private MedicionDao medicionDao =  new MedicionDao();
    
 private LibroreportDto getDtoFromEntite(Libroreport libroreport){
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       
        String fechaAlta =  formateador.format(libroreport.getFechaAlta());
        String fechaBaja = formateador.format(libroreport.getFechaBaja());
     
     
        LibroreportDto libroreportDto = new LibroreportDto();
        libroreportDto.setIdLibroReport(libroreport.getIdLibroReport());
        libroreportDto.setFechaAlta(fechaAlta);
        libroreportDto.setFechaBaja(fechaBaja);
        libroreportDto.setEstado(libroreport.getEstado());
        
        libroreportDto.setPaciente(new PacienteDto( libroreport.getPaciente().getIdPaciente(),
                                           libroreport.getPaciente().getNombre(),
                                           libroreport.getPaciente().getApellido(),
                                           libroreport.getPaciente().getDni(),
                                           libroreport.getPaciente().getEdad(),
                                           libroreport.getPaciente().getAltura(),
                                           libroreport.getPaciente().getPeso()));
        
        
       Set<MedicionDto> medicionsDto = new HashSet<MedicionDto>(0); 
        
        if(!libroreport.getMedicions().isEmpty()){
       
            for ( Iterator iterador = libroreport.getMedicions().iterator(); iterador.hasNext(); ) {
            
            
                MedicionDto medDto = new MedicionDto();

                Medicion medEnt =(Medicion) iterador.next(); 



                if( medEnt instanceof Temperatura){

                    
                    
                    Temperatura temp = (Temperatura) medEnt;
                    medDto.setTemperatura(temp.getTemperatura());
                    medDto.setDescripcion(temp.getDescripcion());
                    String fecha =formateador.format(temp.getFecha());
                    
                    medDto.setFecha(fecha);

                }

                if(medEnt instanceof Nivelglucosa) {

                   Nivelglucosa ngEnti = (Nivelglucosa) medEnt;
                   String fecha =formateador.format(ngEnti.getFecha());
                   
                   medDto.setFecha(fecha);
                   medDto.setDescripcion(ngEnti.getDescripcion());
                   medDto.setDosis(ngEnti.getDosis());
                   medDto.setGlucosa(ngEnti.getGlucosa());

                    }

                if(medEnt instanceof Freceunciarespiratoria) {

                     Freceunciarespiratoria freEnti = (Freceunciarespiratoria) medEnt;
                     String fecha =formateador.format(freEnti.getFecha());
                     
                     medDto.setFecha(fecha);
                     medDto.setDescripcion(medEnt.getDescripcion());
                     medDto.setFrecuenciaRespiratoria(medEnt.getDescripcion());

                 }

                 if(medEnt instanceof Saturometria){

                     Saturometria saEnti = (Saturometria) medEnt; 
                     String fecha =formateador.format(saEnti.getFecha());
                     medDto.setFecha(fecha);
                     medDto.setDescripcion(saEnti.getDescripcion());  
                     medDto.setOxigenoEnSangre(saEnti.getOxigenoEnSangre());

                 }

                 if(medEnt instanceof Tensionarterial){

                   Tensionarterial tenEnti = (Tensionarterial) medEnt;
                   String fecha =formateador.format(tenEnti.getFecha());
                   medDto.setFecha(fecha);
                   medDto.setDescripcion(tenEnti.getDescripcion());  
                   medDto.setTensionArterial(tenEnti.getTensionArterial());

                 }
                       
             medicionsDto.add(medDto);
        }
                   
    }
              
                
       libroreportDto.setMedicions(medicionsDto);
        
       return libroreportDto;
        
    }    
     
 
 private Libroreport getEntitieFromDto(LibroreportDto libroreportDto){
        
        Libroreport libroreport = new Libroreport();
        libroreport.setIdLibroReport(libroreportDto.getIdLibroReport());
        libroreport.setFechaAlta(new Date(libroreportDto.getFechaAlta()));
        libroreport.setFechaBaja( new Date(libroreportDto.getFechaBaja()));
        libroreport.setEstado(libroreportDto.getEstado());
        
        libroreport.setPaciente(new Paciente( 
                                              libroreportDto.getPaciente().getNombre(),
                                              libroreportDto.getPaciente().getApellido(),
                                              libroreportDto.getPaciente().getDni(),
                                              libroreportDto.getPaciente().getEdad(),
                                              libroreportDto.getPaciente().getAltura(),
                                              libroreportDto.getPaciente().getPeso() ));
      
        
       Set<Medicion> medicionsEnt = new HashSet<Medicion>(0);
        
//       while(libroreportDto.getMedicions().iterator().hasNext()){
        
       if(!libroreportDto.getMedicions().isEmpty()){
       
       for ( Iterator iterador = libroreportDto.getMedicions().iterator(); iterador.hasNext(); ) {
            
            MedicionDto medDto =(MedicionDto) iterador.next(); 
           
           if( medDto.getTemperatura()!= null){
            
              Temperatura tempEnti = new Temperatura();
              tempEnti.setFecha(new Date(medDto.getFecha()));
              tempEnti.setDescripcion(medDto.getDescripcion());
              tempEnti.setTemperatura(medDto.getTemperatura());
              medicionsEnt.add(tempEnti);
           }else{
               if((medDto.getGlucosa()!=null) && !(medDto.getGlucosa().isEmpty())) {
               
              Nivelglucosa ngEnti = new Nivelglucosa();
              ngEnti.setFecha(new Date(medDto.getFecha()));
              ngEnti.setDescripcion(medDto.getDescripcion());
              ngEnti.setDosis(medDto.getDosis());
              ngEnti.setGlucosa(medDto.getGlucosa());
              medicionsEnt.add(ngEnti);
               }else{
                   if( (medDto.getFrecuenciaRespiratoria()!=null) &&  !(medDto.getFrecuenciaRespiratoria().isEmpty())){
                       
                       Freceunciarespiratoria freEnti = new Freceunciarespiratoria();
                       freEnti.setFecha(new Date(medDto.getFecha()));
                       freEnti.setDescripcion(medDto.getDescripcion());
                       freEnti.setFreceunciaRespiratoria(medDto.getDescripcion());
                       medicionsEnt.add(freEnti);
                   }else{
                       
                       if(medDto.getOxigenoEnSangre()!=null){
                           
                           Saturometria saEnti = new Saturometria();
                           saEnti.setFecha(new Date(medDto.getFecha()));
                           saEnti.setDescripcion(medDto.getDescripcion());  
                           saEnti.setOxigenoEnSangre(medDto.getOxigenoEnSangre());
                           medicionsEnt.add(saEnti);
                       }else
                           if(medDto.getTensionArterial()!=null){
                               
                             Tensionarterial tenEnti = new Tensionarterial();
                             tenEnti.setFecha(new Date(medDto.getFecha()));
                             tenEnti.setDescripcion(medDto.getDescripcion());  
                             tenEnti.setTensionArterial(medDto.getTensionArterial());
                             medicionsEnt.add(tenEnti);  
                               
                           }
                       
                   }
                   
               }
           }   
                
        }
       
       libroreport.setMedicions(medicionsEnt);
       }
       
       
        
       return libroreport;
        
    }
 

    @POST   
 public String addLibroReport(String libroReportRequest){
     
     LibroreportDto libroreportDto=null;
     Gson gson = null;
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
      libroreportDto = gson.fromJson(libroReportRequest, LibroreportDto.class);
      
     Libroreport libroReport = getEntitieFromDto(libroreportDto);
     
      libroReportDao.persist(libroReport);
     
      libroReport.getPaciente().setIdPaciente(libroReport.getIdLibroReport());
        
      pacienteDao.persist(libroReport.getPaciente());
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
      
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(libroreportDto == null){
                
              libroreportDto = new LibroreportDto();  
            }
         libroreportDto.setError(e.getMessage());
         
     }catch(Exception e){
         System.out.println(e.getMessage());
         
         if(libroreportDto == null){
                
              libroreportDto = new LibroreportDto();  
            }
         
         libroreportDto.setError(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();         
         return gson.toJson(libroreportDto);
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
 public String updateLibroResport(String libroReportRequest){
     
     LibroreportDto libroreportDto=null;
     Gson gson = null;
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
      libroreportDto = gson.fromJson(libroReportRequest, LibroreportDto.class);
     
      Libroreport libroReport = getEntitieFromDto(libroreportDto);
      
      libroReportDao.merge(libroReport);
      
      libroReport.getPaciente().setIdPaciente(libroReport.getIdLibroReport());
      
      pacienteDao.merge(libroReport.getPaciente());
      
      
    for ( Iterator iterador = libroReport.getMedicions().iterator(); iterador.hasNext(); ) {
          
            Medicion med =(Medicion) iterador.next();       
            
            med.setLibroreport(libroReport);     
            
            medicionDao.persist(med);
    }
      
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         
         if(libroreportDto == null){
                
              libroreportDto = new LibroreportDto();  
            }
         libroreportDto.setError(e.getMessage());
         
     }catch(Exception e){
         System.out.println(e.getMessage());
         
         if(libroreportDto == null){
                
              libroreportDto = new LibroreportDto();  
            }
         
         libroreportDto.setError(e.getMessage());
     }
     finally{
        
         HibernateUtil.getSessionFactory().getCurrentSession().close();  
         
         return gson.toJson(libroreportDto);
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
