/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainFarmacia;
import pe.sanpedro.systemcv.view.PnlDespacharPedidos_MF;

/**
 *
 * @author alons
 */
public class CtrlDespacharPedidos_MF {

    private GenericDao daoDespachar;
    private PnlDespacharPedidos_MF pnlDespacharPedidos;

    public CtrlDespacharPedidos_MF(GenericDao daoDespachar) {
        this.daoDespachar = daoDespachar;
    }

    public void initController() {

        pnlDespacharPedidos = new PnlDespacharPedidos_MF();
        mostrarOrdenesPagadas();
        pnlDespacharPedidos.getBtnBuscarOrden().addActionListener(e -> buscarOrden());
        pnlDespacharPedidos.getBtnBuscarOrdenDNI().addActionListener(e -> buscarOrdenDNI());
        pnlDespacharPedidos.getBtnRevisar().addActionListener(e -> revisar());
        pnlDespacharPedidos.getBtnIniciarDespacho().addActionListener(e -> iniciarDespacho());
        pnlDespacharPedidos.getBtnLimpiar().addActionListener(e -> limpiar());
        pnlDespacharPedidos.getBtnLimpiarOrden().addActionListener(e -> limpiarOrden());

        CardLayout vista = (CardLayout) FrmMainFarmacia.Pnl_VP.getLayout();
        FrmMainFarmacia.Pnl_VP.add(pnlDespacharPedidos, "Despachar Pedidos");
        vista.show(FrmMainFarmacia.Pnl_VP, "Despachar Pedidos");
        CtrlMF.frmMF.repaint();
    }

    private void buscarOrden() {
        daoDespachar = new DaoOrdenPedidoImpl();
        int id_orden = Integer.parseInt(pnlDespacharPedidos.getTxtIDOrden().getText());
        if (!"".equals(id_orden)) {
            OrdenPedido dp = (OrdenPedido) daoDespachar.searchById(id_orden);
            if (dp != null) {
                mostrarOrden(id_orden);
                pnlDespacharPedidos.getTxtNumOrd().setText(String.valueOf(id_orden));
                pnlDespacharPedidos.getTxtNombre().setText(dp.getNombreCli());
                pnlDespacharPedidos.getTxtDNI().setText(dp.getDni());
                pnlDespacharPedidos.getTxtEstado().setText(dp.getNombEstado());
            } else {
                JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void mostrarOrden(int id_orden) {
        daoDespachar = new DaoOrdenPedidoImpl();
        List<OrdenPedido> lista = daoDespachar.searchById2(id_orden);
        refrescarOrdenPedido(lista);

    }

    private void refrescarOrdenPedido(List<OrdenPedido> lista) {
        ((DefaultTableModel) pnlDespacharPedidos.getJTableMedicamentos().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlDespacharPedidos.getJTableMedicamentos().getModel();
        if (lista != null) {
            lista.forEach((t) -> {
                Object[] fila = new Object[5];
                fila[0] = t.getId_med();
                fila[1] = t.getCantidad();
                fila[2] = t.getDescrip();
                fila[3] = t.getPrecio();
                fila[4] = t.getSubtotal();
                model.addRow(fila);
            });
            pnlDespacharPedidos.getJTableMedicamentos().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarOrdenesPagadas() {
        daoDespachar = new DaoOrdenPedidoImpl();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID orden");
        modelo.addColumn("DNI");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Estado");
        List<OrdenPedido> lista = daoDespachar.sel1();
        if (lista != null) {
            lista.forEach((m) -> {
                Object[] fila = new Object[4];
                fila[0] = m.getId_orden();
                fila[1] = m.getDni();
                fila[2] = m.getCantidad();
                fila[3] = m.getNombEstado();
                modelo.addRow(fila);
            });
            pnlDespacharPedidos.getJTableOrden().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void buscarOrdenDNI() {
        daoDespachar = new DaoOrdenPedidoImpl();
        String dni = pnlDespacharPedidos.getTxtOrdenDNI().getText();
        if (!"".equals(dni)) {
            OrdenPedido op = (OrdenPedido) daoDespachar.searchByQuery2(dni);
            daoDespachar.getMessage();
            if (op != null) {
                mostrarOrdenPagada(dni);

            } else {
                JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void mostrarOrdenPagada(String dni) {
        daoDespachar = new DaoOrdenPedidoImpl();
        List<OrdenPedido> listaOrdenPagada = daoDespachar.searchByQuery(dni);
        if (listaOrdenPagada == null) {
            daoDespachar.getMessage();
        }
        refrescarOrdenPagada(listaOrdenPagada);
    }

    private void refrescarOrdenPagada(List<OrdenPedido> listaOrdenPagada) {
        ((DefaultTableModel) pnlDespacharPedidos.getJTableOrden().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlDespacharPedidos.getJTableOrden().getModel();
        if (listaOrdenPagada != null) {
            listaOrdenPagada.forEach((m) -> {
                Object[] fila = new Object[4];
                fila[0] = m.getId_orden();
                fila[1] = m.getDni();
                fila[2] = m.getCantidad();
                fila[3] = m.getNombEstado();
                model.addRow(fila);
            });
            pnlDespacharPedidos.getJTableOrden().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void iniciarDespacho() {
        
        if (pnlDespacharPedidos.getTxtEstado().getText().equals("Pagado")) {
            daoDespachar = new DaoOrdenPedidoImpl();
            int num = Integer.parseInt(pnlDespacharPedidos.getTxtNumOrd().getText());
            daoDespachar.update2(num, 3);
            JOptionPane.showMessageDialog(null, "Su orden está siendo atendido en el almacén.");
            initController();
        } else {
            JOptionPane.showMessageDialog(null, "Debe cancelar su orden primero en caja.");

        }
        
    }

    private void limpiarOrden() {
        pnlDespacharPedidos.getTxtIDOrden().setText("");
        pnlDespacharPedidos.getTxtDNI().setText("");
        pnlDespacharPedidos.getTxtNombre().setText("");
        pnlDespacharPedidos.getTxtEstado().setText("");
        pnlDespacharPedidos.getTxtNumOrd().setText("");
    }

    private void limpiar() {
        pnlDespacharPedidos.getTxtOrdenDNI().setText("");
    }

    private void revisar() {
        daoDespachar = new DaoOrdenPedidoImpl();
        Object ob = pnlDespacharPedidos.getJTableOrden().getModel().getValueAt(pnlDespacharPedidos.getJTableOrden().getSelectedRow(), 0);
        int id_orden = Integer.parseInt(String.valueOf(ob));
        if (!"".equals(id_orden)) {
            OrdenPedido dp = (OrdenPedido) daoDespachar.searchById(id_orden);
            if (dp != null) {
                mostrarOrden(id_orden);
                pnlDespacharPedidos.getTxtIDOrden().setText(String.valueOf(ob));
                pnlDespacharPedidos.getTxtNombre().setText(dp.getNombreCli());
                pnlDespacharPedidos.getTxtDNI().setText(dp.getDni());
                pnlDespacharPedidos.getTxtEstado().setText(dp.getNombEstado());
                pnlDespacharPedidos.getTxtNumOrd().setText(String.valueOf(ob));
            } else {
                JOptionPane.showMessageDialog(null, daoDespachar.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

}
