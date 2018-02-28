/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoTienda.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import videoTienda.DAO.connection;
import videoTienda.DAO.peliculaDAO;
import videoTienda.model.Pelicula;
import videoTienda.utilidades.Redireccionar;

/**
 *
 * @author johan
 */
public class homeController extends HttpServlet {
    
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
        
        this.ver3Ultimas(request, response);
    }
    
    protected void ver3Ultimas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        cn = new connection();
        
        List<Pelicula> peliculas = pelDao.getUltimas();
        
        cn.disconnect();
        
        System.err.println(peliculas);
        //RequestDispatcher rd; //permite hacer un reenvio de la solicitud        
        //request.setAttribute("peliculas", peliculas); //se añade un atributo message al request        
        //rd = request.getRequestDispatcher("/vacantes.jsp"); //al dispatcher se redirecciona al jsp        
        //rd.forward(request, response); //se ejecuta la redireccion
        
        Redireccionar.redireccionaListaRes(request, response, "Listado de Películas", peliculas, "/index.jsp");
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
