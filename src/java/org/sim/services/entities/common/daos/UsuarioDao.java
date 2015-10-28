package org.sim.services.entities.common.daos;

import java.util.ArrayList;
import org.hibernate.Query;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.common.GenericDao;
import static org.sim.services.entities.common.GenericDao.getLog;


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

  
   public List<Usuario>  findByNotPaciente(Paciente paciente ) {
        getLog().info("getting instance of " + this.getPersistentClass().getName() + " with id: ");
        try {
            
             Query query = getSession().createQuery("Select u from Usuario as u where u.idUsuario not in ( select us.idUsuario from Usuario as us join us.pacientes as pa where pa.idPaciente =" + paciente.getIdPaciente() + ")");
            
             List<Usuario> usuarios = (List<Usuario>) query.list();
             
             return usuarios;
        
        } catch (RuntimeException re) {
            getLog().log(Level.SEVERE, "get failed", re);
            throw re;
        }
    
    }
   
   
    
}
