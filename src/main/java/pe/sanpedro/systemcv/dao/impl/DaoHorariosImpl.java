
package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Horarios;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoHorariosImpl implements GenericDao<Horarios>{
    
    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoHorariosImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public void update(Horarios t) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE horarios SET ")
                .append("id_estado=? ")
                .append("WHERE id_horario=?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t.getId_estado());
            ps.setInt(2, t.getId_horario());                   
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

    /*@Override
    public List<Horarios> searchByDate(LocalDate d) {
        List<Horarios> lista = null;
        String sql = "SELECT * FROM horarios INNER JOIN estado_horario ON horarios.id_estado = estado_horario.id_estado WHERE dia = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(d));
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    Horarios horario = new Horarios();
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setTurno(rs.getString("turno"));
                    horario.setDia(LocalDate.parse(rs.getString("dia")));
                    horario.setHora_i(rs.getString("hora_i"));
                    horario.setHora_f(rs.getString("hora_f"));
                    horario.setEstado(rs.getString("estado"));
                    lista.add(horario);
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;  
    }*/

    @Override
    public Horarios searchByQuery2(String query) {
        Horarios horario = null;
        String sql = "SELECT * FROM horarios INNER JOIN estado_horario ON horarios.id_estado = estado_horario.id_estado WHERE turno = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    horario = new Horarios();
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setTurno(rs.getString("turno"));
                    horario.setDia(LocalDate.parse(rs.getString("dia")));
                    horario.setHora_i(rs.getString("hora_i"));
                    horario.setHora_f(rs.getString("hora_f"));
                    horario.setEstado(rs.getString("estado"));                   
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return horario;        
    }    

    @Override
    public List<Horarios> searchByDate2(String turno, LocalDate d) {
         List<Horarios> lista = null;
        String sql = "SELECT * FROM horarios INNER JOIN estado_horario ON horarios.id_estado = estado_horario.id_estado WHERE turno = ? AND dia = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, turno);
            ps.setString(2, String.valueOf(d));
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    Horarios horario = new Horarios();
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setTurno(rs.getString("turno"));
                    horario.setDia(LocalDate.parse(rs.getString("dia")));
                    horario.setHora_i(rs.getString("hora_i"));
                    horario.setHora_f(rs.getString("hora_f"));
                    horario.setEstado(rs.getString("estado"));
                    lista.add(horario);
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
    
    
    @Override
    public List<Horarios> sel() {
        List<Horarios> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_horario,")
                .append("turno,")
                .append("dia,")
                .append("hora_i,")
                .append("hora_f,")
                .append("estado_horario.id_estado,")
                .append("estado_horario.estado ")
                .append("FROM (horarios ")
                .append("INNER JOIN estado_horario ON horarios.id_estado = estado_horario.id_estado) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                Horarios horario = new Horarios();
                horario.setId_horario(rs.getInt(1));
                horario.setTurno(rs.getString(2));
                horario.setDia(LocalDate.parse(rs.getString(3)));
                horario.setHora_i(rs.getString(4));
                horario.setHora_f(rs.getString(5));
                horario.setId_estado(rs.getInt(6));
                horario.setEstado(rs.getString(7));
                list.add(horario);
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Horarios> searchByDate(LocalDate d) {
        List<Horarios> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_horario,")
                .append("turno,")
                .append("dia,")
                .append("hora_i,")
                .append("hora_f,")
                .append("estado_horario.id_estado,")
                .append("estado_horario.estado ")
                .append("FROM (horarios ")
                .append("INNER JOIN estado_horario ON horarios.id_estado = estado_horario.id_estado) ")
                .append("where dia = ?");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            list = new ArrayList();
            ps.setString(1, "" + d);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Horarios horario = new Horarios();
                    horario.setId_horario(rs.getInt(1));
                    horario.setTurno(rs.getString(2));
                    horario.setDia(LocalDate.parse(rs.getString(3)));
                    horario.setHora_i(rs.getString(4));
                    horario.setHora_f(rs.getString(5));
                    horario.setId_estado(rs.getInt(6));
                    horario.setEstado(rs.getString(7));
                    list.add(horario);
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return list;
    }

    /*@Override
    public void update(Horarios horario) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE horarios SET ")
                .append("id_estado = ? ")
                .append("WHERE id_horario = ? ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, horario.getId_estado());
            ps.setInt(2, horario.getId_horario());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                mensaje = "No se pudo actualizar";
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
    }*/
}
