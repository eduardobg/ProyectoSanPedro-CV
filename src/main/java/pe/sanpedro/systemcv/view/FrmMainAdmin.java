
package pe.sanpedro.systemcv.view;

import javax.swing.JButton;


/**
 *
 * @author Mysk
 */
public class FrmMainAdmin extends javax.swing.JFrame {


    public FrmMainAdmin() {
        initComponents();
        setLocationRelativeTo(null);
    }
    public JButton getBtnInicio(){
        return jbtn_inicio;
    }
    public JButton getBtnTrabajadores(){
        return jbtn_trabajadores;
    }

    public JButton getBtn_info() {
        return btn_info;
    }

    public JButton getBtn_reportes() {
        return btn_reportes;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtn_inicio = new javax.swing.JButton();
        jbtn_trabajadores = new javax.swing.JButton();
        btn_reportes = new javax.swing.JButton();
        btn_info = new javax.swing.JButton();
        Pnl_VP = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administración");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtn_inicio.setText("Inicio");
        jPanel1.add(jbtn_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 50));

        jbtn_trabajadores.setText("Mantenimiento Trabajadores");
        jPanel1.add(jbtn_trabajadores, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 210, 50));

        btn_reportes.setText("Reporte de Caja");
        jPanel1.add(btn_reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 170, 50));

        btn_info.setText("Información de la Empresa");
        jPanel1.add(btn_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 220, 50));

        Pnl_VP.setBackground(new java.awt.Color(0, 0, 0));
        Pnl_VP.setLayout(new java.awt.CardLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/fondoMA.png"))); // NOI18N
        Pnl_VP.add(jLabel2, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Pnl_VP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pnl_VP, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Pnl_VP;
    private javax.swing.JButton btn_info;
    private javax.swing.JButton btn_reportes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtn_inicio;
    private javax.swing.JButton jbtn_trabajadores;
    // End of variables declaration//GEN-END:variables
}
