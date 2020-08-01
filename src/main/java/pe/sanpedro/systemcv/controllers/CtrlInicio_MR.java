package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmMainRecepcion;
import pe.sanpedro.systemcv.view.PnlInicio_MR;

public class CtrlInicio_MR {
        private GenericDao daoInfo;
    private PnlInicio_MR pnlInicio;
      public CtrlInicio_MR(){
      
    }
      public void initController(String dni){    
        pnlInicio = new PnlInicio_MR();
        mostrarInfo(dni);
        pnlInicio.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
        CardLayout vista= (CardLayout) FrmMainRecepcion.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainRecepcion.Pnl_VP.add(pnlInicio,"InicioTrab");
        vista.show(FrmMainRecepcion.Pnl_VP,"InicioTrab");
        SwingUtilities.updateComponentTreeUI(CtrlMR.frmMR);
        CtrlMR.frmMR.repaint();
          
    }
      
    private void mostrarInfo(String dni){       
        daoInfo= new DaoTrabajadoresImpl();
        Trabajadores trab = (Trabajadores) daoInfo.searchByQuery2(dni);
        pnlInicio.getJtxtCargo().setText(trab.getCargo());
        pnlInicio.getJtxtNombre().setText(trab.getNombre());
        pnlInicio.getJtxtApellido().setText(trab.getApellidop());
    
    }
    
    private void cerrarSesion(){
        System.exit(0);
    }    
}
