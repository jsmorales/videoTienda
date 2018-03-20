
package videoTienda.model;

/**
 *
 * @author johan
 */
public class Imagen {
    
    private int pkID;
    private int pkID_pelicula;
    private String ruta;

    public Imagen(int pkID) {
        this.pkID = pkID;
    }

    public int getPkID() {
        return pkID;
    }

    public void setPkID(int pkID) {
        this.pkID = pkID;
    }

    public int getPkID_pelicula() {
        return pkID_pelicula;
    }

    public void setPkID_pelicula(int pkID_pelicula) {
        this.pkID_pelicula = pkID_pelicula;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Imagen:{" + "pkID:" + pkID + ", pkID_pelicula:" + pkID_pelicula + ", ruta:" + ruta + '}';
    }
    
    
}
