
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlMantenimientoM_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlMantenimientoPro_MAL {
    
    private GenericDao daoDespacho;
    private PnlMantenimientoM_MAL pnlManteniminento;
    
    public CtrlMantenimientoPro_MAL(){
        
    }
    public void initController(){
        pnlManteniminento = new PnlMantenimientoM_MAL();
        //Agregar botones
        
        CardLayout vista= (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlManteniminento,"mantenimiento");
        vista.show(FrmMainAlmacen.Pnl_VP,"mantenimiento");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
    }
}
