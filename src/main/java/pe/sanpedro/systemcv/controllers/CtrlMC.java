
package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.view.FrmMainCaja;

/**
 *
 * @author Mysk
 */
public class CtrlMC {
    public static FrmMainCaja frmMC;   
    public static int idT;
    public CtrlMC(int idTrabajador){
        frmMC= new FrmMainCaja();     
        idT=idTrabajador;
    }
    
    public void initController(String dni){
        String d=dni;
        frmMC.getBtn_inicio().addActionListener(e -> mostrarV_Inicio(d));
        //frmMC.get
        frmMC.getBnt_estetica().addActionListener(e -> mostrarV_Estetica());
        frmMC.setVisible(true);
    }
    private void mostrarV_Inicio(String dni){
        
    }
    private void mostrarV_Estetica(){
        CtrlEstetica_MC ctrlEstetica = new CtrlEstetica_MC();
        ctrlEstetica.initController();
    }
}
