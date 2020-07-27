package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                .append("especie.nombre,")
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
                    mascota.setNom_especie(rs.getString(3));
                    mascota.setNom_raza(rs.getString(4));
                    mascota.setEdad(rs.getInt(5));

                    list.add(mascota);

                }
            }catch(SQLException e){
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }

        return list;
    }

    @Override
    public Mascota searchByQuery2(String query) {
        Mascota mascota =null;
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
                while (rs.next()) {
                    mascota = new Mascota();
                    mascota.setId_mascota(rs.getInt(1));
                    mascota.setNom_mascota(rs.getString(2));
                    mascota.setNom_especie(rs.getString(3));
                    mascota.setNom_raza(rs.getString(4)); 
                    mascota.setEdad(rs.getInt(5)); 
                }
            }catch(SQLException e){
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mascota;
    }

    @Override
    public Mascota searchById(int id) {
        Mascota mascota =null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_especie ")                           
                .append("FROM mascotas ")               
                .append("WHERE id_mascota = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());            
            ps.setString(1, String.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mascota = new Mascota();
                    mascota.setId_especie(rs.getInt(1));                                      
                }
            }catch(SQLException e){
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

}
