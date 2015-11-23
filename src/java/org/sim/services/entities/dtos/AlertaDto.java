package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1

import java.util.Date;



public class AlertaDto extends CommonDto implements java.io.Serializable {


     private Integer idLibroReport;
     private Integer idUsuario ;
     private Integer criticidad;
     private Date fecha;
     private String mensaje;
     
     

    public AlertaDto() {
    }

	
    public AlertaDto( Integer criticidad, Date fecha) {
        this.criticidad = criticidad;
        this.fecha = fecha;
    }
     
    
    public Integer getCriticidad() {
        return this.criticidad;
    }
    
    public void setCriticidad(Integer criticidad) {
        this.criticidad = criticidad;
    }

    
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


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
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
