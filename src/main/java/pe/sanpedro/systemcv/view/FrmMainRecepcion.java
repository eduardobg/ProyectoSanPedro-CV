package pe.sanpedro.systemcv.view;

import javax.swing.JButton;

/**
 *
 * @author Mysk
 */
public class FrmMainRecepcion extends javax.swing.JFrame {

    /**
     * Creates new form FrmMainCaja
     */
    public FrmMainRecepcion() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public JButton getBtnClientes() {
        return btnClientes;
    }
    public JButton getBtnInicio() {
        return btnInicio;
    }

    public JButton getBtnMascotas() {
        return btnMascotas;
    }    public JButton getBtnCitas() {
        return btnCitas;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnMascotas = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        Pnl_VP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        jPanel1.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 170, 70));

        btnMascotas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnMascotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pets35.png"))); // NOI18N
        btnMascotas.setText(" Mascotas");
        jPanel1.add(btnMascotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 160, 70));

        btnCitas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/pets35.png"))); // NOI18N
        btnCitas.setText("Consulta General");
        jPanel1.add(btnCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 200, 70));

        btnInicio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnInicio.setText("Inicio");
        jPanel1.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 70));

        Pnl_VP.setLayout(new java.awt.CardLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/fondoMR.jpg"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1243, 646));
        Pnl_VP.add(jLabel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pnl_VP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Pnl_VP;
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMascotas;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
