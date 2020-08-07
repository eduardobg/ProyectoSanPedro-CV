
package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.view.FrmMainFarmacia;

/**
 *
 * @author Mysk
 */
public class CtrlMF {
    public static FrmMainFarmacia frmMF;
    public static int idT;
    public CtrlMF(){
        frmMF= new FrmMainFarmacia();
    }
    public void initController(String dni){  
        frmMF.getBtnInicio().addActionListener(e -> mostrarV_Inicio(dni));
        frmMF.getBtnMedicamentos().addActionListener(e -> mostrarV_Medicamentos());
        frmMF.getBtnOrdenVenta().addActionListener(e -> mostrarV_OrdenVenta());
        frmMF.getBtnDespacharPedidos().addActionListener(e -> mostrarV_DespacharPedidos());
        frmMF.setVisible(true);
    }
    private void mostrarV_Inicio(String dni){
        GenericDao daoInfo = new DaoTrabajadoresImpl();        
        CtrlInicio_MF ctrlI = new  CtrlInicio_MF();
        ctrlI.initController(dni);
    }
    private void mostrarV_Medicamentos(){
        GenericDao d = new DaoMedicamentosImpl();        
        CtrlMedicamentos_MF ctrlI = new  CtrlMedicamentos_MF(d);
        ctrlI.initController();
    }
    private void mostrarV_OrdenVenta(){
        GenericDao da = new DaoOrdenPedidoImpl();     
        CtrlOrdenVenta_MF ctrlI = new  CtrlOrdenVenta_MF(da);
        ctrlI.initController();
    }
    private void mostrarV_DespacharPedidos(){
        GenericDao daw = new DaoDetallePedidoImpl();        
        CtrlDespacharPedidos_MF ctrlI = new  CtrlDespacharPedidos_MF(daw);
        ctrlI.initController();
    }
}
