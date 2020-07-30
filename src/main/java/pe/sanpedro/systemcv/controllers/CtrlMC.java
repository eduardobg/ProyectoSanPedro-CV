
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
        frmMC.getBtn_inicio().addActionListener(e -> mostrarV_Inicio(dni));
        //frmMC.get
        frmMC.getBnt_estetica().addActionListener(e -> mostrarV_Estetica());
        frmMC.getBnt_examen().addActionListener(e -> mostrarV_Examenes());
        frmMC.setVisible(true);
    }
    private void mostrarV_Inicio(String dni){
       CtrlInicio_MC ctrlInicio = new CtrlInicio_MC();
       ctrlInicio.initController(dni);
    }
    private void mostrarV_Estetica(){
        CtrlEstetica_MC ctrlEstetica = new CtrlEstetica_MC();
        ctrlEstetica.initController();
    }
    private void mostrarV_Examenes(){
        CtrlExamenes_MC ctrlExamenes = new CtrlExamenes_MC();
       ctrlExamenes.initController();
    }
}
