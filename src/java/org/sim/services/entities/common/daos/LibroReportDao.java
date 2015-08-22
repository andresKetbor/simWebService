package org.sim.services.entities.common.daos;


import org.sim.services.entities.Libroreport;
import org.sim.services.entities.common.GenericDao;


public class LibroReportDao extends GenericDao<Libroreport> {

    public LibroReportDao() {
        super(Libroreport.class);
    }

    
    public Libroreport findById(long id) {
        return super.findById(id);
    }

   //que te haces el que trabaja!!!!!!!!!!!!!!!! 
    
}
