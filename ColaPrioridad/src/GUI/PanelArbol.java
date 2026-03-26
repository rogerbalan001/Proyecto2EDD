/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import estructuras.MonticuloBinario;
import modelos.RegistroImpresion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author noelb
 */
public class PanelArbol extends JPanel {
    
    private MonticuloBinario monticulo;

    /**
     * Constructor del panel gráfico.
     * @param monticulo La referencia a la cola de impresión que se va a dibujar.
     */
    public PanelArbol(MonticuloBinario monticulo) {
        this.monticulo = monticulo;
        this.setBackground(Color.WHITE); // Fondo blanco para resaltar el árbol
    }

    /**
     * Actualiza la referencia del montículo y repinta el panel.
     * @param monticulo El nuevo estado del montículo.
     */
    public void setMonticulo(MonticuloBinario monticulo) {
        this.monticulo = monticulo;
        repaint(); // Fuerza a que se vuelva a dibujar
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (monticulo != null && !monticulo.estaVacio()) {
            // Inicia el dibujo desde la raíz (índice 0) en el centro superior del panel
            int xInicial = getWidth() / 2;
            int yInicial = 40;
            int separacionXInicial = getWidth() / 4; // Separación horizontal entre nodos
            int separacionY = 60; // Separación vertical por nivel
            
            dibujarNodo(g, 0, xInicial, yInicial, separacionXInicial, separacionY);
        } else {
            g.setColor(Color.GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("La cola de impresión está vacía", getWidth()/2 - 100, getHeight()/2);
        }
    }

    /**
     * Método recursivo para dibujar cada nodo y sus líneas conectoras.
     */
    private void dibujarNodo(Graphics g, int indice, int x, int y, int sepX, int sepY) {
        RegistroImpresion[] arreglo = monticulo.getArreglo();
        int n = monticulo.getTamanoActual();

        if (indice >= n || arreglo[indice] == null) {
            return; // Caso base: si el nodo no existe, salimos
        }

        int hijoIzq = 2 * indice + 1;
        int hijoDer = 2 * indice + 2;

        // 1. Dibujar las líneas hacia los hijos (se dibujan primero para que queden detrás del círculo)
        g.setColor(Color.BLACK);
        if (hijoIzq < n && arreglo[hijoIzq] != null) {
            g.drawLine(x, y, x - sepX, y + sepY);
            dibujarNodo(g, hijoIzq, x - sepX, y + sepY, sepX / 2, sepY); // Llamada recursiva hijo izquierdo
        }
        if (hijoDer < n && arreglo[hijoDer] != null) {
            g.drawLine(x, y, x + sepX, y + sepY);
            dibujarNodo(g, hijoDer, x + sepX, y + sepY, sepX / 2, sepY); // Llamada recursiva hijo derecho
        }

        // 2. Dibujar el nodo actual (Círculo)
        int radio = 18;
        g.setColor(new Color(173, 216, 230)); // Color azul claro (Light Blue)
        g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
        g.setColor(Color.BLACK);
        g.drawOval(x - radio, y - radio, radio * 2, radio * 2); // Borde del círculo

        // 3. Escribir la etiqueta de tiempo (Prioridad) dentro del círculo
        String tiempo = String.valueOf(arreglo[indice].getEtiquetaTiempo());
        g.setFont(new Font("Arial", Font.BOLD, 12));
        // Centrar el texto un poco a ojo
        g.drawString(tiempo, x - 7, y + 5);

        // 4. Escribir el nombre del documento debajo del círculo
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.setColor(Color.DARK_GRAY);
        String nombreDoc = arreglo[indice].getDocumento().getNombre();
        g.drawString(nombreDoc, x - 15, y + radio + 12);
    }
}
