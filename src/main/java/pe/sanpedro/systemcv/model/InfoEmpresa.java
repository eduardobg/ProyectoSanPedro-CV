
package pe.sanpedro.systemcv.model;

/**
 *
 * @author Mysk
 */
public class InfoEmpresa {
    private Integer ind;
    private String nombreE;
    private String ruc;
    private String propiertario;
    private String direc;
    private String telf;
    private String cel;
    private String correo;
    public InfoEmpresa(){}

    public InfoEmpresa(Integer ind, String nombreE, String ruc, String propiertario, String direc, String telf, String cel, String correo) {
        this.ind = ind;
        this.nombreE = nombreE;
        this.ruc = ruc;
        this.propiertario = propiertario;
        this.direc = direc;
        this.telf = telf;
        this.cel = cel;
        this.correo = correo;
    }
   
    

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPropiertario() {
        return propiertario;
    }

    public void setPropiertario(String propiertario) {
        this.propiertario = propiertario;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }
    
}
