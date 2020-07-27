
package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.view.FrmMainRecepcion;

/**
 *
 * @author Mysk
 */
public class CtrlMR {
    public static FrmMainRecepcion frmMR;
    
    public CtrlMR(){
        frmMR= new FrmMainRecepcion();
    }
     public void initController(String dni){
         frmMR.setVisible(true);
     }
}
