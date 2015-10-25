package org.sim.services.entities.common.daos;


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.hibernate.criterion.Restrictions;
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
            
            List<Usuario> usuarios  = (List<Usuario>)  getSession().createCriteria(Usuario.class).createCriteria("pacientes").add(Restrictions.ne("idPaciente", paciente.getIdPaciente())).list();
           
            return usuarios;
        } catch (RuntimeException re) {
            getLog().log(Level.SEVERE, "get failed", re);
            throw re;
        }
    
    }
   
   
    
}
