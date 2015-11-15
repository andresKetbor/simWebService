package org.sim.services.entities.common.daos;


import java.util.List;
import java.util.logging.Level;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import static org.hibernate.criterion.Example.create;
import org.hibernate.criterion.Restrictions;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.Usuario;
import org.sim.services.entities.Visita;
import org.sim.services.entities.common.GenericDao;
import static org.sim.services.entities.common.GenericDao.getLog;


public class VisitaDao extends GenericDao<Visita> {

    public VisitaDao() {
        super(Visita.class);
    }

    
    public Visita findById(long id) {
        return super.findById(id);
    }

public List<Visita> findByUsuarioAndPaciente(Usuario usuario, Paciente paciente) {
        
        try {
            
            List<Visita> results = (List<Visita>) getSession().createCriteria(Visita.class).add(Restrictions.eq("paciente", paciente)).add(Restrictions.eq("usuario", usuario)).list();
       
            return results;
        } catch (RuntimeException re) {
            getLog().log(Level.SEVERE, "error al buscar visitas por usuario y paciente", re);
            throw re;
        }
    }

}
