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
public class TablaDispersion {
    
    private NodoHash[] tabla;
    private int capacidad;

    /**
     * Constructor de la Tabla de Dispersión.
     * * @param capacidad La capacidad inicial del arreglo (se recomienda un número primo).
     */
    public TablaDispersion(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new NodoHash[capacidad];
    }

    /**
     * Función Hash que convierte el ID del usuario en un índice válido para el arreglo.
     * Suma los valores ASCII de los caracteres y aplica módulo.
     * * @param clave El ID del usuario.
     * @return El índice calculado.
     */
    private int funcionHash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash += clave.charAt(i);
        }
        return hash % capacidad;
    }

    /**
     * Inserta un nuevo registro en la tabla de dispersión.
     * * @param idUsuario      El identificador del usuario.
     * @param documento      El documento que entró a la cola.
     * @param etiquetaTiempo La etiqueta de tiempo con la que se encoló.
     */
    public void insertar(String idUsuario, Documento documento, int etiquetaTiempo) {
        int indice = funcionHash(idUsuario);
        NodoHash nuevoNodo = new NodoHash(idUsuario, documento, etiquetaTiempo);

        if (tabla[indice] == null) {
            // No hay colisión, insertamos directamente
            tabla[indice] = nuevoNodo;
        } else {
            // Hay colisión, recorremos la lista enlazada (encadenamiento) y lo agregamos al final
            NodoHash actual = tabla[indice];
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    /**
     * Busca la etiqueta de tiempo de un documento específico de un usuario.
     * Esta función es vital con complejidad O(1) para encontrar el tiempo y poder eliminarlo del Montículo.
     * * @param idUsuario   El ID del usuario propietario.
     * @param nombreDoc   El nombre del documento a buscar.
     * @return La etiqueta de tiempo del documento, o -1 si no se encuentra.
     */
    public int buscarEtiquetaTiempo(String idUsuario, String nombreDoc) {
        int indice = funcionHash(idUsuario);
        NodoHash actual = tabla[indice];

        while (actual != null) {
            if (actual.getIdUsuario().equals(idUsuario) && actual.getDocumento().getNombre().equals(nombreDoc)) {
                return actual.getEtiquetaTiempo();
            }
            actual = actual.getSiguiente();
        }
        return -1; // No encontrado
    }

    /**
     * Elimina el registro de un documento de la tabla de dispersión una vez que 
     * ha sido impreso o cancelado por el usuario.
     * * @param idUsuario El ID del usuario.
     * @param nombreDoc El nombre del documento a eliminar de los registros.
     * @return true si se eliminó exitosamente, false si no se encontró.
     */
    public boolean eliminar(String idUsuario, String nombreDoc) {
        int indice = funcionHash(idUsuario);
        NodoHash actual = tabla[indice];
        NodoHash anterior = null;

        while (actual != null) {
            if (actual.getIdUsuario().equals(idUsuario) && actual.getDocumento().getNombre().equals(nombreDoc)) {
                if (anterior == null) {
                    // Es el primer elemento de la lista en ese índice
                    tabla[indice] = actual.getSiguiente();
                } else {
                    // Está en el medio o al final de la lista
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }
}
