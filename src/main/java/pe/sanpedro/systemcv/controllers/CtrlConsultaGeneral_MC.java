package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoBoletaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoHorariosImpl;
import pe.sanpedro.systemcv.dao.impl.DaoInfoEmpresaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.dto.Boleta;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.Horarios;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainCaja;
import pe.sanpedro.systemcv.view.PnlConsultaGeneral_MC;

/**
 *
 * @author Mysk
 */
public class CtrlConsultaGeneral_MC {

    private GenericDao daoConsulta;
    private PnlConsultaGeneral_MC pnlConsultaG;

    public CtrlConsultaGeneral_MC() {

    }

    public void initController() {        
        pnlConsultaG = new PnlConsultaGeneral_MC();
        mostrarHorarios();
        pnlConsultaG.getBtn_buscarCli().addActionListener(e -> buscarCliente());
        pnlConsultaG.getBtn_buscarM().addActionListener(e -> buscarMascota());
        pnlConsultaG.getBtn_agregarM().addActionListener(e -> agregarMascota());
        pnlConsultaG.getBtn_buscarH().addActionListener(e -> buscarHorario());
        pnlConsultaG.getBtn_agregarH().addActionListener(e -> agregarHorario());
        pnlConsultaG.getBtn_generarBoleta().addActionListener(e -> generarBoleta());
        pnlConsultaG.getBtn_cambiarCli().addActionListener(e-> cambiarCliente());
        pnlConsultaG.getBtn_cobrar().addActionListener(e -> cobrar());
        CardLayout vista = (CardLayout) FrmMainCaja.Pnl_VP.getLayout();
        FrmMainCaja.Pnl_VP.add(pnlConsultaG, "Consulta");
        vista.show(FrmMainCaja.Pnl_VP, "Consulta");
        SwingUtilities.updateComponentTreeUI(CtrlMC.frmMC);
        CtrlMC.frmMC.repaint();
    }
    
    private void mostrarHorarios(){
        daoConsulta = new DaoHorariosImpl();
        List<Horarios> listaHorarios = daoConsulta.searchByDate(LocalDate.now());
        refrescarHorarios(listaHorarios);
    }
    
