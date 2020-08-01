package pe.sanpedro.systemcv.model;

public class Raza {
    private int id_raza;
    private String nombre;
    private int id_especie;

    public Raza() {
    }

    public int getId_raza() {
        return id_raza;
    }

    public void setId_raza(int id_raza) {
        this.id_raza = id_raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
    
}
