package pe.sanpedro.systemcv.view;

import javax.swing.JButton;

/**
 *
 * @author Mysk
 */
public class FrmMainCaja extends javax.swing.JFrame {

    /**
     * Creates new form FrmMainCaja
     */
    public FrmMainCaja() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public JButton getBnt_estetica() {
        return bnt_estetica;
    }

    public JButton getBnt_examen() {
        return bnt_examen;
    }

    public JButton getBnt_pagor() {
        return bnt_pagor;
    }

    public JButton getBtn_consulta() {
        return btn_consulta;
    }

    public JButton getBtn_inicio() {
        return btn_inicio;
    }

    public JButton getBtn_pagom() {
        return btn_pagom;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_inicio = new javax.swing.JButton();
        btn_consulta = new javax.swing.JButton();
        bnt_estetica = new javax.swing.JButton();
        bnt_examen = new javax.swing.JButton();
        bnt_pagor = new javax.swing.JButton();
        btn_pagom = new javax.swing.JButton();
        Pnl_VP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_inicio.setText("Inicio");
        jPanel1.add(btn_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 60));

        btn_consulta.setText("Consulta General");
        jPanel1.add(btn_consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 160, 60));

        bnt_estetica.setText("Estética");
        jPanel1.add(bnt_estetica, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 160, 60));

        bnt_examen.setText("Exámenes Médicos");
        jPanel1.add(bnt_examen, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 150, 60));

        bnt_pagor.setText("Pago Reserva");
        jPanel1.add(bnt_pagor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 130, 60));

        btn_pagom.setText("Pago Medicamentos");
        jPanel1.add(btn_pagom, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 170, 60));

        Pnl_VP.setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/fondoMC.jpg"))); // NOI18N
        Pnl_VP.add(jLabel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMainCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMainCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMainCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMainCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMainCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Pnl_VP;
    private javax.swing.JButton bnt_estetica;
    private javax.swing.JButton bnt_examen;
    private javax.swing.JButton bnt_pagor;
    private javax.swing.JButton btn_consulta;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton btn_pagom;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
