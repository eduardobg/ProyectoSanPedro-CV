

package pe.sanpedro.systemcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.util.ConectaBD;

/**
 *
 * @author Mysk
 */
public class DaoClienteImpl implements GenericDao<Cliente>{
    private final ConectaBD conectaDb;
    private String mensaje;

    public DaoClienteImpl() {
        this.conectaDb = new ConectaBD();
    }

    @Override
    public Cliente searchByQuery2(String query) {
        Cliente cli =null;
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try (Connection cn = conectaDb.conexionDB()) {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, query);
            try (ResultSet rs = ps.executeQuery()) {
                cli = new Cliente();
                while (rs.next()) {
                    cli.setId_cliente(rs.getInt("id_cli"));
                    cli.setDni(rs.getString("dni"));
                    cli.setNombre(rs.getString("nombre"));
                    cli.setApellido(rs.getString("apellido"));
                    cli.setDireccion(rs.getString("direc")); 
                }

            } catch (Exception e) {
                mensaje = e.getMessage();
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return cli;
    }

    @Override
    public String getMessage() {
        return mensaje; 
    }
    
    
}
