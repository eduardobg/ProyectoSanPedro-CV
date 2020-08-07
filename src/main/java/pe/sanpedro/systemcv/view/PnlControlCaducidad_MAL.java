
package pe.sanpedro.systemcv.view;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Mysk
 */
public class PnlControlCaducidad_MAL extends javax.swing.JPanel {

    /**
     * Creates new form PnlControlCaducidad_MAL
     */
    public PnlControlCaducidad_MAL() {
        initComponents();
        btn_generarFichero.setEnabled(false);
        date.setEnabled(true);
    }

    public JButton getBtn_filtrar() {
        return btn_filtrar;
    }

    public JButton getBtn_generarFichero() {
        return btn_generarFichero;
    }

    public JButton getBtn_mostrarTodo() {
        return btn_mostrarTodo;
    }

    public DatePicker getDate() {
        return date;
    }

    public JTable getTable() {
        return table;
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btn_generarFichero = new javax.swing.JButton();
        date = new com.github.lgooddatepicker.components.DatePicker();
        btn_filtrar = new javax.swing.JButton();
        btn_mostrarTodo = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 102, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrado de Productos"));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "FECHA_ELAB", "FECHA_VENC", "PRECIO", "STOCK", "PRESENT", "LABORATORIO"
            }
        ));
        jScrollPane1.setViewportView(table);

        btn_generarFichero.setBackground(new java.awt.Color(0, 153, 0));
        btn_generarFichero.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btn_generarFichero.setForeground(new java.awt.Color(255, 255, 255));
        btn_generarFichero.setText("Generar Reporte en Fichero");

        btn_filtrar.setText("Filtrar");

        btn_mostrarTodo.setText("Mostrar Todo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btn_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_mostrarTodo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(495, 495, 495)
                        .addComponent(btn_generarFichero))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_filtrar)
                    .addComponent(btn_mostrarTodo))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_generarFichero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_filtrar;
    private javax.swing.JButton btn_generarFichero;
    private javax.swing.JButton btn_mostrarTodo;
    private com.github.lgooddatepicker.components.DatePicker date;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
