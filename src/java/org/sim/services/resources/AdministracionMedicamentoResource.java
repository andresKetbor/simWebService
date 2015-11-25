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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.hibernate.HibernateException;
import org.sim.services.entities.Administracionmedicamento;
import org.sim.services.entities.Alerta;
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.Mensaje;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.AdministracionMedicamentoDao;
import org.sim.services.entities.common.daos.AlertaDao;
import org.sim.services.entities.common.daos.CriticidadDao;
import org.sim.services.entities.common.daos.LibroReportDao;
import org.sim.services.entities.common.daos.MensajeDao;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.AdministracionMedicamentoDto;
import org.sim.services.entities.dtos.AlertaApiDto;
import org.sim.services.entities.dtos.Data;
import org.sim.services.util.HibernateUtil;

/**
 *
 * @author adengra
 */

@Path("/AdministracionMedicamentoResource") 
public class AdministracionMedicamentoResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    private AdministracionMedicamentoDao  administracionMedicamentoDao = new AdministracionMedicamentoDao();
    private AlertaDao alertaDao = new AlertaDao();
    private CriticidadDao criticidadDao = new CriticidadDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    private MensajeDao mensajeDao = new MensajeDao();

private void enviarAlerta(String textoMensaje, List<String> idsRegistro){
     
     HttpURLConnection connection = null;
     
     Gson gson = new Gson();
     
     try{
       
         AlertaApiDto alertaApiDto = new AlertaApiDto();
        
         alertaApiDto.setData(new Data("Administracion de Medicamento",textoMensaje));
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
    
    
    
 private AdministracionMedicamentoDto getDtoFromEntite(Administracionmedicamento administracionmedicamento){
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        AdministracionMedicamentoDto administracionMedicamentoDto = new AdministracionMedicamentoDto();
        administracionMedicamentoDto.setIdAdminMedic(administracionmedicamento.getIdAdminMedic());
        administracionMedicamentoDto.setIdLibroReport(administracionmedicamento.getLibroreport().getIdLibroReport());
        administracionMedicamentoDto.setMedicamento(administracionmedicamento.getMedicamento());
        administracionMedicamentoDto.setVolumen(administracionmedicamento.getVolumen());
        administracionMedicamentoDto.setGoteo(administracionmedicamento.getGoteo());
        administracionMedicamentoDto.setFecha(formateador.format(administracionmedicamento.getFecha()));
      
       
        return administracionMedicamentoDto;
        
    }    
     
 
 private Administracionmedicamento getEntitieFromDto(AdministracionMedicamentoDto administracionMedicamentoDto){
        
        Administracionmedicamento administracionmedicamento = new Administracionmedicamento();
        
        administracionmedicamento.setMedicamento(administracionMedicamentoDto.getMedicamento());
        administracionmedicamento.setVolumen(administracionMedicamentoDto.getVolumen());
        administracionmedicamento.setGoteo(administracionMedicamentoDto.getGoteo());
        administracionmedicamento.setFecha(new Date(administracionMedicamentoDto.getFecha()));
        return administracionmedicamento;
        
    }

    @POST   
 public void addAdministracionMedicamento(String administracionMedicamentoRequest){
     
     AdministracionMedicamentoDto administracionMedicamentoDto = null;
     Gson gson = null;
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      gson = new Gson();
     
      administracionMedicamentoDto = gson.fromJson(administracionMedicamentoRequest, AdministracionMedicamentoDto.class);
     
      Administracionmedicamento administracionmedicamento = getEntitieFromDto(administracionMedicamentoDto);
     
      Libroreport libroreport = libroReportDao.findById(administracionMedicamentoDto.getIdLibroReport());
      
      administracionmedicamento.setLibroreport(libroreport);
      administracionMedicamentoDao.persist(administracionmedicamento);
     
      
      Usuario usuarioRemitente = usuarioDao.findById(administracionMedicamentoDto.getIdUsuario());
      Set<Usuario> usuarios = libroreport.getPaciente().getUsuarios();
      
      
      String textoMensaje = "Medicamento: " + administracionmedicamento.getMedicamento() + 
                            "Valumen: " + administracionmedicamento.getVolumen() + 
                            "Goteo: " + administracionmedicamento.getGoteo() + 
                            "Fecha: "  + administracionmedicamento.getFecha();
      
      Iterator<Usuario> it = usuarios.iterator();
      List<String> idsRegistro = new ArrayList<>();
      
      while(it.hasNext()){
          
          Usuario usuarioDestinatario = (Usuario) it.next();
           
          //filtro el mensaje al usuario que lo envia
     //     if(usuarioDestinatario.getIdUsuario()!= alertaDto.getIdUsuario()){
                                 
              Alerta alerta = new Alerta();
              alerta.setLibroreport(libroreport);
               
              Mensaje mensaje = new Mensaje(usuarioRemitente,usuarioDestinatario, textoMensaje);
              alerta.setMensaje(mensaje);
              alerta.setFecha(new Date());
              alerta.setCriticidad(criticidadDao.findById(2));
              
              alertaDao.persist(alerta);
              mensaje.setAlerta(alerta);
              mensajeDao.persist(mensaje);
        //  }
          
              idsRegistro.add(usuarioDestinatario.getMensajeRegId());
      }
      
         enviarAlerta(textoMensaje, idsRegistro);
        
      
      HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
      
      administracionMedicamentoDto.setError("OK");
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
                if(administracionMedicamentoDto == null){
              administracionMedicamentoDto = new AdministracionMedicamentoDto();  
            }
         
         administracionMedicamentoDto.setError("Error al agregar Administracion de medicamento : " + e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(administracionMedicamentoDto == null){
              administracionMedicamentoDto = new AdministracionMedicamentoDto();  
            }
         
         administracionMedicamentoDto.setError("Error al agregar Administracion de medicamento : " + e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();
     }
 } 
 
}
