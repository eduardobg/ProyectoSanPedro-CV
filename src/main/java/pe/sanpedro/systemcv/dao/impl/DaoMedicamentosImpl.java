
package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author alons
 */
public class DaoMedicamentosImpl implements GenericDao<Medicamentos>{
    
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
                .append("FROM medicamentos ")    ;         
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
            }catch(SQLException e){
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return lista;
    }
    
    @Override
    public Medicamentos searchByQuery2(String query) {
        Medicamentos med = new Medicamentos();
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
                while (rs.next()) {
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
            }catch(SQLException e){
                mensaje = e.getMessage();
            }
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return med;
    }
    
    
    
    
    
    
    
    


    @Override
    public String getMessage() {
        return mensaje;
    }

}
