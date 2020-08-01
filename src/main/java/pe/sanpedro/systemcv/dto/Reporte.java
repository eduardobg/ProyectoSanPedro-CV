
package pe.sanpedro.systemcv.dto;

import java.time.LocalDate;

/**
 *
 * @author Mysk
 */
public class Reporte {
    private Integer id_boleta;
    private LocalDate fecha_;
    private String dniTrab;
    private String nombreTrab;
    private String apellidopTrab;
    private String apellidomTrab;
    private String dniCli;
    private String nombreCli;
    private double importeTotal;
    
    public Reporte(){
        
    }

    public Integer getId_boleta() {
        return id_boleta;
    }

    public void setId_boleta(Integer id_boleta) {
        this.id_boleta = id_boleta;
    }

    public LocalDate getFecha_() {
        return fecha_;
    }

    public void setFecha_(LocalDate fecha_) {
        this.fecha_ = fecha_;
    }

    public String getDniTrab() {
        return dniTrab;
    }

    public void setDniTrab(String dniTrab) {
        this.dniTrab = dniTrab;
    }

    public String getNombreTrab() {
        return nombreTrab;
    }

    public void setNombreTrab(String nombreTrab) {
        this.nombreTrab = nombreTrab;
    }

    public String getApellidopTrab() {
        return apellidopTrab;
    }

    public void setApellidopTrab(String apellidopTrab) {
        this.apellidopTrab = apellidopTrab;
    }

    public String getApellidomTrab() {
        return apellidomTrab;
    }

    public void setApellidomTrab(String apellidomTrab) {
        this.apellidomTrab = apellidomTrab;
    }

    public String getDniCli() {
        return dniCli;
    }

    public void setDniCli(String dniCli) {
        this.dniCli = dniCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    
}
