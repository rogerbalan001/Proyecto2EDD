/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author noelb
 */
public class Documento {
    
    private String nombre;
    private int tamano; 
    private String tipo;
    private boolean esPrioritario;

    /**
     * Constructor para inicializar un nuevo Documento.
     * * @param nombre        El nombre descriptivo del documento.
     * @param tamano        El tamaño del documento (por ejemplo, cantidad de páginas).
     * @param tipo          El tipo o formato del documento (ej. PDF, DOCX, TXT).
     * @param esPrioritario Indica si el usuario solicita que el documento se imprima con prioridad.
     */
    public Documento(String nombre, int tamano, String tipo, boolean esPrioritario) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.tipo = tipo;
        this.esPrioritario = esPrioritario;
    }

    /**
     * Obtiene el nombre del documento.
     * @return El nombre del documento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para el documento.
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tamaño del documento.
     * @return El tamaño del documento.
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Establece el tamaño del documento.
     * @param tamano El nuevo tamaño.
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     * Obtiene el tipo o formato del documento.
     * @return El tipo de documento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de documento.
     * @param tipo El nuevo tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Verifica si el documento fue marcado como prioritario.
     * @return true si es prioritario, false en caso contrario.
     */
    public boolean isEsPrioritario() {
        return esPrioritario;
    }

    /**
     * Cambia el estado de prioridad del documento.
     * @param esPrioritario true para marcar como prioritario, false para uso normal.
     */
    public void setEsPrioritario(boolean esPrioritario) {
        this.esPrioritario = esPrioritario;
    }

    /**
     * Representación en texto del documento para facilitar la depuración y visualización.
     * @return Cadena con los datos del documento.
     */
    @Override
    public String toString() {
        return nombre + "." + tipo + " (" + tamano + " pags) - Prioritario: " + (esPrioritario ? "Sí" : "No");
    }
}