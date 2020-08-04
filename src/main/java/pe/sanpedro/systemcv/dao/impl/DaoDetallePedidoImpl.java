package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoDetallePedidoImpl implements GenericDao<DetallePedido> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoDetallePedidoImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public Boolean insert2(List<DetallePedido> l, boolean b) {
        boolean ok = false;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO detalle_pedido( ")
                .append("id_orden,");
        if (b) {
            sql.append("id_serv,");

        } else {
            sql.append("id_med,");
        }
        sql.append("cantidad,")
                .append("descrip,")
                .append("precio,")
                .append("subtotal ")
                .append(") VALUES (?,?,?,?,?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            int dml = 0;
            for (int i = 0; i < l.size(); i++) {
                ps.setString(1, l.get(i).getId_orden().toString());
                ps.setString(2, l.get(i).getId_pro().toString());
                ps.setString(3, l.get(i).getCantidad().toString());
                ps.setString(4, l.get(i).getDescripcion());
                ps.setString(5, String.valueOf(l.get(i).getPrecio()));
                ps.setString(6, String.valueOf(l.get(i).getSubtotal()));
                dml = ps.executeUpdate();
                dml= dml +1;
            }            
            
            if (dml == l.size()+1) {
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
    public List<DetallePedido> sel() {
        List<DetallePedido> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_orden,")
                .append("detalle_pedido.id_med,")
                .append("detalle_pedido.cantidad,")
                .append("detalle_pedido.descrip,")
                .append("detalle_pedido.precio,")
                .append("detalle_pedido.subtotal ")
                .append("FROM detalle_pedido INNER JOIN medicamentos ON detalle_pedido.id_med = medicamentos.id_med");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    DetallePedido med = new DetallePedido();
                    med.setId_orden(rs.getInt(1));
                    med.setId_pro(rs.getInt(2));
                    med.setCantidad(rs.getInt(3));
                    med.setDescripcion(rs.getString(4));
                    med.setPrecio(rs.getDouble(5));
                    med.setSubtotal(rs.getDouble(6));
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
    public DetallePedido searchById(int id) {
        DetallePedido med = new DetallePedido();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_orden,")
                .append("medicamentos.id_med,")
                .append("detalle_pedido.cantidad,")
                .append("detalle_pedido.descrip,")
                .append("medicamentos.precio,")
                .append("detalle_pedido.subtotal ")
                .append("FROM detalle_pedido INNER JOIN medicamentos ON detalle_pedido.id_med = medicamentos.id_med WHERE id_orden=? ");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    med.setId_orden(rs.getInt(1));
                    med.setId_pro(rs.getInt(2));
                    med.setCantidad(rs.getInt(3));
                    med.setDescripcion(rs.getString(4));
                    med.setPrecio(rs.getInt(5));
                    med.setSubtotal(rs.getDouble(6));
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
    public List<DetallePedido> searchById2(int id) {
        List<DetallePedido> lista = null;
        String sql = "SELECT * FROM detalle_pedido WHERE id_orden = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);         
            try (ResultSet rs = ps.executeQuery()) {  
                lista = new ArrayList();
                while (rs.next()) {                   
                    DetallePedido orden = new DetallePedido();
                    orden.setId_orden(rs.getInt("id_orden"));
                    orden.setCantidad(rs.getInt("cantidad"));
                    orden.setDescripcion(rs.getString("descrip"));
                    orden.setPrecio(rs.getDouble("precio"));
                    orden.setSubtotal(rs.getDouble("subtotal"));
                    lista.add(orden);                    
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
