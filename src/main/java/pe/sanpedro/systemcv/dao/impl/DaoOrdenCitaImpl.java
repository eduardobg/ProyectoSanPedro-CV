package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.OrdenCita;
import pe.sanpedro.systemcv.util.ConectaBD;

public class DaoOrdenCitaImpl implements GenericDao<OrdenCita> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoOrdenCitaImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public Boolean insert(OrdenCita ordenCita) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO orden_cita( ")
                .append("id_orden,")
                .append("id_mascota,")
                .append("id_horario,")
                .append("id_estado")
                .append(") VALUES (?,?,?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());

            ps.setInt(1, ordenCita.getId_orden());
            ps.setInt(2, ordenCita.getId_mascota());
            ps.setInt(3, ordenCita.getId_horario());
            ps.setInt(4, ordenCita.getId_estado());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                ok = true;
                mensaje = "cero filas insertadas";
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        System.out.println(mensaje);
        return ok;
    }

}
