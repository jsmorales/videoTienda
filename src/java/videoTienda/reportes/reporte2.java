
package videoTienda.reportes;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
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
public class reporte2 {
    
    static connection cn = new connection();
    
    public static void genReporte (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // set header as pdf
        response.setContentType("application/pdf");
        
        // set input and output stream
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis;
        BufferedInputStream bufferedInputStream;
        
        try {
            // get report location
             //= getServletContext();
            String reportLocation = "/home/johan/NetBeansProjects/videoTienda/build/web/reportes/reporte_videoTienda.jasper";
            System.err.println(reportLocation);
            
            // get report
            fis = new FileInputStream(reportLocation);
            bufferedInputStream = new BufferedInputStream(fis);
            
            //llena el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(
				reportLocation, null,
				cn.getConnection());
            
            // export to pdf
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
 
            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);
 
            // close it
            fis.close();
            bufferedInputStream.close();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
        }
    }
    
}
