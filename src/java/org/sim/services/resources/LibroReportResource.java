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
import org.sim.services.entities.Libroreport;
import org.sim.services.entities.common.daos.LibroReportDao;

/**
 *
 * @author adengra
 */

@Path("/LibroReportResource") 
public class LibroReportResource {
    
    private  LibroReportDao libroReportDao = new LibroReportDao(); 
    
    
 @POST   
 public void addLibroReport(String libroReportString){
      
     Gson gson = new Gson();
     
     Libroreport libroReport = gson.fromJson(libroReportString, Libroreport.class);
     
     libroReportDao.persist(libroReport);
     
 }  
 
 
 @GET
 public String getLibroReport(){
     
     
     return "libro report";
     
 }
 
 
 
    
}
