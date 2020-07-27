
package pe.sanpedro.systemcv.model;

import java.time.LocalDate;

/**
 *
 * @author Mysk
 */
public class Trabajadores {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String celular;
    private String correo;
    private LocalDate fecha_nac;
    private LocalDate fecha_reg;
    private Integer tipo;
    private String cargo;//new
    
    public Trabajadores(){}
   
    public Trabajadores(Integer id,String dni, String nombre, String apellidop, String apellidom, String celular, String correo, LocalDate fecha_nac,  Integer tipo) {
        this.id=id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.celular = celular;
        this.correo = correo;
        this.fecha_nac = fecha_nac;  
        this.tipo = tipo;    
    }
    public Trabajadores(String dni, String nombre, String apellidop, String apellidom, String celular, String correo, LocalDate fecha_nac, LocalDate fecha_reg, Integer tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.celular = celular;
        this.correo = correo;
        this.fecha_nac = fecha_nac;
        this.fecha_reg = fecha_reg;
        this.tipo = tipo;
    }
    

    
    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public LocalDate getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(LocalDate fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
