package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Raza;
import pe.sanpedro.systemcv.util.ConectaBD;

public class DaoRazaImpl implements GenericDao<Raza> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoRazaImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Raza> searchById2(int id) {
        List<Raza> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_raza,")
                .append("nombre,")
                .append("id_especie ")
                .append("FROM raza ")
                .append("WHERE id_especie = ? ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Raza raza = new Raza();
                raza.setId_raza(rs.getInt(1));
                raza.setNombre(rs.getString(2));
                raza.setId_especie(rs.getInt(3));
                list.add(raza);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return list;
    }

    @Override
    public Raza searchById(int id) {
        Raza raza = new Raza();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_raza,")
                .append("nombre,")
                .append("id_especie ")
                .append("FROM raza ")
                .append("WHERE id_raza = ?");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    raza.setId_raza(rs.getInt(1));
                    raza.setNombre(rs.getString(2));
                    raza.setId_especie(rs.getInt(3));

                } else {
                    raza = null;
                }

            } catch (SQLException e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return raza;
    }

    @Override
    public List<Raza> sel() {
        List<Raza> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_raza,")
                .append("nombre,")
                .append("id_especie ")
                .append("FROM razas");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            list = new ArrayList<>();
            while (rs.next()) {
                Raza raza = new Raza();

                raza.setId_raza(rs.getInt(1));
                raza.setNombre(rs.getString(2));
                raza.setId_especie(rs.getInt(3));
                list.add(raza);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public void delete(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM raza WHERE id_raza = ?");
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
    }

    @Override
    public void update(Raza raza) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE raza SET ")
                .append("nombre = ?,")
                .append("id_especie = ? ")
                .append("WHERE id_raza = ? ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, raza.getNombre());
            ps.setInt(2, raza.getId_especie());
            ps.setInt(3, raza.getId_raza());
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
    public Boolean insert(Raza raza) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO raza( ")
                .append("nombre,")
                .append("id_especie ")
                .append(") VALUES (?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setString(1, raza.getNombre());
            ps.setInt(2, raza.getId_especie());
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
