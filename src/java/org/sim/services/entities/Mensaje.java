package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Mensaje generated by hbm2java
 */
@Entity
@Table(name="mensaje"
    ,catalog="sim2"
)
public class Mensaje  implements java.io.Serializable {


     private Integer idMensaje;
     private Alerta alerta;
     private Usuario usuarioRemitente;
     private Usuario usuarioDestinatario;
     private String texto;

    public Mensaje() {
    }

    public Mensaje(Alerta alerta, Usuario usuarioRemitente, Usuario usuarioDestinatario, String texto) {
       this.alerta = alerta;
       this.usuarioRemitente = usuarioRemitente;
       this.usuarioDestinatario = usuarioDestinatario;
       this.texto = texto;
    }
   
    public Mensaje(Usuario usuarioRemitente, Usuario usuarioDestinatario, String texto) {
       this.usuarioRemitente = usuarioRemitente;
       this.usuarioDestinatario = usuarioDestinatario;
       this.texto = texto;
    }
    
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="alerta"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="idMensaje", unique=true, nullable=false)
    public Integer getIdMensaje() {
        return this.idMensaje;
    }
    
    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Alerta getAlerta() {
        return this.alerta;
    }
    
    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUsuarioRemitente", nullable=false)
    public Usuario getUsuarioRemitente() {
        return this.usuarioRemitente;
    }
    
    public void setUsuarioRemitente(Usuario usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idUsuaruiDestinatario", nullable=false)
    public Usuario getUsuarioDestinatario() {
        return this.usuarioDestinatario;
    }
    
    public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    
    @Column(name="Texto", nullable=false, length=500)
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }




}


