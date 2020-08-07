
package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoMedicamentosImpl;
import pe.sanpedro.systemcv.model.Medicamentos;
import pe.sanpedro.systemcv.view.FrmMainAlmacen;
import pe.sanpedro.systemcv.view.PnlMantenimientoM_MAL;

/**
 *
 * @author Mysk
 */
public class CtrlMantenimientoPro_MAL {
    
    private GenericDao daoDespacho;
    private PnlMantenimientoM_MAL pnlManteniminento;
    
    public CtrlMantenimientoPro_MAL(){
        
    }
    public void initController(){
        pnlManteniminento = new PnlMantenimientoM_MAL();
        mostrarMedicamentos();
        pnlManteniminento.getBtn_buscar().addActionListener(e -> buscarMedicamento());
        pnlManteniminento.getBtn_mostrarTodo().addActionListener(e -> mostrarMedicamentos());
        pnlManteniminento.getBtn_nuevoM().addActionListener(e -> jalarNuevo());
        pnlManteniminento.getBtn_guardarM().addActionListener(e -> guardar());
        pnlManteniminento.getBtn_editarM().addActionListener(e -> verMedicamento());
        pnlManteniminento.getBtn_eliminarM().addActionListener(e -> eliminar());
        pnlManteniminento.getBtn_cancelarM().addActionListener(e -> cancelar());
        CardLayout vista= (CardLayout) FrmMainAlmacen.Pnl_VP.getLayout(); //El Pnl_VP es el un panel creado en el FrmMainAdmin, revisar!!
        FrmMainAlmacen.Pnl_VP.add(pnlManteniminento,"mantenimiento");
        vista.show(FrmMainAlmacen.Pnl_VP,"mantenimiento");
        SwingUtilities.updateComponentTreeUI(CtrlMAL.frmMAL);
        CtrlMAL.frmMAL.repaint();
    }
    
    private void mostrarMedicamentos(){
        daoDespacho = new DaoMedicamentosImpl();
        List<Medicamentos> lista = daoDespacho.sel();
        refrescarTabla(lista);
      
    }
    
    private void refrescarTabla(List<Medicamentos> lista){
        ((DefaultTableModel) pnlManteniminento.getTable().getModel()).setNumRows(0);
        DefaultTableModel modelo = (DefaultTableModel) pnlManteniminento.getTable().getModel();
        if (lista!=null) {
            lista.forEach((t) -> {
                Object[] fila = new Object[8];
                fila[0] = t.getID_Med();
                fila[1] = t.getNombre();
                fila[2] = t.getF_elab();
                fila[3] = t.getF_venci();
                fila[4] = t.getPrecio();
                fila[5] = t.getStock();
                fila[6] = t.getPresent();
                fila[7] = t.getLab();                         
                modelo.addRow(fila);
            });
             pnlManteniminento.getTable().setModel(modelo);
        }else {
            JOptionPane.showMessageDialog(null, daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);            
        }
    }
    
    private void buscarMedicamento(){
        daoDespacho = new DaoMedicamentosImpl();
        String query = pnlManteniminento.getTxt_buscarM().getText();
        Medicamentos medicamentos = (Medicamentos) daoDespacho.searchByQuery2(query);
        if (medicamentos.getID_Med()!=null) {
            List<Medicamentos> listaMedicamentos = new ArrayList();
            listaMedicamentos.add(medicamentos);
            refrescarTabla(listaMedicamentos);  
        }else{
            JOptionPane.showMessageDialog(null, "Medicamento no encontrado", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
                
    }
    private void jalarNuevo(){
        pnlManteniminento.limpiarAbajo();        
        pnlManteniminento.getTxt_nombreM().requestFocus();
    }
    private void guardar(){
        Medicamentos med = new Medicamentos();
        med.setNombre(pnlManteniminento.getTxt_nombreM().getText());
        med.setF_elab(pnlManteniminento.getDate1().getDate());
        med.setF_venci(pnlManteniminento.getDate2().getDate());
        med.setDescrip(pnlManteniminento.getTxt_areaM().getText());
        med.setPrecio(Double.parseDouble(pnlManteniminento.getTxt_precioM().getText()));
        med.setStock(Integer.parseInt(pnlManteniminento.getTxt_stockM().getText()));
        med.setPresent(pnlManteniminento.getTxt_presenM().getText());
        med.setLab(pnlManteniminento.getTxt_labM().getText());
        if (pnlManteniminento.getTxt_idM().getText().equalsIgnoreCase("")) {
            daoDespacho = new DaoMedicamentosImpl();
            if (daoDespacho.insert(med)) {
                JOptionPane.showMessageDialog(null, "Aviso: " + daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                mostrarMedicamentos();
                pnlManteniminento.limpiarAbajo();
            }else{
                JOptionPane.showMessageDialog(null, daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            med.setID_Med(pnlManteniminento.getTxt_idM().getText());           
            daoDespacho.update(med);
            JOptionPane.showMessageDialog(null, "Aviso: " + daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            mostrarMedicamentos();
            pnlManteniminento.limpiarAbajo();            
        }
    }
    private void verMedicamento(){
        pnlManteniminento.limpiarAbajo();
        Object ob =  pnlManteniminento.getTable().getModel().getValueAt(pnlManteniminento.getTable().getSelectedRow(), 0);          
        Medicamentos med = (Medicamentos) daoDespacho.searchById(Integer.parseInt(String.valueOf(ob)));
        if (med!=null) {
            pnlManteniminento.getTxt_idM().setText(String.valueOf(med.getID_Med()));
            pnlManteniminento.getTxt_nombreM().setText(med.getNombre());
            pnlManteniminento.getDate1().setDate(med.getF_elab());
            pnlManteniminento.getDate2().setDate(med.getF_venci());
            pnlManteniminento.getTxt_areaM().setText(med.getDescrip());
            pnlManteniminento.getTxt_precioM().setText(String.valueOf(med.getPrecio()));
            pnlManteniminento.getTxt_stockM().setText(String.valueOf(med.getStock()));
            pnlManteniminento.getTxt_presenM().setText(med.getPresent());
            pnlManteniminento.getTxt_labM().setText(med.getLab());
         
        }
    }
    private void eliminar(){
        Object ob = pnlManteniminento.getTable().getModel().getValueAt(pnlManteniminento.getTable().getSelectedRow(), 0);
        if (ob!=null) {
            daoDespacho.delete(Integer.parseInt(String.valueOf(ob)));
            JOptionPane.showMessageDialog(null, "Aviso: " + daoDespacho.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            mostrarMedicamentos();   
        }else{
            JOptionPane.showMessageDialog(null,"Debe escojer una fila primero");
        }
        
    }
    
    private void cancelar(){
        initController();
    }
    
}
