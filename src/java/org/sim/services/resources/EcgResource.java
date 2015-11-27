/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Alerta;
import org.sim.services.entities.Ecg;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Mensaje;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.AlertaDao;
import org.sim.services.entities.common.daos.CriticidadDao;
import org.sim.services.entities.common.daos.EcgDao;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.MensajeDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.AlertaApiDto;
import org.sim.services.entities.dtos.Data;
import org.sim.services.entities.dtos.EcgDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/EcgResource") 
public class EcgResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private EcgDao ecgDao = new EcgDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    private AlertaDao alertaDao = new AlertaDao();
    private MensajeDao mensajeDao = new MensajeDao();
    private CriticidadDao criticidadDao = new CriticidadDao();
  
    
 private void enviarAlerta(String textoMensaje, List<String> idsRegistro){
     
     HttpURLConnection connection = null;
     
     Gson gson = new Gson();
     
     try{
       
         AlertaApiDto alertaApiDto = new AlertaApiDto();
        
         alertaApiDto.setData(new Data("Electro Cardio Grama",textoMensaje));
         alertaApiDto.setRegistration_ids(idsRegistro);
         
        String mensaje = gson.toJson(alertaApiDto);
     
        URL url = new URL("https://gcm-http.googleapis.com/gcm/send");
             
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Key=AIzaSyBkOtEYmM5QXhtOW0cFQdwUXDiWGARErCA");
        connection.setRequestProperty("Content-Type", "application/json");
         
     // ide del proyecto en las api de google proyecto-sim
     
        connection.setDoOutput(true);
        connection.setDoInput(true);
         
        connection.setDefaultUseCaches(false); 
       
        connection.connect();

        OutputStream out = connection.getOutputStream();
        DataOutputStream writer = new DataOutputStream (out);
        
        writer.writeBytes(mensaje);
        writer.flush();
        writer.close();
        
        out.close();
         
        System.out.println( connection.getResponseMessage()+ "  " + connection.getResponseCode());
         
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        while((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
     }
       
        in.close(); 
        
             
      }catch (MalformedURLException ex) {
         
        System.out.println(ex);
          
      } catch (IOException ex) {
         System.out.println(ex);
         
      } catch (Exception ex) {
         System.out.println(ex);
         
         
   } finally{
            
            connection.disconnect();
        }
     
 }       
    
    
    
    
 private EcgDto getDtoFromEntite(Ecg ecg){
        
        EcgDto ecgDto = new EcgDto();
        ecgDto.setIdEcg(ecg.getIdEcg());
        ecgDto.setCaptura(ecg.getCaptura());
        
        
        return ecgDto;
        
    }    
     
 
 private Ecg getEntitieFromDto(EcgDto ecgDto){
        
        Ecg ecg = new Ecg();
      //  ecg.setIdEcg(ecgDto.getIdEcg());
        ecg.setFecha(ecgDto.getFecha());
        ecg.setDiagnostico(ecgDto.getDiagnostico());
        ecg.setDiagnosticoDetallado(ecgDto.getDiagnosticoDetallado());
        ecg.setCaptura(ecgDto.getCaptura());
        ecg.setPpmMax(ecgDto.getPpmMax());
        ecg.setPpmMin(ecgDto.getPpmMin());
        ecg.setPpmProm(ecgDto.getPpmProm());
        
        
    
        return ecg;
        
    }
 
 
    @POST   
 public void addEcg(String ecgRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      EcgDto ecgDto = gson.fromJson(ecgRequest, EcgDto.class);
     
      Ecg ecg = getEntitieFromDto(ecgDto);
     
      Libroreport libroReport = libroReportDao.findById(ecgDto.getIdLibroreport());
      
      ecg.setLibroreport(libroReport);
      
      ecgDao.persist(ecg);
      
      if(ecgDto.getPpmMax()!=null){
             
       Usuario usuarioMonitor = usuarioDao.findById(100);
       Set<Usuario> usuarios = libroReport.getPaciente().getUsuarios();
       String textoMensaje = "ECG : " + ecgDto.getDiagnostico();
       
       Iterator<Usuario> it = usuarios.iterator();
       List<String> idsRegistro = new ArrayList<>();
      
      while(it.hasNext()){
          
          Usuario usuarioDestinatario = (Usuario) it.next();
                                  
              Alerta alerta = new Alerta();
              alerta.setLibroreport(libroReport);
               
              Mensaje mensaje = new Mensaje(usuarioMonitor,usuarioDestinatario, textoMensaje);
              alerta.setMensaje(mensaje);
              alerta.setFecha(new Date());
              alerta.setCriticidad(criticidadDao.findById(1));
              
              alertaDao.persist(alerta);
              mensaje.setAlerta(alerta);
              mensajeDao.persist(mensaje);
          
              idsRegistro.add(usuarioDestinatario.getMensajeRegId());
      }
      
          
          enviarAlerta(textoMensaje,idsRegistro);
          
          
      }else{
          
       	Runtime.getRuntime().exec("");    
          // comando de linux para ejecutar el 
          
      }
      
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
