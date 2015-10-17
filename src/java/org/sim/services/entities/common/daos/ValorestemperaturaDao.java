package org.sim.services.entities.common.daos;


import java.io.Serializable;
import java.util.logging.Level;
import org.hibernate.criterion.Restrictions;
import org.sim.services.entities.Valorestemperatura;
import org.sim.services.entities.common.GenericDao;
import static org.sim.services.entities.common.GenericDao.getLog;


public class ValorestemperaturaDao extends GenericDao<Valorestemperatura> {

    public ValorestemperaturaDao() {
        super(Valorestemperatura.class);
    }

    
    }