package pe.sanpedro.systemcv.view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import pe.sanpedro.systemcv.model.Cliente;

/**
 *
 * @author Mysk
 */
public class PnlClientes_MR extends javax.swing.JPanel {
public static Cliente cliente = new Cliente();

    /**
     * Creates new form PnlTrabajadores_MA
     */
    public PnlClientes_MR() {
        initComponents();

    }

    public JTextField getJTextFieldDNI() {
        return jTextFieldDNI;
    }

    public JTextField getJTextFieldNombre() {
        return jTextFieldNombre;
    }

    public JTextField getJTextFieldDireccion() {
        return jTextFieldDireccion;
    }

    public JTextField getJTextFieldApellidos() {
        return jTextFieldApellidos;
    }

    public JTextField getJTextFieldCelular() {
        return jTextFieldCelular;
    }

    public JTextField getJTextFieldDNIBuscar() {
        return jTextFieldDNIBuscar;
    }

    public JButton getBtn_buscarDni() {
        return btn_buscarDni;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }
     public JButton getBtnModificarC() {
        return btnModificarC;
    }
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnMostrarClientes() {
        return btnMostrarClientes;
    }

    public JButton getBtn_buscarDniCliente() {
        return btn_buscarDniCliente;
    }

    public DatePicker getDatepicker_nacimiento() {
        return datepicker_nacimiento;
    }

    public JTable getJTablaClientes() {
        return jTablaClientes;
    }

    public JComboBox<String> getJComboBoxSexo() {
        return jComboBoxSexo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtnGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabeDNI = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelFechaN = new javax.swing.JLabel();
        jTextFieldDNI = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldCelular = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jLabelCelular = new javax.swing.JLabel();
        jComboBoxSexo = new javax.swing.JComboBox<>();
        jTextFieldNombre = new javax.swing.JTextField();
        datepicker_nacimiento = new com.github.lgooddatepicker.components.DatePicker();
        btn_buscarDni = new javax.swing.JButton();
        btnModificarC = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldDNIBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaClientes = new javax.swing.JTable();
        btn_buscarDniCliente = new javax.swing.JButton();
        btnMostrarClientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2), "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabeDNI.setText("DNI:");
        jLabeDNI.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabeDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 43, 30));

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelNombre.setText("Nombre:");
        jPanel2.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 80, 30));

        jLabelDireccion.setText("Dirección:");
        jLabelDireccion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 80, 30));

        jLabelApellidos.setText("Apellidos:");
        jLabelApellidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 80, 30));

        jLabelFechaN.setText("F. Nacimiento:");
        jLabelFechaN.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelFechaN, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 110, 30));

        jTextFieldDNI.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 120, -1));

        jTextFieldApellidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 220, -1));

        jTextFieldDireccion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 260, -1));

        jTextFieldCelular.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 140, -1));

        jLabelSexo.setText("Sexo:");
        jLabelSexo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 50, 30));

        jLabelCelular.setText("Celular:");
        jLabelCelular.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 60, 30));

        jComboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione su sexo", "Masculino", "Femenino" }));
        jComboBoxSexo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jComboBoxSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 170, -1));

        jTextFieldNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 160, -1));

        datepicker_nacimiento.setOpaque(false);
        jPanel2.add(datepicker_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 200, 30));

        btn_buscarDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/search.png"))); // NOI18N
        jPanel2.add(btn_buscarDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 40, -1));

        btnModificarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/edit.png"))); // NOI18N
        btnModificarC.setText("Modificar");
        btnModificarC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(btnModificarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, -1, -1));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/add-user.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 30, 130, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldDNIBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel2.setText("Ingrese el número de DNI");

        jTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "DNI", "Nombres", "Apellidos", "F. Nacimientol", "Dirección", "Celularl", "Sexo"
            }
        ));
        jScrollPane1.setViewportView(jTablaClientes);

        btn_buscarDniCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/search.png"))); // NOI18N

        btnMostrarClientes.setText("Mostrar Clientes");
        btnMostrarClientes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel1.setText("Lista de Clientes");
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/edit.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/del-user.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldDNIBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscarDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnMostrarClientes)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar)
                        .addGap(48, 48, 48)
                        .addComponent(btnEliminar)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(49, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_buscarDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar)
                        .addComponent(jLabel1)
                        .addComponent(btnMostrarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDNIBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarC;
    private javax.swing.JButton btnMostrarClientes;
    private javax.swing.JButton btn_buscarDni;
    private javax.swing.JButton btn_buscarDniCliente;
    private com.github.lgooddatepicker.components.DatePicker datepicker_nacimiento;
    private javax.swing.JComboBox<String> jComboBoxSexo;
    private javax.swing.JLabel jLabeDNI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelCelular;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelFechaN;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaClientes;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDNIBuscar;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.ButtonGroup rbtnGroup;
    // End of variables declaration//GEN-END:variables
}
