package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlDespachoPro_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlDespachoM_MAL {

    private GenericDao daoDespacho;
    private PnlDespachoPro_MAL pnlDespacho;

    public CtrlDespachoM_MAL() {
    }

    public void initController() {
        pnlDespacho = new PnlDespachoPro_MAL();
        mostrarPedidos();
        pnlDespacho.getBtnVerPedido().addActionListener(e -> verPedido());
        pnlDespacho.getBtnDespachar().addActionListener(e -> despachar());
        pnlDespacho.getBtnCancelar().addActionListener(e -> cancelar());
        CardLayout vista = (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout();
        FrmMainAlmacen.Pnl_VP.add(pnlDespacho, "Despacho");
        vista.show(FrmMainAlmacen.Pnl_VP, "Despacho");
        CtrlMAL.frmMAL.repaint();
    }

    private void verPedido() {
        if (pnlDespacho.getJTablePedido().getSelectedRow() != -1) {
            daoDespacho = new DaoDetallePedidoImpl();
            Object ob = pnlDespacho.getJTablePedido().getModel().getValueAt(pnlDespacho.getJTablePedido().getSelectedRow(), 0);
            int id_orden = Integer.parseInt(String.valueOf(ob));
            if (!"".equals(id_orden)) {
                DetallePedido dp = (DetallePedido) daoDespacho.searchById3(id_orden);
                if (dp != null) {
                    mostrarOrden(id_orden);
                    pnlDespacho.getBtnDespachar().setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una orden");

        }

    }

    private void despachar() {

        if (pnlDespacho.getJTablePedido().getModel().getValueAt(pnlDespacho.getJTablePedido().getSelectedRow(), 3).equals("En despacho")) {
            System.out.println("" + pnlDespacho.getJTableDetallePedido().getModel().getRowCount());

            for (int i = 0; i < pnlDespacho.getJTableDetallePedido().getModel().getRowCount(); i++) {
                daoDespacho = new DaoMedicamentosImpl();
                int id_med = Integer.parseInt(pnlDespacho.getJTableDetallePedido().getModel().getValueAt(i, 0).toString());
                int cantidad = Integer.parseInt(pnlDespacho.getJTableDetallePedido().getModel().getValueAt(i, 2).toString());

                Medicamentos dp = (Medicamentos) daoDespacho.searchById(id_med);
                int sa = dp.getStock() - cantidad;
                if (dp.getStock() >= cantidad) {
                    daoDespacho.update2(id_med, sa);

                } else {
                    JOptionPane.showMessageDialog(null, "no hay suficiente stock del producto: " + dp.getNombre() + ", el stock es de: " + dp.getStock());
                }

            }
            daoDespacho = new DaoOrdenPedidoImpl();
            int num = Integer.parseInt(String.valueOf(pnlDespacho.getJTablePedido().getModel().getValueAt(pnlDespacho.getJTablePedido().getSelectedRow(), 0)));
            daoDespacho.update2(num, 4);
            JOptionPane.showMessageDialog(null, "La orden acaba de ser procesada.");
            initController();

        } else {
            JOptionPane.showMessageDialog(null, "La orden seleccionada no pertenece a esta área.");

        }

    }

    private void cancelar() {
        initController();
    }

    private void mostrarPedidos() {
        daoDespacho = new DaoOrdenPedidoImpl();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID orden");
        modelo.addColumn("Cantidad total");
        modelo.addColumn("Fecha de emisión");
        modelo.addColumn("Estado");
        List<OrdenPedido> lista = daoDespacho.sel2();
        if (lista != null) {
            lista.forEach((m) -> {
                Object[] fila = new Object[4];
                fila[0] = m.getId_orden();
                fila[1] = m.getCantidad();
                fila[2] = m.getFecha();
                fila[3] = m.getNombEstado();
                modelo.addRow(fila);
            });
            pnlDespacho.getJTablePedido().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refrescarOrdenPedido(List<DetallePedido> lista) {
        ((DefaultTableModel) pnlDespacho.getJTableDetallePedido().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlDespacho.getJTableDetallePedido().getModel();
        if (lista != null) {
            lista.forEach((m) -> {
                Object[] fila = new Object[4];
                fila[0] = m.getId_pro();
                fila[1] = m.getDescripcion();
                fila[2] = m.getCantidad();
                fila[3] = m.getPresentacion();
                model.addRow(fila);
            });
            pnlDespacho.getJTableDetallePedido().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarOrden(int id_orden) {
        daoDespacho = new DaoDetallePedidoImpl();
        List<DetallePedido> lista = daoDespacho.searchById4(id_orden);
        refrescarOrdenPedido(lista);
    }

}
