
package videoTienda.model;

import java.util.List;

/**
 *
 * @author johan
 */
public class Pelicula {
    
    private int pkID;
    private String codigo;
    private String nombre;
    private String descripcion;
    private List<Imagen> imagenes;
    
    //no olvidar el contructor que tiene por defecto el id

    public Pelicula(int pkID) {
        this.pkID = pkID;
    }
    

    public int getPkID() {
        return pkID;
    }

    public void setPkID(int pkID) {
        this.pkID = pkID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "pkID=" + pkID + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagenes=" + imagenes + '}';
    }
        
        
}
