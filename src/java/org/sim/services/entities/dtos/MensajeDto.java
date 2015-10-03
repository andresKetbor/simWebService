package org.sim.services.entities.dtos;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import org.sim.services.entities.*;


public class MensajeDto  implements java.io.Serializable {


     private Integer idMensaje;
     private Alerta alerta;
     private UsuarioDto usuarioRemitente;
     private UsuarioDto usuaruiDestinatario;
     private String texto;

    public MensajeDto() {
    }

    public MensajeDto(Alerta alerta, UsuarioDto usuarioRemitente, UsuarioDto usuaruiDestinatario, String texto) {
       this.alerta = alerta;
       this.usuarioRemitente = usuarioRemitente;
       this.usuaruiDestinatario = usuaruiDestinatario;
       this.texto = texto;
    }
   

    public Integer getIdMensaje() {
        return this.idMensaje;
    }
    
    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }


    public Alerta getAlerta() {
        return this.alerta;
    }
    
    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }


    public UsuarioDto getIdUsuarioRemitente() {
        return this.usuarioRemitente;
    }
    
    public void setIdUsuarioRemitente(UsuarioDto usuarioRemitente) {
        this.usuarioRemitente = usuarioRemitente;
    }


    public UsuarioDto UsuaruiDestinatario() {
        return this.usuaruiDestinatario;
    }
    
    public void setidUsuaruiDestinatario(UsuarioDto usuaruiDestinatario) {
        this.usuaruiDestinatario = usuaruiDestinatario;
    }

    
    public String getTexto() {
        return this.texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }




}


