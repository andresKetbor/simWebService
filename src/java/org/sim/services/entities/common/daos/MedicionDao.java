package org.sim.services.entities.common.daos;


import org.sim.services.entities.Medicion;
import org.sim.services.entities.common.GenericDao;


public class MedicionDao extends GenericDao<Medicion> {

    public MedicionDao() {
        super(Medicion.class);
    }

    
    public Medicion findById(Integer id) {
        return super.findById(id);
    }

    
    
}
