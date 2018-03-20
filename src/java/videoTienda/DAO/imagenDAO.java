/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoTienda.DAO;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import videoTienda.model.Imagen;

/**
 *
 * @author johan
 */
public class imagenDAO {
    
    //conexion
    connection cn;

    public imagenDAO(connection cn) {
        this.cn = cn;
    }
    
    public boolean inserta(Imagen imagen){
        String sql = "insert into Imagen values(?,?,?);";
        try {
            //se instancia el metodo para ejecutar la sentencia sql
            
            //se instancia un statatement y se declaran las variables para cada poscicion
            PreparedStatement pa = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            
            pa.setInt(1, imagen.getPkID());
            pa.setInt(2, imagen.getPkID_pelicula());
            pa.setString(3, imagen.getRuta());
            
            
            //ejecuta la sentencia sql
            pa.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(peliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    public List<Imagen> getByIdPelicula(int pkID_pelicula){
        
        try {
            //se crea la sentencia sql
            String sql = "SELECT * FROM Imagen where pkID_pelicula = ?";
            //se prepara la ejecucion
            PreparedStatement ps = (PreparedStatement) cn.getConnection().prepareStatement(sql);
            ps.setInt(1,pkID_pelicula);
            //se ejcuta la sentencia dentro de un resultset(objeto que almacena resultados de bases de datos)            
            ResultSet rs = ps.executeQuery();
            //en una lista se cargan los resultados
            List<Imagen> imagenes = new LinkedList<>();
            
            
            //se crea objeto vacio de tipo Pelicula del paquete model
            Imagen imagen;
            
            //se itera el resultset
            while(rs.next()){
                
                //se instancia el objeto Pelicula
                imagen = new Imagen(rs.getInt("pkID"));
                
                imagen.setPkID_pelicula(rs.getInt("pkID_pelicula"));
                imagen.setRuta(rs.getString("ruta"));
                                
                //se agrega a la lista el objeto obtenido
                imagenes.add(imagen);
            }
            
            return imagenes;
            
        } catch (Exception e) {
            
            System.out.println("Error DAO pkID_pelicula: "+e.getMessage());
            return null;
        }
    }
    
    public boolean eliminar(int pkID){
        String sql = "delete from Imagen where pkID = ?;";
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
    
}
