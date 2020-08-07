/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.model;

import java.time.LocalDate;

/**
 *
 * @author alons
 */
public class Medicamentos {

    private String ID_Med;
    private String nombre;
    private LocalDate f_elab;
    private LocalDate f_venci;
    private double precio;
    private int stock;
    private String tipo;
    private String present;
    private String lab;
    private String descrip;

    public Medicamentos(String ID_Med, String nombre, LocalDate f_elab, LocalDate f_venci, double precio, int stock, String tipo, String present, String lab, String descrip) {
        this.ID_Med = ID_Med;
        this.nombre = nombre;
        this.f_elab = f_elab;
        this.f_venci = f_venci;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
        this.present = present;
        this.lab = lab;
        this.descrip = descrip;
    }

    public Medicamentos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getF_elab() {
        return f_elab;
    }

    public void setF_elab(LocalDate f_elab) {
        this.f_elab = f_elab;
    }

    public LocalDate getF_venci() {
        return f_venci;
    }

    public void setF_venci(LocalDate f_venci) {
        this.f_venci = f_venci;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getID_Med() {
        return ID_Med;
    }

    public void setID_Med(String ID_Med) {
        this.ID_Med = ID_Med;
    }

}
