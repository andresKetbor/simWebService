package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,catalog="sim2"
)
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private Rol rol;
     private int dni;
     private String nombre;
     private String usuario;
     private String password;
     private Set<Mensaje> mensajesForIdUsuarioRemitente = new HashSet<Mensaje>(0);
     private Set<Mensaje> mensajesForIdUsuaruiDestinatario = new HashSet<Mensaje>(0);

    public Usuario() {
    }

	
    public Usuario(Rol rol, int dni, String nombre) {
        this.rol = rol;
        this.dni = dni;
        this.nombre = nombre;
    }
    public Usuario(Rol rol, int dni, String nombre, Set<Mensaje> mensajesForIdUsuarioRemitente, Set<Mensaje> mensajesForIdUsuaruiDestinatario) {
       this.rol = rol;
       this.dni = dni;
       this.nombre = nombre;
       this.mensajesForIdUsuarioRemitente = mensajesForIdUsuarioRemitente;
       this.mensajesForIdUsuaruiDestinatario = mensajesForIdUsuaruiDestinatario;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idUsuario", unique=true, nullable=false)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idRol", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="DNI", nullable=false)
    public int getDni() {
        return this.dni;
    }
    
    public void setDni(int dni) {
        this.dni = dni;
    }

    
    @Column(name="Nombre", nullable=false, length=25)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name="Usuario", nullable=false, length=25)
    public String getUsuario() {
        return usuario;
    }

    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Column(name="Password", nullable=false, length=25)
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioRemitente")
    public Set<Mensaje> getMensajesForIdUsuarioRemitente() {
        return this.mensajesForIdUsuarioRemitente;
    }
    
    public void setMensajesForIdUsuarioRemitente(Set<Mensaje> mensajesForIdUsuarioRemitente) {
        this.mensajesForIdUsuarioRemitente = mensajesForIdUsuarioRemitente;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarioDestinatario")
    public Set<Mensaje> getMensajesForIdUsuaruiDestinatario() {
        return this.mensajesForIdUsuaruiDestinatario;
    }
    
    public void setMensajesForIdUsuaruiDestinatario(Set<Mensaje> mensajesForIdUsuaruiDestinatario) {
        this.mensajesForIdUsuaruiDestinatario = mensajesForIdUsuaruiDestinatario;
    }




}


