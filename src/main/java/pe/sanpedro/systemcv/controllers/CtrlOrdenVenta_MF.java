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
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainFarmacia;
import pe.sanpedro.systemcv.view.PnlOrdenVenta_MF;

/**
 *
 * @author alons
 */
public class CtrlOrdenVenta_MF {

    private GenericDao daoOrdenVenta;
    private PnlOrdenVenta_MF pnlOrdenVenta;

    public CtrlOrdenVenta_MF(GenericDao daoOrdenVenta) {
        this.daoOrdenVenta = daoOrdenVenta;

    }

    public void initController() {

        pnlOrdenVenta = new PnlOrdenVenta_MF();
        mostrarmedicamentos();
        pnlOrdenVenta.getBtnAgregar().addActionListener(e -> agregarMedicamento());
        pnlOrdenVenta.getBtnBuscar().addActionListener(e -> buscarMedicamentos());
        pnlOrdenVenta.getBtnBuscarCliente().addActionListener(e -> buscarCliente());
        pnlOrdenVenta.getBtnCancelar().addActionListener(e -> cancelar());
        pnlOrdenVenta.getBtnLimpiar().addActionListener(e -> limpiar());
        pnlOrdenVenta.getBtnCrearOrden().addActionListener(e -> generarOrdenVenta());
        pnlOrdenVenta.getBtnQuitarDetalle().addActionListener(e -> quitarMedicamentosVenta());
        pnlOrdenVenta.getBtn_mostrarTodo().addActionListener(e -> mostrarTodo());

        CardLayout vista = (CardLayout) FrmMainFarmacia.Pnl_VP.getLayout();
        FrmMainFarmacia.Pnl_VP.add(pnlOrdenVenta, "Orden de Venta");
        vista.show(FrmMainFarmacia.Pnl_VP, "Orden de Venta");
        CtrlMF.frmMF.repaint();
    }

    private void mostrarTodo() {
        mostrarmedicamentos();
    }

