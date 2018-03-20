package videoTienda.reportes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import videoTienda.DAO.connection;

/**
 *
 * @author johan
 */
public class reporte1 {
    
    static String rutaReporte = "/home/johan/NetBeansProjects/videoTienda/build/web/reportes/reporte_videoTienda.jasper";
    static connection cn = new connection();

    public static void verReporte() {
        
        try {

            JasperPrint jasperPrintWindow
                    = JasperFillManager.fillReport(
                            rutaReporte, null,
                            cn.getConnection());
            JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
            jasperViewer.setVisible(true);

        } catch (Exception e) {
            System.err.println("Error jasper :" + e.getMessage());
        }
    }
    
    public static void verReporteIn() {
        
        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(
				rutaReporte, null,
				cn.getConnection());
            
		JRPdfExporter exp = new JRPdfExporter();
                
		exp.setExporterInput(new SimpleExporterInput(jasperPrint));
                
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteVideoTienda.pdf"));
                
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
                
		exp.setConfiguration(conf);
                
		exp.exportReport();

        } catch (Exception e) {
            System.err.println("Error jasper :" + e.getMessage());
        }
    }
}
