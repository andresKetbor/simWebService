package org.sim.services.entities.common.daos;


import org.sim.services.entities.Administracionmedicamento;
import org.sim.services.entities.common.GenericDao;


public class AdministracionMedicamentoDao extends GenericDao<Administracionmedicamento> {

    public AdministracionMedicamentoDao() {
        super(Administracionmedicamento.class);
    }

    
    public Administracionmedicamento findById(Integer id) {
        return super.findById(id);
    }

    
    
}
