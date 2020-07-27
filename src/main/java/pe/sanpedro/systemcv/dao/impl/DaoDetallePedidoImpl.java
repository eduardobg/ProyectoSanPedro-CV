package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                .append("descrip, ")
                .append("precio ")
                .append(") VALUES (?,?,?,?,?) ");

        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            int dml = 0;
            for (int i = 0; i < l.size(); i++) {
                ps.setString(1, l.get(i).getId_orden().toString());
                ps.setString(2, l.get(i).getId_pro().toString());
                ps.setString(3, l.get(i).getCantidad().toString());
                ps.setString(4, l.get(i).getDescripcion());
                ps.setString(5, String.valueOf(l.get(i).getPrecio()));
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
    public String getMessage() {
        return mensaje; 
    }
    
    

}
