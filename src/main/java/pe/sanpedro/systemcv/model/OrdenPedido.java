package pe.sanpedro.systemcv.model;

import java.time.LocalDate;

/**
 *
 * @author Mysk
 */
public class OrdenPedido {

    private Integer id_orden;
    private Integer id_cliente;
    private Integer id_tipoP;
    private String dni;
    private String nombreCli;
    private String direcCli;
    private Integer cantidad;
    private Integer id_estado;
    private String nombEstado;
    private Integer id_med;
    private String descrip;
    private double precio;
    private double subtotal;
    private LocalDate fecha;

    public OrdenPedido() {
    }

    public OrdenPedido(Integer id_cliente, Integer id_tipoP, String dni, String nombreCli, String direcCli, Integer cantidad, Integer id_estado) {
        this.id_cliente = id_cliente;
        this.id_tipoP = id_tipoP;
        this.dni = dni;
        this.nombreCli = nombreCli;
        this.direcCli = direcCli;
        this.cantidad = cantidad;
        this.id_estado = id_estado;
    }


    public Integer getId_orden() {
        return id_orden;
    }

    public void setId_orden(Integer id_orden) {
        this.id_orden = id_orden;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_tipoP() {
        return id_tipoP;
    }

    public void setId_tipoP(Integer id_tipoP) {
        this.id_tipoP = id_tipoP;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getDirecCli() {
        return direcCli;
    }

    public void setDirecCli(String direcCli) {
        this.direcCli = direcCli;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombEstado() {
        return nombEstado;
    }

    public void setNombEstado(String nombEstado) {
        this.nombEstado = nombEstado;
    }

    public Integer getId_med() {
        return id_med;
    }

    public void setId_med(Integer id_med) {
        this.id_med = id_med;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
