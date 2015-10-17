package org.sim.services.entities.common.daos;


import org.sim.services.entities.Alerta;
import org.sim.services.entities.common.GenericDao;


public class AlertaDao extends GenericDao<Alerta> {

    public AlertaDao() {
        super(Alerta.class);
    }

    
    public Alerta findById(Integer id) {
        return super.findById(id);
    }

    
    
}
