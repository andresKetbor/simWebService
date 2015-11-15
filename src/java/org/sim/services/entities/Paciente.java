package org.sim.services.entities;
// Generated Aug 22, 2015 1:16:11 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Paciente generated by hbm2java
 */
@Entity
@Table(name="paciente"
    ,catalog="sim2"
    , uniqueConstraints = @UniqueConstraint(columnNames="DNI") 
)
public class Paciente  implements java.io.Serializable {


     private Integer idPaciente;
     private Integer dni;
     private String nombre;
     private String apellido;
     private Integer edad;
     private Float altura;
     private Float peso;
     private Libroreport libroreport;
     private Set<Usuario> usuarios = new HashSet<Usuario>();
     private Set<Visita> visitas = new HashSet<>();
    
     public Paciente() {
    }

	
    public Paciente(Integer idPaciente, String nombre, String apellido, Integer dni, Integer edad, Float altura, Float peso) {   
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }
    
    
        public Paciente(String nombre, String apellido, Integer dni, Integer edad, Float altura, Float peso) {   
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
    }
    
     
    public Paciente(Integer dni, String nombre, String apellido, Integer edad, Float altura, Float peso, Libroreport libroreport) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.dni = dni;
       this.edad = edad;
       this.altura = altura;
       this.peso = peso;
       this.libroreport = libroreport;
    }
   
     

    //@GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="libroreport"))@Id @GeneratedValue(generator="generator")
    @Id
    @Column(name="idPaciente", unique=true, nullable=false)
    public Integer getIdPaciente() {
        return this.idPaciente;
    }
    
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    @Column(name="DNI", unique=true, nullable=false)
    public Integer getDni() {
        return this.dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    
    @Column(name="Nombre", nullable=false, length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="Apellido", nullable=false, length=30)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    @Column(name="Edad", nullable=false)
    public Integer getEdad() {
        return this.edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    
    @Column(name="Altura", nullable=false, precision=12, scale=0)
    public Float getAltura() {
        return this.altura;
    }
    
    public void setAltura(Float altura) {
        this.altura = altura;
    }

    
    @Column(name="Peso", nullable=false, precision=12, scale=0)
    public Float getPeso() {
        return this.peso;
    }
    
    public void setPeso(Float peso) {
        this.peso = peso;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="paciente")
    public Libroreport getLibroreport() {
        return this.libroreport;
    }
    
    public void setLibroreport(Libroreport libroreport) {
        this.libroreport = libroreport;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuariopaciente", catalog = "sim2", joinColumns = { 
			@JoinColumn(name = "idPaciente", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idUsuario", 
					nullable = false, updatable = false) })
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


@OneToMany(fetch=FetchType.LAZY, mappedBy="paciente")
    public Set<Visita> getVisitas() {
        return visitas;
    }

    /**
     * @param visitas the visitas to set
     */
    public void setVisitas(Set<Visita> visitas) {
        this.visitas = visitas;
    }

}


