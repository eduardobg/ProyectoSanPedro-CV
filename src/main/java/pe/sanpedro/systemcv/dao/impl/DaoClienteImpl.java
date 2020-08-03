package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoClienteImpl implements GenericDao<Cliente> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoClienteImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public Cliente searchByQuery2(String dni) {
        Cliente cliente = new Cliente();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_cli,")
                .append("dni,")
                .append("nombre,")
                .append("apellido,")
                .append("direc,")
                .append("fecha_nac,")
                .append("celular, ")
                .append("sexo ")
                .append("FROM clientes ")
                .append("WHERE dni = ?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt(1));
                    cliente.setDni(rs.getString(2));
                    cliente.setNombre(rs.getString(3));
                    cliente.setApellido(rs.getString(4));
                    cliente.setDireccion(rs.getString(5));
                    cliente.setFecha_nac(LocalDate.parse(rs.getString(6)));
                    cliente.setCelular(rs.getString(7));
                    cliente.setSexo(rs.getString(8));
                } else {
                    cliente = null;
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return cliente;
    }
    @Override
    public List<Cliente> sel() {
        List<Cliente> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_cli,")
                .append("dni,")
                .append("nombre,")
                .append("apellido,")
                .append("direc,")
                .append("fecha_nac,")
                .append("celular, ")
                .append("sexo ")
                .append("FROM clientes");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setApellido(rs.getString(4));
                cliente.setDireccion(rs.getString(5));
                cliente.setFecha_nac(LocalDate.parse(rs.getString(6)));
                cliente.setCelular(rs.getString(7));
                cliente.setSexo(rs.getString(8));
                list.add(cliente);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }
    @Override
    public void delete(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM clientes WHERE id_cli = ?");
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
    public void update(Cliente cliente) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE clientes SET ")
                .append("dni = ?,")
                .append("nombre = ?,")
                .append("apellido = ?,")
                .append("direc = ?,")
                .append("fecha_nac = ?,")
                .append("celular = ?, ")
                .append("sexo = ? ")
                .append("WHERE id_cli = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getFecha_nac().toString());
            ps.setString(6, cliente.getCelular());
            ps.setString(7, cliente.getSexo());
            ps.setInt(8, cliente.getId_cliente());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }
    @Override
    public Boolean insert(Cliente cliente) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO clientes( ")
                .append("dni,")
                .append("nombre,")
                .append("apellido,")
                .append("direc,")
                .append("fecha_nac,")
                .append("celular, ")
                .append("sexo ")
                .append(") VALUES (?,?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getFecha_nac().toString());
            ps.setString(6, cliente.getCelular());
            ps.setString(7, cliente.getSexo());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = true;
                mensaje = "cero filas insertadas";
                System.out.println(mensaje);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
            System.out.println(mensaje);
        }
        return ok;
    }
}
