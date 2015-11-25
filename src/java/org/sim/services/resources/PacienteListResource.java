/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.hibernate.HibernateException;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.entities.dtos.PacienteDto;
import org.sim.services.entities.dtos.PacienteListDto;
import org.sim.services.util.HibernateUtil;


/**
 *
 * @author adengra
 */
 @Path("/PacienteResource/list")
public class PacienteListResource{
    
PacienteDao pacienteDao = new PacienteDao();
     
 private PacienteListDto getDtoFromEntite(List<Paciente> pacientes){
           
     PacienteListDto pacienteListDto = new PacienteListDto();
     List<PacienteDto> pacientesDto = new ArrayList();
     Iterator<Paciente> it = pacientes.iterator();
     
     while(it.hasNext()){
     
        Paciente paciente = (Paciente) it.next();
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setIdPaciente(paciente.getIdPaciente());
        pacienteDto.setDni(paciente.getDni());
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setAltura(paciente.getAltura());
        paciente.setPeso(paciente.getPeso());
        paciente.setEdad(paciente.getEdad());
        
        pacientesDto.add(pacienteDto);
     }
        
     pacienteListDto.setPacientesDto(pacientesDto);
     return pacienteListDto;
        
    }    
   
 
  @GET
  public String getPaciente(){
    
     Gson gson = null;
     PacienteListDto pacienteListDto = null;   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     gson = new Gson();
     
     List<Paciente> pacientes = pacienteDao.findAll();
     
     pacienteListDto = getDtoFromEntite(pacientes);
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     pacienteListDto.setError("OK");
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
         if(pacienteListDto == null){
              pacienteListDto = new PacienteListDto();  
            }
         
         pacienteListDto.setError("Error al consultar Paciente : " + e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
         if(pacienteListDto == null){
              pacienteListDto = new PacienteListDto();  
            }
         
         pacienteListDto.setError("Error al consultar Paciente : " + e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().close();                       
         return gson.toJson(pacienteListDto);
     }
     
    }
     
        
        
        
    
    
    
}
