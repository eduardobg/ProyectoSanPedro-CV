
package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoOrdenPedidoImpl implements GenericDao<OrdenPedido>{
    private final ConectaBD conectaDb;
    private String mensaje;
    
    public DaoOrdenPedidoImpl(){
        this.conectaDb= new ConectaBD();
    }

    @Override
    public Boolean insert(OrdenPedido t) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO orden_pedido( ")
                .append("id_cli,")
                .append("id_serv,")
                .append("dni,")
                .append("nombreC,")
                .append("direc,")
                .append("cantidad, ")
                .append("id_estado ")
                .append(") VALUES (?,?,?,?,?,?,?) ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, t.getId_cliente().toString());
            ps.setString(2, t.getId_tipoP().toString());
            ps.setString(3, t.getDni());
            ps.setString(4, t.getNombreCli());
            ps.setString(5, t.getDirecCli());
            ps.setString(6, t.getCantidad().toString());
            ps.setString(7, t.getId_estado().toString());
            
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
    public Integer searchById4(int id, int id2) {
        Integer num_orden=null;
        String sql = "SELECT id_pedido FROM orden_pedido WHERE id_serv = ? AND id_cli = ? ORDER BY id_pedido DESC LIMIT 1";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id2);
            try (ResultSet rs = ps.executeQuery()) {                
                while (rs.next()) {
                    num_orden =rs.getInt("id_pedido");            
                }

            } catch (Exception e) {
                mensaje = e.getMessage();                
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            
        }        
        return num_orden;
    }

  
    @Override
    public OrdenPedido searchById3(int id) {
        OrdenPedido orden = null;
        String sql = "SELECT * FROM orden_pedido WHERE id_pedido = ? AND id_estado = 1";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);         
            try (ResultSet rs = ps.executeQuery()) {  
                if (rs.next()) {
                    orden = new OrdenPedido();
                    orden.setId_orden(rs.getInt("id_pedido"));
                    orden.setId_cliente(rs.getInt("id_cli"));
                    orden.setDni(rs.getString("dni"));
                    orden.setNombreCli(rs.getString("nombreC"));
                    orden.setDirecCli(rs.getString("direc"));
                    orden.setCantidad(rs.getInt("cantidad"));
                    
                }

            } catch (Exception e) {
                mensaje = e.getMessage();                
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            
        }        
        return orden;
    }

    @Override
    public void update2(int id, int estado) {
         String sql = "UPDATE orden_pedido SET id_estado = ? WHERE id_pedido = ? ";
         try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, id);          
            int dml = ps.executeUpdate();
            if (dml == 1) {
                mensaje="Estado Actualizado";
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
