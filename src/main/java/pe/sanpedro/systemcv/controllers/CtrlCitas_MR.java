package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoDetallePedidoImpl;
import pe.sanpedro.systemcv.dao.impl.DaoEspecieImpl;
import pe.sanpedro.systemcv.dao.impl.DaoHorarioImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenCitaImpl;
import pe.sanpedro.systemcv.dao.impl.DaoOrdenPedidoImpl;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.DetallePedido;
import pe.sanpedro.systemcv.model.Horario;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.OrdenCita;
import pe.sanpedro.systemcv.model.OrdenPedido;
import pe.sanpedro.systemcv.view.FrmMainRecepcion;
import pe.sanpedro.systemcv.view.PnlCitas_MR;

public class CtrlCitas_MR {

    private GenericDao daoclientes;
    private GenericDao daomascotas;
    private GenericDao daohorarios;
    private GenericDao daoOrden;
    private GenericDao daoDetalle;
    private GenericDao daoOrdenCita;
    private PnlCitas_MR pnlCita;

    public CtrlCitas_MR() {

    }

    public void initController() {
        this.daoclientes = new DaoClienteImpl();
        this.daomascotas = new DaoMascotaImpl();
        this.daohorarios = new DaoHorarioImpl();
        this.daoOrden = new DaoOrdenPedidoImpl();
        this.daoDetalle = new DaoDetallePedidoImpl();
        this.daoOrdenCita = new DaoOrdenCitaImpl();
        pnlCita = new PnlCitas_MR();
        pnlCita.getDatepicker_dia().setDate(LocalDate.now());
        inhabilitar();
        pnlCita.getBtnAgregarHorario().addActionListener(e -> AgregarHorario());
        pnlCita.getBtnAgregarMascota().addActionListener(e -> AgregarMascota());
        pnlCita.getBtnBuscar().addActionListener(e -> buscarCliente());
        pnlCita.getBtnHFecha().addActionListener(e -> mostrarHorarios());
        pnlCita.getBtnGenerarOrden().addActionListener(e -> generarOrden());
        CardLayout vista = (CardLayout) FrmMainRecepcion.Pnl_VP.getLayout();
        FrmMainRecepcion.Pnl_VP.add(pnlCita, "Cita");
        vista.show(FrmMainRecepcion.Pnl_VP, "Cita");
        SwingUtilities.updateComponentTreeUI(CtrlMR.frmMR);
        CtrlMR.frmMR.repaint();
    }

    private void buscarCliente() {
        String dni = pnlCita.getJTextFieldDNI().getText();
        if (!"".equals(dni)) {
            pnlCita.cliente = (Cliente) daoclientes.searchByQuery2(dni);
            if (pnlCita.cliente != null) {
                pnlCita.getJLabelId().setText(String.valueOf(pnlCita.cliente.getId_cliente()));
                pnlCita.getJLabelDNI().setText(pnlCita.cliente.getDni());
                pnlCita.getJLabelNombres().setText(pnlCita.cliente.getNombre());
                pnlCita.getJLabelApellidos().setText(pnlCita.cliente.getApellido());
                pnlCita.getJLabelDireccion().setText(pnlCita.cliente.getDireccion());
                pnlCita.mascota = null;
                mostrarMascotas(pnlCita.cliente.getId_cliente());

            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un DNI");
            }
        }
    }

    public void inhabilitar() {
        pnlCita.getJTextFieldIdMascota().setEnabled(true);
        pnlCita.getJTextFieldNombreMascota().setEnabled(true);
        pnlCita.getJTextFieldEspecie().setEnabled(true);
        pnlCita.getJTextFieldHora().setEnabled(true);
        pnlCita.getJTextFieldTurno().setEnabled(true);
        pnlCita.getJTextFieldDia().setEnabled(true);
    }

    public void limpiar() {
        pnlCita.getJTextFieldDNI().setText("");
        pnlCita.getJTextFieldIdMascota().setText("");
        pnlCita.getJTextFieldNombreMascota().setText("");
        pnlCita.getJTextFieldEspecie().setText("");
        pnlCita.getJTextFieldHora().setText("");
        pnlCita.getJTextFieldTurno().setText("");
        pnlCita.getJTextFieldDia().setText("");
        pnlCita.getJLabelDNI().setText("");
        pnlCita.getJLabelDNI().setText("");
        pnlCita.getJLabelApellidos().setText("");
        pnlCita.getJLabelDireccion().setText("");
        pnlCita.getJLabelId().setText("");
        pnlCita.getJLabelNombres().setText("");
        mostrarMascotas(0);
    }

