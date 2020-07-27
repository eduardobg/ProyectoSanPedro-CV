package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoBoletaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoInfoEmpresaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoServiciosImpl;
//import pe.sanpedro.systemcv.model.Boleta;
import pe.sanpedro.systemcv.dto.Boleta;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.InfoEmpresa;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.model.Servicios;
import pe.sanpedro.systemcv.view.FrmMainCaja;
import pe.sanpedro.systemcv.view.PnlEstetica_MC;
/*
 *
 * @author Mysk
 */
public class CtrlEstetica_MC {

    private GenericDao daoEstetica;
    private PnlEstetica_MC pnlEstetica;
    
    public CtrlEstetica_MC() {
              
    }

    public void initController() {
        pnlEstetica = new PnlEstetica_MC();
        pnlEstetica.getBtn_buscarCli().addActionListener(e -> buscarCliente());
        pnlEstetica.getBtn_cambiarCli().addActionListener(e -> cambiarCliente());
        pnlEstetica.getBtn_buscarM().addActionListener(e -> buscarMascota());
        pnlEstetica.getBtn_agregarM().addActionListener(e -> agregarMascota());
        pnlEstetica.getBtn_agregarS().addActionListener(e -> agregarServicio());
        pnlEstetica.getBtn_cobrar().addActionListener(e ->cobrar());
        pnlEstetica.getBtn_generarBoleta().addActionListener(e ->generarBoleta());
        CardLayout vista = (CardLayout) FrmMainCaja.Pnl_VP.getLayout();
        FrmMainCaja.Pnl_VP.add(pnlEstetica, "Estetica");
        vista.show(FrmMainCaja.Pnl_VP, "Estetica");
        SwingUtilities.updateComponentTreeUI(CtrlMC.frmMC);
        CtrlMC.frmMC.repaint();
    }

