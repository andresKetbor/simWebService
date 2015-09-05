/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sim.services.resources;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.hibernate.HibernateException;
import org.sim.services.entities.Rol;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.daos.UsuarioDao;
import org.sim.services.entities.dtos.RolDto;
import org.sim.services.entities.dtos.UsuarioDto;
import org.sim.services.util.HibernateUtil;


/**
 *
 * @author adengra
 */
 @Path("/UsuarioResource")
public class UsuarioResource{
     
     UsuarioDao usuarioDao = new UsuarioDao();
     
     
     
 private UsuarioDto getDtoFromEntite(Usuario usuario){
        
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setDni(usuario.getDni());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setUsuario(usuario.getUsuario());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRol(new RolDto(usuario.getRol().getIdRol()));
        
        return usuarioDto;
        
    }    
     
 
 private Usuario getEntitieFromDto(UsuarioDto usuarioDto){
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioDto.getIdUsuario());
        usuario.setDni(usuarioDto.getDni());
        usuario.setIdUsuario(usuarioDto.getIdUsuario());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setUsuario(usuarioDto.getUsuario());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(new Rol(usuarioDto.getRol().getIdRol()));
        
        return usuario;
        
    }
 
 
 
 
    
    @POST   
 public void addUsuario(String usuarioRequest){
     
     try{
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      UsuarioDto usuarioDto = gson.fromJson(usuarioRequest, UsuarioDto.class);
     
      usuarioDao.persist(getEntitieFromDto(usuarioDto));
     
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     }
 }  
 
 
 
 @DELETE  
 public void deleteUsuario(@QueryParam ("id") int id){
     
      HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Usuario usuario = usuarioDao.findById(id);
     
      usuarioDao.delete(usuario);
     
     HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     
 }     
 
 
 @PUT  
 public void updateUsuario(String usuarioRequest){
     
     try{ 
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
      Gson gson = new Gson();
     
      UsuarioDto usuarioDto = gson.fromJson(usuarioRequest, UsuarioDto.class);
     
     usuarioDao.merge(getEntitieFromDto(usuarioDto));
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
     }
     
 }
 
 
  @GET
    public String getUsuario(@QueryParam ("id") int id){
    
     String usuarioResponse ="";   
     try{   
        
     HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
     
     Gson gson = new Gson();
     
     Usuario usuario = usuarioDao.findById(id);
     
     usuarioResponse = gson.toJson(getDtoFromEntite(usuario));
     
     }catch(HibernateException | JsonSyntaxException e){
         System.out.println(e.getMessage());
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     finally{
         HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();     
         return usuarioResponse;
     }
    

    
    }
    
 

    
    
        
        
        
    
    
    
}
