
package videoTienda.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.Utility;
import videoTienda.DAO.connection;
import videoTienda.DAO.imagenDAO;
import videoTienda.model.Imagen;
import videoTienda.utilidades.Redireccionar;

/**
 *
 * @author johan
 */
public class imagenController extends HttpServlet {
    
    //conexion
    connection cn = new connection();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action.equals("eliminarImagen")){
            this.eliminarImagen(request, response);
        }
    }
    
    protected void eliminarImagen(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int pkID_imagen = Integer.parseInt(request.getParameter("pkID"));
        
        cn.getConnection();
        
        imagenDAO imgDao = new imagenDAO(cn);
        
        boolean res = imgDao.eliminar(pkID_imagen);
        
        if(res){
            //Redireccionar.redireccionaRespuesta(request, response, "Imagen eliminada.", "pelicula?action=cargarPelicula&pkID_pelicula="+request.getParameter("pkID_pelicula"));
            Redireccionar.redireccionaRespuestaLink(request, response, "Imagen eliminada.", "pelicula?action=cargarPelicula&pkID_pelicula="+request.getParameter("pkID_pelicula"), "mensaje.jsp");
        }else{
            Redireccionar.redireccionaRespuesta(request, response, "No se eliminó imagen.", "mensaje.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String UPLOAD_DIR = "uploads";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int pkID_peliculaParam = Integer.parseInt(request.getParameter("pkID_pelicula"));
        String ruta = request.getParameter("ruta");
        
        Imagen imagen = new Imagen(0);
        imagen.setPkID_pelicula(pkID_peliculaParam);
        
        cn.getConnection();
        
        imagenDAO imgDao = new imagenDAO(cn);
        
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        
        //revisar dentro del request la variable archivo
        Collection <Part> partCol = request.getParts();
        int cont = 0;
                       
        for (Part part : partCol) {
            String type = part.getName();
            //Part archivo = request.getPart("archivo");
            if(type.equals("archivo")){

                String archivoParam = part.getSubmittedFileName();
                RequestDispatcher rd;
                //Archivo valido, si lo guardamos
                String msg="";
                
                if (archivoParam.endsWith("jpg") || archivoParam.endsWith("jpeg") || archivoParam.endsWith("png")) {


                    String archivoFisico = Utility.randomAlphaNumeric(10) + archivoParam.replace(" ", "_");
                    // Asignamos el nombre del archivo que guadaremos en la bd
                    //solicitud.setArchivo(archivoFisico);
                    imagen.setRuta(archivoFisico);

                    //inserta el objeto en la base de datos
                    boolean status = imgDao.inserta(imagen);


                    if(status){
                        // Escribimos el archivo al disco duro del servidor
                        // Aqui se guarda el archivo al directorio webapps/vacantesV2/uploads con el nombre formado anteriormente
                        part.write(uploadFilePath + File.separator + archivoFisico); 
                        System.err.println("Imagen "+archivoFisico+" subida correctamente.");
                        
                        //Redireccionar.redireccionaRespuesta(request, response, "Imagen subida correctamente.", "mensaje.jsp");
                    }else{
                        Redireccionar.redireccionaRespuesta(request, response, "No se subió la imagen.", "mensaje.jsp");
                    }

                    //cn.disconnect();

                } else { // No es un archivo valido...

                    msg = "Solo se permiten archivos de tipo imagen jpg y png.";
                    Redireccionar.redireccionaRespuesta(request, response, msg, "mensaje.jsp");
                }
                                
            }
            
            cont++;
            if(cont==partCol.size()){
                   Redireccionar.redireccionaRespuesta(request, response, "Imagen(es) subida(s) correctamente.", "mensaje.jsp"); 
            }
        }   
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
