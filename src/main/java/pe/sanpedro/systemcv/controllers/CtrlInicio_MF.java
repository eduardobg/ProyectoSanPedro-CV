/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmLoginT;
import pe.sanpedro.systemcv.view.FrmMainFarmacia;
import pe.sanpedro.systemcv.view.PnlInicio_MF;

/**
 *
 * @author alons
 */
public class CtrlInicio_MF {
    
    private GenericDao daoInfo;
    private PnlInicio_MF pnlInicio;
    
    public CtrlInicio_MF(){
      
    }
    public void initController(String dni){    
        pnlInicio = new PnlInicio_MF();
        mostrarInfo(dni);
        pnlInicio.getBtn_cerrarSesion().addActionListener(e -> cerrarSesion());
        CardLayout vista= (CardLayout) FrmMainFarmacia.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainFarmacia.Pnl_VP.add(pnlInicio,"InicioTrab");
        vista.show(FrmMainFarmacia.Pnl_VP,"InicioTrab");
        SwingUtilities.updateComponentTreeUI(CtrlMF.frmMF);
        CtrlMF.frmMF.repaint();
          
    }
    private void mostrarInfo(String dni){       
        daoInfo= new DaoTrabajadoresImpl();
        Trabajadores trab = (Trabajadores) daoInfo.searchByQuery2(dni);
        pnlInicio.getJtxt_cargo().setText(trab.getCargo());
        pnlInicio.getJtxt_nom().setText(trab.getNombre());
        pnlInicio.getJtxt_ape().setText(trab.getApellidop());
    
    }
    
    private void cerrarSesion(){
       CtrlMF.frmMF.dispose();
       GenericDao daotrab = new DaoTrabajadoresImpl();
       FrmLoginT  frmlogin = new FrmLoginT();
       CtrlLogin ctrl = new CtrlLogin( daotrab, frmlogin);
       ctrl.initControllerT();
    }    
    
}