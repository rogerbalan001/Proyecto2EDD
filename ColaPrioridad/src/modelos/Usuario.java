/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author noelb
 */
public class Usuario {

    private String idUsuario;
    private String tipo;
    
    // Arreglo nativo para almacenar los documentos sin usar librerías como ArrayList
    private Documento[] documentosCreados;
    private int cantidadDocumentos;

    /**
     * Constructor para inicializar un nuevo Usuario.
     * * @param idUsuario El identificador único o nombre del usuario.
     * @param tipo      El tipo de usuario, el cual definirá su nivel de prioridad.
     */
    public Usuario(String idUsuario, String tipo) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        // Inicializamos el arreglo con una capacidad base, por ejemplo, 10
        this.documentosCreados = new Documento[10];
        this.cantidadDocumentos = 0;
    }

    /**
     * Obtiene el identificador del usuario.
     * * @return El nombre o ID del usuario.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Obtiene el tipo de usuario.
     * * @return El tipo de usuario (ej. prioridad_alta, prioridad_media).
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el arreglo de los documentos creados por el usuario.
     * * @return Un arreglo con los documentos.
     */
    public Documento[] getDocumentosCreados() {
        return documentosCreados;
    }

    /**
     * Obtiene la cantidad actual de documentos que tiene el usuario.
     * * @return El número de documentos creados.
     */
    public int getCantidadDocumentos() {
        return cantidadDocumentos;
    }

    /**
     * Agrega un nuevo documento a la lista de documentos creados por el usuario.
     * Si el arreglo está lleno, su capacidad se duplica automáticamente.
     * * @param doc El objeto Documento a agregar.
     */
    public void agregarDocumento(Documento doc) {
        // Si el arreglo está lleno, lo expandimos manualmente
        if (cantidadDocumentos == documentosCreados.length) {
            expandirArregloDocumentos();
        }
        documentosCreados[cantidadDocumentos] = doc;
        cantidadDocumentos++;
    }

    /**
     * Elimina un documento de la lista del usuario dado su nombre.
     * Un usuario podrá eliminar un documento que aún no ha sido enviado a la cola de impresión.
     * * @param nombreDoc El nombre del documento a eliminar.
     * @return true si el documento fue encontrado y eliminado, false en caso contrario.
     */
    public boolean eliminarDocumento(String nombreDoc) {
        for (int i = 0; i < cantidadDocumentos; i++) {
            if (documentosCreados[i].getNombre().equals(nombreDoc)) {
                // Desplazamos los elementos hacia la izquierda para llenar el hueco
                for (int j = i; j < cantidadDocumentos - 1; j++) {
                    documentosCreados[j] = documentosCreados[j + 1];
                }
                // Limpiamos la última referencia y reducimos el contador
                documentosCreados[cantidadDocumentos - 1] = null;
                cantidadDocumentos--;
                return true;
            }
        }
        return false; // No se encontró el documento
    }

    /**
     * Método privado para duplicar la capacidad del arreglo de documentos
     * cuando este se llena, evitando el uso de librerías externas.
     */
    private void expandirArregloDocumentos() {
        Documento[] nuevoArreglo = new Documento[documentosCreados.length * 2];
        for (int i = 0; i < documentosCreados.length; i++) {
            nuevoArreglo[i] = documentosCreados[i];
        }
        documentosCreados = nuevoArreglo;
    }

    /**
     * Representación en texto del usuario para facilitar la depuración.
     * * @return Cadena con los datos del usuario.
     */
    @Override
    public String toString() {
        return idUsuario + " (" + tipo + ") - Documentos: " + cantidadDocumentos;
    }
}
