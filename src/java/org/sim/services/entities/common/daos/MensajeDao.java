package org.sim.services.entities.common.daos;


import org.sim.services.entities.Mensaje;
import org.sim.services.entities.common.GenericDao;


public class MensajeDao extends GenericDao<Mensaje> {

    public MensajeDao() {
        super(Mensaje.class);
    }

    
    public Mensaje findById(Integer id) {
        return super.findById(id);
    }

    
    
}
