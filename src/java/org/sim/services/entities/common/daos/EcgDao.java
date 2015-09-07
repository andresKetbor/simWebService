package org.sim.services.entities.common.daos;


import org.sim.services.entities.Ecg;
import org.sim.services.entities.common.GenericDao;


public class EcgDao extends GenericDao<Ecg> {

    public EcgDao() {
        super(Ecg.class);
    }

    
    public Ecg findById(Integer id) {
        return super.findById(id);
    }

    
    
}
