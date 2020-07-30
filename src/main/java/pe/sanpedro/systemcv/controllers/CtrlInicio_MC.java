
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmMainCaja;
import pe.sanpedro.systemcv.view.PnlInicio_MC;

/**
 *
 * @author Mysk
 */
public class CtrlInicio_MC {
    
    private GenericDao daoInfo;
    private PnlInicio_MC pnlInicio;
    
    public CtrlInicio_MC(){
      
    }
    public void initController(String dni){    
        pnlInicio = new PnlInicio_MC();
        mostrarInfo(dni);
        pnlInicio.getBtn_cerrarSesion().addActionListener(e -> cerrarSesion());
        CardLayout vista= (CardLayout) FrmMainCaja.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainCaja.Pnl_VP.add(pnlInicio,"InicioTrab");
        vista.show(FrmMainCaja.Pnl_VP,"InicioTrab");
        SwingUtilities.updateComponentTreeUI(CtrlMC.frmMC);
        CtrlMC.frmMC.repaint();
          
    }
    private void mostrarInfo(String dni){       
        daoInfo= new DaoTrabajadoresImpl();
        Trabajadores trab = (Trabajadores) daoInfo.searchByQuery2(dni);
        pnlInicio.getJtxt_cargo().setText(trab.getCargo());
        pnlInicio.getJtxt_nom().setText(trab.getNombre());
        pnlInicio.getJtxt_ape().setText(trab.getApellidop());
    
    }
    
    private void cerrarSesion(){
        System.exit(0);
    }    
    
}
