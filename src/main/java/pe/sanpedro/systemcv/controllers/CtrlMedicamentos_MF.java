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
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.view.FrmMainFarmacia;
import pe.sanpedro.systemcv.view.PnlMedicamentos_MF;

/**
 *
 * @author alons
 */
public class CtrlMedicamentos_MF {

    private GenericDao daomedicamentos;
    private PnlMedicamentos_MF pnlMedicamentos;

    public CtrlMedicamentos_MF(GenericDao daomedicamentos) {
        this.daomedicamentos = daomedicamentos;

    }

    public void initController() {

        pnlMedicamentos = new PnlMedicamentos_MF(); //nuevo
        mostrarMedicamentos();
        pnlMedicamentos.getBtnBuscar().addActionListener(e -> buscarMedicamentos());
        pnlMedicamentos.getBtnDescripcion().addActionListener(e -> verDescripcion());

        CardLayout vista = (CardLayout) FrmMainFarmacia.Pnl_VP.getLayout();
        FrmMainFarmacia.Pnl_VP.add(pnlMedicamentos, "Medicamentos");
        vista.show(FrmMainFarmacia.Pnl_VP, "Medicamentos");
        CtrlMF.frmMF.repaint();
    }

    private void mostrarMedicamentos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Medicamento");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha Elaboracion");
        modelo.addColumn("Fecha Vencimiento");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Presentacion");
        modelo.addColumn("Laboratorio");
        List<Medicamentos> lista = daomedicamentos.sel();
        if (lista != null) {
            lista.forEach((m) -> {
                Object[] fila = new Object[8];
                fila[0] = m.getID_Med();
                fila[1] = m.getNombre();
                fila[2] = m.getF_elab();
                fila[3] = m.getF_venci();
                fila[4] = m.getPrecio();
                fila[5] = m.getStock();
                fila[6] = m.getPresent();
                fila[7] = m.getLab();
                modelo.addRow(fila);
            });
            pnlMedicamentos.getJTable().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daomedicamentos.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void verDescripcion() {
        if (pnlMedicamentos.getJTable().getSelectedRow() != -1) {
            Object ob1 = pnlMedicamentos.getJTable().getModel().getValueAt(pnlMedicamentos.getJTable().getSelectedRow(), 1);
            daomedicamentos = new DaoMedicamentosImpl();
            String d = String.valueOf(ob1);
            Medicamentos medicamentos = (Medicamentos) daomedicamentos.searchByQuery2(d);
            if (medicamentos != null) {
                pnlMedicamentos.getTxt_Descripcion().setText(medicamentos.getDescrip());
            } else {
                JOptionPane.showMessageDialog(null, daomedicamentos.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un medicamento", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    private void buscarMedicamentos() {
        if (!pnlMedicamentos.getTxt_medicamento().getText().equalsIgnoreCase("")) {
            daomedicamentos = new DaoMedicamentosImpl();
            String query = pnlMedicamentos.getTxt_medicamento().getText();
            if (!"".equals(query)) {
                Medicamentos med = (Medicamentos) daomedicamentos.searchByQuery2(query);
                if (med != null) {
                    mostrarListaMedicamento(query);
                } else {
                    JOptionPane.showMessageDialog(null, daomedicamentos.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de un medicamento por favor");

        }

    }

    private void mostrarListaMedicamento(String query) {
        daomedicamentos = new DaoMedicamentosImpl();
        List<Medicamentos> lista = daomedicamentos.searchByQuery(query);
        if (0 == lista.size()) {
            JOptionPane.showMessageDialog(null, "Medicamento no encontrado", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            initController();
        } else {
            refrescarListaMedicamentos(lista);
        }

    }

    private void refrescarListaMedicamentos(List<Medicamentos> lista) {
        ((DefaultTableModel) pnlMedicamentos.getJTable().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlMedicamentos.getJTable().getModel();
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
            pnlMedicamentos.getJTable().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daomedicamentos.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
}
