
package videoTienda.DAO;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import videoTienda.model.Imagen;
import videoTienda.model.Pelicula;

/**
 *
 * @author johan
 */
public class peliculaDAO {
    
    //DAO con metodos para la manipulacion del objeto pelicula
    
    //conexion
    connection cn;
    
    //en el contructor crea la conexion

    public peliculaDAO(connection cn) {
        this.cn = cn;
    }
    
    //metodo para crear la pelicula en la BD desde un objeto pelicula
    public boolean inserta(Pelicula pelicula){
        String sql = "insert into Pelicula values(?,?,?,?);";
        try {
            //se instancia el metodo para ejecutar la sentencia sql
                        
            //se instancia un statatement y se declaran las variables para cada poscicion
            PreparedStatement pa = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            
            pa.setInt(1, pelicula.getPkID());
            pa.setString(2, pelicula.getCodigo());
            pa.setString(3, pelicula.getNombre());
            pa.setString(4, pelicula.getDescripcion());
            
            //ejecuta la sentencia sql
            pa.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(peliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    public boolean editar(Pelicula pelicula){
        String sql = "update Pelicula set codigo=?, nombre=?, descripcion=? where pkID=?;";
        try {
            //se instancia el metodo para ejecutar la sentencia sql
                        
            //se instancia un statatement y se declaran las variables para cada poscicion
            PreparedStatement pa = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            
            
            pa.setString(1, pelicula.getCodigo());
            pa.setString(2, pelicula.getNombre());
            pa.setString(3, pelicula.getDescripcion());
            pa.setInt(4, pelicula.getPkID());
            //ejecuta la sentencia sql
            pa.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(peliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    public boolean eliminar(int pkID){
        String sql = "delete from Pelicula where pkID = ?;";
        try {
            //se instancia el metodo para ejecutar la sentencia sql
            
            //se instancia un statatement y se declaran las variables para cada poscicion
            PreparedStatement pa = (PreparedStatement) cn.getConnection().prepareStatement(sql);            
            pa.setInt(1, pkID);
            
            //ejecuta la sentencia sql
            pa.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(peliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    public List<Pelicula> getAll(){
        
        try {
            //se crea la sentencia sql
            String sql = "select * from Pelicula order by pkID desc";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<Pelicula> peliculas = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo Pelicula del paquete model
            Pelicula pelicula;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia el objeto Pelicula
                pelicula = new Pelicula(rs.getInt("pkID"));
                
                pelicula.setCodigo(rs.getString("codigo"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                
                List<Imagen> imagenes = new LinkedList<>();                
                imagenDAO imgDao = new imagenDAO(cn);                
                imagenes = imgDao.getByIdPelicula(rs.getInt("pkID"));
                
                pelicula.setImagenes(imagenes);
                
                //se agrega a la lista el objeto obtenido
                peliculas.add(pelicula);
            }
            
            return peliculas;
            
        } catch (Exception e) {
            
            System.out.println("Error DAO getAll: "+e.getMessage());
            return null;
        }
    }
    
    public List<Pelicula> getUltimas(){
        
        try {
            //se crea la sentencia sql
            String sql = "select * from Pelicula ORDER by pkID desc limit 3";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<Pelicula> peliculas = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo Pelicula del paquete model
            Pelicula pelicula;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia el objeto Pelicula
                pelicula = new Pelicula(rs.getInt("pkID"));
                
                pelicula.setCodigo(rs.getString("codigo"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                
                List<Imagen> imagenes = new LinkedList<>();                
                imagenDAO imgDao = new imagenDAO(cn);                
                imagenes = imgDao.getByIdPelicula(rs.getInt("pkID"));
                
                pelicula.setImagenes(imagenes);
                
                //se agrega a la lista el objeto obtenido
                peliculas.add(pelicula);
            }
            
            return peliculas;
            
        } catch (Exception e) {
            
            System.out.println("Error DAO getUltimas: "+e.getMessage());
            return null;
        }
    }
    
    public Pelicula getById(int pkID_pelicula){
        
        try {
            //se crea la sentencia sql
            String sql = "select * from Pelicula where pkID = ?";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            
            ps.setInt(1, pkID_pelicula);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            
            Pelicula pelicula = new Pelicula(0);
                                               
            //se itera el resultset
            while(rs.next()){
                
                //se instancia el objeto Pelicula
                pelicula.setPkID(rs.getInt("pkID"));                
                pelicula.setCodigo(rs.getString("codigo"));
                pelicula.setNombre(rs.getString("nombre"));
                pelicula.setDescripcion(rs.getString("descripcion"));
                
                List<Imagen> imagenes = new LinkedList<>();                
                imagenDAO imgDao = new imagenDAO(cn);                
                imagenes = imgDao.getByIdPelicula(rs.getInt("pkID"));
                
                pelicula.setImagenes(imagenes);
                
                
            }
            
            return pelicula;
            
        } catch (Exception e) {
            
            System.out.println("Error DAO getById: "+e.getMessage());
            return null;
        }
    }
    
}
