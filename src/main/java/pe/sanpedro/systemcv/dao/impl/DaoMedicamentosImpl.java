package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author alons
 */
public class DaoMedicamentosImpl implements GenericDao<Medicamentos> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoMedicamentosImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Medicamentos> sel() {
        List<Medicamentos> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_med,")
                .append("nombre,")
                .append("fecha_elab,")
                .append("fecha_ven,")
                .append("precio,")
                .append("stock,")
                .append("presen,")
                .append("labo,")
                .append("descrip ")
                .append("FROM medicamentos ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    Medicamentos med = new Medicamentos();
                    med.setID_Med(rs.getString(1));
                    med.setNombre(rs.getString(2));
                    med.setF_elab(rs.getDate(3).toLocalDate());
                    med.setF_venci(rs.getDate(4).toLocalDate());
                    med.setPrecio(rs.getDouble(5));
                    med.setStock(rs.getInt(6));
                    med.setPresent(rs.getString(7));
                    med.setLab(rs.getString(8));
                    med.setDescrip(rs.getString(9));
                    lista.add(med);
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    @Override
    public Medicamentos searchByQuery2(String query) {
        Medicamentos med = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_med,")
                .append("nombre,")
                .append("fecha_elab,")
                .append("fecha_ven,")
                .append("precio,")
                .append("stock,")
                .append("presen,")
                .append("labo,")
                .append("descrip ")
                .append("FROM medicamentos WHERE nombre=?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                med = new Medicamentos();
                if (rs.next()) {                  
                    med.setID_Med(rs.getString(1));
                    med.setNombre(rs.getString(2));
                    med.setF_elab(rs.getDate(3).toLocalDate());
                    med.setF_venci(rs.getDate(4).toLocalDate());
                    med.setPrecio(rs.getDouble(5));
                    med.setStock(rs.getInt(6));
                    med.setPresent(rs.getString(7));
                    med.setLab(rs.getString(8));
                    med.setDescrip(rs.getString(9));
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return med;
    }

    @Override
    public Boolean insert(Medicamentos t) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO medicamentos( ")
                .append("tipo_serv,")
                .append("nombre,")
                .append("fecha_elab,")
                .append("fecha_ven,")
                .append("precio,")
                .append("stock,")
                .append("presen,")
                .append("labo, ")
                .append("descrip ")
                .append(") VALUES (?,?,?,?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, 4);
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getF_elab().toString());
            ps.setString(4, t.getF_venci().toString());
            ps.setString(5, String.valueOf(t.getPrecio()));
            ps.setString(6, String.valueOf(t.getStock()));
            ps.setString(7, t.getPresent());
            ps.setString(8, t.getLab());
            ps.setString(9, t.getDescrip());
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
    public List<Medicamentos> searchByQuery(String nombre) {
        List<Medicamentos> list = new ArrayList();
        String sql = "SELECT id_med,nombre,fecha_elab,fecha_ven,precio,stock,presen,labo,descrip FROM medicamentos WHERE nombre = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicamentos m = new Medicamentos();
                    m.setID_Med(rs.getString(1));
                    m.setNombre(rs.getString(2));
                    m.setF_elab(rs.getDate(3).toLocalDate());
                    m.setF_venci(rs.getDate(4).toLocalDate());
                    m.setPrecio(rs.getDouble(5));
                    m.setStock(rs.getInt(6));
                    m.setPresent(rs.getString(7));
                    m.setLab(rs.getString(8));
                    m.setDescrip(rs.getString(9));
                    list.add(m);
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
    public Medicamentos searchById(int id) {
        Medicamentos med = null;
        String sql = "SELECT * FROM medicamentos WHERE id_med = ? ";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                med = new Medicamentos();
                if (rs.next()) {
                    med.setID_Med(rs.getString("id_med"));
                    med.setNombre(rs.getString("nombre"));
                    med.setF_elab(LocalDate.parse(rs.getString("fecha_elab")));
                    med.setF_venci(LocalDate.parse(rs.getString("fecha_ven")));
                    med.setPrecio(Double.parseDouble(rs.getString("precio")));
                    med.setStock(Integer.parseInt(rs.getString("stock")));
                    med.setPresent(rs.getString("presen"));
                    med.setLab(rs.getString("labo"));
                    med.setDescrip(rs.getString("descrip"));
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();

        }
        return med;

    }    

    @Override
    public void update2(int cant, int idmed) {
        String sql = "UPDATE medicamentos SET stock = ? WHERE id_med = ? ";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idmed);
            ps.setInt(2, cant);
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje = "Estado Actualizado";
            } else {
                mensaje = "ERROR AL ACTUALIZAR";
            }
        } catch (SQLException ex) {
            mensaje = ex.getMessage();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM medicamentos WHERE id_med= ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje = "Medicamento ELIMINADO";
            } else {
                mensaje = "ERROR AL ELIMINAR";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }

    @Override
    public void update(Medicamentos t) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE medicamentos SET ")
                .append("nombre=?,")
                .append("fecha_elab=?,")
                .append("fecha_ven=?,")
                .append("precio=?,")
                .append("stock=?,")
                .append("presen=?,")
                .append("labo=?,")
                .append("descrip=? ")
                .append("WHERE id_med=?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getF_elab().toString());
            ps.setString(3, t.getF_venci().toString());
            ps.setString(4, String.valueOf(t.getPrecio()));
            ps.setString(5, String.valueOf(t.getStock()));
            ps.setString(6, t.getPresent());
            ps.setString(7, t.getLab());
            ps.setString(8, t.getDescrip());
            ps.setString(9, t.getID_Med());
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje = "ACTUALIZADO CORRECTAMENTE";
            } else {
                mensaje = "ERROR AL ACTUALIZAR";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }

    @Override
    public List<Medicamentos> searchByDate(LocalDate d) {
        List<Medicamentos> lista = null;
        String sql = "SELECT * FROM medicamentos WHERE fecha_ven <= ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(d));            
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    Medicamentos med = new Medicamentos();
                    med.setID_Med(rs.getString("id_med"));
                    med.setNombre(rs.getString("nombre"));
                    med.setF_elab(LocalDate.parse(rs.getString("fecha_elab")));
                    med.setF_venci(LocalDate.parse(rs.getString("fecha_ven")));
                    med.setPrecio(Double.parseDouble(rs.getString("precio")));
                    med.setStock(Integer.parseInt(rs.getString("stock")));
                    med.setPresent(rs.getString("presen"));
                    med.setLab(rs.getString("labo"));
                    med.setDescrip(rs.getString("descrip"));
                    lista.add(med);
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();

        }
        return lista;
    }
    

    @Override
    public String getMessage() {
        return mensaje;
    }

}
