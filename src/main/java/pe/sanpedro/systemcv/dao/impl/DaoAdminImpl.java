
package pe.sanpedro.systemcv.dao.impl;

import pe.sanpedro.systemcv.util.ConectaBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Admin;

/**
 *
 * @author Eduardo Bautista
 */
public class DaoAdminImpl implements GenericDao<Admin>{
    private final ConectaBD conectaDb;
    private String mensaje;
    public DaoAdminImpl(){
        this.conectaDb = new ConectaBD();
    }
        
    
    @Override
    public Admin login(String usuario, String pass) {
        Admin admin = new Admin();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("user_name ")           
                .append(" FROM admin WHERE user_name = ? AND clave = AES_ENCRYPT(?,?)");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ps.setString(3, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin.setUser_name(rs.getString(1));
                    
                } else {
                    admin = null;
                    mensaje = "Credenciales inválidas";
                }
            } catch (SQLException e) {
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return admin;
    }

    @Override
    public List<Admin> searchByQuery(String query) {
        List<Admin> list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("nombre,")
                .append("apellido ") 
                .append("FROM admin WHERE user_name = ? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, query);      
            try (ResultSet rs = ps.executeQuery()) {
                Admin admin= new Admin();
                if (rs.next()) {
                    admin.setNombre(rs.getString(1));
                    admin.setApellido(rs.getString(2));
                    list.add(admin);
                } else {
                    list = null;
                    mensaje = "Información No disponible";
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
    public String getMessage() {
        return mensaje;
    }
    
    
}
