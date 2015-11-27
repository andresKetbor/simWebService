package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;



public class EcgDto  implements java.io.Serializable {


     private Integer idEcg;
     private Integer idLibroReport;
     private String diagnostico;
     private String diagnosticoDetallado;
     private Date fecha;
     private String captura;
     private Float ppmMax;
     private Float ppmMin;
     private Float ppmProm;

    public EcgDto() {
    }

	
    public EcgDto(Integer idLibroreport, String diagnostico,String diagnosticoDettalado, Date fecha, Float ppmMax, Float ppmMin, Float ppmProm) {
        this.idLibroReport = idLibroreport;
        this.diagnostico = diagnostico;
        this.diagnosticoDetallado = diagnosticoDettalado;
        this.fecha = fecha;
        this.ppmMax = ppmMax;
        this.ppmMin = ppmMin;
        this.ppmProm = ppmProm;
    }
    
    
   
    public Integer getIdEcg() {
        return this.idEcg;
    }
    
    public void setIdEcg(Integer idEcg) {
        this.idEcg = idEcg;
    }


    public Integer getIdLibroreport() {
        return this.idLibroReport;
    }
    
    public void setLibroreport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }

    

     public String getDiagnostico() {
        return this.diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    

    public String getDiagnosticoDetallado() {
        return this.diagnosticoDetallado;
    }
    
    public void setDiagnosticoDetallado(String diagnosticoDetallado) {
        this.diagnosticoDetallado = diagnosticoDetallado;
    }


    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
   public String getCaptura() {
        return this.captura;
    }
    
    public void setCaptura(String captura) {
        this.captura = captura;
    }
    
    

    public Float getPpmMax() {
        return this.ppmMax;
    }
    
    public void setPpmMax(Float ppmMax) {
        this.ppmMax = ppmMax;
    }

    public Float getPpmMin() {
        return this.ppmMin;
    }
    
    public void setPpmMin(Float ppmMin) {
        this.ppmMin = ppmMin;
    }

    
    public Float getPpmProm() {
        return this.ppmProm;
    }
    
    public void setPpmProm(Float ppmProm) {
        this.ppmProm = ppmProm;
    }



}


