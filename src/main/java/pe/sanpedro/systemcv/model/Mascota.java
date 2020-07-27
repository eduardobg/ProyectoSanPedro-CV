package pe.sanpedro.systemcv.model;

/**
 *
 * @author Mysk
 */
public class Mascota {
    private int id_mascota;
    private String nom_mascota;
    private int id_especie;
    private String nom_especie;
    private int id_raza;
    private String nom_raza;
    private int edad;
    private int id_cliente;

    public Mascota() {
    }

    public Mascota( String nom_mascota, int id_especie, int id_raza, int edad, int id_cliente) {
  
        this.nom_mascota = nom_mascota;
        this.id_especie = id_especie;
        this.id_raza = id_raza;
        this.edad = edad;
        this.id_cliente = id_cliente;
    }

    public String getNom_especie() {
        return nom_especie;
    }

    public void setNom_especie(String nom_especie) {
        this.nom_especie = nom_especie;
    }

    public String getNom_raza() {
        return nom_raza;
    }

    public void setNom_raza(String nom_raza) {
        this.nom_raza = nom_raza;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNom_mascota() {
        return nom_mascota;
    }

    public void setNom_mascota(String nom_mascota) {
        this.nom_mascota = nom_mascota;
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public int getId_raza() {
        return id_raza;
    }

    public void setId_raza(int id_raza) {
        this.id_raza = id_raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    

}
