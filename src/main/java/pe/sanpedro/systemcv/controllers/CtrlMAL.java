
package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;

/**
 *
 * @author Mysk
 */
public class CtrlMAL {
    
    public static FrmMainAlmacen frmMAL;
    
    public CtrlMAL(){       
        frmMAL=new FrmMainAlmacen();
    }
    public void initController(String dni){ 
        frmMAL.getBtn_inicio().addActionListener(e -> mostrarV_Inicio(dni));
        frmMAL.getBtn_despacho().addActionListener(e -> mostrarV_Despacho());
        frmMAL.getBtn_mantenimiento().addActionListener(e -> mostrarV_MantenimientoM());
        frmMAL.getBtn_control().addActionListener(e -> mostrarV_ControlCaducidad());
        frmMAL.setVisible(true);
    }
    private void mostrarV_Inicio(String dni){
       CtrlInicio_MAL ctrlInicio = new CtrlInicio_MAL();
       ctrlInicio.initController(dni);       
    }
    private void mostrarV_Despacho(){
       CtrlDespachoM_MAL ctrldespacho = new CtrlDespachoM_MAL();
       ctrldespacho.initController();
    }
    private void mostrarV_MantenimientoM(){
        CtrlMantenimientoPro_MAL ctrlMan = new CtrlMantenimientoPro_MAL();
        ctrlMan.initController();
    }
    private void mostrarV_ControlCaducidad(){
        CtrlControlCaducidad_MAL ctrlControl = new CtrlControlCaducidad_MAL();
        ctrlControl.initController();
    }
    
}
