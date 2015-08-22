/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;


/**
 *
 * @author adengra
 */
 @Path("/NotaResource")
public class NotaResource{
    
  @GET
    public String getNota(){
    
     return "Nota Resource";
}
    
    
        
        
        
    
    
    
}
