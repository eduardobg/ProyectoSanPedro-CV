package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Horario;
import pe.sanpedro.systemcv.util.ConectaBD;

public class DaoHorarioImpl implements GenericDao<Horario> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoHorarioImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Horario> sel() {
        List<Horario> list = null;
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
                Horario horario = new Horario();
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
    public List<Horario> searchByDate(LocalDate d) {
        List<Horario> list = null;
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
                    Horario horario = new Horario();
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

    @Override
    public void update(Horario horario) {
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
    }

}