    public void mostrarMascotas(Integer id_cliente) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Especie");
        modelo.addColumn("Raza");
        modelo.addColumn("Edad");
        int i = 0;
        List<Mascota> list = daomascotas.searchById2(id_cliente);
        for (Mascota c : list) {
            Object[] fila = new Object[4];
            fila[0] = c.getNom_mascota();
            fila[1] = c.getNom_especie();
            fila[2] = c.getNom_raza();
            fila[3] = c.getEdad();
            modelo.addRow(fila);
            i++;
        }
        pnlCita.getJTablaMascotas().setModel(modelo);
    }

    public void mostrarHorarios() {
        LocalDate d = pnlCita.getDatepicker_dia().getDate();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Turno");
        model.addColumn("Dia");
        model.addColumn("Hora inicial");
        model.addColumn("Hora final");
        model.addColumn("Estado");

        List<Horario> list = daohorarios.searchByDate(d);
        if (list != null) {
            for (Horario h : list) {
                Object[] fila = new Object[6];
                fila[0] = h.getId_horario();
                fila[1] = h.getTurno();
                fila[2] = h.getDia();
                fila[3] = h.getHora_i();
                fila[4] = h.getHora_f();
                fila[5] = h.getEstado();
                model.addRow(fila);
            }
            pnlCita.getJTablaHorario().setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, "No hay horarios disponibles");
        }
    }

    public void AgregarMascota() {
        int fila = -1;
        fila = pnlCita.getJTablaMascotas().getSelectedRow();
        if (fila != -1) {
            List<Mascota> u = daomascotas.searchById2(Integer.parseInt(pnlCita.getJLabelId().getText()));
            pnlCita.mascota = u.get(fila);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una mascota");
        }
        pnlCita.getJTextFieldIdMascota().setText(pnlCita.mascota.getId_mascota() + "");
        pnlCita.getJTextFieldNombreMascota().setText(pnlCita.mascota.getNom_mascota());
        pnlCita.getJTextFieldEspecie().setText(pnlCita.mascota.getNom_especie() + "");
    }

    public void AgregarHorario() {
        int fila = -1;
        fila = pnlCita.getJTablaHorario().getSelectedRow();
        if (fila != -1) {
            List<Horario> u = daohorarios.searchByDate(pnlCita.getDatepicker_dia().getDate());
            pnlCita.horario = u.get(fila);
            if (pnlCita.horario.getId_estado() != 2) {

                pnlCita.getJTextFieldHora().setText(pnlCita.horario.getHora_i() + "-" + pnlCita.horario.getHora_f());
                pnlCita.getJTextFieldDia().setText(pnlCita.horario.getDia() + "");
                pnlCita.getJTextFieldTurno().setText(pnlCita.horario.getTurno());
            } else {
                pnlCita.horario = null;
                JOptionPane.showMessageDialog(null, "El horario no esta disponible");
                pnlCita.getJTextFieldHora().setText("");
                pnlCita.getJTextFieldDia().setText("");
                pnlCita.getJTextFieldTurno().setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una mascota");
        }
    }

    public void generarOrden() {
        int id_cli = pnlCita.cliente.getId_cliente();
        int tipo = 1;
        String dni = pnlCita.cliente.getDni();
        String nombreCli = pnlCita.cliente.getNombre() + " " + pnlCita.cliente.getApellido();
        String direcCli = pnlCita.cliente.getDireccion();
        int cantidad = 1; //Para examenes y medicamentos cambiara
        int id_estado = 1;//Para citas y  medicamentos se empieza con 1
        OrdenPedido ordenP = new OrdenPedido(id_cli, tipo, dni, nombreCli, direcCli, cantidad, id_estado);
        if (pnlCita.horario != null) {
            daoOrden.insert(ordenP);
            daoOrden.getMessage();//Borrar        
            int id_orden = daoOrden.searchById4(tipo, id_cli);// get numero de orden        
            this.generarDetallePedido(id_orden);
            this.generarCita(id_orden);
            JOptionPane.showMessageDialog(null, "Cita de consulta #"+id_orden);
            NoDisponibleH();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un horario para la cita");
        }
        this.mostrarHorarios();
        pnlCita.cliente = null;
        pnlCita.horario = null;
        pnlCita.mascota = null;
        limpiar();

    }

    public void generarCita(int id_orden) {
        OrdenCita oc = new OrdenCita();
        oc.setId_orden(id_orden);
        oc.setId_horario(pnlCita.horario.getId_horario());
        oc.setId_mascota(pnlCita.mascota.getId_mascota());
        oc.setId_estado(1);
        daoOrdenCita.insert(oc);
    }

    private List<DetallePedido> generarDetallePedido(int id_orden) {
        int id_serv = 1;

        String descrip = "Consulta General " + pnlCita.horario.getHora_i() + " - " + pnlCita.horario.getHora_i() + " " + pnlCita.horario.getDia();
        double precio = 50;
        double subtotal = precio * 1;
        DetallePedido detalleP = new DetallePedido(id_orden, id_serv, 1, descrip, precio, subtotal);
        List<DetallePedido> listaDetalle = new ArrayList();
        listaDetalle.add(detalleP);
        daoDetalle.insert2(listaDetalle, true);
        return listaDetalle;
    }

    public void NoDisponibleH() {
        pnlCita.horario.setId_estado(2);
        daohorarios.update(pnlCita.horario);
    }
}
