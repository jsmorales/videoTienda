
package videoTienda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import videoTienda.DAO.connection;
import videoTienda.DAO.peliculaDAO;
import videoTienda.model.Pelicula;
import videoTienda.reportes.reporte1;
import videoTienda.reportes.reporte2;
import videoTienda.utilidades.Redireccionar;

/**
 *
 * @author johan
 */
public class peliculaController extends HttpServlet {
    
    //variable global de conexion a la base de datos
    connection cn = new connection();
    peliculaDAO pelDao = new peliculaDAO(cn);
            
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
        
        // se recibe el parámetro action
        String actionParam = request.getParameter("action");
        
        //se decide la accion segun el parametro
        if("all".equals(actionParam)){
            
            this.verTodos(request, response);
        }else if("detallePelicula".equals(actionParam)){
            //metodo para ver el detalle de la pelicula por id
            this.detallePelicula(request, response,Integer.parseInt(request.getParameter("pkID_pelicula")));
        }else if("crearPelicula".equals(actionParam)){
            Redireccionar.redireccionaRespuesta(request, response, "Crear Película", "frmPelicula.jsp");
        }else if("cargarPelicula".equals(actionParam)){
            this.cargaFormPelicula(request, response, Integer.parseInt(request.getParameter("pkID_pelicula")));
        }else if("eliminarPelicula".equals(actionParam)){
            this.eliminaPelicula(request, response, Integer.parseInt(request.getParameter("pkID")));
        }else if("reportePelicula".equals(actionParam)){
            //this.eliminaPelicula(request, response, Integer.parseInt(request.getParameter("pkID")));
            this.reportePelicula(request, response);
        }
    }
    
    protected void reportePelicula(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
        reporte2.genReporte(request, response);
    }
    
    protected void verTodos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        cn = new connection();
        
        List<Pelicula> peliculas = pelDao.getAll();
        
        cn.disconnect();
        
        System.err.println(peliculas);
        //RequestDispatcher rd; //permite hacer un reenvio de la solicitud        
        //request.setAttribute("peliculas", peliculas); //se añade un atributo message al request        
        //rd = request.getRequestDispatcher("/vacantes.jsp"); //al dispatcher se redirecciona al jsp        
        //rd.forward(request, response); //se ejecuta la redireccion
        
        Redireccionar.redireccionaListaRes(request, response, "Listado de Películas", peliculas, "/peliculas.jsp");
    }
    
    protected void detallePelicula(HttpServletRequest request, HttpServletResponse response, int pkID_pelicula)
            throws ServletException, IOException {
    
        cn = new connection();
        
        Pelicula pelicula = new Pelicula(0);
        
        pelicula = pelDao.getById(pkID_pelicula);
        
        Redireccionar.redireccionaObjetoRes(request, response, "Detalle Película", pelicula, "/pelicula.jsp");
    }
    
    protected void cargaFormPelicula (HttpServletRequest request, HttpServletResponse response, int pkID_pelicula)
            throws ServletException, IOException {
        
        cn = new connection();
        
        Pelicula pelicula = new Pelicula(0);
        
        pelicula = pelDao.getById(pkID_pelicula);
        
        Redireccionar.redireccionaObjetoRes(request, response, "Editar Película", pelicula, "frmPelicula.jsp");
    }
    
    protected void eliminaPelicula (HttpServletRequest request, HttpServletResponse response, int pkID_pelicula)
            throws ServletException, IOException {
        
        cn = new connection();      
                
        boolean res = pelDao.eliminar(pkID_pelicula);
        if(res){
            Redireccionar.redireccionaRespuestaLink(request, response, "Pelicula Eliminada.", "pelicula?action=all", "mensaje.jsp");
        }else{
            Redireccionar.redireccionaRespuestaLink(request, response, "La Pelicula no fue Eliminada.", "pelicula?action=all", "mensaje.jsp");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                                 
        //se reciben los parametros enviados
        byte[] utf8BytesCodigo = request.getParameter("codigo").getBytes(StandardCharsets.ISO_8859_1);
        String codigo = new String(utf8BytesCodigo, StandardCharsets.UTF_8);
        
        byte[] utf8BytesNombre = request.getParameter("nombre").getBytes(StandardCharsets.ISO_8859_1);
        String nombre = new String(utf8BytesNombre, StandardCharsets.UTF_8);
        
        byte[] utf8BytesDescripcion = request.getParameter("descripcion").getBytes(StandardCharsets.ISO_8859_1);
        String descripcion = new String(utf8BytesDescripcion, StandardCharsets.UTF_8);
        
        //se crea un objeto de tipo pelicula y se le asignan los valores de formulario
        Pelicula pelicula = new Pelicula(0);
        
        pelicula.setCodigo(codigo);
        pelicula.setNombre(nombre);
        pelicula.setDescripcion(descripcion);
        
        
        cn = new connection();
        
        boolean res;
        String msg = "";
        
        if(request.getParameter("pkID").equals("")){
            
            res = pelDao.inserta(pelicula);
            
            if(res){
                //creo la pelicula en la bd
                msg = "Se creó la película correctamente.";
            }else{
                //algo salió mal
                msg = "No se creó la película.";
            }
            
        }else{
            
            int pkID = Integer.parseInt(request.getParameter("pkID"));
            pelicula.setPkID(pkID);
            
            res = pelDao.editar(pelicula);
            
            if(res){
                //creo la pelicula en la bd
                msg = "Se editó la película correctamente.";
            }else{
                //algo salió mal
                msg = "No se editó la película.";
            }
        }
        
        //del DAO global se saca el resultado
                                        
        Redireccionar.redireccionaRespuesta(request, response, msg, "mensaje.jsp");
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
