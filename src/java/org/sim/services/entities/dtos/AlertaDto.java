package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;



public class AlertaDto implements java.io.Serializable {


     private Integer idAlerta;
     private LibroreportDto libroreportDto;
     private String criticidad;
     private Date fecha;
     private MensajeDto mensajeDto;

    public AlertaDto() {
    }

	
    public AlertaDto(LibroreportDto libroreportDto, String criticidad, Date fecha) {
        this.libroreportDto = libroreportDto;
        this.criticidad = criticidad;
        this.fecha = fecha;
    }
    public AlertaDto(LibroreportDto libroreportDto, String criticidad, Date fecha, MensajeDto mensajeDto) {
       this.libroreportDto = libroreportDto;
       this.criticidad = criticidad;
       this.fecha = fecha;
       this.mensajeDto = mensajeDto;
    }
   
     
    public Integer getIdAlerta() {
        return this.idAlerta;
    }
    
    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public LibroreportDto getLibroreport() {
        return this.libroreportDto;
    }
    
    public void setLibroreportDto(LibroreportDto libroreportDto) {
        this.libroreportDto = libroreportDto;
    }

    
    public String getCriticidad() {
        return this.criticidad;
    }
    
    public void setCriticidad(String criticidad) {
        this.criticidad = criticidad;
    }

    
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public MensajeDto getMensajeDto() {
        return this.mensajeDto;
    }
    
    public void setMensajeDto(MensajeDto mensajeDto) {
        this.mensajeDto = mensajeDto;
    }




}


