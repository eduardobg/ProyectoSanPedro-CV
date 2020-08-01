package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pe.sanpedro.systemcv.dao.impl.DaoRazaImpl;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoClienteImpl;
import pe.sanpedro.systemcv.dao.impl.DaoEspecieImpl;
import pe.sanpedro.systemcv.dao.impl.DaoMascotaImpl;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.Especie;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.Raza;
import pe.sanpedro.systemcv.view.FrmMainRecepcion;
import pe.sanpedro.systemcv.view.PnlMascotas_MR;

/**
 *
 * @author Brayan
 */
class CtrlMascotas_MR {

    private GenericDao daoclientes;
    private GenericDao daomascotas;
    private GenericDao daoespecies;
    private GenericDao daorazas;
    private PnlMascotas_MR pnlMascotas;

    public CtrlMascotas_MR(GenericDao daoclientes, GenericDao daomascotas, GenericDao daoespecies, GenericDao daorazas) {
        this.daoclientes = daoclientes;
        this.daomascotas = daomascotas;
        this.daoespecies = daoespecies;
        this.daorazas = daorazas;
    }

    public void initController() {
        this.daoclientes = new DaoClienteImpl();
        this.daomascotas = new DaoMascotaImpl();
        this.daoespecies = new DaoEspecieImpl();
        this.daorazas = new DaoRazaImpl();
        pnlMascotas = new PnlMascotas_MR();
        this.EspeciesCbx();
        pnlMascotas.getBtnAgregar().setEnabled(false);
        pnlMascotas.getBtnModificar().setEnabled(false);
        pnlMascotas.getBtnAgregar().addActionListener(e -> agregar());
        pnlMascotas.getBtnModificar().addActionListener(e -> modificar());
        pnlMascotas.getBtnModificarExtraer().addActionListener(e -> modificarE());
        pnlMascotas.getBtnEliminar().addActionListener(e -> eliminar());
        pnlMascotas.getBtnBuscar().addActionListener(e -> buscarCliente());
        pnlMascotas.getJComboBoxEspecie().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                cbxEspecie();
            }
        });
        //pnlMascotas.setVisible(true);

        CardLayout vista = (CardLayout) FrmMainRecepcion.Pnl_VP.getLayout();
        FrmMainRecepcion.Pnl_VP.add(pnlMascotas, "Mascotas");
        vista.show(FrmMainRecepcion.Pnl_VP, "Mascotas");
        SwingUtilities.updateComponentTreeUI(CtrlMR.frmMR);
        CtrlMR.frmMR.repaint();
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
        pnlMascotas.getJLabelNMascotas().setText(i + "");
        pnlMascotas.getJTableMascotas().setModel(modelo);

    }

    public void EspeciesCbx() {
        List<Especie> lista = daoespecies.sel();
        Especie es = new Especie();
        Especie sele = new Especie();
        sele.setId_especie(0);
        sele.setNombre("Seleccione una especie");
        pnlMascotas.getJComboBoxEspecie().addItem(sele);
        if (0 <= lista.size()) {
            for (int i = 0; i < lista.size(); i++) {
                es = lista.get(i);
                pnlMascotas.getJComboBoxEspecie().addItem(es);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro especies");
        }
    }

    public void RazasCbx(int id_especie) {
        List<Raza> liste = daorazas.searchById2(id_especie);
        Raza r = new Raza();
        Raza sele = new Raza();
        sele.setId_raza(0);
        sele.setNombre("Seleccione una especie");
        pnlMascotas.getJComboBoxRaza().addItem(sele);
        if (liste.size() > 0) {
            for (int i = 0; i < liste.size(); i++) {
                r = liste.get(i);
                pnlMascotas.getJComboBoxRaza().addItem(r);
            }
        }

    }

    public void agregar() {
        String nombre = pnlMascotas.getJtxtNombre().getText();
        int edad = Integer.parseInt(pnlMascotas.getJtxtEdad().getText());
        int id = Integer.parseInt(pnlMascotas.getJLabelId_cliente().getText());
        pnlMascotas.es = (Especie) pnlMascotas.getJComboBoxEspecie().getSelectedItem();
        pnlMascotas.r = (Raza) pnlMascotas.getJComboBoxRaza().getSelectedItem();
        if (pnlMascotas.es.getId_especie() != 3) {
            daomascotas.insert(new Mascota(nombre, pnlMascotas.es.getId_especie(), pnlMascotas.r.getId_raza(), edad, id));
            JOptionPane.showMessageDialog(null, "Se ha registrado a " + nombre);

            this.mostrarMascotas(id);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una especie para su mascota");
        }
        pnlMascotas.getJtxtNombre().setText("");
        pnlMascotas.getJtxtEdad().setText("");
        pnlMascotas.getJComboBoxEspecie().getComponent(1);

    }

    public void modificar() {
        pnlMascotas.mascota.setNom_mascota(pnlMascotas.getJtxtNombre().getText());
        pnlMascotas.mascota.setEdad(Integer.parseInt(pnlMascotas.getJtxtEdad().getText()));
        pnlMascotas.mascota.setId_cliente(Integer.parseInt(pnlMascotas.getJLabelId_cliente().getText()));
        pnlMascotas.es = (Especie) pnlMascotas.getJComboBoxEspecie().getSelectedItem();
        pnlMascotas.r = (Raza) pnlMascotas.getJComboBoxRaza().getSelectedItem();
        pnlMascotas.mascota.setId_especie(pnlMascotas.es.getId_especie());
        pnlMascotas.mascota.setId_raza(pnlMascotas.r.getId_raza());
        if (pnlMascotas.es.getId_especie() != 3) {
            daomascotas.update(pnlMascotas.mascota);
            JOptionPane.showMessageDialog(null, "Se ha actualizado: " + pnlMascotas.mascota.getNom_mascota() + " con id: " + pnlMascotas.mascota.getId_mascota());
            this.mostrarMascotas(pnlMascotas.mascota.getId_cliente());
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una especie para su mascota");
        }
        pnlMascotas.getJtxtNombre().setText("");
        pnlMascotas.getJtxtEdad().setText("");
        pnlMascotas.getJComboBoxEspecie().setSelectedIndex(0);
        pnlMascotas.getBtnModificar().setEnabled(false);
        pnlMascotas.getBtnAgregar().setEnabled(true);
    }

    public void cbxEspecie() {
        pnlMascotas.getJComboBoxRaza().removeAllItems();
        Especie esp = (Especie) pnlMascotas.getJComboBoxEspecie().getSelectedItem();
        if (esp.getId_especie() != 3) {
            this.RazasCbx(esp.getId_especie());
        } else {
            Raza ra = new Raza();
            ra.setNombre("Seleccione una raza");
            pnlMascotas.getJComboBoxRaza().addItem(ra);
        }
    }

    public void eliminar() {
        int fila = -1;
        fila = pnlMascotas.getJTableMascotas().getSelectedRow();
        if (fila != -1) {
            Mascota m = new Mascota();
            List<Mascota> u = daomascotas.searchById2(Integer.parseInt(pnlMascotas.getJLabelId_cliente().getText()));
            m = u.get(fila);
            daomascotas.delete(m.getId_mascota());
            int id = Integer.parseInt(pnlMascotas.getJLabelId_cliente().getText());
            this.mostrarMascotas(id);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la mascota a eliminar");
        }
    }

    public void buscarCliente() {
        String dni = pnlMascotas.getJTextFieldDNIBuscar().getText();
        Cliente c = (Cliente) daoclientes.searchByQuery2(dni);
        if (c != null) {
            pnlMascotas.getJLabelNombre().setText(c.getNombre());
            pnlMascotas.getJLabelApellidos().setText(c.getApellido());
            pnlMascotas.getJLabelId_cliente().setText(c.getId_cliente() + "");

            this.mostrarMascotas(c.getId_cliente());
            pnlMascotas.getBtnAgregar().setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "El cliente con el DNI " + dni + " no ha sido registrado");
        }
    }

    public void modificarE() {
        int fila = -1;
        fila = pnlMascotas.getJTableMascotas().getSelectedRow();
        if (fila != -1) {

            List<Mascota> u = daomascotas.searchById2(Integer.parseInt(pnlMascotas.getJLabelId_cliente().getText()));
            pnlMascotas.mascota = u.get(fila);
            pnlMascotas.getJtxtNombre().setText(pnlMascotas.mascota.getNom_mascota());
            pnlMascotas.getJComboBoxEspecie().setSelectedIndex(pnlMascotas.mascota.getId_especie());
            if (pnlMascotas.mascota.getId_especie() == 1) {
                pnlMascotas.getJComboBoxRaza().setSelectedIndex(pnlMascotas.mascota.getId_raza());
            } else {
                pnlMascotas.getJComboBoxRaza().setSelectedIndex(pnlMascotas.mascota.getId_raza() - 2);
            }
            pnlMascotas.getJtxtEdad().setText(pnlMascotas.mascota.getEdad() + "");

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione la mascota a modificar");
        }
        pnlMascotas.getBtnModificar().setEnabled(true);
        pnlMascotas.getBtnAgregar().setEnabled(false);
    }

}
