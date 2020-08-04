/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Mysk
 */
public class PnlReportes_MA extends javax.swing.JPanel {

    /**
     * Creates new form PnlReportes_MA
     */
    public PnlReportes_MA() {
        initComponents();
    }

    public JButton getBtn_buscar() {
        return btn_buscar;
    }

    public JButton getBtn_generarExcel() {
        return btn_generarExcel;
    }

    public JButton getBtn_limpiar() {
        return btn_limpiar;
    }

    public JComboBox<String> getCbx() {
        return cbx;
    }

    public DatePicker getDate1() {
        return date1;
    }

    public DatePicker getDate2() {
        return date2;
    }

    public JTable getJtbl() {
        return jtbl;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbx = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        date1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        date2 = new com.github.lgooddatepicker.components.DatePicker();
        btn_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl = new javax.swing.JTable();
        btn_limpiar = new javax.swing.JButton();
        btn_generarExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Reportes de Caja"));

        jLabel1.setText("Fuentes de Ingresos:");

        cbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consultas Generales", "Baños y cortes", "Exámenes Médicos", "Venta de Medicamentos" }));

        jLabel2.setText("Del:");

        jLabel3.setText("Hasta:");

        btn_buscar.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(cbx, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(btn_buscar)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Boleta", "Fecha de Emisión", "Dni Trabajador", "NombTrabajador", "ApeP Trabajador", "ApeM Trabajador", "Dni Cliente", "Nombre Cliente", "Importe TOTAL"
            }
        ));
        jScrollPane1.setViewportView(jtbl);

        btn_limpiar.setText("Limpiar");

        btn_generarExcel.setText("Generar Excel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123))
            .addGroup(layout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(btn_limpiar)
                .addGap(256, 256, 256)
                .addComponent(btn_generarExcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_generarExcel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_generarExcel;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JComboBox<String> cbx;
    private com.github.lgooddatepicker.components.DatePicker date1;
    private com.github.lgooddatepicker.components.DatePicker date2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbl;
    // End of variables declaration//GEN-END:variables
}
