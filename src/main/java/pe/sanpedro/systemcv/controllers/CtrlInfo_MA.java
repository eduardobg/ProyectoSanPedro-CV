
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.view.FrmMainAdmin;
import pe.sanpedro.systemcv.view.PnlInfoEmpresa_MA;

/**
 *
 * @author Mysk
 */
public class CtrlInfo_MA {
    
    private final GenericDao daoInfo;
    private PnlInfoEmpresa_MA pnlInfo;
    
    public CtrlInfo_MA(GenericDao daoInfo){
        this.daoInfo= daoInfo;
                
    }
    public void initController(){
        pnlInfo= new PnlInfoEmpresa_MA();
        mostrarInfo();
        pnlInfo.getBtn_guardar().addActionListener(e -> actualizarInfo());
        
        CardLayout vista = (CardLayout) FrmMainAdmin.Pnl_VP.getLayout();
        FrmMainAdmin.Pnl_VP.add(pnlInfo, "Info");
        vista.show(FrmMainAdmin.Pnl_VP, "Info");
        SwingUtilities.updateComponentTreeUI(CtrlMA.frmMA);
        CtrlMA.frmMA.repaint();
    }
    private void mostrarInfo(){
        List<InfoEmpresa> info = daoInfo.sel();
        pnlInfo.getTxt_nomE().setText(info.get(0).getNombreE());
        pnlInfo.getTxt_ruc().setText(info.get(0).getRuc());
        pnlInfo.getTxt_propi().setText(info.get(0).getPropiertario());
        pnlInfo.getTxt_ubi().setText(info.get(0).getDirec());
        pnlInfo.getTxt_tel().setText(info.get(0).getTelf());
        pnlInfo.getTxt_cel().setText(info.get(0).getCel());
        pnlInfo.getTxt_correo().setText(info.get(0).getCorreo());
        
    }
    private void actualizarInfo(){
        String nombreE= pnlInfo.getTxt_nomE().getText();
        String ruc= pnlInfo.getTxt_ruc().getText();
        String propi= pnlInfo.getTxt_propi().getText();
        String ubi= pnlInfo.getTxt_ubi().getText();
        String telf=pnlInfo.getTxt_tel().getText();
        String cel= pnlInfo.getTxt_cel().getText();
        String correo=pnlInfo.getTxt_correo().getText();
        InfoEmpresa info = new InfoEmpresa(1, nombreE, ruc, propi, ubi, telf, cel, correo);
        daoInfo.update(info);
        JOptionPane.showMessageDialog(null, "Aviso: " + daoInfo.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
    }
}