    private void buscarCliente() {
        daoEstetica = new DaoClienteImpl();
        String dni = pnlEstetica.getTxt_buscarCli().getText();
        if (!"".equals(dni)) {
            Cliente cli = (Cliente) daoEstetica.searchByQuery2(dni);
            if (cli != null) {
                pnlEstetica.getTxt_idCli().setText(String.valueOf(cli.getId_cliente()));
                pnlEstetica.getTxt_dniCli().setText(cli.getDni());
                pnlEstetica.getTxt_nombCli().setText(cli.getNombre());
                pnlEstetica.getTxt_apeCli().setText(cli.getApellido());
                pnlEstetica.getTxt_direcCli().setText(cli.getDireccion());
                mostrarMascotas(cli.getId_cliente());
            } else {
                JOptionPane.showMessageDialog(null, daoEstetica.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        }
    }   

    private void mostrarMascotas(int id_cli) {
        daoEstetica = new DaoMascotaImpl();
        List<Mascota> listaMascota = daoEstetica.searchById2(id_cli);
        refrescarMascotas(listaMascota);

    }

    private void refrescarMascotas(List<Mascota> listaMascota) {
        ((DefaultTableModel) pnlEstetica.getJtbl1().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlEstetica.getJtbl1().getModel();
        if (listaMascota != null) {
            listaMascota.forEach((t) -> {
                Object[] fila = new Object[5];
                fila[0] = t.getId_mascota();
                fila[1] = t.getNom_mascota();
                fila[2] = t.getEdad();
                fila[3] = t.getNom_especie();
                fila[4] = t.getNom_raza();
                model.addRow(fila);
            });
            pnlEstetica.getJtbl1().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoEstetica.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void refrescarServicios(List<Servicios> listaServicios){
        ((DefaultTableModel) pnlEstetica.getJtbl2().getModel()).setNumRows(0);
        DefaultTableModel model = (DefaultTableModel) pnlEstetica.getJtbl2().getModel();
        if (listaServicios != null) {
            listaServicios.forEach((t) -> {
                Object[] fila = new Object[3];
                fila[0] = t.getId_servicios();
                fila[1] = t.getNombreS();
                fila[2] = t.getTarifa();         
                model.addRow(fila);
            });
            pnlEstetica.getJtbl2().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, daoEstetica.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void agregarMascota(){              
        Object ob =  pnlEstetica.getJtbl1().getModel().getValueAt(pnlEstetica.getJtbl1().getSelectedRow(), 0);
        Object ob2 = pnlEstetica.getJtbl1().getModel().getValueAt(pnlEstetica.getJtbl1().getSelectedRow(), 4);
        Object ob3 = pnlEstetica.getJtbl1().getModel().getValueAt(pnlEstetica.getJtbl1().getSelectedRow(), 1);
        daoEstetica = new DaoMascotaImpl();
        Mascota masc = (Mascota) daoEstetica.searchById(Integer.parseInt(String.valueOf(ob)));
        daoEstetica = new DaoServiciosImpl();
        List<Servicios> listaServicios= (List<Servicios>) daoEstetica.searchById3(2, masc.getId_especie());
        if (listaServicios!=null) {           
            refrescarServicios(listaServicios);
            pnlEstetica.getTxt_idM().setText(String.valueOf(ob));
            pnlEstetica.getTxt_nombM().setText(String.valueOf(ob3));
            pnlEstetica.getTxt_especieM().setText(String.valueOf(ob2));
        }
    }
    
    private void buscarMascota(){
        daoEstetica = new DaoMascotaImpl();
        String query = pnlEstetica.getTxt_buscarM().getText();
        if ((!"".equals(query))) {
             Mascota mascota = (Mascota) daoEstetica.searchByQuery2(query);
             if (mascota != null) {
                 List<Mascota> listaMascota = new ArrayList();
                 listaMascota.add(mascota);
                 refrescarMascotas(listaMascota);
            }
        }
       
    }
    private void agregarServicio(){
        Object ob =  pnlEstetica.getJtbl2().getModel().getValueAt(pnlEstetica.getJtbl2().getSelectedRow(), 0);
        Object ob2 = pnlEstetica.getJtbl2().getModel().getValueAt(pnlEstetica.getJtbl2().getSelectedRow(), 1);
        Object ob3 = pnlEstetica.getJtbl2().getModel().getValueAt(pnlEstetica.getJtbl2().getSelectedRow(), 2);
        pnlEstetica.getTxt_idS().setText(String.valueOf(ob));
        pnlEstetica.getTxt_nombS().setText(String.valueOf(ob2));
        pnlEstetica.getTxt_tarifaS().setText(String.valueOf(ob3));
        pnlEstetica.getTxt_total().setText(String.valueOf(ob3));
        
    }
    private void cobrar(){
        double total;
        
        if (pnlEstetica.getBtnGroup().getSelection()!=null) {
            total = Double.parseDouble(pnlEstetica.getTxt_total().getText());
            if (pnlEstetica.getRbtn1().isSelected()) {
                total=total+15.0;
            }
            if (!"".equals(pnlEstetica.getTxt_ingresado().getText())) {
                double importe = Double.parseDouble(pnlEstetica.getTxt_ingresado().getText());
                double vuelto = importe-total;
                if (vuelto>=0) {
                    pnlEstetica.getTxt_total().setText(String.valueOf(total));
                    pnlEstetica.getTxt_ingresado().setEditable(false);
                    pnlEstetica.getTxt_devolucion().setText(String.valueOf(vuelto));
                    pnlEstetica.getBtn_generarBoleta().setEnabled(true);
                }else{
                  JOptionPane.showMessageDialog(null, "Las cuentas no cuadran :( ", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);  
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Escoje una opción de envío", "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void generarBoleta(){
        //Primero generamos Orden de Pedido
        int id_cli = Integer.parseInt(pnlEstetica.getTxt_idCli().getText());
        int tipo = 2;
        String dni = pnlEstetica.getTxt_dniCli().getText();
        String nombreCli= pnlEstetica.getTxt_nombCli().getText()+" "+ pnlEstetica.getTxt_apeCli().getText();
        String direcCli= pnlEstetica.getTxt_direcCli().getText();
        int cantidad =1; //Para examenes y medicamentos cambiara
        int id_estado=2;//Para citas y  medicamentos se empieza con 1
        OrdenPedido ordenP = new OrdenPedido(id_cli, tipo, dni, nombreCli, direcCli, cantidad, id_estado);
        daoEstetica = new DaoOrdenPedidoImpl();
        daoEstetica.insert(ordenP);
        daoEstetica.getMessage();//Borrar
              
        //Segundo Generamos Detalle Orden de Pedido
        int id_orden = daoEstetica.searchById4(2, id_cli);// get numero de orden
        int id_serv  = Integer.parseInt(pnlEstetica.getTxt_idS().getText());
        String descrip= pnlEstetica.getTxt_nombS().getText();
        double precio= Double.parseDouble(pnlEstetica.getTxt_tarifaS().getText());
        DetallePedido detalleP = new DetallePedido(id_orden, id_serv, cantidad, descrip, precio);
        List<DetallePedido> listaDetalle= new ArrayList();
        listaDetalle.add(detalleP);
        daoEstetica = new DaoDetallePedidoImpl();
        daoEstetica.insert2(listaDetalle, true);
        daoEstetica.getMessage();//Borrar
        
        //Tercero generamos boleta
        int idTrabajador = CtrlMC.idT;
        double importeTotal = Double.parseDouble(pnlEstetica.getTxt_total().getText());
        double montoIngresado= Double.parseDouble(pnlEstetica.getTxt_ingresado().getText());
        double vuelto= Double.parseDouble(pnlEstetica.getTxt_devolucion().getText());
        Boleta boleta = new Boleta(id_orden, idTrabajador, LocalDate.now().toString(),importeTotal,montoIngresado,vuelto);
        daoEstetica = new DaoBoletaImpl();
        daoEstetica.insert(boleta);
        daoEstetica.getMessage();
        
        //JASPER
        InputStream arch = CtrlEstetica_MC.class.getClassLoader().getResourceAsStream("informes/Factura.jrxml");
        System.out.println( "Ubicacion de reporte " + arch.toString() );
        daoEstetica = new DaoInfoEmpresaImpl();
        List<InfoEmpresa> info = daoEstetica.sel();
        
        List<Boleta> lr= new ArrayList();
        listaDetalle.forEach((t) -> {
            lr.add(new Boleta(info.get(0).getNombreE(),info.get(0).getRuc(),info.get(0).getPropiertario(),info.get(0).getDirec(),info.get(0).getTelf(), info.get(0).getCel(), info.get(0).getCorreo(), 
                    t.getId_orden(), nombreCli, direcCli, dni, LocalDate.now().toString(), t.getCantidad(), t.getDescripcion(), t.getPrecio(),
                    t.getPrecio(), montoIngresado, importeTotal, vuelto));
        });
        try{
            JasperReport jr = JasperCompileManager.compileReport(arch);
            Map<String, Object> pr = new HashMap<>();
            JRBeanCollectionDataSource jbcd = new JRBeanCollectionDataSource(lr);            
            JasperPrint jp = JasperFillManager.fillReport(jr, pr, jbcd);
            JasperViewer.viewReport(jp);
        }catch (JRException e){
            e.printStackTrace();
        }      
      
        
    }
    
     private void cambiarCliente() {
        pnlEstetica.limpiarCliente();
        pnlEstetica.limpiarMascota();    
        pnlEstetica.limpiarServicio();
     }

        
}
