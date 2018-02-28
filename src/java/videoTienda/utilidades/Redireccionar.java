/*
 * Redirecciona la respuesta de un servlet
 */
package videoTienda.utilidades;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author johan
 * @see Redirecciona la respuesta de un servlet.
 * @param request
 * @param response
 * @param msg
 * @param pagina
 */
public class Redireccionar {
    
    public static void redireccionaRespuesta(HttpServletRequest request, HttpServletResponse response, String msg, String pagina)
            throws ServletException, IOException{
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("mensaje", msg); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
    }
    
    public static void redireccionaRespuestaLink(HttpServletRequest request, HttpServletResponse response, String msg, String link, String pagina)
            throws ServletException, IOException{
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("mensaje", msg); //se añade un atributo message al request
        request.setAttribute("link", link); //se añade un atributo message al request
        
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
    }
    
    public static void redireccionaListaRes(HttpServletRequest request, HttpServletResponse response, String msg, List listRes, String pagina)
            throws ServletException, IOException{
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("mensaje", msg); //se añade un atributo message al request
        
        request.setAttribute("resultado", listRes);
        
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
    }
    
    public static void redireccionaObjetoRes(HttpServletRequest request, HttpServletResponse response, String msg, Object objetoRes, String pagina)
            throws ServletException, IOException{
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("mensaje", msg); //se añade un atributo message al request
        
        request.setAttribute("resultado", objetoRes);
        
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
    }
    
    public static void redireccionaObjetoForm(HttpServletRequest request, HttpServletResponse response, String msg, Object objetoRes,String accion, String pagina)
            throws ServletException, IOException{
        
        RequestDispatcher rd; //permite hacer un reenvio de la solicitud
        
        request.setAttribute("mensaje", msg); //se añade un atributo message al request
        
        request.setAttribute("accion", accion);
        
        request.setAttribute("resultado", objetoRes);
        
        rd = request.getRequestDispatcher(pagina); //al dispatcher se redirecciona al jsp
        
        rd.forward(request, response); //se ejecuta la redireccion
    }
}
