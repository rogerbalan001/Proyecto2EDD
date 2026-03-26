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
    private boolean esPrioritario;

    // Constructor actualizado (¡Solo pide 3 cosas!)
    public Documento(String nombre, int tamano, boolean esPrioritario) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.esPrioritario = esPrioritario;
    }

    // --- Getters y Setters ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public boolean isEsPrioritario() {
        return esPrioritario;
    }

    public void setEsPrioritario(boolean esPrioritario) {
        this.esPrioritario = esPrioritario;
    }

    // Este método es un "truco" para que cuando lo imprimas en tu lista 
    // de la ventana principal, se vea bonito automáticamente.
    @Override
    public String toString() {
        String texto = nombre + " (" + tamano + " págs)";
        if (esPrioritario) {
            texto += " [PRIORITARIO]";
        }
        return texto;
    }
}