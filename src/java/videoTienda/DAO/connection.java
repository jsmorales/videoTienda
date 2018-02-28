//Conexion mysql

package videoTienda.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author johan
 */
public class connection {

    static String bd = "videoTienda";
    static String login = "root";
    static String password = "root";
    static String url = "jdbc:mysql://localhost/" + bd;

    // Esta variable va a guardar la conexion
    Connection conn = null;

    public connection() {

        try {
            //obtenemos el driver para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos una conexion con los parametros especificados anteriormente 
            conn = DriverManager.getConnection(url, login, password);
            // Si conn no es nulo, significa que pidimos conectarnos
            if (conn != null) {
                System.out.println("Connecting database [" + conn + "] OK");
            }
        } catch (SQLException e) // Excepcion ocurrida por la conexion 
        {
            System.out.println("Excepcion conexion: " + e.getMessage());
        } catch (ClassNotFoundException e) // Excepcion ocurrida por no encontrar el driver
        {
            System.out.println("Excepcion driver: " + e.getMessage());
        }

    }

    public Connection getConnection() {
        return conn;
    }

    // Quitamos de memoria la conexion
    public void disconnect() {
        System.out.println("Closing database: [" + conn + "] OK");
        if (conn != null) {
            try {
                // System.out.println("Desconectado de " + bd + " OK");
                conn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
