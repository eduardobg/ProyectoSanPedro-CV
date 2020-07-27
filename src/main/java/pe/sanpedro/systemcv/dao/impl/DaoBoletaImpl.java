/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pe.sanpedro.systemcv.dao.GenericDao;
//import pe.sanpedro.systemcv.model.Boleta;
import pe.sanpedro.systemcv.dto.Boleta;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoBoletaImpl implements GenericDao<Boleta>{
    private final ConectaBD conectaDb;
    private String mensaje;
    
    public DaoBoletaImpl(){
        this.conectaDb = new ConectaBD();
    }

    @Override
    public Boolean insert(Boleta t) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO boleta( ")
                .append("id_orden,")
                .append("id_trab,")
                .append("fecha_,")
                .append("importeTotal,")
                .append("montoIngresado, ")
                .append("vuelto ")
                .append(") VALUES (?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, t.getId_orden().toString());
            ps.setString(2, t.getId_trabajador().toString());
            ps.setString(3, t.getFecha_reg());
            ps.setString(4, String.valueOf(t.getImporteTotal()));
            ps.setString(5, String.valueOf(t.getMontoIngresado())); 
            ps.setString(6, String.valueOf(t.getVuelto()));
            int dml = ps.executeUpdate();
            if (dml == 1) {
                ok = true;
                mensaje = "INSERTADO CORRECTAMENTE";
                System.out.println("INSERTADO CORRECTAMENTE");
            } else {
                mensaje = "ERROR AL INSERTAR";
                System.out.println("ERROR AL INSERTAR");
            };
        } catch (SQLException e) {
            mensaje = e.getMessage();
            System.out.println(mensaje);
        }
        return ok;
    }

    @Override
    public String getMessage() {
        return mensaje; 
    }
    
    
}
