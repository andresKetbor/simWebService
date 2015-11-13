package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.sim.services.entities.Paciente;



public class LibroreportDto extends CommonDto implements java.io.Serializable {


     private Integer idLibroReport;
     private String fechaAlta;
     private String fechaBaja;
     private String estado;
     private PacienteDto paciente;
     private Set<MedicionDto> medicions = new HashSet<MedicionDto>(0);
     

    public LibroreportDto() {
    }

    
    public LibroreportDto(Integer idLibroReport,String fechaAlta, String fechaBaja, String estado, PacienteDto paciente  ) {
    
    this.idLibroReport=idLibroReport;
    this.fechaAlta = fechaAlta;
    this.fechaBaja = fechaBaja;
    this.estado = estado;
    this.paciente = paciente;
    
    }
	
    public LibroreportDto( String fechaAlta, String estado) {
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }


    public Integer getIdLibroReport() {
        return this.idLibroReport;
    }
    
    public void setIdLibroReport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }
    
    public String getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    
    public String getFechaBaja() {
        return this.fechaBaja;
    }
    
    public void setFechaBaja(String fechaBaja) {
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


