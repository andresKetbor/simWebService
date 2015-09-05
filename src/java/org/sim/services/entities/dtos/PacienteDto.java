package org.sim.services.entities.dtos;
// Generated 06/08/2015 15:00:36 by Hibernate Tools 3.6.0

import org.sim.services.entities.Libroreport;


/**
 * Paciente generated by hbm2java
 */


public class PacienteDto  implements java.io.Serializable {


     private Integer idPaciente;
     private Integer dni;
     private String nombre;
     private String apellido;
     private Integer edad;
     private Float altura;
     private Float peso;
     private Libroreport libroreport;
   

    public PacienteDto() {
    }

	
    public PacienteDto(Integer idPaceinte,String nombre, String apellido, Integer dni,  Integer edad, Float altura, Float peso) {
        this.idPaciente = idPaceinte;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }
    
   
  
    public Integer getDni() {
        return this.dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    

    public Integer getEdad() {
        return this.edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    

    public Float getAltura() {
        return this.altura;
    }
    
    public void setAltura(Float altura) {
        this.altura = altura;
    }

    

    public Float getPeso() {
        return this.peso;
    }
    
    public void setPeso(Float peso) {
        this.peso = peso;
    }

    /**
     * @return the idPaciente
     */
    public Integer getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the libroreport
     */
    public Libroreport getLibroreport() {
        return libroreport;
    }

    /**
     * @param libroreport the libroreport to set
     */
    public void setLibroreport(Libroreport libroreport) {
        this.libroreport = libroreport;
    }



}


