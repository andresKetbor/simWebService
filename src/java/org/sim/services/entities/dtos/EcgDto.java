package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;
import org.sim.services.entities.Libroreport;



public class EcgDto extends CommonDto implements java.io.Serializable {


     private Integer idEcg;
     private LibroreportDto libroreport;
     private String estado;
     private Date fecha;
     private String captura;
     private Float ppm;

    public EcgDto() {
    }

	
    public EcgDto(LibroreportDto libroreport, String estado, Date fecha, Float ppm) {
        this.libroreport = libroreport;
        this.estado = estado;
        this.fecha = fecha;
        this.ppm = ppm;
    }
    public EcgDto(LibroreportDto libroreport, String estado, Date fecha, String captura, Float ppm) {
       this.libroreport = libroreport;
       this.estado = estado;
       this.fecha = fecha;
       this.captura = captura;
       this.ppm = ppm;
    }
   
    

    public Integer getIdEcg() {
        return this.idEcg;
    }
    
    public void setIdEcg(Integer idEcg) {
        this.idEcg = idEcg;
    }

    public LibroreportDto getLibroreport() {
        return this.libroreport;
    }
    
    public void setLibroreport(LibroreportDto libroreport) {
        this.libroreport = libroreport;
    }

    

    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
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


    public Float getPpm() {
        return this.ppm;
    }
    
    public void setPpm(Float ppm) {
        this.ppm = ppm;
    }




}


