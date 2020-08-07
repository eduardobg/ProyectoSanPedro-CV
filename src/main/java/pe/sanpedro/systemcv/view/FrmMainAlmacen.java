
package pe.sanpedro.systemcv.view;

import javax.swing.JButton;

/**
 *
 * @author Mysk
 */
public class FrmMainAlmacen extends javax.swing.JFrame {

    /**
     * Creates new form FrmMainAlmacen
     */
    public FrmMainAlmacen() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public JButton getBtn_control() {
        return btn_control;
    }

    public JButton getBtn_despacho() {
        return btn_despacho;
    }

    public JButton getBtn_inicio() {
        return btn_inicio;
    }

    public JButton getBtn_mantenimiento() {
        return btn_mantenimiento;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_inicio = new javax.swing.JButton();
        btn_despacho = new javax.swing.JButton();
        btn_mantenimiento = new javax.swing.JButton();
        btn_control = new javax.swing.JButton();
        Pnl_VP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_inicio.setText("Inicio");
        jPanel1.add(btn_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 60));

        btn_despacho.setText("Despacho de Productos");
        jPanel1.add(btn_despacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 190, 60));

        btn_mantenimiento.setText("Mantenimiento Productos");
        jPanel1.add(btn_mantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 210, 60));

        btn_control.setText("Contrlol de Caducidad");
        jPanel1.add(btn_control, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 170, 60));

        Pnl_VP.setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/fondoMAL.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        Pnl_VP.add(jLabel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, 1307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Pnl_VP;
    private javax.swing.JButton btn_control;
    private javax.swing.JButton btn_despacho;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton btn_mantenimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
