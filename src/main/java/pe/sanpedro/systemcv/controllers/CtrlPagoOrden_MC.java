
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
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoInfoEmpresaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.dto.Boleta;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainCaja;
import pe.sanpedro.systemcv.view.PnlPagoOrden_MC;

/**
 *
 * @author Mysk
 */
public class CtrlPagoOrden_MC {
    
    private GenericDao daoPagoOrden;
    private PnlPagoOrden_MC pnlPagoOrden;
    double total;
     List<DetallePedido> lista= null;
    public CtrlPagoOrden_MC(){}
    
    public void initController() {
        pnlPagoOrden = new PnlPagoOrden_MC();
        pnlPagoOrden.getBtn_buscarOrden().addActionListener(e -> buscarOrden());
        pnlPagoOrden.getBtn_cobrar().addActionListener(e -> cobrar());
        pnlPagoOrden.getBtn_generarBoleta().addActionListener(e -> generarBoleta());
        pnlPagoOrden.getBtn_cancelar().addActionListener(e -> initController());
        CardLayout vista = (CardLayout) FrmMainCaja.Pnl_VP.getLayout();
        FrmMainCaja.Pnl_VP.add(pnlPagoOrden, "PagoOrden");
        vista.show(FrmMainCaja.Pnl_VP, "PagoOrden");
        SwingUtilities.updateComponentTreeUI(CtrlMC.frmMC);
        CtrlMC.frmMC.repaint();
    }
    private void buscarOrden(){
        total=0;
        daoPagoOrden = new DaoOrdenPedidoImpl();
        int id_orden = Integer.parseInt(pnlPagoOrden.getTxt_buscarOrden().getText());
        OrdenPedido orden = (OrdenPedido) daoPagoOrden.searchById3(id_orden);
        if (orden!=null) {
            pnlPagoOrden.getTxr_idOrden().setText(String.valueOf(orden.getId_orden()));
            pnlPagoOrden.getTxt_idCli().setText(String.valueOf(orden.getId_cliente()));
            pnlPagoOrden.getTxt_dniCli().setText(String.valueOf(orden.getDni()));
            pnlPagoOrden.getTxt_nombCli().setText(String.valueOf(orden.getNombreCli()));
            pnlPagoOrden.getTxt_direcCli().setText(String.valueOf(orden.getDirecCli()));
            pnlPagoOrden.getTxt_cantidadTotal().setText(String.valueOf(orden.getCantidad()));            
            daoPagoOrden = new DaoDetallePedidoImpl();
            lista = daoPagoOrden.searchById2(id_orden);
            refrescarDetalleOrden(lista);
        }else{
            JOptionPane.showMessageDialog(null,"Orden no Encontrada", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
        
               
    }
    private void refrescarDetalleOrden(List<DetallePedido> listaExamenes) {
        ((DefaultTableModel)pnlPagoOrden.getTbl().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlPagoOrden.getTbl().getModel();
       
        if (listaExamenes != null) {            
            listaExamenes.forEach((t) -> {
                Object[] fila = new Object[4];
                fila[0] = t.getCantidad();
                fila[1] = t.getDescripcion();
                fila[2] = t.getPrecio();
                fila[3] = t.getSubtotal();
                double subtotal= t.getSubtotal();
                total= total + subtotal;
                model.addRow(fila);
            });
            pnlPagoOrden.getTbl().setModel(model);
            pnlPagoOrden.getTxt_total().setText(String.valueOf(total));
            
        } else {
            JOptionPane.showMessageDialog(null, daoPagoOrden.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void cobrar(){
        double t = Double.parseDouble(pnlPagoOrden.getTxt_total().getText());
        if (!"".equals(pnlPagoOrden.getTxt_ingresado().getText())) {
            double importe = Double.parseDouble(pnlPagoOrden.getTxt_ingresado().getText());
            double vuelto = importe - t;
            if (vuelto >= 0) {
                pnlPagoOrden.getTxt_total().setText(String.valueOf(total));
                pnlPagoOrden.getTxt_ingresado().setEditable(false);
                pnlPagoOrden.getTxt_devolucion().setText(String.valueOf(vuelto));
                pnlPagoOrden.getBtn_cancelar().setEnabled(true);
                pnlPagoOrden.getBtn_generarBoleta().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Las cuentas no cuadran :( ", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private void generarBoleta() {
        
        String dni = pnlPagoOrden.getTxt_dniCli().getText();
        String nombreCli = pnlPagoOrden.getTxt_nombCli().getText();
        String direcCli = pnlPagoOrden.getTxt_direcCli().getText();
        int id_orden = Integer.parseInt(String.valueOf(pnlPagoOrden.getTxr_idOrden().getText()));     

        int idTrabajador = CtrlMC.idT;
        double importeTotal = Double.parseDouble(pnlPagoOrden.getTxt_total().getText());
        double montoIngresado = Double.parseDouble(pnlPagoOrden.getTxt_ingresado().getText());
        double vuelto = Double.parseDouble(pnlPagoOrden.getTxt_devolucion().getText());
        Boleta boleta = new Boleta(id_orden, idTrabajador, LocalDate.now().toString(), importeTotal, montoIngresado, vuelto);
        daoPagoOrden = new DaoBoletaImpl();
        daoPagoOrden.insert(boleta);
        daoPagoOrden.getMessage();
        daoPagoOrden = new DaoOrdenPedidoImpl();
        daoPagoOrden.update2(id_orden, 2);        
        //JASPER
        InputStream arch = CtrlEstetica_MC.class.getClassLoader().getResourceAsStream("informes/Factura.jrxml");
        System.out.println("Ubicacion de reporte " + arch.toString());
        daoPagoOrden = new DaoInfoEmpresaImpl();
        List<InfoEmpresa> info = daoPagoOrden.sel();

        List<Boleta> lr = new ArrayList();
        lista.forEach((t) -> {
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
        
    
}
