/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Valoresfrecuenciarespiratoria;
import org.sim.services.entities.Valorestemperatura;
import org.sim.services.entities.common.daos.ValoresfrecuenciarespiratoriaDao;
import org.sim.services.util.HibernateUtil;
import org.sim.services.entities.dtos.ValoresMedicionDto;
/**
 *
 * @author adengra
 */
 @Path("/ValoresFrecuenciaRespiratoriaResource")
public class ValoresFrecuenciaRespiratoria{
     
     
  private  ValoresfrecuenciarespiratoriaDao valoresfrecuenciarespiratoriaDao = new ValoresfrecuenciarespiratoriaDao();  
    
  
  private ValoresMedicionDto getDtoFromEntite(Valoresfrecuenciarespiratoria valoresEnti){
        
        ValoresMedicionDto valoresDto = new ValoresMedicionDto();
        valoresDto.setValorMaximo(valoresEnti.getValorMaximo());
        valoresDto.setValorMinimo(valoresEnti.getValorMinimo());
   
        
        return valoresDto;
  }
  
    
     @GET
    public String getValoresFrecuenciaRespiratoria(@QueryParam("edad") int edad) {

        String valoresResponse = "";
        try {

            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            Gson gson = new Gson();

            Valoresfrecuenciarespiratoria valoresfrecuenciarespiratoria = valoresfrecuenciarespiratoriaDao.findByEdad(edad);

            valoresResponse = gson.toJson(getDtoFromEntite(valoresfrecuenciarespiratoria));

            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

        } catch (HibernateException | JsonSyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            HibernateUtil.getSessionFactory().getCurrentSession().close();
            return valoresResponse;
        }

    }
}
   
