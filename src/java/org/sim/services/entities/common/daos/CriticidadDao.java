package org.sim.services.entities.common.daos;


import org.sim.services.entities.Criticidad;
import org.sim.services.entities.common.GenericDao;


public class CriticidadDao extends GenericDao<Criticidad> {

    public CriticidadDao() {
        super(Criticidad.class);
    }

    
    public Criticidad findById(Integer id) {
        return super.findById(id);
    }

    
    
}
