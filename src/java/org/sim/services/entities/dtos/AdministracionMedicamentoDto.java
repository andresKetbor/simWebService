package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


public class AdministracionMedicamentoDto extends CommonDto implements java.io.Serializable {


     private Integer idAdminMedic;
     private Integer idLibroReport;
     private Integer idUsuario;
     private String fecha;
     private Float volumen;
     private String medicamento;
     private Float goteo;

    public AdministracionMedicamentoDto() {
    }

    public AdministracionMedicamentoDto(Integer idLibroReport, String fecha, Float volumen, String medicamento, Float goteo) {
       this.idLibroReport = idLibroReport;
       this.fecha = fecha;
       this.volumen = volumen;
       this.medicamento = medicamento;
       this.goteo = goteo;
    }
   
    public Integer getIdAdminMedic() {
        return this.idAdminMedic;
    }
    
    public void setIdAdminMedic(Integer idAdminMedic) {
        this.idAdminMedic = idAdminMedic;
    }


    public Integer getIdLibroReport() {
        return this.idLibroReport;
    }
    
    public void setIdLibroReport(Integer idLibroReport) {
        this.idLibroReport = idLibroReport;
    }

    public String getFecha() {
        return this.fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    

    public Float getVolumen() {
        return this.volumen;
    }
    
    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    

    public String getMedicamento() {
        return this.medicamento;
    }
    
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    

    public Float getGoteo() {
        return this.goteo;
    }
    
    public void setGoteo(Float goteo) {
        this.goteo = goteo;
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

}