
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Admin;
import pe.sanpedro.systemcv.view.FrmLoginT;
import pe.sanpedro.systemcv.view.FrmMainAdmin;
import pe.sanpedro.systemcv.view.PnlInicio_MA;

/**
 *
 * @author Mysk
 */
public class CtrlInicio_MA {
    private final GenericDao daoInfo;
    private PnlInicio_MA pnlInicio;

    public CtrlInicio_MA(GenericDao daoInfo) {
        this.daoInfo = daoInfo;        
    }
    
    public void initController(){        
        pnlInicio = new PnlInicio_MA();
        mostrarInfo();
        pnlInicio.getBtn_cerrarSesion().addActionListener(e -> cerrarSesion());
        
        CardLayout vista= (CardLayout) FrmMainAdmin.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAdmin.Pnl_VP.add(pnlInicio,"InicioAdmin");
        vista.show(FrmMainAdmin.Pnl_VP,"InicioAdmin");
        SwingUtilities.updateComponentTreeUI(CtrlMA.frmMA);
        CtrlMA.frmMA.repaint();
    }
    
    private void mostrarInfo(){
        List<Admin> list;
        list = (List<Admin>) daoInfo.searchByQuery("Admin");
        
        if (list!=null) {           
            pnlInicio.getJtxt_cargo().setText("Administrador");
            pnlInicio.getJtxt_nom().setText(list.get(0).getNombre());
            pnlInicio.getJtxt_ape().setText(list.get(0).getApellido());
        }else{
            JOptionPane.showMessageDialog(null, daoInfo.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    private void cerrarSesion(){
       CtrlMA.frmMA.dispose();
       GenericDao daotrab = new DaoTrabajadoresImpl();
       FrmLoginT  frmlogin = new FrmLoginT();
       CtrlLogin ctrl = new CtrlLogin( daotrab, frmlogin);
       ctrl.initControllerT();
    }
}