    private void agregarMedicamento() {
        int cant=0;
        if (!pnlOrdenVenta.getTxtCantidad().getText().equalsIgnoreCase("") && pnlOrdenVenta.getJTableMed().getSelectedRow() != -1) {
            Object obS =pnlOrdenVenta.getJTableMed().getModel().getValueAt(pnlOrdenVenta.getJTableMed().getSelectedRow(), 3);
            int st = Integer.parseInt(String.valueOf(obS));
            cant = Integer.parseInt(String.valueOf(pnlOrdenVenta.getTxtCantidad().getText()));
            if (st-cant >=2) {
                Object oj = pnlOrdenVenta.getJTableMed().getModel().getValueAt(pnlOrdenVenta.getJTableMed().getSelectedRow(), 0);
                Object ob2 = pnlOrdenVenta.getJTableMed().getModel().getValueAt(pnlOrdenVenta.getJTableMed().getSelectedRow(), 1);
                Object ob3 = pnlOrdenVenta.getJTableMed().getModel().getValueAt(pnlOrdenVenta.getJTableMed().getSelectedRow(), 2);
                DefaultTableModel model = (DefaultTableModel) pnlOrdenVenta.getJTableDetalle().getModel();
                double importe = Double.parseDouble(String.valueOf(ob3)) * cant;
                Object[] fila = new Object[5];
                fila[0] = oj;
                fila[1] = pnlOrdenVenta.getTxtCantidad().getText();
                fila[2] = ob2;
                fila[3] = ob3;
                fila[4] = importe;
                model.addRow(fila);
                pnlOrdenVenta.getJTableDetalle().setModel(model);
                double subtotal = Double.parseDouble(String.valueOf(ob3)) * cant;
                double totalS = 0;
                if (!"".equals(pnlOrdenVenta.getTxtTotal().getText())) {
                    totalS = Double.parseDouble(pnlOrdenVenta.getTxtTotal().getText());
                }
                totalS = totalS + subtotal;
                pnlOrdenVenta.getTxtTotal().setText(String.valueOf(totalS));  
            }else{
                JOptionPane.showMessageDialog(null, "No Suficiente Stock para la cantidad demandada");
            }           
            
        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad y seleccionar un medicamento", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarCliente() {
        daoOrdenVenta = new DaoClienteImpl();
        String dni = pnlOrdenVenta.getTxtDNIBuscar().getText();
        if (!"".equals(dni)) {
            Cliente cli = (Cliente) daoOrdenVenta.searchByQuery2(dni);
            if (cli != null) {
                pnlOrdenVenta.getTxt_idCli().setText(String.valueOf(cli.getId_cliente()));
                pnlOrdenVenta.getTxtDNI().setText(cli.getDni());
                pnlOrdenVenta.getTxtNombre().setText(cli.getNombre());
                pnlOrdenVenta.getTxtApellido().setText(cli.getApellido());
                pnlOrdenVenta.getTxtDireccion().setText(cli.getDireccion());
            } else {
                JOptionPane.showMessageDialog(null, daoOrdenVenta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void buscarMedicamentos() {
        if (!pnlOrdenVenta.getTxtMedicamento().getText().equalsIgnoreCase("")) {
            daoOrdenVenta = new DaoMedicamentosImpl();
            String query = pnlOrdenVenta.getTxtMedicamento().getText();
            if (!"".equals(query)) {
                Medicamentos med = (Medicamentos) daoOrdenVenta.searchByQuery2(query);
                if (med != null) {
                    mostrarListaMedicamento(query);
                } else {
                    JOptionPane.showMessageDialog(null, daoOrdenVenta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de un medicamento por favor");

        }

    }

    private void mostrarListaMedicamento(String query) {
        daoOrdenVenta = new DaoMedicamentosImpl();
        List<Medicamentos> lista = daoOrdenVenta.searchByQuery(query);
        if (0 == lista.size()) {
            JOptionPane.showMessageDialog(null, "Medicamento no encontrado", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        } else {
            refrescarListaMedicamentos(lista);
        }

    }

    private void refrescarListaMedicamentos(List<Medicamentos> lista) {
        ((DefaultTableModel) pnlOrdenVenta.getJTableMed().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlOrdenVenta.getJTableMed().getModel();
        if (lista != null) {
            lista.forEach((n) -> {
                Object[] fila = new Object[8];
                fila[0] = n.getID_Med();
                fila[1] = n.getNombre();
                fila[2] = n.getF_elab();
                fila[3] = n.getF_venci();
                fila[4] = n.getPrecio();
                fila[5] = n.getStock();
                fila[6] = n.getPresent();
                fila[7] = n.getLab();
                model.addRow(fila);
            });
            pnlOrdenVenta.getJTableMed().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoOrdenVenta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cancelar() {
        initController();
    }

    private void limpiar() {
        pnlOrdenVenta.getTxtDNIBuscar().setText("");
        pnlOrdenVenta.getTxtDNI().setText("");
        pnlOrdenVenta.getTxtNombre().setText("");
        pnlOrdenVenta.getTxtApellido().setText("");
        pnlOrdenVenta.getTxtDireccion().setText("");
    }

    private void generarOrdenVenta() {
        int canT = 0;

        int id_cli = Integer.parseInt(pnlOrdenVenta.getTxt_idCli().getText());
        String dni = pnlOrdenVenta.getTxtDNI().getText();
        String nombreCli = pnlOrdenVenta.getTxtNombre().getText() + " " + pnlOrdenVenta.getTxtApellido().getText();
        String direcCli = pnlOrdenVenta.getTxtDireccion().getText();
        for (int i = 0; i < pnlOrdenVenta.getJTableDetalle().getModel().getRowCount(); i++) {
            canT = canT + Integer.parseInt(pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 1).toString()); //Para examenes y medicamentos cambiara
        }

        int id_estado = 1;//Para citas y  medicamentos se empieza con 1
        OrdenPedido ordenP = new OrdenPedido(id_cli, 4, dni, nombreCli, direcCli, canT, id_estado);
        daoOrdenVenta = new DaoOrdenPedidoImpl();
        daoOrdenVenta.insert(ordenP);
        daoOrdenVenta.getMessage();//Borrar        
        int id_orden = daoOrdenVenta.searchById4(4, id_cli);// el numero 3 dependende del tipo de serivio o producto 
        generarDetallePedido(id_orden);
        initController();
        JOptionPane.showMessageDialog(null, "Orden creada N°" + id_orden);

    }

    private void generarDetallePedido(int id_orden) {
        List<DetallePedido> listaDetalle = new ArrayList();
        for (int i = 0; i < pnlOrdenVenta.getJTableDetalle().getModel().getRowCount(); i++) {
            Object ob = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 0);
            Object ob1 = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 1);
            Object ob2 = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 2);
            Object ob3 = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 3);
            Object ob4 = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(i, 4);

            listaDetalle.add(new DetallePedido(id_orden, Integer.parseInt(String.valueOf(ob)), Integer.parseInt(String.valueOf(ob1)), String.valueOf(ob2), Double.parseDouble(String.valueOf(ob3)), Double.parseDouble(String.valueOf(ob4))));
        }
        daoOrdenVenta = new DaoDetallePedidoImpl();
        daoOrdenVenta.insert2(listaDetalle, false);
        daoOrdenVenta.getMessage();//Borrar       
    }

    private void quitarMedicamentosVenta() {
        if (pnlOrdenVenta.getJTableMed().getSelectedRow() != -1) {
            DefaultTableModel model1 = (DefaultTableModel) pnlOrdenVenta.getJTableDetalle().getModel();
            Object ob2 = pnlOrdenVenta.getJTableDetalle().getModel().getValueAt(pnlOrdenVenta.getJTableDetalle().getSelectedRow(), 4);

            model1.removeRow(pnlOrdenVenta.getJTableDetalle().getSelectedRow());
            pnlOrdenVenta.getJTableDetalle().setModel(model1);
            double total = Double.parseDouble(pnlOrdenVenta.getTxtTotal().getText());
            double subtotal = Double.parseDouble(String.valueOf(ob2));
            total = total - subtotal;
            pnlOrdenVenta.getTxtTotal().setText(String.valueOf(total));
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila por favor");
        }
    }

    private void mostrarmedicamentos() {
        daoOrdenVenta = new DaoMedicamentosImpl();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Med");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Presentacion");
        List<Medicamentos> lista = daoOrdenVenta.sel();
        if (lista != null) {
            lista.forEach((m) -> {
                Object[] fila = new Object[5];
                fila[0] = m.getID_Med();
                fila[1] = m.getNombre();
                fila[2] = m.getPrecio();
                fila[3] = m.getStock();
                fila[4] = m.getPresent();
                modelo.addRow(fila);
            });
            pnlOrdenVenta.getJTableMed().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoOrdenVenta.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

}
