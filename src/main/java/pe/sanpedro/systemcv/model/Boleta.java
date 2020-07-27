
package pe.sanpedro.systemcv.model;

import java.time.LocalDate;

/**
 *
 * @author Mysk
 */
public class Boleta {
    private Integer id_boleta;
    private Integer id_ordenPedido;
    private Integer id_trabajador;
    private LocalDate fecha_reg;
    private double importeTotal;
    private double montoIngresado;
    private double vuelto;
    
    public void Boleta(){
        
    }

    public Boleta(Integer id_ordenPedido, Integer id_trabajador, LocalDate fecha_reg, double importeTotal, double montoIngresado, double vuelto) {
        this.id_ordenPedido = id_ordenPedido;
        this.id_trabajador = id_trabajador;
        this.fecha_reg = fecha_reg;
        this.montoIngresado= montoIngresado;
        this.importeTotal = importeTotal;
        this.vuelto = vuelto;
    }
    

    public Integer getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(Integer id_boleta) {
        this.id_boleta = id_boleta;
    }

    public Integer getId_ordenPedido() {
        return id_ordenPedido;
    }

    public void setId_ordenPedido(Integer id_ordenPedido) {
        this.id_ordenPedido = id_ordenPedido;
    }

    public Integer getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Integer id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public LocalDate getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(LocalDate fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public double getMontoIngresado() {
        return montoIngresado;
    }

    public void setMontoIngresado(double montoIngresado) {
        this.montoIngresado = montoIngresado;
    }
    
}
