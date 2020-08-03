
package pe.sanpedro.systemcv.model;

/**
 *
 * @author Mysk
 */
public class Servicios_1 {
    private Integer id_servicios;
    private String nombreS;
    private Integer id_especie;
    private double tarifa;
    
    public Servicios_1(){}

    public Integer getId_servicios() {
        return id_servicios;
    }

    public void setId_servicios(Integer id_servicios) {
        this.id_servicios = id_servicios;
    }

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public Integer getId_especie() {
        return id_especie;
    }

    public void setId_especie(Integer id_especie) {
        this.id_especie = id_especie;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
    
    
}
