
package pe.sanpedro.systemcv.model;

/**
 *
 * @author Mysk
 */
public class Admin {
    private String user_name;
    private String nombre;
    private String apellido;
    public Admin(){}
    

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
