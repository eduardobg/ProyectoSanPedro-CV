package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.util.GrabarFichero;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlControlCaducidad_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlControlCaducidad_MAL {

    private GenericDao daoControl;
    private PnlControlCaducidad_MAL pnlControl;
    List<Medicamentos> lista;

    public CtrlControlCaducidad_MAL() {

    }

    public void initController() {

        pnlControl = new PnlControlCaducidad_MAL();
        pnlControl.getBtn_mostrarTodo().addActionListener(e -> mostrarTodo());
        pnlControl.getBtn_filtrar().addActionListener(e -> filtrar());
        pnlControl.getBtn_generarFichero().addActionListener(e -> generarFichero());
        CardLayout vista = (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlControl, "control");
        vista.show(FrmMainAlmacen.Pnl_VP, "control");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
    }

    private void mostrarTodo() {
        daoControl = new DaoMedicamentosImpl();
        lista = daoControl.sel();
        refrescarTabla(lista);
        pnlControl.getBtn_generarFichero().setEnabled(false);
        pnlControl.getDate().setEnabled(true);
    }

    private void refrescarTabla(List<Medicamentos> lista) {
        ((DefaultTableModel) pnlControl.getTable().getModel()).setNumRows(0);
        DefaultTableModel modelo = (DefaultTableModel) pnlControl.getTable().getModel();
        if (lista != null) {
            lista.forEach((t) -> {
                Object[] fila = new Object[8];
                fila[0] = t.getID_Med();
                fila[1] = t.getNombre();
                fila[2] = t.getF_elab();
                fila[3] = t.getF_venci();
                fila[4] = t.getPrecio();
                fila[5] = t.getStock();
                fila[6] = t.getPresent();
                fila[7] = t.getLab();
                modelo.addRow(fila);
            });
            pnlControl.getTable().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoControl.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void filtrar() {
        if (pnlControl.getDate().getDate() != null) {
            daoControl = new DaoMedicamentosImpl();
           // lista = daoControl.searchBetween(0, LocalDate.now(), pnlControl.getDate().getDate());
            lista = daoControl.searchByDate(pnlControl.getDate().getDate());
            refrescarTabla(lista);
            pnlControl.getBtn_generarFichero().setEnabled(true);
            pnlControl.getDate().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccionar una Fecha!");
        }

    }

    private void generarFichero() {
        if (lista != null) {
            if (pnlControl.getDate().getDate() != null) {
                if (GrabarFichero.grabar(lista, pnlControl.getDate().getDate()) != false) {
                    JOptionPane.showMessageDialog(null, "Fichero Grabado");
                    initController();
                } else {
                    JOptionPane.showMessageDialog(null, "No se puedo generar el fichero");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Seleccionar una Fecha!");
            }

        }
    }
}
