/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;
import modelos.Documento;
/**
 *
 * @author noelb
 */
public class NodoHash {
    
    private String idUsuario;
    private Documento documento;
    private int etiquetaTiempo;
    private NodoHash siguiente;

    /**
     * Constructor del Nodo Hash.
     * * @param idUsuario      El identificador del usuario propietario del documento.
     * @param documento      El documento que se encuentra en la cola de impresión.
     * @param etiquetaTiempo La etiqueta de tiempo (prioridad) asignada al documento.
     */
    public NodoHash(String idUsuario, Documento documento, int etiquetaTiempo) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.etiquetaTiempo = etiquetaTiempo;
        this.siguiente = null;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el ID del usuario.
     * @return El nombre o ID del usuario.
     */
    public String getIdUsuario() { return idUsuario; }

    /**
     * Obtiene el documento asociado.
     * @return El objeto Documento.
     */
    public Documento getDocumento() { return documento; }

    /**
     * Obtiene la etiqueta de tiempo actual del documento en la cola.
     * @return El valor entero de la etiqueta de tiempo.
     */
    public int getEtiquetaTiempo() { return etiquetaTiempo; }

    /**
     * Actualiza la etiqueta de tiempo (útil si la prioridad cambia o para eliminación).
     * @param etiquetaTiempo El nuevo valor de tiempo.
     */
    public void setEtiquetaTiempo(int etiquetaTiempo) { this.etiquetaTiempo = etiquetaTiempo; }

    /**
     * Obtiene el siguiente nodo en la lista enlazada (en caso de colisión).
     * @return El siguiente NodoHash, o null si es el último.
     */
    public NodoHash getSiguiente() { return siguiente; }

    /**
     * Establece el siguiente nodo para manejar colisiones por encadenamiento.
     * @param siguiente El nodo a enlazar.
     */
    public void setSiguiente(NodoHash siguiente) { this.siguiente = siguiente; }
}