
package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.view.FrmMainFarmacia;

/**
 *
 * @author Mysk
 */
public class CtrlMF {
    public static FrmMainFarmacia frmMF;
    
    public CtrlMF(){
        frmMF= new FrmMainFarmacia();
    }
    public void initController(String dni){        
        frmMF.setVisible(true);
    }
}
