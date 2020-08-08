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
import pe.sanpedro.systemcv.dao.impl.DaoInfoEmpresaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoServiciosImpl;
import pe.sanpedro.systemcv.dto.Boleta;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.model.Servicios;
import pe.sanpedro.systemcv.view.FrmMainCaja;
import pe.sanpedro.systemcv.view.PnlExamenes_MC;

/**
 *
 * @author Mysk
 */
public class CtrlExamenes_MC {

    private GenericDao daoExamen;
    private PnlExamenes_MC pnlExamenes;

    public CtrlExamenes_MC() {
    }

    public void initController() {
        pnlExamenes = new PnlExamenes_MC();
        pnlExamenes.getBtn_buscarCli().addActionListener(e -> buscarCliente());
        pnlExamenes.getBtn_cambiarCli().addActionListener(e -> cambiarCliente());
        pnlExamenes.getBtn_buscarM().addActionListener(e -> buscarMascota());
        pnlExamenes.getBtn_agregarM().addActionListener(e -> agregarMascota());
        pnlExamenes.getBtn_agregarE().addActionListener(e -> agregarExamen());
        pnlExamenes.getBtn_cambiarM().addActionListener(e -> cambiarMascota());
        pnlExamenes.getBtn_quitarE().addActionListener(e -> quitarExamen());
        pnlExamenes.getBtn_cobrar().addActionListener(e -> cobrar());
        pnlExamenes.getBtn_generarBoleta().addActionListener(e -> generarBoleta());
        CardLayout vista = (CardLayout) FrmMainCaja.Pnl_VP.getLayout();
        FrmMainCaja.Pnl_VP.add(pnlExamenes, "Examenes");
        vista.show(FrmMainCaja.Pnl_VP, "Examenes");
        SwingUtilities.updateComponentTreeUI(CtrlMC.frmMC);
        CtrlMC.frmMC.repaint();

    }

