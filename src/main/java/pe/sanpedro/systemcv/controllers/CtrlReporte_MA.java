package pe.sanpedro.systemcv.controllers;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.sanpedro.systemcv.dao.GenericDao;
import pe.sanpedro.systemcv.dao.impl.DaoReporteImpl;
import pe.sanpedro.systemcv.dto.Reporte;
import pe.sanpedro.systemcv.view.FrmMainAdmin;
import pe.sanpedro.systemcv.view.PnlReportes_MA;

/**
 *
 * @author Mysk
 */
public class CtrlReporte_MA {

    private GenericDao daoReporte;
    private PnlReportes_MA pnlReporte;
    int n = 0;

    public CtrlReporte_MA() {
    }

    public void initController() {
        pnlReporte = new PnlReportes_MA();
        mostrarReportes();
        pnlReporte.getBtn_buscar().addActionListener(e -> buscarReporte());
        pnlReporte.getBtn_limpiar().addActionListener(e -> limpiar());
        pnlReporte.getBtn_generarExcel().addActionListener(e -> crearExcel());
        CardLayout vista = (CardLayout) FrmMainAdmin.Pnl_VP.getLayout();
        FrmMainAdmin.Pnl_VP.add(pnlReporte, "Estetica");
        vista.show(FrmMainAdmin.Pnl_VP, "Estetica");
        SwingUtilities.updateComponentTreeUI(CtrlMA.frmMA);
        CtrlMA.frmMA.repaint();

    }

    private void mostrarReportes() {
        daoReporte = new DaoReporteImpl();
        List<Reporte> list = daoReporte.sel();
        pnlReporte.lista = null;
        pnlReporte.lista = list;
        refrescarTabla(list);

    }

    private void refrescarTabla(List<Reporte> list) {
        ((DefaultTableModel) pnlReporte.getJtbl().getModel()).setNumRows(0);
        DefaultTableModel modelo = (DefaultTableModel) pnlReporte.getJtbl().getModel();
        if (list != null) {
            list.forEach((t) -> {
                Object[] fila = new Object[9];
                fila[0] = t.getId_boleta();
                fila[1] = t.getFecha_();
                fila[2] = t.getDniTrab();
                fila[3] = t.getNombreTrab();
                fila[4] = t.getApellidopTrab();
                fila[5] = t.getApellidomTrab();
                fila[6] = t.getDniCli();
                fila[7] = t.getNombreCli();
                fila[8] = t.getImporteTotal();
                modelo.addRow(fila);
            });
            pnlReporte.getJtbl().setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, daoReporte.getMessage(), "ADMINISTRACIÓN", JOptionPane.WARNING_MESSAGE);
        }
        pnlReporte.lista = null;
        pnlReporte.lista = list;

    }

    private void buscarReporte() {
        int area = pnlReporte.getCbx().getSelectedIndex() + 1;
        LocalDate d1 = pnlReporte.getDate1().getDate();
        LocalDate d2 = pnlReporte.getDate2().getDate();
        daoReporte = new DaoReporteImpl();
        List<Reporte> list = daoReporte.searchBetween(area, d1, d2);
        refrescarTabla(list);
    }

    private void limpiar() {
        ((DefaultTableModel) pnlReporte.getJtbl().getModel()).setNumRows(0);
    }

    private void crearExcel() {

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sh = wb.createSheet("Reportes");
        sh.setColumnWidth(0, 500);
        sh.setColumnWidth(1, 1000);
        sh.setColumnWidth(2, 3000);
        sh.setColumnWidth(3, 3500);
        sh.setColumnWidth(4, 3000);
        sh.setColumnWidth(5, 3000);
        sh.setColumnWidth(6, 3000);
        sh.setColumnWidth(7, 3500);
        sh.setColumnWidth(8, 8000);
        sh.setColumnWidth(9, 4000);

        if (pnlReporte.lista != null) {

            XSSFFont defaultFont = wb.createFont();
            defaultFont.setFontHeightInPoints((short) 10);
            defaultFont.setFontName("Arial");
            defaultFont.setColor(IndexedColors.BLACK.getIndex());
            defaultFont.setBold(false);
            defaultFont.setItalic(false);
            XSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 10);
            font.setFontName("Arial");
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            font.setItalic(false);
            CellStyle style = wb.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFont(font);

            XSSFRow fila = sh.createRow(0);
            fila.createCell(1).setCellStyle(style);
            fila.getCell(1).setCellValue("ID");
            fila.createCell(2).setCellStyle(style);
            fila.getCell(2).setCellValue("F.Emision");
            fila.createCell(3).setCellStyle(style);
            fila.getCell(3).setCellValue("DNI Trabajador");
            fila.createCell(4).setCellStyle(style);
            fila.getCell(4).setCellValue("Trabajador");
            fila.createCell(5).setCellStyle(style);
            fila.getCell(5).setCellValue("ApellidoP");
            fila.createCell(6).setCellStyle(style);
            fila.getCell(6).setCellValue("ApellidoM");
            fila.createCell(7).setCellStyle(style);
            fila.getCell(7).setCellValue("DNI Cliente");
            fila.createCell(8).setCellStyle(style);
            fila.getCell(8).setCellValue("Cliente");
            fila.createCell(9).setCellStyle(style);
            fila.getCell(9).setCellValue("Importe total");
            for (int x = 0; x < pnlReporte.lista.size(); x++) {
                XSSFRow fil = sh.createRow(x + 1);
                //Cell c= fil.createCell(0);
                //c.setCellValue(pnlReporte.lista.get(x).getApellidomTrab());
                //c=fil.createCell(1);
                fil.createCell(1).setCellValue(pnlReporte.lista.get(x).getId_boleta());
                fil.createCell(2).setCellValue(pnlReporte.lista.get(x).getFecha_().toString());
                fil.createCell(3).setCellValue(pnlReporte.lista.get(x).getDniTrab());
                fil.createCell(4).setCellValue(pnlReporte.lista.get(x).getNombreTrab());
                fil.createCell(5).setCellValue(pnlReporte.lista.get(x).getApellidopTrab());
                fil.createCell(6).setCellValue(pnlReporte.lista.get(x).getApellidomTrab());
                fil.createCell(7).setCellValue(pnlReporte.lista.get(x).getDniCli());
                fil.createCell(8).setCellValue(pnlReporte.lista.get(x).getNombreCli());
                fil.createCell(9).setCellValue(pnlReporte.lista.get(x).getImporteTotal());
            }
            try {
                FileOutputStream fo = new FileOutputStream(new File("C:\\tmp\\ExcelSanPedro.xlsx"));
                wb.write(fo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay valores");
        }

        JOptionPane.showMessageDialog(null, "Excel creado");
    }

}
