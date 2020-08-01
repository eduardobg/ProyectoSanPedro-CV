package pe.sanpedro.systemcv.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.CardLayout;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.view.FrmMainRecepcion;
import pe.sanpedro.systemcv.view.PnlClientes_MR;

/**
 *
 * @author Brayan
 */
public class CtrlClientes_MR {

    private GenericDao daoclientes;
    private PnlClientes_MR pnlClientes;

    public CtrlClientes_MR(GenericDao daoclientes) {
        this.daoclientes = daoclientes;
    }

    public void initController() {
        pnlClientes = new PnlClientes_MR();
        this.daoclientes = new DaoClienteImpl();
        mostrarClientes();
        pnlClientes.getBtnModificarC().setEnabled(false);
        pnlClientes.getDatepicker_nacimiento().setDate(LocalDate.now());
        pnlClientes.getBtn_buscarDni().addActionListener(e -> buscarReniec());
        pnlClientes.getBtnAgregar().addActionListener(e -> agregarCliente());
        pnlClientes.getBtnModificar().addActionListener(e -> extraerCliente());
        pnlClientes.getBtnModificarC().addActionListener(e -> modificarCliente());
        pnlClientes.getBtnEliminar().addActionListener(e -> eliminarCliente());
        pnlClientes.getBtn_buscarDniCliente().addActionListener(e -> buscarCliente());
        pnlClientes.getBtnMostrarClientes().addActionListener(e -> mostrarTodo());

        CardLayout vista = (CardLayout) FrmMainRecepcion.Pnl_VP.getLayout();
        FrmMainRecepcion.Pnl_VP.add(pnlClientes, "Clientes");
        vista.show(FrmMainRecepcion.Pnl_VP, "Clientes");
        SwingUtilities.updateComponentTreeUI(CtrlMR.frmMR);
        CtrlMR.frmMR.repaint();
    }

