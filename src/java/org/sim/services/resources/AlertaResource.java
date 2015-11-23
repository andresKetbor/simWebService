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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.hibernate.HibernateException;
import org.sim.services.entities.Alerta;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Mensaje;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.AlertaDao;
import org.sim.services.entities.common.daos.CriticidadDao;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.MensajeDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.AlertaApiDto;
import org.sim.services.entities.dtos.AlertaDto;
import org.sim.services.entities.dtos.Data;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/AlertaResource") 
public class AlertaResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private AlertaDao alertaDao = new AlertaDao();
    
    private UsuarioDao usuarioDao = new UsuarioDao();
    private CriticidadDao criticidadDao = new CriticidadDao();
    private MensajeDao mensajeDao = new MensajeDao();
     
    
 private void enviarAlerta(AlertaDto alerta, List<String> idsRegistro){
     
     HttpURLConnection connection = null;
     
     Gson gson = new Gson();
     
     try{
       
         AlertaApiDto alertaApiDto = new AlertaApiDto();
         
        // alertaApiDto.setTo(idsRegistro.get(0));
         alertaApiDto.setData(new Data("Titulo",alerta.getMensaje()));
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
 
 
 
    @POST   
 public void addAlerta(String alertaRequest){
     
     AlertaDto alertaDto =null;
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      alertaDto = gson.fromJson(alertaRequest, AlertaDto.class);
     
      Usuario usuarioRemitente = usuarioDao.findById(alertaDto.getIdUsuario());
      
      Libroreport libroReport = libroReportDao.findById(alertaDto.getIdLibroReport());
      
      Set<Usuario> usuarios = libroReport.getPaciente().getUsuarios();
      
      Iterator<Usuario> it = usuarios.iterator();
      List<String> idsRegistro = new ArrayList<>();
      while(it.hasNext()){
          
          Usuario usuarioDestinatario = (Usuario) it.next();
           
          //filtro el mensaje al usuario que lo envia
     //     if(usuarioDestinatario.getIdUsuario()!= alertaDto.getIdUsuario()){
                                 
              Alerta alerta = new Alerta();
              alerta.setLibroreport(libroReport);
               
              Mensaje mensaje = new Mensaje(usuarioRemitente,usuarioDestinatario, alertaDto.getMensaje());
              alerta.setMensaje(mensaje);
              alerta.setFecha(new Date());
              alerta.setCriticidad(criticidadDao.findById(alertaDto.getCriticidad()));
              
              alertaDao.persist(alerta);
              mensaje.setAlerta(alerta);
              mensajeDao.persist(mensaje);
        //  }
          
              idsRegistro.add(usuarioDestinatario.getMensajeRegId());
      }
      
         enviarAlerta(alertaDto, idsRegistro);
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
      
     }catch(HibernateException | JsonSyntaxException e){
         
         if(alertaDto == null){
         
          /*alertaDto = new AlertaDto();
          alertaDto.*/
         System.out.println(e.getMessage());
         
         }
         
         
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();
     
     }
 }  
 
 
}