    private void buscarCliente() {
        daoExamen = new DaoClienteImpl();
        String dni = pnlExamenes.getTxt_buscarCli().getText();
        if (!"".equals(dni)) {
            Cliente cli = (Cliente) daoExamen.searchByQuery2(dni);
            if (cli != null) {
                pnlExamenes.getTxt_idCli().setText(String.valueOf(cli.getId_cliente()));
                pnlExamenes.getTxt_dniCli().setText(cli.getDni());
                pnlExamenes.getTxt_nombCli().setText(cli.getNombre());
                pnlExamenes.getTxt_apeCli().setText(cli.getApellido());
                pnlExamenes.getTxt_direcCli().setText(cli.getDireccion());
                mostrarMascotas(cli.getId_cliente());
            } else {
                JOptionPane.showMessageDialog(null, daoExamen.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void mostrarMascotas(int id_cli) {
        daoExamen = new DaoMascotaImpl();
        List<Mascota> listaMascota = daoExamen.searchById2(id_cli);
        refrescarMascotas(listaMascota);

    }

    private void refrescarMascotas(List<Mascota> listaMascota) {
        ((DefaultTableModel) pnlExamenes.getJtbl1().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlExamenes.getJtbl1().getModel();
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
            pnlExamenes.getJtbl1().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoExamen.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refrescarExamenes(List<Servicios> listaExamenes) {
        ((DefaultTableModel) pnlExamenes.getJtbl2().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlExamenes.getJtbl2().getModel();
        if (listaExamenes != null) {
            listaExamenes.forEach((t) -> {
                Object[] fila = new Object[3];
                fila[0] = t.getId_servicios();
                fila[1] = t.getNombreS();
                fila[2] = t.getTarifa();
                model.addRow(fila);
            });
            pnlExamenes.getJtbl2().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoExamen.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void agregarMascota() {
        Object ob = pnlExamenes.getJtbl1().getModel().getValueAt(pnlExamenes.getJtbl1().getSelectedRow(), 0);
        Object ob2 = pnlExamenes.getJtbl1().getModel().getValueAt(pnlExamenes.getJtbl1().getSelectedRow(), 4);
        Object ob3 = pnlExamenes.getJtbl1().getModel().getValueAt(pnlExamenes.getJtbl1().getSelectedRow(), 1);
        daoExamen = new DaoMascotaImpl();
        Mascota masc = (Mascota) daoExamen.searchById(Integer.parseInt(String.valueOf(ob)));
        daoExamen = new DaoServiciosImpl();
        List<Servicios> listaExamenes = (List<Servicios>) daoExamen.searchById3(3, masc.getId_especie());
        if (listaExamenes != null) {
            refrescarExamenes(listaExamenes);
            pnlExamenes.getTxt_idM().setText(String.valueOf(ob));
            pnlExamenes.getTxt_nombM().setText(String.valueOf(ob3));
            pnlExamenes.getTxt_especieM().setText(String.valueOf(ob2));
        }
    }

    private void buscarMascota() {
        daoExamen = new DaoMascotaImpl();
        String query = pnlExamenes.getTxt_buscarM().getText();
        if ((!"".equals(query))) {
            Mascota mascota = (Mascota) daoExamen.searchByQuery2(query);
            if (mascota != null) {
                List<Mascota> listaMascota = new ArrayList();
                listaMascota.add(mascota);
                refrescarMascotas(listaMascota);
            }
        }

    }

    private void agregarExamen() {
        Object ob = pnlExamenes.getJtbl2().getModel().getValueAt(pnlExamenes.getJtbl2().getSelectedRow(), 0);
        Object ob2 = pnlExamenes.getJtbl2().getModel().getValueAt(pnlExamenes.getJtbl2().getSelectedRow(), 1);
        Object ob3 = pnlExamenes.getJtbl2().getModel().getValueAt(pnlExamenes.getJtbl2().getSelectedRow(), 2);
        DefaultTableModel model = (DefaultTableModel) pnlExamenes.getJtbl3().getModel();
        Object[] fila = new Object[4];
        fila[0] = ob;
        fila[1] = 1;
        fila[2] = ob2;
        fila[3] = ob3;
        model.addRow(fila);
        pnlExamenes.getJtbl3().setModel(model);
        double subtotal = Double.parseDouble(String.valueOf(ob3));
        double total = 0;
        if (!"".equals(pnlExamenes.getTxt_total().getText())) {
            total = Double.parseDouble(pnlExamenes.getTxt_total().getText());
        }
        total = total + subtotal;
        pnlExamenes.getTxt_total().setText(String.valueOf(total));
        //Quitamos de la lista al seleccionado        
        DefaultTableModel model2 = (DefaultTableModel) pnlExamenes.getJtbl2().getModel();
        model2.removeRow(pnlExamenes.getJtbl2().getSelectedRow());
        pnlExamenes.getJtbl2().setModel(model2);
    }

    private void quitarExamen() {
        DefaultTableModel model1 = (DefaultTableModel) pnlExamenes.getJtbl3().getModel();
        DefaultTableModel model2 = (DefaultTableModel) pnlExamenes.getJtbl2().getModel();

        Object ob = pnlExamenes.getJtbl3().getModel().getValueAt(pnlExamenes.getJtbl3().getSelectedRow(), 0);
        Object ob2 = pnlExamenes.getJtbl3().getModel().getValueAt(pnlExamenes.getJtbl3().getSelectedRow(), 2);
        Object ob3 = pnlExamenes.getJtbl3().getModel().getValueAt(pnlExamenes.getJtbl3().getSelectedRow(), 3);
        Object[] fila = new Object[3];
        fila[0] = ob;
        fila[1] = ob2;
        fila[2] = ob3;
        model2.addRow(fila);
        model1.removeRow(pnlExamenes.getJtbl3().getSelectedRow());
        pnlExamenes.getJtbl2().setModel(model2);
        pnlExamenes.getJtbl3().setModel(model1);
        double total = Double.parseDouble(pnlExamenes.getTxt_total().getText());
        double subtotal = Double.parseDouble(String.valueOf(ob3));
        total = total - subtotal;
        pnlExamenes.getTxt_total().setText(String.valueOf(total));
    }

    private void cobrar() {
        double total = Double.parseDouble(pnlExamenes.getTxt_total().getText());
        if (!"".equals(pnlExamenes.getTxt_ingresado().getText())) {
            double importe = Double.parseDouble(pnlExamenes.getTxt_ingresado().getText());
            double vuelto = importe - total;
            if (vuelto >= 0) {
                pnlExamenes.getTxt_total().setText(String.valueOf(total));
                pnlExamenes.getTxt_ingresado().setEditable(false);
                pnlExamenes.getTxt_devolucion().setText(String.valueOf(vuelto));
                pnlExamenes.getBtn_generarBoleta().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Las cuentas no cuadran :( ", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private int generarOrdenPedido() {
        int id_cli = Integer.parseInt(pnlExamenes.getTxt_idCli().getText());
        String dni = pnlExamenes.getTxt_dniCli().getText();
        String nombreCli = pnlExamenes.getTxt_nombCli().getText() + " " + pnlExamenes.getTxt_apeCli().getText();
        String direcCli = pnlExamenes.getTxt_direcCli().getText();
        int cantidad = pnlExamenes.getJtbl3().getModel().getRowCount(); //Para examenes y medicamentos cambiara
        int id_estado = 2;//Para citas y  medicamentos se empieza con 1
        OrdenPedido ordenP = new OrdenPedido(id_cli, 3, dni, nombreCli, direcCli, cantidad, id_estado);
        daoExamen = new DaoOrdenPedidoImpl();
        daoExamen.insert(ordenP);
        daoExamen.getMessage();//Borrar        
        int id_orden = daoExamen.searchById4(3, id_cli);// el numero 3 dependende del tipo de serivio o producto        
        return id_orden;
    }

    private List<DetallePedido> generarDetallePedido(int id_orden) {
        List<DetallePedido> listaDetalle = new ArrayList();
        for (int i = 0; i < pnlExamenes.getJtbl3().getModel().getRowCount(); i++) {
            Object ob = pnlExamenes.getJtbl3().getModel().getValueAt(i, 0);
            Object ob1 = pnlExamenes.getJtbl3().getModel().getValueAt(i, 1);
            Object ob2 = pnlExamenes.getJtbl3().getModel().getValueAt(i, 2);
            Object ob3 = pnlExamenes.getJtbl3().getModel().getValueAt(i, 3);
            
            listaDetalle.add(new DetallePedido(id_orden,Integer.parseInt(String.valueOf(ob)), 1, String.valueOf(ob2), Double.parseDouble(String.valueOf(ob3)),Double.parseDouble(String.valueOf(ob3))));
        }
        daoExamen = new DaoDetallePedidoImpl();
        daoExamen.insert2(listaDetalle, true);
        daoExamen.getMessage();//Borrar
        return listaDetalle;        
    }
    private void generarBoleta() {
        //Tercero generamos boleta modificado
        String dni =pnlExamenes.getTxt_dniCli().getText();
        String nombreCli = pnlExamenes.getTxt_nombCli().getText() + " " + pnlExamenes.getTxt_apeCli().getText();
        String direcCli = pnlExamenes.getTxt_direcCli().getText();

        int id_orden = generarOrdenPedido();
        List<DetallePedido> listaDetalle = generarDetallePedido(id_orden);

        int idTrabajador = CtrlMC.idT;
        double importeTotal = Double.parseDouble(pnlExamenes.getTxt_total().getText());
        double montoIngresado = Double.parseDouble(pnlExamenes.getTxt_ingresado().getText());
        double vuelto = Double.parseDouble(pnlExamenes.getTxt_devolucion().getText());
        Boleta boleta = new Boleta(id_orden, idTrabajador, LocalDate.now().toString(), importeTotal, montoIngresado, vuelto);
        daoExamen = new DaoBoletaImpl();
        daoExamen.insert(boleta);
        daoExamen.getMessage();

        //JASPER
        InputStream arch = CtrlEstetica_MC.class.getClassLoader().getResourceAsStream("informes/Factura.jrxml");
        System.out.println("Ubicacion de reporte " + arch.toString());
        daoExamen = new DaoInfoEmpresaImpl();
        List<InfoEmpresa> info = daoExamen.sel();

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
    private void cambiarMascota(){
        pnlExamenes.limpiarMascota();
        ((DefaultTableModel) pnlExamenes.getJtbl2().getModel()).setNumRows(0);
        
    }

    private void cambiarCliente() {
       initController();
    }

}