    public void mostrarClientes() {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Direccion");
        modelo.addColumn("Fecha Nacimiento");
        modelo.addColumn("Celular");
        modelo.addColumn("Sexo");
        List<Cliente> lista = daoclientes.sel();
        if (lista != null) {
            lista.forEach((c) -> {
                Object[] fila = new Object[8];
                fila[0] = c.getId_cliente();
                fila[1] = c.getDni();
                fila[2] = c.getNombre();
                fila[3] = c.getApellido();
                fila[4] = c.getDireccion();
                fila[5] = c.getFecha_nac();
                fila[6] = c.getCelular();
                fila[7] = c.getSexo();
                modelo.addRow(fila);
            });
            pnlClientes.getJTablaClientes().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoclientes.getMessage(), "RECEPCIÓN", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void limpiarjtxt() {
        pnlClientes.getJTextFieldDNI().setText("");
        pnlClientes.getJTextFieldNombre().setText("");
        pnlClientes.getJTextFieldApellidos().setText("");
        pnlClientes.getJTextFieldDireccion().setText("");
        pnlClientes.getJTextFieldCelular().setText("");
        pnlClientes.getDatepicker_nacimiento().setDate(LocalDate.now());
        pnlClientes.getJComboBoxSexo().setSelectedIndex(0);

    }

    public void buscarReniec() {
        String dni = pnlClientes.getJTextFieldDNI().getText();
        String enlace = "https://api.reniec.cloud/dni/" + dni;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();
            com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (dni.length() == 8) {
                if (root.isJsonObject()) {
                    JsonObject rootobj = root.getAsJsonObject();

                    String apellido_paterno = rootobj.get("apellido_paterno").getAsString();
                    String apellido_materno = rootobj.get("apellido_materno").getAsString();
                    String nombres = rootobj.get("nombres").getAsString();
                    if (rootobj.equals(null)) {
                        System.out.println("No se encontro el dni");
                    } else {
                        pnlClientes.getJTextFieldApellidos().setText("");
                        pnlClientes.getJTextFieldApellidos().setText(apellido_paterno + " " + apellido_materno);
                        pnlClientes.getJTextFieldNombre().setText("");
                        pnlClientes.getJTextFieldNombre().setText(nombres);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "DNI no encontrado (tiene menos o más de 8 caracteres )");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void agregarCliente() {
        Cliente c = new Cliente();
        c.setDni(pnlClientes.getJTextFieldDNI().getText());
        c.setNombre(pnlClientes.getJTextFieldNombre().getText());
        c.setApellido(pnlClientes.getJTextFieldApellidos().getText());
        c.setDireccion(pnlClientes.getJTextFieldDireccion().getText());
        c.setFecha_nac(pnlClientes.getDatepicker_nacimiento().getDate());
        c.setCelular(pnlClientes.getJTextFieldCelular().getText());
        c.setSexo((String) pnlClientes.getJComboBoxSexo().getSelectedItem());
        try {
            daoclientes.insert(c);
            JOptionPane.showMessageDialog(null, "Se ha registrado el cliente " + c.getNombre(), "Registrado", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            System.err.println("Error: "
                    + daoclientes.getMessage()
                    + ex.getMessage());
        }
        this.mostrarClientes();
        this.limpiarjtxt();
    }

    public void eliminarCliente() {
        int fila = -1;
        fila = pnlClientes.getJTablaClientes().getSelectedRow();
        GenericDao daomascotas;
        if (fila != -1) {

            Cliente c = new Cliente();
            List<Cliente> u = daoclientes.sel();
            c = u.get(fila);
            daomascotas = new DaoMascotaImpl();
            daomascotas.deleteall(c.getId_cliente());

            daoclientes.delete(c.getId_cliente());

            this.mostrarClientes();
            this.limpiarjtxt();
            pnlClientes.getBtnModificarC().setEnabled(false);
            pnlClientes.getBtnAgregar().setEnabled(true);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el cliente a eliminar");
        }
    }

    public void modificarCliente() {
        pnlClientes.cliente.setDni(pnlClientes.getJTextFieldDNI().getText());
        pnlClientes.cliente.setNombre(pnlClientes.getJTextFieldNombre().getText());
        pnlClientes.cliente.setApellido(pnlClientes.getJTextFieldApellidos().getText());
        pnlClientes.cliente.setDireccion(pnlClientes.getJTextFieldDireccion().getText());
        pnlClientes.cliente.setCelular(pnlClientes.getJTextFieldCelular().getText());
        pnlClientes.cliente.setFecha_nac(pnlClientes.getDatepicker_nacimiento().getDate());
        pnlClientes.cliente.setSexo((String) pnlClientes.getJComboBoxSexo().getSelectedItem());
        daoclientes.update(pnlClientes.cliente);
        JOptionPane.showMessageDialog(null, "Se ha modificado el cliente " + pnlClientes.cliente.getNombre());

        this.mostrarClientes();
        this.limpiarjtxt();
        pnlClientes.getBtnModificarC().setEnabled(false);
        pnlClientes.getBtnAgregar().setEnabled(true);
    }

    public void extraerCliente() {
        int fila = -1;
        fila = pnlClientes.getJTablaClientes().getSelectedRow();
        if (fila != -1) {
            List<Cliente> u = daoclientes.sel();
            pnlClientes.cliente = u.get(fila);
            pnlClientes.getJTextFieldDNI().setText(pnlClientes.cliente.getDni());
            pnlClientes.getJTextFieldNombre().setText(pnlClientes.cliente.getNombre());
            pnlClientes.getJTextFieldApellidos().setText(pnlClientes.cliente.getApellido());
            pnlClientes.getJTextFieldDireccion().setText(pnlClientes.cliente.getDireccion());
            pnlClientes.getJTextFieldCelular().setText(pnlClientes.cliente.getCelular());
            pnlClientes.getDatepicker_nacimiento().setDate(pnlClientes.cliente.getFecha_nac());
            pnlClientes.getJComboBoxSexo().setSelectedItem(pnlClientes.cliente.getSexo());

            pnlClientes.getBtnModificarC().setEnabled(true);
            pnlClientes.getBtnAgregar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el cliente a modificar");
        }
    }

    public void buscarCliente() {

        String dni = pnlClientes.getJTextFieldDNIBuscar().getText();
        Cliente c = new Cliente();

        c = (Cliente) daoclientes.searchByQuery2(dni);
        if (c != null) {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Codigo");
            modelo.addColumn("DNI");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Direccion");
            modelo.addColumn("Fecha Nacimiento");
            modelo.addColumn("Celular");
            modelo.addColumn("Sexo");
            Object[] fila = new Object[8];
            fila[0] = c.getId_cliente();
            fila[1] = c.getDni();
            fila[2] = c.getNombre();
            fila[3] = c.getApellido();
            fila[4] = c.getDireccion();
            fila[5] = c.getFecha_nac();
            fila[6] = c.getCelular();
            fila[7] = c.getSexo();
            modelo.addRow(fila);

            pnlClientes.getJTablaClientes().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con el DNI " + dni + " no ha sido registrado");
        }

    }

    public void mostrarTodo() {
        mostrarClientes();
        pnlClientes.getJTextFieldDNIBuscar().setText("");
    }
}
