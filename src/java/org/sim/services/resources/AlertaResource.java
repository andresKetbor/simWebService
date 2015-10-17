/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.hibernate.HibernateException;
import org.sim.services.entities.Alerta;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Mensaje;
import org.sim.services.entities.Rol;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.AlertaDao;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.dtos.AlertaDto;
import org.sim.services.entities.dtos.MensajeDto;
import org.sim.services.entities.dtos.RolDto;
import org.sim.services.entities.dtos.UsuarioDto;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/AlertaResource") 
public class AlertaResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private AlertaDao alertaDao = new AlertaDao();
    
    
 private AlertaDto getDtoFromEntite(Alerta alerta){
        
        AlertaDto alertaDto = new AlertaDto();
        alertaDto.setIdAlerta(alerta.getIdAlerta());
        alertaDto.setFecha(alerta.getFecha());
        
        alertaDto.setMensajeDto(new MensajeDto(new UsuarioDto(new RolDto(alerta.getMensaje().getUsuarioRemitente().getRol().getIdRol()),alerta.getMensaje().getUsuarioRemitente().getDni(),
                                                alerta.getMensaje().getUsuarioRemitente().getNombre()),
                                                new UsuarioDto(new RolDto(alerta.getMensaje().getUsuarioDestinatario().getRol().getIdRol()),alerta.getMensaje().getUsuarioDestinatario().getDni(),
                                                alerta.getMensaje().getUsuarioDestinatario().getNombre()
                                                ), alerta.getMensaje().getTexto()));
        
        
      
        return alertaDto;
        
    }    
     
 
 private Alerta getEntitieFromDto(AlertaDto alertaDto){
        
        Alerta alerta = new Alerta();
        alerta.setIdAlerta(alertaDto.getIdAlerta());
        alerta.setFecha(alertaDto.getFecha());
        
        alerta.setMensaje(new Mensaje(new Usuario(alertaDto.getMensajeDto().getIdUsuarioRemitente().getIdUsuario(),alertaDto.getMensajeDto().getIdUsuarioRemitente().getNombre(),alertaDto.getMensajeDto().getIdUsuarioRemitente().getDni(),
                                                new Rol(alertaDto.getMensajeDto().getIdUsuarioRemitente().getRol().getIdRol())),
                                                new Usuario(alerta.getMensaje().getUsuarioDestinatario().getIdUsuario(),alerta.getMensaje().getUsuarioDestinatario().getNombre(),alerta.getMensaje().getUsuarioDestinatario().getDni(),
                                                new Rol(alerta.getMensaje().getUsuarioDestinatario().getRol().getIdRol())
                                                ), alerta.getMensaje().getTexto()));
        
        
      
        return alerta;
    }

 
 
 private void enviarAlerta(AlertaDto alerta){
     
     HttpURLConnection connection = null;
     
     Gson gson = new Gson();
     
     try{
     String mensaje = gson.toJson(alerta);
            
     URL url = new URL("https://gcm-http.googleapis.com/gcm/send");
             
     connection = (HttpURLConnection) url.openConnection();
     connection.setRequestMethod("POST");
     connection.setRequestProperty("Authorization", "Key=AIzaSyDHs3Q5SOE4fIbYYmZzo17Dk96_Ita-eyg");
     connection.setRequestProperty("Content-Type", "text/json");
         
     connection.setDoOutput(true);
     connection.setDoInput(true);
         
     connection.setDefaultUseCaches(false); 
       
     OutputStream out = connection.getOutputStream();
     DataOutputStream writer = new DataOutputStream (out);
        
     writer.writeBytes(mensaje);
     writer.flush();
     writer.close();
        
     out.close();
         
     connection.connect();
     System.out.println( connection.getResponseMessage()+ "  " + connection.getResponseCode());
         
             
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
 
 
 
    @POST   
 public void addAlerta(String alertaRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      AlertaDto alertaDto = gson.fromJson(alertaRequest, AlertaDto.class);
     
      Alerta alerta = getEntitieFromDto(alertaDto);
     
      Libroreport libroReport = libroReportDao.findById(alertaDto.getIdLibroReport());
      
      alerta.setLibroreport(libroReport);
      alertaDao.persist(alerta);
     
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
