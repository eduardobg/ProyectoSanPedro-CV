/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlControlCaducidad_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlControlCaducidad_MAL {
    
    private GenericDao daoControl;
    private PnlControlCaducidad_MAL pnlControl;
    
    public CtrlControlCaducidad_MAL(){
        
    }
    public void initController(){
        pnlControl = new PnlControlCaducidad_MAL();
        //Agregar botones
        
        CardLayout vista= (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlControl,"control");
        vista.show(FrmMainAlmacen.Pnl_VP,"control");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
    }
}
