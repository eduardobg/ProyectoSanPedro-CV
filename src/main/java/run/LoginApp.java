
package run;

import java.io.InputStream;
import pe.sanpedro.systemcv.controllers.CtrlEstetica_MC;
import pe.sanpedro.systemcv.controllers.CtrlLogin;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoTrabajadoresImpl;
import pe.sanpedro.systemcv.view.FrmLoginT;



/**
 *
 * @author Mysk
 */
public class LoginApp {
    public static void main(String[] args) {
       GenericDao daotrab = new DaoTrabajadoresImpl();
       FrmLoginT  frmlogin = new FrmLoginT();
       CtrlLogin ctrl = new CtrlLogin( daotrab, frmlogin);
       ctrl.initControllerT();     
       
       
       
    }
    
}
