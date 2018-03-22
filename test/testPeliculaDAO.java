/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import videoTienda.DAO.connection;
import videoTienda.DAO.peliculaDAO;
import videoTienda.model.Pelicula;

/**
 *
 * @author johan
 */
public class testPeliculaDAO {
    
    peliculaDAO pelDao;
    connection cn;
    
    public testPeliculaDAO() {
    }
    
    @BeforeClass
    public static void setUpClass() {                
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("Conectando a la base de datos...");
        cn = new connection();
        pelDao = new peliculaDAO(cn);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testInserta(){
        
        Pelicula pelicula = new Pelicula(0);
        
        pelicula.setNombre("Testing1");
        pelicula.setCodigo("T-1200");
        pelicula.setDescripcion("Una descripción para el test!");
        
        
        boolean res = pelDao.inserta(pelicula);
        
        assertEquals(true, res);
    }
    
    @Test
    public void testInsertaError(){
        
        Pelicula pelicula = new Pelicula(0);
        
        //pelicula.setNombre("Testing1");
        pelicula.setCodigo("T-1200");
        pelicula.setDescripcion("Una descripción para el test!");
        
        
        boolean res = pelDao.inserta(pelicula);
        
        //retorna false porque no lleva nombre este objeto pelicula y 
        //genera una exepcion de tipo sql
        
        assertEquals(false, res);
    }
}
