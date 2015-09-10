package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.sim.services.entities.Paciente;



public class LibroreportDto  implements java.io.Serializable {


     private Integer idLibroReport;
     private Date fechaAlta;
     private Date fechaBaja;
     private String estado;
     private PacienteDto paciente;
     private Set<MedicionDto> medicions = new HashSet<MedicionDto>(0);
     

    public LibroreportDto() {
    }

    
    public LibroreportDto(Integer idLibroReport,Date fechaAlta, Date fechaBaja, String estado, PacienteDto paciente  ) {
    
    this.idLibroReport=idLibroReport;
    this.fechaAlta = fechaAlta;
    this.fechaBaja = fechaBaja;
    this.estado = estado;
    this.paciente = paciente;
    
    }
	
    public LibroreportDto( Date fechaAlta, String estado) {
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }


    public Integer getIdLibroReport() {
        return this.idLibroReport;
    }
    
    public void setIdLibroReport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }
    
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    
    public Date getFechaBaja() {
        return this.fechaBaja;
    }
    
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

        
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the pacienteDto
     */
    public PacienteDto getPaciente() {
        return paciente;
    }

    /**
     * @param pacienteDto the pacienteDto to set
     */
    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the medicions
     */
    public Set<MedicionDto> getMedicions() {
        return medicions;
    }

    /**
     * @param medicions the medicions to set
     */
    public void setMedicions(Set<MedicionDto> medicions) {
        this.medicions = medicions;
    }


}


