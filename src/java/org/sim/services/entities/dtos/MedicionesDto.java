package org.sim.services.entities.dtos;
// Generated Oct 12, 2015 9:23:05 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
// Generated Oct 12, 2015 9:23:05 PM by Hibernate Tools 4.3.1



/**
 * Valorestemperatura generated by hbm2java
 */
public class MedicionesDto extends CommonDto implements java.io.Serializable {

     private Integer idLibroReport;
     private Set<MedicionDto> mediciones = new HashSet<MedicionDto>(); 

    /**
     * @return the idLibroReport
     */
    public Integer getIdLibroReport() {
        return idLibroReport;
    }

    /**
     * @param idLibroReport the idLibroReport to set
     */
    public void setIdLibroReport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }

    /**
     * @return the mediciones
     */
    public Set<MedicionDto> getMediciones() {
        return mediciones;
    }

    /**
     * @param mediciones the mediciones to set
     */
    public void setMediciones(Set<MedicionDto> mediciones) {
        this.mediciones = mediciones;
    }
}


