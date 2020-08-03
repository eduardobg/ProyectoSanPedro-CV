package pe.sanpedro.systemcv.controllers;

import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoEspecieImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoRazaImpl;
import pe.sanpedro.systemcv.view.FrmMainRecepcion;

/**
 *
 * @author Mysk
 */
public class CtrlMR {

    public static FrmMainRecepcion frmMR;

    public CtrlMR() {
        frmMR = new FrmMainRecepcion();
    }

    public void initController(String dni) {
        frmMR.getBtnClientes().addActionListener(e -> mostrarV_Clientes());
        frmMR.getBtnMascotas().addActionListener(e -> mostrarV_Mascotas());
        frmMR.getBtnCitas().addActionListener(e -> mostrarV_Citas());
        frmMR.getBtnInicio().addActionListener(e -> mostrarV_Inicio(dni));
        frmMR.setVisible(true);
    }

    private void mostrarV_Clientes() {
        GenericDao daoclientes = new DaoClienteImpl();
        CtrlClientes_MR ctrlC = new CtrlClientes_MR(daoclientes);
        ctrlC.initController();
    }

    private void mostrarV_Mascotas() {
        GenericDao daoclientes = new DaoClienteImpl();
        GenericDao daomascotas = new DaoMascotaImpl();
        GenericDao daorazas = new DaoRazaImpl();
        GenericDao daoespecies = new DaoEspecieImpl();
        CtrlMascotas_MR ctrlM = new CtrlMascotas_MR(daoclientes, daomascotas, daoespecies, daorazas);
        ctrlM.initController();
    }

    private void mostrarV_Citas() {
        CtrlCitas_MR ctrlM = new CtrlCitas_MR();
        ctrlM.initController();
    }
        private void mostrarV_Inicio(String dni) {
        CtrlInicio_MR ctrlI = new CtrlInicio_MR();
        ctrlI.initController(dni);
    }
}
