package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoMascotaImpl implements GenericDao<Mascota> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoMascotaImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Mascota> searchById2(int id) {
        List<Mascota> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_mascota,")
                .append("mascotas.nombre,")
                .append("especie.id_especie,")
                .append("especie.nombre,")
                .append("raza.id_raza,")
                .append("raza.nombre,")
                .append("edad ")
                .append("FROM ((mascotas ")
                .append("INNER JOIN especie ON mascotas.id_especie = especie.id_especie) ")
                .append("INNER JOIN raza ON mascotas.id_raza = raza.id_raza) ")
                .append("WHERE id_cli = ? ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            list = new ArrayList();
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Mascota mascota = new Mascota();
                    mascota.setId_mascota(rs.getInt(1));
                    mascota.setNom_mascota(rs.getString(2));
                    mascota.setId_especie(rs.getInt(3));
                    mascota.setNom_especie(rs.getString(4));
                    mascota.setId_raza(rs.getInt(5));
                    mascota.setNom_raza(rs.getString(6));
                    mascota.setEdad(rs.getInt(7));

                    list.add(mascota);

                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return list;
    }

    @Override
    public Mascota searchByQuery2(String query) {
        Mascota mascota = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_mascota,")
                .append("mascotas.nombre,")
                .append("especie.nombre,")
                .append("raza.nombre,")
                .append("edad ")
                .append("FROM ((mascotas ")
                .append("INNER JOIN especie ON mascotas.id_especie = especie.id_especie) ")
                .append("INNER JOIN raza ON mascotas.id_raza = raza.id_raza) ")
                .append("WHERE mascotas.nombre = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota();
                    mascota.setId_mascota(rs.getInt(1));
                    mascota.setNom_mascota(rs.getString(2));
                    mascota.setNom_especie(rs.getString(3));
                    mascota.setNom_raza(rs.getString(4));
                    mascota.setEdad(rs.getInt(5));
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mascota;
    }

    @Override
    public Mascota searchById(int id) {
        Mascota mascota = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_especie ")
                .append("FROM mascotas ")
                .append("WHERE id_mascota = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, String.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota();
                    mascota.setId_especie(rs.getInt(1));
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mascota;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public void deleteall(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM mascotas WHERE id_cli= ?");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, id);
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = false;
                mensaje = "ID: " + id + " no tiene mascotas";
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
    public List<Mascota> sel() {
        List<Mascota> list = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_mascota,")
                .append("nombre,")
                .append("id_especie,")
                .append("id_raza,")
                .append("edad,")
                .append("id_cli ")
                .append("FROM mascotas");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            list = new ArrayList<>();
            while (rs.next()) {
                Mascota mascota = new Mascota();

                mascota.setId_mascota(rs.getInt(1));
                mascota.setNom_mascota(rs.getString(2));
                mascota.setId_especie(rs.getInt(3));
                mascota.setId_raza(rs.getInt(4));
                mascota.setEdad(rs.getInt(5));
                mascota.setId_cliente(rs.getInt(6));
                list.add(mascota);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public void delete(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM mascotas WHERE id_mascota = ?");

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
    public void update(Mascota mascota) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE mascotas SET ")
                .append("id_mascota = ?,")
                .append("nombre = ?,")
                .append("id_especie = ?,")
                .append("id_raza= ?,")
                .append("edad = ?,")
                .append("id_cli = ? ")
                .append("WHERE id_mascota = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, mascota.getId_mascota());
            ps.setString(2, mascota.getNom_mascota());
            ps.setInt(3, mascota.getId_especie());
            ps.setInt(4, mascota.getId_raza());
            ps.setInt(5, mascota.getEdad());
            ps.setInt(6, mascota.getId_cliente());
            ps.setInt(7, mascota.getId_mascota());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }

    @Override
    public Boolean insert(Mascota mascota) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO mascotas( ")
                .append("nombre,")
                .append("id_especie,")
                .append("id_raza,")
                .append("edad,")
                .append("id_cli ")
                .append(") VALUES (?,?,?,?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setString(1, mascota.getNom_mascota());
            ps.setInt(2, mascota.getId_especie());
            ps.setInt(3, mascota.getId_raza());
            ps.setInt(4, mascota.getEdad());
            ps.setInt(5, mascota.getId_cliente());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = true;
                mensaje = "cero filas insertadas";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return ok;
    }

}
