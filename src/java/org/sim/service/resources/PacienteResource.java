/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.service.resources;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.PacienteDao;


/**
 *
 * @author adengra
 */
 @Path("/PacienteResource")
public class PacienteResource{
    
     PacienteDao pacienteDao = new PacienteDao();
     
  @POST   
 public void addPaciente(String pacienteString){
      
     Gson gson = new Gson();
     
     Paciente paciente = gson.fromJson(pacienteString, Paciente.class);
     
     pacienteDao.persist(paciente);
     
 }  
    
     
     
  @GET
    public String getPaciente(){
    
     return "Paciente Resource";
}
    
 
    
        
        
        
    
    
    
}
