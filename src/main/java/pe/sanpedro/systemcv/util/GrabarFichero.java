/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.sanpedro.systemcv.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import pe.sanpedro.systemcv.model.Medicamentos;

/**
 *
 * @author Mysk
 */
public class GrabarFichero {
    
    public static Boolean grabar( List<Medicamentos> lista, LocalDate date){
        boolean ok=false;    
        JFileChooser guardar = new JFileChooser();      
        guardar.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        guardar.showSaveDialog(null);
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File archivo = guardar.getSelectedFile();
        try (FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);){            
            bw.newLine();
            bw.write("--------- Fecha límite de vencimiento: " + date.toString()+ " ---------");
            bw.newLine();
            bw.newLine();
            lista.forEach((t) -> {
                try {
                    bw.write(".) "+t.getID_Med()+"-"+t.getNombre()+"-"+t.getPresent()+"-"+t.getF_venci().toString()+"\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            });
            ok=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return ok;
    }

}
