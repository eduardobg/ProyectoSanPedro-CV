package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Especie;
import pe.sanpedro.systemcv.util.ConectaBD;

public class DaoEspecieImpl implements GenericDao<Especie> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoEspecieImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Especie> sel() {
        List<Especie> list = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_especie,")
                .append("nombre ")
                .append("FROM especie");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            list = new ArrayList<>();
            while (rs.next()) {
                Especie especie = new Especie();

                especie.setId_especie(rs.getInt(1));
                especie.setNombre(rs.getString(2));
                list.add(especie);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public Especie searchById(int id) {
        Especie especie = new Especie();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_especie,")
                .append("nombre ")
                .append("FROM especie ")
                .append("WHERE id_especie = ?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    especie.setId_especie(rs.getInt(1));
                    especie.setNombre(rs.getString(2));
                } else {
                    especie = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return especie;
    }

    @Override
    public void delete(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM especie WHERE id_especie = ?");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, id);
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = false;
                mensaje = "ID: " + id + " no existe";
            }

            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }

            cn.setAutoCommit(true);

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        System.out.println(mensaje);
    }

    @Override
    public void update(Especie especie) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE especie SET ")
                .append("nombre = ?,")
                .append("WHERE id_especie = ? ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, especie.getNombre());
            ps.setInt(2, especie.getId_especie());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        System.out.println(mensaje);
    }

    @Override
    public Boolean insert(Especie especie) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO especie( ")
                .append("nombre")
                .append(") VALUES (?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(2, especie.getNombre());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = true;
                mensaje = "cero filas insertadas";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();// si hay error
        }
        System.out.println(mensaje);
        return ok;
    }

}
