/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.daos.PacienteDao;
import org.sim.services.util.HibernateUtil;


/**
 *
 * @author adengra
 */
 @Path("/PacienteResource")
public class PacienteResource{
    
     PacienteDao pacienteDao = new PacienteDao();
     
  //@POST   
 public Integer addPaciente(String pacienteString){
     
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Paciente paciente = gson.fromJson(pacienteString, Paciente.class);
     
     pacienteDao.persist(paciente);
     
HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
return 2;
     
 }  
    
     
     
  @GET
    public String getPaciente(@QueryParam ("id") int id){
    
     return $getPaciente(id);
}
    
 
   
    private String $getPaciente(int id){
        
        
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Paciente paciente = pacienteDao.findById(id);
     
     /*Paciente paciente = new Paciente();
     
      
       paciente.setDni(26520555);
       paciente.setAltura(1.82f);
       paciente.setEdad(37);
       paciente.setNombre("Andres");
       paciente.setApellido("Dengra");
       paciente.setPeso(88);     */
      String pacienteString = gson.toJson(paciente);
     
     //HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     return pacienteString;
        
        
    }
    
        
        
        
    
    
    
}
