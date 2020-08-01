
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoReporteImpl;
import pe.sanpedro.systemcv.dto.Reporte;
import pe.sanpedro.systemcv.view.FrmMainAdmin;
import pe.sanpedro.systemcv.view.PnlReportes_MA;

/**
 *
 * @author Mysk
 */
public class CtrlReporte_MA {
    
    private GenericDao daoReporte;
    private PnlReportes_MA pnlReporte;
    
    public CtrlReporte_MA(){}
    
    public void initController() {
        pnlReporte = new PnlReportes_MA();
        mostrarReportes();
        pnlReporte.getBtn_buscar().addActionListener(e -> buscarReporte());
        pnlReporte.getBtn_limpiar().addActionListener(e -> limpiar());
        CardLayout vista = (CardLayout) FrmMainAdmin.Pnl_VP.getLayout();
        FrmMainAdmin.Pnl_VP.add(pnlReporte, "Estetica");
        vista.show(FrmMainAdmin.Pnl_VP, "Estetica");
        SwingUtilities.updateComponentTreeUI(CtrlMA.frmMA);
        CtrlMA.frmMA.repaint();
        
    }
    private void mostrarReportes(){        
        daoReporte = new DaoReporteImpl();   
        List<Reporte> list = daoReporte.sel();
        refrescarTabla(list);
        
    }
    
    private void refrescarTabla(List<Reporte> list){
        ((DefaultTableModel) pnlReporte.getJtbl().getModel()).setNumRows(0);
        DefaultTableModel modelo = (DefaultTableModel) pnlReporte.getJtbl().getModel();
        if (list!=null) {
            list.forEach((t) -> {
                Object[] fila = new Object[9];
                fila[0] = t.getId_boleta();
                fila[1] = t.getFecha_();
                fila[2] = t.getDniTrab();
                fila[3] = t.getNombreTrab();
                fila[4] = t.getApellidopTrab();
                fila[5] = t.getApellidomTrab();
                fila[6] = t.getDniCli();
                fila[7] = t.getNombreCli();
                fila[8] = t.getImporteTotal();             
                modelo.addRow(fila);
            });
             pnlReporte.getJtbl().setModel(modelo);
        }else {
            JOptionPane.showMessageDialog(null, daoReporte.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);            
        }
        
    }
    private void buscarReporte(){
        int area = pnlReporte.getCbx().getSelectedIndex() +1;
        LocalDate d1 = pnlReporte.getDate1().getDate();
        LocalDate d2 = pnlReporte.getDate2().getDate();  
        daoReporte = new DaoReporteImpl();
        List<Reporte> list = daoReporte.searchBetween(area, d1, d2);
        refrescarTabla(list);
    }
    private void limpiar(){
        ((DefaultTableModel) pnlReporte.getJtbl().getModel()).setNumRows(0);
    }
    
}
