package pe.sanpedro.systemcv.view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.Especie;
import pe.sanpedro.systemcv.model.Mascota;
import pe.sanpedro.systemcv.model.Raza;

/**
 *
 * @author Mysk
 */
public class PnlMascotas_MR extends javax.swing.JPanel {
public static Cliente cliente = new Cliente();
    public Mascota mascota = null;
    public Cliente c = null;
    public Especie es = null;
    public Raza r = null;
    /**
     * Creates new form PnlTrabajadores_MA
     */
    public PnlMascotas_MR() {
        initComponents();

    }

    
    public JTextField getJTextFieldDNIBuscar() {
        return jTextFieldDNIBuscar;
    }

    public JTextField getJtxtNombre() {
        return jtxtNombre;
    }

    public JTextField getJtxtEdad() {
        return jtxtEdad;
    }

    public JLabel getJLabelNombre() {
        return jLabelNombre;
    }

    public JLabel getJLabelApellidos() {
        return jLabelApellidos;
    }

    public JLabel getJLabelId_cliente() {
        return jLabelId_cliente;
    }

    public JLabel getJLabelNMascotas() {
        return jLabelNMascotas;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnModificarExtraer() {
        return btnModificarExtraer;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JComboBox<Object> getJComboBoxEspecie() {
        return jComboBoxEspecie;
    }

    public JComboBox<Object> getJComboBoxRaza() {
        return jComboBoxRaza;
    }

    public JTable getJTableMascotas() {
        return jTableMascotas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtnGroup = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelApellidos1 = new javax.swing.JLabel();
        jTextFieldDNIBuscar = new javax.swing.JTextField();
        jLabelId_cliente2 = new javax.swing.JLabel();
        jLabelId_cliente = new javax.swing.JLabel();
        jLabelNMascotas = new javax.swing.JLabel();
        jLabelApellidos2 = new javax.swing.JLabel();
        jLabelNombre1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jtxtEdad = new javax.swing.JTextField();
        jComboBoxEspecie = new javax.swing.JComboBox<>();
        jComboBoxRaza = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMascotas = new javax.swing.JTable();
        btnModificarExtraer = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1243, 646));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1243, 646));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 210, 30));

        jLabelApellidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 270, 30));

        jLabelApellidos1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelApellidos1.setText("Cantidad de mascotas:");
        jPanel2.add(jLabelApellidos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 170, 30));

        jTextFieldDNIBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldDNIBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 260, -1));

        jLabelId_cliente2.setText("Id cliente:");
        jLabelId_cliente2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelId_cliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 90, 30));

        jLabelId_cliente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelId_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 40, 30));

        jLabelNMascotas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelNMascotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, 40, 30));

        jLabelApellidos2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelApellidos2.setText("Apellidos:");
        jPanel2.add(jLabelApellidos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 80, 30));

        jLabelNombre1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabelNombre1.setText("Nombre:");
        jPanel2.add(jLabelNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 80, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, 20));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 290, 20));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/search.png"))); // NOI18N
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 50, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1170, 150));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Datos de la mascota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nombre Mascota: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Especie:");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 60, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Raza:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 40, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Edad:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 50, 30));

        jtxtNombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 260, -1));

        jtxtEdad.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(jtxtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 260, -1));

        jComboBoxEspecie.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxEspecie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEspecieItemStateChanged(evt);
            }
        });
        jComboBoxEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEspecieActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 260, -1));

        jComboBoxRaza.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBoxRaza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una especie" }));
        jPanel1.add(jComboBoxRaza, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 260, -1));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/edit.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 160, -1));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/add-user.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 160, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 450, 440));

        jTableMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableMascotas);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 720, 360));

        btnModificarExtraer.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnModificarExtraer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/edit.png"))); // NOI18N
        btnModificarExtraer.setText("Modificar");
        jPanel3.add(btnModificarExtraer, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 160, -1));

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/del-user.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 190, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxEspecieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEspecieItemStateChanged

    }//GEN-LAST:event_jComboBoxEspecieItemStateChanged

    private void jComboBoxEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEspecieActionPerformed

    }//GEN-LAST:event_jComboBoxEspecieActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarExtraer;
    private javax.swing.JComboBox<Object> jComboBoxEspecie;
    private javax.swing.JComboBox<Object> jComboBoxRaza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelApellidos1;
    private javax.swing.JLabel jLabelApellidos2;
    private javax.swing.JLabel jLabelId_cliente;
    private javax.swing.JLabel jLabelId_cliente2;
    private javax.swing.JLabel jLabelNMascotas;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableMascotas;
    private javax.swing.JTextField jTextFieldDNIBuscar;
    private javax.swing.JTextField jtxtEdad;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.ButtonGroup rbtnGroup;
    // End of variables declaration//GEN-END:variables
}
