
package pe.sanpedro.systemcv.controllers;


import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoAdminImpl;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.model.Admin;
import pe.sanpedro.systemcv.model.Trabajadores;
import pe.sanpedro.systemcv.view.FrmLoginT;
import pe.sanpedro.systemcv.view.JdlgLoginA;

/**
 *
 * @author Mysk
 */
public class CtrlLogin {
    private GenericDao daoLogin;
    private final FrmLoginT frmlogin;
    private JdlgLoginA jdlgAdmin;
    
   public CtrlLogin(GenericDao daotrabajadores, FrmLoginT frmlogin) {
        this.daoLogin = daotrabajadores;
        this.frmlogin = frmlogin;
    }   
  
    public void initControllerT() {       
        frmlogin.getbtnIngresar().addActionListener(e -> loginTrab());
        frmlogin.getbtnAdmin().addActionListener(e -> initControllerA());
        frmlogin.getbtnCerrar().addActionListener(e -> cerrar());
        frmlogin.setVisible(true);
    }

    private void initControllerA() {        
        jdlgAdmin = new JdlgLoginA(new javax.swing.JFrame(), true);
        jdlgAdmin.getbtnIngresar().addActionListener(e -> loginAdmin());
        jdlgAdmin.setVisible(true);
    }

    private void loginTrab(){
        daoLogin= new DaoTrabajadoresImpl();
        Trabajadores employee = null;
        String dni= frmlogin.getDNI().getText();
        int area = frmlogin.getCbxArea().getSelectedIndex()+1;
        
        try {
           employee= (Trabajadores) daoLogin.login(dni,String.valueOf(area));
            if (employee != null) {
                System.out.println("logeado");
                JOptionPane.showMessageDialog(null, "Bienvenido " + employee.getNombre()+" "+employee.getApellidop(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                frmlogin.setVisible(false);                                
                switch(area){
                    case 1: CtrlMR ctrlMR = new CtrlMR(); //Para inicar el Main de Recepcion
                            ctrlMR.initController(dni);
                            break;
                    case 2: CtrlMC ctrlMC = new CtrlMC(employee.getId()); //Solo el controlador de farmacia debe tener un id en su constructor
                            ctrlMC.initController(dni);
                            break;
                    case 3: CtrlMF ctrlMF = new CtrlMF(); //Para inicar el Main de Farmacia
                            ctrlMF.initController(dni);
                            break;  
                    case 4: CtrlMAL ctrlMAL = new CtrlMAL();
                            ctrlMAL.initController(dni);
                            break;        
                }                               
                
            } else {
                JOptionPane.showMessageDialog(null, daoLogin.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private Admin loginAdmin() {
        daoLogin = new DaoAdminImpl();
        Admin admin = null;
        String user = jdlgAdmin.getTxtUsuario().getText();
        String pass = jdlgAdmin.getTxtClave().getText();

        try {
            admin = (Admin) daoLogin.login(user, pass);
            if (admin != null) {
                System.out.println("logeado");
                JOptionPane.showMessageDialog(null, "Bienvenido " + admin.getUser_name(), "ADMINISTRACIÓN", JOptionPane.INFORMATION_MESSAGE);
                frmlogin.setVisible(false);
                jdlgAdmin.setVisible(false);                
                CtrlMA ctrlMA = new CtrlMA();
                ctrlMA.initController();
            } else {
                JOptionPane.showMessageDialog(null, daoLogin.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "ERROR",JOptionPane.ERROR_MESSAGE);
        }

        return admin;
    }
    private void cerrar(){
        System.exit(0);
    }
}
