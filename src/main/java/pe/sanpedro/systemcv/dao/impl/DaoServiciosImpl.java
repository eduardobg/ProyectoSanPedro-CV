package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Servicios;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoServiciosImpl implements GenericDao<Servicios> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoServiciosImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Servicios> searchById3(int id, int id2) {
        List<Servicios> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_serv,")
                .append("nombre,")               
                .append("tarifa ")
                .append("FROM servicios ")             
                .append("WHERE tipo_serv = ? AND id_especie = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());            
            ps.setString(1, String.valueOf(id));
            ps.setString(2, String.valueOf(id2));
            try (ResultSet rs = ps.executeQuery()) {
                    lista = new ArrayList();
                while (rs.next()) {
                    Servicios serv = new Servicios();
                    serv.setId_servicios(rs.getInt(1));
                    serv.setNombreS(rs.getString(2));
                    serv.setTarifa(rs.getDouble(3));
                    lista.add(serv);
                }
            }catch(SQLException e){
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
