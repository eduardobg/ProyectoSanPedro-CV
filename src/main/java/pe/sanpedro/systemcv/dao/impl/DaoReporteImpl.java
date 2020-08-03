
package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dto.Reporte;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoReporteImpl implements GenericDao<Reporte>{
    
    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoReporteImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public List<Reporte> sel() {
        List<Reporte> list = new ArrayList();
        String sql="SELECT id_boleta, fecha_, trabajadores.dni,trabajadores."+
                "nombre, trabajadores.apellidop, trabajadores.apellidom, orden_pedido.dni, "+
                "orden_pedido.nombreC, importeTotal from boleta INNER JOIN trabajadores "+
                "ON boleta.id_trab=trabajadores.id_trab INNER JOIN orden_pedido ON boleta.id_orden=orden_pedido.id_pedido";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte report = new Reporte();
                    report.setId_boleta(rs.getInt("id_boleta"));
                    report.setFecha_(LocalDate.parse(rs.getString("fecha_")));
                    report.setDniTrab(rs.getString("trabajadores.dni"));
                    report.setNombreTrab(rs.getString("trabajadores.nombre"));
                    report.setApellidopTrab(rs.getString("trabajadores.apellidop"));
                    report.setApellidomTrab(rs.getString("trabajadores.apellidom"));
                    report.setDniCli(rs.getString("orden_pedido.dni"));
                    report.setNombreCli(rs.getString("orden_pedido.nombreC"));
                    report.setImporteTotal(Double.parseDouble(rs.getString("importeTotal")));                    
                    list.add(report);
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
                System.out.println(mensaje);
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            System.out.println(mensaje);
        }
        return list;       
    }

    @Override
    public List<Reporte> searchBetween(int area, LocalDate d1, LocalDate d2) {
        List<Reporte> list = new ArrayList();
        String sql="SELECT id_boleta, fecha_, trabajadores.dni,trabajadores."+
                "nombre, trabajadores.apellidop, trabajadores.apellidom, orden_pedido.dni, "+
                "orden_pedido.nombreC, importeTotal from boleta INNER JOIN trabajadores "+
                "ON boleta.id_trab=trabajadores.id_trab INNER JOIN orden_pedido ON boleta.id_orden=orden_pedido.id_pedido "+
                "WHERE orden_pedido.id_serv= ? AND (fecha_ BETWEEN ? AND ?);";
        
       try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, area);
            ps.setString(2,String.valueOf(d1));
            ps.setString(3,String.valueOf(d2));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reporte report = new Reporte();
                    report.setId_boleta(rs.getInt("id_boleta"));
                    report.setFecha_(LocalDate.parse(rs.getString("fecha_")));
                    report.setDniTrab(rs.getString("trabajadores.dni"));
                    report.setNombreTrab(rs.getString("trabajadores.nombre"));
                    report.setApellidopTrab(rs.getString("trabajadores.apellidop"));
                    report.setApellidomTrab(rs.getString("trabajadores.apellidom"));
                    report.setDniCli(rs.getString("orden_pedido.dni"));
                    report.setNombreCli(rs.getString("orden_pedido.nombreC"));
                    report.setImporteTotal(Double.parseDouble(rs.getString("importeTotal")));                    
                    list.add(report);
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
                 System.out.println("1"+ mensaje);
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
             System.out.println(mensaje);
        }
        return list;
    }
    
    

    @Override
    public String getMessage() {
        return mensaje; 
    }
    
    
    
    
}
