package org.sim.services.entities.common.daos;


import java.util.Set;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.GenericDao;


public class UsuarioDao extends GenericDao<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    

    public Usuario findById(Integer id) {
        return super.findById(id);
    }

    
   public Set<Usuario> findByListIds(Set<Integer> ids){
       return super.findListByListId(ids);
   }

  
    
}
