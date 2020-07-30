
package pe.sanpedro.systemcv.model;

/**
 *
 * @author Mysk
 */
public class DetallePedido {
    private Integer id_orden;
    private Integer id_pro;
    private Integer cantidad;
    private String descripcion;
    private double precio;
    private double subtotal;
    
    public void DetallePedido(){}
    //Para crear Servicios
    public DetallePedido(Integer id_orden, Integer id_pro, Integer cantidad, String descripcion, double precio, double subtotal) {
        this.id_orden = id_orden;
        this.id_pro = id_pro;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.subtotal=subtotal;
    }
    

    public Integer getId_orden() {
        return id_orden;
    }

    public void setId_orden(Integer id_orden) {
        this.id_orden = id_orden;
    }

    public Integer getId_pro() {
        return id_pro;
    }

    public void setId_pro(Integer id_pro) {
        this.id_pro = id_pro;
    }


    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
        
}
