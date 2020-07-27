package pe.sanpedro.systemcv.controllers;



import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoAdminImpl;
import pe.sanpedro.systemcv.dao.impl.DaoInfoEmpresaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.view.FrmMainAdmin;




public class CtrlMA {    
    public static FrmMainAdmin frmMA;    
    
    public CtrlMA( ) {       
        CtrlMA.frmMA = new FrmMainAdmin();
    }
    
    public void initController(){
        frmMA.getBtnInicio().addActionListener(e -> mostrarV_Inicio()); //paneles INICIO
        frmMA.getBtnTrabajadores().addActionListener(e -> mostrarV_Trabajadores());// PANEL TRABAJADORES
        frmMA.getBtn_info().addActionListener(e -> mostrarV_Info());
        frmMA.setVisible(true);
    }
    private void mostrarV_Inicio(){
        GenericDao daoInfo = new DaoAdminImpl();        
        CtrlInicio_MA ctrlI = new  CtrlInicio_MA(daoInfo);
        ctrlI.initController();
    }
    private void mostrarV_Trabajadores() {
        GenericDao  daotrabajadores = new DaoTrabajadoresImpl();       
        CtrlTrabajadores_MA ctrlT = new CtrlTrabajadores_MA(daotrabajadores);
        ctrlT.initController();
    }
    private void mostrarV_Info(){
        GenericDao daoInfo= new DaoInfoEmpresaImpl();
        CtrlInfo_MA ctrlInfo = new CtrlInfo_MA(daoInfo);
        ctrlInfo.initController();
    }
}
