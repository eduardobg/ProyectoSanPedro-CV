
package pe.sanpedro.systemcv.dto;



/**
 *
 * @author Mysk
 */
public class Boleta {
    private Integer id_boleta;    
    private Integer id_trabajador;
    //Primera parte de boleta
    private String empresa;
    private String ruc;
    private String propietario;
    private String direccion;
    private String telf;
    private String celular;
    private String correo;
    private Integer id_orden;
    //-------------------------------
    //Cabecera
    private String cliente;
    private String direccionCliente;
    private String dniCliente;
    private String fecha_reg;
    //-----------------------------
    //Detalle de Pedido
    private Integer cantidad;
    private String descripcion;
    private double precioUnitario;
    private double importe;
    //-------------------------------
    //Pie de Boleta
    private double montoIngresado;
    private double importeTotal;
    private double vuelto;
    //-------------------------------
    
    
    
    
}
