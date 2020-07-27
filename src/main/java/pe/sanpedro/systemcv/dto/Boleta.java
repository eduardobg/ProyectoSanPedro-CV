
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
    
     public Boleta(Integer id_orden, Integer id_trabajador, String fecha_reg, double importeTotal, double montoIngresado, double vuelto) {
        this.id_orden = id_orden;
        this.id_trabajador = id_trabajador;
        this.fecha_reg = fecha_reg;
        this.montoIngresado= montoIngresado;
        this.importeTotal = importeTotal;
        this.vuelto = vuelto;
    }
     
     public Boleta(String empresa, String ruc, String propietario, String direccion, 
             String telf, String celular, String correo, Integer id_orden, String cliente, 
             String direccionCliente, String dniCliente, String fecha_reg, Integer cantidad, 
             String descripcion, double precioUnitario, double importe, double montoIngresado, 
             double importeTotal, double vuelto) {
        this.empresa = empresa;
        this.ruc = ruc;
        this.propietario = propietario;
        this.direccion = direccion;
        this.telf = telf;
        this.celular = celular;
        this.correo = correo;
        this.id_orden = id_orden;
        this.cliente = cliente;
        this.direccionCliente = direccionCliente;
        this.dniCliente = dniCliente;
        this.fecha_reg = fecha_reg;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.montoIngresado = montoIngresado;
        this.importeTotal = importeTotal;
        this.vuelto = vuelto;
    }

    public Integer getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(Integer id_boleta) {
        this.id_boleta = id_boleta;
    }

    public Integer getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Integer id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
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

    public Integer getId_orden() {
        return id_orden;
    }

    public void setId_orden(Integer id_orden) {
        this.id_orden = id_orden;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getMontoIngresado() {
        return montoIngresado;
    }

    public void setMontoIngresado(double montoIngresado) {
        this.montoIngresado = montoIngresado;
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
     
     
    
}
