package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.util.ConectaBD;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Trabajadores;

/**
 *
 * @author Mysk
 */
public class DaoTrabajadoresImpl implements GenericDao<Trabajadores> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoTrabajadoresImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public Trabajadores login(String u, String a) {
        Trabajadores employee = new Trabajadores();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_trab,")
                .append("dni,")
                .append("nombre,")
                .append("apellidop ")
                .append("FROM trabajadores WHERE dni = ? AND tipo = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, u);
            ps.setString(2, a);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                    employee.setTipo(rs.getInt(2));
                    employee.setNombre(rs.getString(3));
                    employee.setApellidop(rs.getString(4));
                } else {
                    employee = null;
                    mensaje = "No se encuentra al tabajador";
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return employee;
    }

    @Override
    public Boolean insert(Trabajadores employee) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO trabajadores( ")
                .append("dni,")
                .append("nombre,")
                .append("apellidop,")
                .append("apellidom,")
                .append("celular,")
                .append("correo,")
                .append("fecha_nac,")
                .append("fecha_reg, ")
                .append("tipo ")
                .append(") VALUES (?,?,?,?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, employee.getDni());
            ps.setString(2, employee.getNombre());
            ps.setString(3, employee.getApellidop());
            ps.setString(4, employee.getApellidom());
            ps.setString(5, employee.getCelular());
            ps.setString(6, employee.getCorreo());
            ps.setString(7, employee.getFecha_nac().toString());
            ps.setString(8, employee.getFecha_reg().toString());
            ps.setString(9, employee.getTipo().toString());
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
    public List<Trabajadores> sel() {
        List<Trabajadores> list = new ArrayList();
        String sql = "SELECT * FROM trabajadores INNER JOIN tipo_trabajadores WHERE trabajadores.tipo = tipo_trabajadores.id_tipo ";//nuevo
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Trabajadores employee = new Trabajadores();
                    employee.setId(rs.getInt("id_trab"));
                    employee.setDni(rs.getString("dni"));
                    employee.setNombre(rs.getString("nombre"));
                    employee.setApellidop(rs.getString("apellidop"));
                    employee.setApellidom(rs.getString("apellidom"));
                    employee.setCelular(rs.getString("celular"));
                    employee.setCorreo(rs.getString("correo"));
                    employee.setFecha_nac(LocalDate.parse(rs.getString("fecha_nac")));
                    employee.setFecha_reg(LocalDate.parse(rs.getString("fecha_reg")));     
                    employee.setCargo(rs.getString("categoria"));
                    list.add(employee);
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
    public Trabajadores searchById(int id) {
        Trabajadores employee = null;
        String sql = "SELECT * FROM trabajadores WHERE id_trab = ? ";       
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                employee = new Trabajadores();
                while (rs.next()) {
                    employee.setId(rs.getInt("id_trab"));
                    employee.setDni(rs.getString("dni"));
                    employee.setNombre(rs.getString("nombre"));
                    employee.setApellidop(rs.getString("apellidop"));
                    employee.setApellidom(rs.getString("apellidom"));
                    employee.setCelular(rs.getString("celular"));
                    employee.setCorreo(rs.getString("correo"));
                    employee.setFecha_nac(LocalDate.parse(rs.getString("fecha_nac")));
                    employee.setFecha_reg(LocalDate.parse(rs.getString("fecha_reg")));
                    employee.setTipo(rs.getInt("tipo"));                    
                }

            } catch (Exception e) {
                mensaje = e.getMessage();                
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            
        }
        return employee;
    }

    @Override
    public List<Trabajadores> searchByQuery(String query) {
        List<Trabajadores> list = new ArrayList();
        //String sql = "SELECT * FROM trabajadores WHERE tipo = ? ";
        String sql = "SELECT * FROM (trabajadores INNER JOIN tipo_trabajadores ON trabajadores.tipo = tipo_trabajadores.id_tipo) WHERE tipo = ? ";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Trabajadores employee = new Trabajadores();
                    employee.setId(rs.getInt("id_trab"));
                    employee.setDni(rs.getString("dni"));
                    employee.setNombre(rs.getString("nombre"));
                    employee.setApellidop(rs.getString("apellidop"));
                    employee.setApellidom(rs.getString("apellidom"));
                    employee.setCelular(rs.getString("celular"));
                    employee.setCorreo(rs.getString("correo"));
                    employee.setFecha_nac(LocalDate.parse(rs.getString("fecha_nac")));
                    employee.setFecha_reg(LocalDate.parse(rs.getString("fecha_reg")));
                    //employee.setTipo(rs.getInt("tipo"));
                    employee.setCargo(rs.getString("categoria"));
                    list.add(employee);
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
                System.out.println(mensaje); //borrar
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            System.out.println(mensaje); //borrar
        }
        return list;
    }

    @Override
    public Trabajadores searchByQuery2(String query) {
        Trabajadores employee = null;
        String sql = "SELECT * FROM (trabajadores INNER JOIN tipo_trabajadores ON trabajadores.tipo = tipo_trabajadores.id_tipo) WHERE dni = ? ";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                employee = new Trabajadores();
                while (rs.next()) {
                    employee.setId(rs.getInt("id_trab"));
                    employee.setDni(rs.getString("dni"));
                    employee.setNombre(rs.getString("nombre"));
                    employee.setApellidop(rs.getString("apellidop"));
                    employee.setApellidom(rs.getString("apellidom"));
                    employee.setCelular(rs.getString("celular"));
                    employee.setCorreo(rs.getString("correo"));
                    employee.setFecha_nac(LocalDate.parse(rs.getString("fecha_nac")));
                    employee.setFecha_reg(LocalDate.parse(rs.getString("fecha_reg")));
                    //employee.setTipo(rs.getInt("tipo"));
                    employee.setCargo(rs.getString("categoria"));
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return employee;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM trabajadores WHERE id_trab = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));                    
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje="TRABAJADOR ELIMINADO";
            } else {
                mensaje="ERROR AL ELIMINAR";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }

    @Override
    public void update(Trabajadores t) {        
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE trabajadores SET ")
                .append("dni=?,")
                .append("nombre=?,")
                .append("apellidop=?,")
                .append("apellidom=?,")
                .append("celular=?,")
                .append("correo=?,")
                .append("fecha_nac=?,")
                .append("tipo=? ")
                .append("WHERE id_trab=?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, t.getDni());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellidop());
            ps.setString(4, t.getApellidom());
            ps.setString(5, t.getCelular());
            ps.setString(6, t.getCorreo());
            ps.setString(7, t.getFecha_nac().toString());
            ps.setString(8, t.getTipo().toString());
            ps.setString(9, t.getId().toString());            
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje="ACTUALIZADO CORRECTAMENTE";
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
