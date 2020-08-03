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
public class DaoOrdenPedidoImpl implements GenericDao<OrdenPedido> {

    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoOrdenPedidoImpl() {
        this.conectaDb = new ConectaBD();
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
        Integer num_orden = null;
        String sql = "SELECT id_pedido FROM orden_pedido WHERE id_serv = ? AND id_cli = ? ORDER BY id_pedido DESC LIMIT 1";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id2);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    num_orden = rs.getInt("id_pedido");
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
    public List<OrdenPedido> sel() {
        List<OrdenPedido> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_pedido,")
                .append("id_cli,")
                .append("id_serv,")
                .append("dni,")
                .append("nombreC,")
                .append("direc,")
                .append("cantidad,")
                .append("estado_orden.estado ")
                .append("FROM orden_pedido INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado WHERE id_serv=4");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    OrdenPedido med = new OrdenPedido();
                    med.setId_orden(rs.getInt(1));
                    med.setId_cliente(rs.getInt(2));
                    med.setId_tipoP(rs.getInt(3));
                    med.setDni(rs.getString(4));
                    med.setNombreCli(rs.getString(5));
                    med.setDirecCli(rs.getString(6));
                    med.setCantidad(rs.getInt(7));
                    med.setNombEstado(rs.getString(8));
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
    public List<OrdenPedido> sel1() {
        List<OrdenPedido> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("id_pedido,")
                .append("id_cli,")
                .append("id_serv,")
                .append("dni,")
                .append("nombreC,")
                .append("direc,")
                .append("cantidad,")
                .append("estado_orden.estado ")
                .append("FROM orden_pedido INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado WHERE (orden_pedido.id_estado=1) OR (orden_pedido.id_estado=2) AND id_serv=4");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            try (ResultSet rs = ps.executeQuery()) {
                lista = new ArrayList();
                while (rs.next()) {
                    OrdenPedido med = new OrdenPedido();
                    med.setId_orden(rs.getInt(1));
                    med.setId_cliente(rs.getInt(2));
                    med.setId_tipoP(rs.getInt(3));
                    med.setDni(rs.getString(4));
                    med.setNombreCli(rs.getString(5));
                    med.setDirecCli(rs.getString(6));
                    med.setCantidad(rs.getInt(7));
                    med.setNombEstado(rs.getString(8));
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
    public List<OrdenPedido> searchByQuery(String dni) {
        List<OrdenPedido> list = new ArrayList();
        String sql = "SELECT id_pedido,id_cli,id_serv,dni,nombreC,direc,cantidad,estado_orden.estado FROM orden_pedido INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado WHERE dni = ? AND (orden_pedido.id_estado=1) OR (orden_pedido.id_estado=2) AND id_serv=4";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenPedido op = new OrdenPedido();
                    op.setId_orden(rs.getInt(1));
                    op.setId_cliente(rs.getInt(2));
                    op.setId_tipoP(rs.getInt(3));
                    op.setDni(rs.getString(4));
                    op.setNombreCli(rs.getString(5));
                    op.setDirecCli(rs.getString(6));
                    op.setCantidad(rs.getInt(7));
                    op.setNombEstado(rs.getString(8));
                    list.add(op);
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
    public OrdenPedido searchByQuery2(String dni) {
        OrdenPedido med = null;
        StringBuilder sql = new StringBuilder();
       sql.append("SELECT ")
                .append("id_pedido,")
                .append("id_cli,")
                .append("id_serv,")
                .append("dni,")
                .append("nombreC,")
                .append("direc,")
                .append("cantidad,")
                .append("estado_orden.estado ")
                .append("FROM orden_pedido INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado WHERE dni = ?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    med = new OrdenPedido();
                    med.setId_orden(rs.getInt(1));
                    med.setId_cliente(rs.getInt(2));
                    med.setId_tipoP(rs.getInt(3));
                    med.setDni(rs.getString(4));
                    med.setNombreCli(rs.getString(5));
                    med.setDirecCli(rs.getString(6));
                    med.setCantidad(rs.getInt(7));
                    med.setSubtotal(rs.getDouble(8));
                    med.setNombEstado(rs.getString(9));
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
    public OrdenPedido searchById(int id) {
        OrdenPedido med = new OrdenPedido();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("detalle_pedido.id_med,")
                .append("detalle_pedido.cantidad,")
                .append("detalle_pedido.descrip,")
                .append("orden_pedido.dni,")
                .append("orden_pedido.nombreC,")
                .append("detalle_pedido.precio,")
                .append("detalle_pedido.subtotal,")
                .append("orden_pedido.id_estado,")
                .append("estado_orden.estado ")
                .append("FROM ((orden_pedido ")
                .append("INNER JOIN detalle_pedido ON orden_pedido.id_pedido = detalle_pedido.id_orden) ")
                .append("INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado) ")
                .append("WHERE orden_pedido.id_pedido= ?");
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    med.setId_med(rs.getInt(1));
                    med.setCantidad(rs.getInt(2));
                    med.setDescrip(rs.getString(3));
                    med.setDni(rs.getString(4));
                    med.setNombreCli(rs.getString(5));
                    med.setPrecio(rs.getDouble(6));
                    med.setSubtotal(rs.getDouble(7));
                    med.setId_estado(rs.getInt(8));
                    med.setNombEstado(rs.getString(9));
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
    public List<OrdenPedido> searchById2(int id) {
        List<OrdenPedido> list = new ArrayList();
        String sql = "SELECT detalle_pedido.id_med,detalle_pedido.cantidad,detalle_pedido.descrip,orden_pedido.dni,orden_pedido.nombreC,detalle_pedido.precio,detalle_pedido.subtotal,orden_pedido.id_estado,estado_orden.estado FROM ((orden_pedido INNER JOIN detalle_pedido ON orden_pedido.id_pedido = detalle_pedido.id_orden) INNER JOIN estado_orden ON orden_pedido.id_estado = estado_orden.id_estado) WHERE orden_pedido.id_pedido= ? ";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenPedido op = new OrdenPedido();
                    op.setId_med(rs.getInt(1));
                    op.setCantidad(rs.getInt(2));
                    op.setDescrip(rs.getString(3));
                    op.setDni(rs.getString(4));
                    op.setNombreCli(rs.getString(5));
                    op.setPrecio(rs.getDouble(6));
                    op.setSubtotal(rs.getDouble(7));
                    op.setId_estado(rs.getInt(8));
                    op.setNombEstado(rs.getString(9));
                    list.add(op);
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
