/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sim.services.entities.common.daos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sim.services.entities.GenericDaoTest;
import org.sim.services.entities.Paciente;
import org.sim.services.entities.common.GenericDao;

/**
 *
 * @author Andres
 */
public class PacienteDaoTest extends GenericDaoTest<Paciente>{
    
    private static final PacienteDao pacienteDao = new PacienteDao(); 
    
    public PacienteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    
    public void testPersist(){
        
       Paciente paciente = new Paciente();
       
       paciente.setDni(26520555);
       paciente.setAltura(1.82f);
       paciente.setEdad(37);
       paciente.setNombre("Andres");
       paciente.setApellido("Dengra");
       paciente.setPeso(88);
       
       pacienteDao.persist(paciente);
       
       
        
    }

    @Override
    protected GenericDao<Paciente> getHome() {
        
        return pacienteDao;
    }
    
    
}
