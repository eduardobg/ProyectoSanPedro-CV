package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmMainAdmin;
import pe.sanpedro.systemcv.view.PnlTrabajadores_MA;

/**
 *
 * @author Mysk
 */
public class CtrlTrabajadores_MA {

    private final GenericDao daotrabajadores;
    private PnlTrabajadores_MA pnlTrabajadores;
  
    public CtrlTrabajadores_MA(GenericDao daotrabajadores){
        this.daotrabajadores = daotrabajadores;
       
    }

    public void initController() {             
             
        pnlTrabajadores = new PnlTrabajadores_MA(); //nuevo
        mostrarTrabajadores();
        pnlTrabajadores.getBtn_buscar().addActionListener(e -> buscarEmpleado());
        pnlTrabajadores.getJButtonGuardar().addActionListener(e -> guardarEmpleado());
        pnlTrabajadores.getBtn_agregar().addActionListener(e-> agregarEmpleado());
        pnlTrabajadores.getBtn_cancelar().addActionListener(e -> cancelar());
        pnlTrabajadores.getBtn_eliminar().addActionListener(e -> eliminarEmpleado());
        //pnlTrabajadores.getJTable().getModel().addTableModelListener(e -> editarEmpleado());//Para Completar los campos dando Click en row
        pnlTrabajadores.getBtnEditar().addActionListener(e -> editarEmpleado());
                
        CardLayout vista = (CardLayout) FrmMainAdmin.Pnl_VP.getLayout();
        FrmMainAdmin.Pnl_VP.add(pnlTrabajadores, "Trabajadores");
        vista.show(FrmMainAdmin.Pnl_VP, "Trabajadores");
        SwingUtilities.updateComponentTreeUI(CtrlMA.frmMA);
        CtrlMA.frmMA.repaint();
    }

    private void mostrarTrabajadores() {        
        List<Trabajadores> lista = daotrabajadores.sel();
        refrescarTabla(lista);       
    }
    private void refrescarTabla(List<Trabajadores> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ape.Paterno");
        modelo.addColumn("Ape.Materno");
        modelo.addColumn("Celular");
        modelo.addColumn("correo");
        modelo.addColumn("F.Nacimineto");
        modelo.addColumn("F.Registro");
        modelo.addColumn("Cargo");
        if (lista != null) {
            lista.forEach((t) -> {
                Object[] fila = new Object[10];
                fila[0] = t.getId();
                fila[1] = t.getDni();
                fila[2] = t.getNombre();
                fila[3] = t.getApellidop();
                fila[4] = t.getApellidom();
                fila[5] = t.getCelular();
                fila[6] = t.getCorreo();
                fila[7] = t.getFecha_nac();
                fila[8] = t.getFecha_reg();
                //fila[9] = t.getTipo();
                fila[9] = t.getCargo();//new
                modelo.addRow(fila);
            });
            pnlTrabajadores.getJTable().setModel(modelo);
            pnlTrabajadores.limpiarEspacios();
        } else {
            JOptionPane.showMessageDialog(null, daotrabajadores.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);            
        }
    }
    
    private void buscarEmpleado(){
        if (pnlTrabajadores.getRbtnGroup().getSelection()!=null) {
            List<Trabajadores> lista = new ArrayList();            
            if (pnlTrabajadores.getRbtn_1().isSelected()) {
                String dni = pnlTrabajadores.getTxt_buscardni().getText();
                Trabajadores employee = (Trabajadores) daotrabajadores.searchByQuery2(dni);
                if (employee!=null) {
                   lista.add(employee);
                   refrescarTabla(lista);
                }
            }else{                
                if (pnlTrabajadores.getCbx_areas1().getSelectedIndex()!=0) {
                   String tipo = String.valueOf(pnlTrabajadores.getCbx_areas1().getSelectedIndex());
                   lista = daotrabajadores.searchByQuery(tipo);                   
                   refrescarTabla(lista);                   
                } else {
                    mostrarTrabajadores();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debes escoger una opción", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void agregarEmpleado(){
        pnlTrabajadores.limpiarEspacios();
        pnlTrabajadores.getTxt_dni().requestFocus();
    }
    private void eliminarEmpleado(){
        Object ob =  pnlTrabajadores.getJTable().getModel().getValueAt(pnlTrabajadores.getJTable().getSelectedRow(), 0);
        daotrabajadores.delete(Integer.parseInt(String.valueOf(ob)));
        JOptionPane.showMessageDialog(null, "Aviso: " + daotrabajadores.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
        mostrarTrabajadores();
    }
    
    private void editarEmpleado(){               
        pnlTrabajadores.limpiarEspacios();
        Object ob =  pnlTrabajadores.getJTable().getModel().getValueAt(pnlTrabajadores.getJTable().getSelectedRow(), 0);
           
        Trabajadores employee = (Trabajadores) daotrabajadores.searchById(Integer.parseInt(String.valueOf(ob)));
        if (employee!=null) {            
            pnlTrabajadores.getTxt_id().setText(String.valueOf(employee.getId()));
            pnlTrabajadores.getTxt_dni().setText(employee.getDni());
            pnlTrabajadores.getTxt_nom().setText(employee.getNombre());
            pnlTrabajadores.getTxt_apep().setText(employee.getApellidop());
            pnlTrabajadores.getTxt_apem().setText(employee.getApellidom());
            pnlTrabajadores.getTxt_cel().setText(employee.getCelular());
            pnlTrabajadores.getTxt_correo().setText(employee.getCorreo());
            pnlTrabajadores.getDp_fnac().setDate(employee.getFecha_nac());
            pnlTrabajadores.getCbx_areas2().setSelectedIndex(employee.getTipo()-1);
            
        }       
       
    }

    private void guardarEmpleado() {      
        String dni = pnlTrabajadores.getTxt_dni().getText();
        String nombre = pnlTrabajadores.getTxt_nom().getText();
        String apep = pnlTrabajadores.getTxt_apep().getText();
        String apem = pnlTrabajadores.getTxt_apem().getText();
        String cel = pnlTrabajadores.getTxt_cel().getText();
        String correo = pnlTrabajadores.getTxt_correo().getText();
        LocalDate fecha_nac = pnlTrabajadores.getDp_fnac().getDate();
        Integer tipo = pnlTrabajadores.getCbx_areas2().getSelectedIndex() + 1;        

        if (pnlTrabajadores.getTxt_id().getText().equalsIgnoreCase("")) {
            Trabajadores employee = new Trabajadores(dni, nombre, apep, apem, cel, correo, fecha_nac, LocalDate.now(), tipo);
            if (daotrabajadores.insert(employee)) {
                JOptionPane.showMessageDialog(null, "Aviso: " + daotrabajadores.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                mostrarTrabajadores();
            } else {
                JOptionPane.showMessageDialog(null, daotrabajadores.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            Integer id =Integer.parseInt(pnlTrabajadores.getTxt_id().getText());
            Trabajadores employee = new Trabajadores(id,dni, nombre, apep, apem, cel, correo, fecha_nac, tipo);
            daotrabajadores.update(employee);
            JOptionPane.showMessageDialog(null, "Aviso: " + daotrabajadores.getMessage(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
            mostrarTrabajadores();
        }
      
    }
    private void cancelar(){
        pnlTrabajadores.limpiarEspacios();
        pnlTrabajadores.getTxt_buscardni().requestFocus();
    }
}
