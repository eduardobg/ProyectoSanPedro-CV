package pe.sanpedro.systemcv.view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import pe.sanpedro.systemcv.model.Cliente;
import pe.sanpedro.systemcv.model.Horario;
import pe.sanpedro.systemcv.model.Mascota;

public class PnlCitas_MR extends javax.swing.JPanel {

    public Cliente cliente = null;
    public Mascota mascota = null;
    public Horario horario = null;

    public PnlCitas_MR() {
        initComponents();

    }

    public JButton getBtnAgregarMascota() {
        return btnAgregarMascota;
    }

    public JButton getBtnAgregarHorario() {
        return btnAgregarHorario;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnHFecha() {
        return btnHFecha;
    }

    public JButton getBtnGenerarOrden() {
        return btnGenerarOrden;
    }

    public JTextField getJTextFieldDNI() {
        return jTextFieldDNI;
    }

    public JTextField getJTextFieldHora() {
        return jTextFieldHora;
    }

    public JTextField getJTextFieldDia() {
        return jTextFieldDia;
    }

    public JTextField getJTextFieldTurno() {
        return jTextFieldTurno;
    }

    public JTextField getJTextFieldIdMascota() {
        return jTextFieldIdMascota;
    }

    public JTextField getJTextFieldNombreMascota() {
        return jTextFieldNombreMascota;
    }

    public JTextField getJTextFieldEspecie() {
        return jTextFieldEspecie;
    }

    public JLabel getJLabelId() {
        return jLabelId;
    }

    public JLabel getJLabelDNI() {
        return jLabelDNI;
    }

    public JLabel getJLabelNombres() {
        return jLabelNombres;
    }

    public JLabel getJLabelApellidos() {
        return jLabelApellidos;
    }

    public JLabel getJLabelDireccion() {
        return jLabelDireccion;
    }

    public JTable getJTablaMascotas() {
        return jTablaMascotas;
    }

    public JTable getJTablaHorario() {
        return jTablaHorario;
    }

    public DatePicker getDatepicker_dia() {
        return datepicker_dia;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabelDNI = new javax.swing.JLabel();
        jLabelNombres = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        datepicker_dia = new com.github.lgooddatepicker.components.DatePicker();
        btnAgregarHorario = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaHorario = new javax.swing.JTable();
        btnHFecha = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jTextFieldEspecie = new javax.swing.JTextField();
        jTextFieldHora = new javax.swing.JTextField();
        jTextFieldDia = new javax.swing.JTextField();
        jTextFieldTurno = new javax.swing.JTextField();
        jTextFieldIdMascota = new javax.swing.JTextField();
        jTextFieldNombreMascota = new javax.swing.JTextField();
        btnGenerarOrden = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaMascotas = new javax.swing.JTable();
        btnAgregarMascota = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1243, 646));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1243, 646));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 650));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldDNI.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextFieldDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Dirección:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Apellidos:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabelDireccion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 410, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Nombres:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 420, 10));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 40, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 420, 10));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 420, 10));

        jLabel5.setText("ID:");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabelDNI.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 190, 20));

        jLabelNombres.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 410, 20));

        jLabelApellidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 410, 20));

        btnBuscar.setText("Buscar");
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel13.setText("DNI:");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabelId.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jLabelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 40, 20));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 230, 10));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Horarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(650, 650));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Costo Fijo por consulta:   S/ 50.00");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, 20));

        datepicker_dia.setOpaque(false);
        jPanel3.add(datepicker_dia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 200, 30));

        btnAgregarHorario.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAgregarHorario.setText("Agregar");
        jPanel3.add(btnAgregarHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, -1, -1));

        jTablaHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hora Inicio", "Hora Final", "Turno", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablaHorario);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 580, 200));

        btnHFecha.setText("Buscar");
        jPanel3.add(btnHFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Detalle Consulta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(650, 650));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Especie:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 60, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Hora:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Día:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 40, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Turno:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 50, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("ID Mascota: ");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Nombre Mascota:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, 30));

        jSeparator6.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 420, 10));

        jTextFieldEspecie.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldEspecie.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jPanel4.add(jTextFieldEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 300, -1));

        jTextFieldHora.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel4.add(jTextFieldHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 320, -1));

        jTextFieldDia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel4.add(jTextFieldDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 330, -1));

        jTextFieldTurno.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel4.add(jTextFieldTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 310, -1));

        jTextFieldIdMascota.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldIdMascota.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jPanel4.add(jTextFieldIdMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 280, -1));

        jTextFieldNombreMascota.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextFieldNombreMascota.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jPanel4.add(jTextFieldNombreMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 240, -1));

        btnGenerarOrden.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnGenerarOrden.setText("Generar Orden Cita");
        jPanel4.add(btnGenerarOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 240, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2), "Mascotas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(650, 650));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablaMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Edad", "Raza", "Especie"
            }
        ));
        jScrollPane1.setViewportView(jTablaMascotas);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 580, 200));

        btnAgregarMascota.setText("Agregar");
        btnAgregarMascota.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel5.add(btnAgregarMascota, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarHorario;
    private javax.swing.JButton btnAgregarMascota;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarOrden;
    private javax.swing.JButton btnHFecha;
    private com.github.lgooddatepicker.components.DatePicker datepicker_dia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTable jTablaHorario;
    private javax.swing.JTable jTablaMascotas;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDia;
    private javax.swing.JTextField jTextFieldEspecie;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldIdMascota;
    private javax.swing.JTextField jTextFieldNombreMascota;
    private javax.swing.JTextField jTextFieldTurno;
    // End of variables declaration//GEN-END:variables
}
