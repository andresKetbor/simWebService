package org.sim.services.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sim.services.entities.common.GenericDao;

/**
 * @author darrighi 
 * Clase Generica para crear los test
 * 
 */
public abstract class GenericDaoTest<T> {

    protected static Logger log;
   
    public GenericDaoTest() {
    }
 

    @Before
    public void before() throws Exception {
        GenericDaoTest.log = Logger.getLogger(this.getClass().getName());
        getHome().getSessionFactory().getCurrentSession().beginTransaction();
    }

    @After
    public void after() throws Exception {
        getHome().getSessionFactory().getCurrentSession().getTransaction().commit();
    }

    
    protected abstract GenericDao<T> getHome();

    
}
