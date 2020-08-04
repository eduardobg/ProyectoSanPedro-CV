
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlDespachoPro_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlDespachoM_MAL {
    
    private GenericDao daoDespacho;
    private PnlDespachoPro_MAL pnlDespacho;
    
    private void CtrlDespachoM_MAL(){
        
    }
    public void initController(){
        pnlDespacho = new PnlDespachoPro_MAL();
        //Agregar botones
        
        CardLayout vista= (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlDespacho,"Despacho");
        vista.show(FrmMainAlmacen.Pnl_VP,"Despacho");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
    }
}