    private void refrescarHorarios (List<Horarios> listaHorarios) {
        ((DefaultTableModel) pnlConsultaG.getJtbl2().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlConsultaG.getJtbl2().getModel();
        if (listaHorarios != null) {
            listaHorarios.forEach((t) -> {
                Object[] fila = new Object[6];
                fila[0] = t.getId_horario();                
                fila[1] = t.getDia();
                fila[2] = t.getHora_i();
                fila[3] = t.getHora_f();
                fila[4] = t.getTurno();
                fila[5] = t.getEstado();
                model.addRow(fila);
            });
            pnlConsultaG.getJtbl2().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoConsulta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    private void buscarCliente() {
        daoConsulta = new DaoClienteImpl();
        String dni = pnlConsultaG.getTxt_buscarCli().getText();
        if (!"".equals(dni)) {
            Cliente cli = (Cliente) daoConsulta.searchByQuery2(dni);
            if (cli != null) {
                pnlConsultaG.getTxt_idCli().setText(String.valueOf(cli.getId_cliente()));
                pnlConsultaG.getTxt_dniCli().setText(cli.getDni());
                pnlConsultaG.getTxt_nombCli().setText(cli.getNombre());
                pnlConsultaG.getTxt_apeCli().setText(cli.getApellido());
                pnlConsultaG.getTxt_direcCli().setText(cli.getDireccion());
                mostrarMascotas(cli.getId_cliente());
            } else {
                JOptionPane.showMessageDialog(null, daoConsulta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void mostrarMascotas(int id_cli) {
        daoConsulta = new DaoMascotaImpl();
        List<Mascota> listaMascota = daoConsulta.searchById2(id_cli);
        refrescarMascotas(listaMascota);

    }

    private void refrescarMascotas(List<Mascota> listaMascota) {
        ((DefaultTableModel) pnlConsultaG.getJtbl1().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlConsultaG.getJtbl1().getModel();
        if (listaMascota != null) {
            listaMascota.forEach((t) -> {
                Object[] fila = new Object[5];
                fila[0] = t.getId_mascota();
                fila[1] = t.getNom_mascota();
                fila[2] = t.getEdad();
                fila[3] = t.getNom_especie();
                fila[4] = t.getNom_raza();
                model.addRow(fila);
            });
            pnlConsultaG.getJtbl1().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoConsulta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarMascota() {
        daoConsulta = new DaoMascotaImpl();
        String query = pnlConsultaG.getTxt_buscarM().getText();
        if ((!"".equals(query))) {
            Mascota mascota = (Mascota) daoConsulta.searchByQuery2(query);
            if (mascota != null) {
                List<Mascota> listaMascota = new ArrayList();
                listaMascota.add(mascota);
                refrescarMascotas(listaMascota);
            }
        }

    }

    private void agregarMascota() {
        Object ob = pnlConsultaG.getJtbl1().getModel().getValueAt(pnlConsultaG.getJtbl1().getSelectedRow(), 0);
        Object ob2 = pnlConsultaG.getJtbl1().getModel().getValueAt(pnlConsultaG.getJtbl1().getSelectedRow(), 4);
        Object ob3 = pnlConsultaG.getJtbl1().getModel().getValueAt(pnlConsultaG.getJtbl1().getSelectedRow(), 1);        
        // refrescarServicios(listaServicios);
        pnlConsultaG.getTxt_idM().setText(String.valueOf(ob));
        pnlConsultaG.getTxt_nombM().setText(String.valueOf(ob3));
        pnlConsultaG.getTxt_especieM().setText(String.valueOf(ob2));
    }
    
    private void buscarHorario(){
        daoConsulta = new DaoHorariosImpl();
        int t = pnlConsultaG.getCbx().getSelectedIndex();
        String turno;
        if (t==0) {
          turno="Mañana";  
        }else{
            turno="Tarde";
        }
        List<Horarios> listaHorarios = daoConsulta.searchByDate2(turno, LocalDate.now());
        refrescarHorarios(listaHorarios);
    }
    
    private void agregarHorario(){
        Object ob = pnlConsultaG.getJtbl2().getModel().getValueAt(pnlConsultaG.getJtbl2().getSelectedRow(), 0);
        Object ob2 = pnlConsultaG.getJtbl2().getModel().getValueAt(pnlConsultaG.getJtbl2().getSelectedRow(), 2);
        Object ob3 = pnlConsultaG.getJtbl2().getModel().getValueAt(pnlConsultaG.getJtbl2().getSelectedRow(), 4);
        Object ob4 = pnlConsultaG.getJtbl2().getModel().getValueAt(pnlConsultaG.getJtbl2().getSelectedRow(), 5);
        if (!String.valueOf(ob4).equalsIgnoreCase("Ocupado")){
            pnlConsultaG.getTxt_idH().setText(String.valueOf(ob));
            pnlConsultaG.getTxt_nombH().setText(String.valueOf(ob3));
            pnlConsultaG.getTxt_turnoH().setText(String.valueOf(ob2));
            double total = 50;
            pnlConsultaG.getTxt_total().setText(String.valueOf(total));   
        }else{
            JOptionPane.showMessageDialog(null,"Horario ocupado");
        }      
       
    }
    
    private void cobrar(){
        double total = Double.parseDouble( pnlConsultaG.getTxt_total().getText());
        if (!"".equals( pnlConsultaG.getTxt_ingresado().getText())) {
            double importe = Double.parseDouble( pnlConsultaG.getTxt_ingresado().getText());
            double vuelto = importe - total;
            if (vuelto >= 0) {
                pnlConsultaG.getTxt_total().setText(String.valueOf(total));
                pnlConsultaG.getTxt_ingresado().setEditable(false);
                pnlConsultaG.getTxt_devolucion().setText(String.valueOf(vuelto));
                pnlConsultaG.getBtn_generarBoleta().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Las cuentas no cuadran :( ", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private int generarOrdenPedido() {
        int id_cli = Integer.parseInt(pnlConsultaG.getTxt_idCli().getText());
        String dni =pnlConsultaG.getTxt_dniCli().getText();
        String nombreCli = pnlConsultaG.getTxt_nombCli().getText() + " " + pnlConsultaG.getTxt_apeCli().getText();
        String direcCli = pnlConsultaG.getTxt_direcCli().getText();    
        int id_estado = 2;//Para citas y  medicamentos se empieza con 1
        OrdenPedido ordenP = new OrdenPedido(id_cli, 1, dni, nombreCli, direcCli, 1, id_estado);
        daoConsulta = new DaoOrdenPedidoImpl();
        daoConsulta.insert(ordenP);
        daoConsulta.getMessage();//Borrar        
        int id_orden = daoConsulta.searchById4(1, id_cli);// el numero 3 dependende del tipo de serivio o producto      
       
        Horarios h = new Horarios();
        h.setId_horario(Integer.parseInt(pnlConsultaG.getTxt_idH().getText()));
        h.setId_estado(2);
        daoConsulta = new DaoHorariosImpl();
        daoConsulta.update(h);
        daoConsulta.getMessage();
        
        return id_orden;
    }
    private List<DetallePedido> generarDetallePedido(int id_orden) {              
        DetallePedido detalleP = new DetallePedido(id_orden, 1, 1, "Consulta General", 50, 50);
        List<DetallePedido> listaDetalle = new ArrayList();
        listaDetalle.add(detalleP);
        daoConsulta = new DaoDetallePedidoImpl();
        daoConsulta.insert2(listaDetalle, true);
        daoConsulta.getMessage();//Borrar
        return listaDetalle;
    }
    private void generarBoleta() {
        //Tercero generamos boleta modificado
        String dni =pnlConsultaG.getTxt_dniCli().getText();
        String nombreCli = pnlConsultaG.getTxt_nombCli().getText() + " " + pnlConsultaG.getTxt_apeCli().getText();
        String direcCli = pnlConsultaG.getTxt_direcCli().getText();

        int id_orden = generarOrdenPedido();
        List<DetallePedido> listaDetalle = generarDetallePedido(id_orden);

        int idTrabajador = CtrlMC.idT;
        double importeTotal = Double.parseDouble(pnlConsultaG.getTxt_total().getText());
        double montoIngresado = Double.parseDouble(pnlConsultaG.getTxt_ingresado().getText());
        double vuelto = Double.parseDouble(pnlConsultaG.getTxt_devolucion().getText());
        Boleta boleta = new Boleta(id_orden, idTrabajador, LocalDate.now().toString(), importeTotal, montoIngresado, vuelto);
        daoConsulta = new DaoBoletaImpl();
        daoConsulta.insert(boleta);
        daoConsulta.getMessage();

        //JASPER
        InputStream arch = CtrlEstetica_MC.class.getClassLoader().getResourceAsStream("informes/Factura.jrxml");
        System.out.println("Ubicacion de reporte " + arch.toString());
        daoConsulta = new DaoInfoEmpresaImpl();
        List<InfoEmpresa> info = daoConsulta.sel();

        List<Boleta> lr = new ArrayList();
        listaDetalle.forEach((t) -> {
            lr.add(new Boleta(info.get(0).getNombreE(), info.get(0).getRuc(), info.get(0).getPropiertario(), info.get(0).getDirec(), info.get(0).getTelf(), info.get(0).getCel(), info.get(0).getCorreo(),
                    t.getId_orden(), nombreCli, direcCli, dni, LocalDate.now().toString(), t.getCantidad(), t.getDescripcion(), t.getPrecio(),
                    t.getSubtotal(), montoIngresado, importeTotal, vuelto));
        });
        try {
            JasperReport jr = JasperCompileManager.compileReport(arch);
            Map<String, Object> pr = new HashMap<>();
            JRBeanCollectionDataSource jbcd = new JRBeanCollectionDataSource(lr);
            JasperPrint jp = JasperFillManager.fillReport(jr, pr, jbcd);
            JasperViewer.viewReport(jp,false);
            initController();
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
    private void cambiarCliente(){
        pnlConsultaG.limpiarCliente();
        pnlConsultaG.limpiarMascota();
        ((DefaultTableModel) pnlConsultaG.getJtbl1().getModel()).setNumRows(0);
    }

}
