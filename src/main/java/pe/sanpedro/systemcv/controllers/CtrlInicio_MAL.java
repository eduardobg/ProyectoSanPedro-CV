
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmLoginT;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlInicio_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlInicio_MAL {
    
    private GenericDao daoInfo;
    private PnlInicio_MAL pnlInicio;
    
     public CtrlInicio_MAL(){
      
    }
    public void initController(String dni){    
        pnlInicio = new PnlInicio_MAL();
        mostrarInfo(dni);
        pnlInicio.getBtn_cerrarSesion().addActionListener(e -> cerrarSesion());
        CardLayout vista= (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlInicio,"InicioTrab");
        vista.show(FrmMainAlmacen.Pnl_VP,"InicioTrab");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
          
    }
    private void mostrarInfo(String dni){
        daoInfo= new DaoTrabajadoresImpl();
        Trabajadores trab = (Trabajadores) daoInfo.searchByQuery2(dni);
        pnlInicio.getJtxt_cargo().setText(trab.getCargo());
        pnlInicio.getJtxt_nom().setText(trab.getNombre());
        pnlInicio.getJtxt_ape().setText(trab.getApellidop());
    }
    private void cerrarSesion(){
       CtrlMAL.frmMAL.dispose();
       GenericDao daotrab = new DaoTrabajadoresImpl();
       FrmLoginT  frmlogin = new FrmLoginT();
       CtrlLogin ctrl = new CtrlLogin( daotrab, frmlogin);
       ctrl.initControllerT();
    }
}
