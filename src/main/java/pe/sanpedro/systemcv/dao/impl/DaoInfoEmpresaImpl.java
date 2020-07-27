/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.util.ConectaBD;
/**
 *
 * @author Mysk
 */
public class DaoInfoEmpresaImpl implements GenericDao<InfoEmpresa> {
    private final ConectaBD conectaDb;
    private String mensaje;
    
    public DaoInfoEmpresaImpl(){
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<InfoEmpresa> sel() {
        List<InfoEmpresa> list = new ArrayList();
        String sql = "SELECT * FROM info_empresa";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InfoEmpresa info = new InfoEmpresa();
                    info.setNombreE(rs.getString("nombreE"));
                    info.setRuc(rs.getString("ruc"));
                    info.setPropiertario(rs.getString("propietario"));
                    info.setDirec(rs.getString("direc"));
                    info.setTelf(rs.getString("telf"));
                    info.setCel(rs.getString("cel"));
                    info.setCorreo(rs.getString("correo"));               
                    list.add(info);
                }
            } catch (Exception e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public void update(InfoEmpresa i) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE info_empresa SET ")
                .append("nombreE=?,")
                .append("ruc=?,")
                .append("propietario=?,")
                .append("direc=?,")
                .append("telf=?,")
                .append("cel=?,")
                .append("correo=? ")           
                .append("WHERE pk=?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, i.getNombreE());  
            ps.setString(2, i.getRuc()); 
            ps.setString(3, i.getPropiertario()); 
            ps.setString(4, i.getDirec()); 
            ps.setString(5, i.getTelf()); 
            ps.setString(6, i.getCel()); 
            ps.setString(7, i.getCorreo());
            ps.setString(8, i.getInd().toString());
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje="INFORMACIÓN ACTUALIZADA";
            } else {
                mensaje="ERROR AL ACTUALIZAR";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }
    @Override
    public String getMessage() {
        return mensaje;
    }

    
    
}
