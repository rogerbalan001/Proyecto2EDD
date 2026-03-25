/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;
import modelos.RegistroImpresion;
/**
 *
 * @author noelb
 */
public class MonticuloBinario {
    
    // Arreglo nativo que representará el árbol binario
    private RegistroImpresion[] arreglo;
    private int tamanoActual;
    private int capacidad;

    /**
     * Constructor del Montículo Binario.
     * @param capacidadInicial La cantidad de elementos máxima inicial que puede almacenar.
     */
    public MonticuloBinario(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tamanoActual = 0;
        this.arreglo = new RegistroImpresion[this.capacidad];
    }

    /**
     * Obtiene la posición del padre de un nodo.
     * @param pos La posición del nodo actual.
     * @return La posición de su nodo padre.
     */
    private int padre(int pos) {
        return (pos - 1) / 2;
    }

    /**
     * Obtiene la posición del hijo izquierdo de un nodo.
     * @param pos La posición del nodo actual.
     * @return La posición de su hijo izquierdo.
     */
    private int hijoIzquierdo(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Obtiene la posición del hijo derecho de un nodo.
     * @param pos La posición del nodo actual.
     * @return La posición de su hijo derecho.
     */
    private int hijoDerecho(int pos) {
        return (2 * pos) + 2;
    }

    /**
     * Intercambia dos registros de posición dentro del arreglo.
     * @param pos1 Posición del primer elemento.
     * @param pos2 Posición del segundo elemento.
     */
    private void intercambiar(int pos1, int pos2) {
        RegistroImpresion temporal = arreglo[pos1];
        arreglo[pos1] = arreglo[pos2];
        arreglo[pos2] = temporal;
    }

    /**
     * Primitiva insertar: Agrega un nuevo registro en la cola de prioridad.
     * Lo coloca al final y luego lo hace "flotar" hasta su posición correcta.
     * * @param registro El registro de impresión a encolar.
     */
    public void insertar(RegistroImpresion registro) {
        if (tamanoActual == capacidad) {
            expandirCapacidad();
        }
        
        // Insertar al final del árbol (última posición del arreglo)
        arreglo[tamanoActual] = registro;
        
        // Lo hacemos flotar a su posición correcta
        flotar(tamanoActual);
        
        tamanoActual++;
    }

    /**
     * Primitiva eliminar_min: Extrae y elimina el elemento con mayor prioridad 
     * (el de menor tiempo). Saca la raíz, coloca el último elemento en su lugar 
     * y lo hace "hundir" para reacomodar el árbol.
     * * @return El RegistroImpresion con mayor prioridad, o null si está vacío.
     */
    public RegistroImpresion eliminarMin() {
        if (tamanoActual == 0) {
            return null; // La cola está vacía
        }
        
        // El elemento a extraer es la raíz (el de mayor prioridad)
        RegistroImpresion min = arreglo[0];
        
        // Movemos el último elemento de la cola a la raíz
        arreglo[0] = arreglo[tamanoActual - 1];
        arreglo[tamanoActual - 1] = null; // Limpiamos la referencia
        tamanoActual--;
        
        // Hundir (Sift-Down) para reordenar el árbol
        if (tamanoActual > 0) {
            hundir(0);
        }
        
        return min;
    }

    /**
     * Hace flotar un elemento hacia la raíz si su prioridad es mayor 
     * (etiqueta de tiempo menor) que la de su padre.
     * * @param indice El índice del elemento que queremos hacer flotar.
     */
    public void flotar(int indice) {
        while (indice > 0 && arreglo[indice].getEtiquetaTiempo() < arreglo[padre(indice)].getEtiquetaTiempo()) {
            intercambiar(indice, padre(indice));
            indice = padre(indice);
        }
    }

    /**
     * Función que empuja un nodo hacia abajo en el árbol si es mayor que sus hijos, 
     * manteniendo la regla del Min-Heap.
     * * @param pos La posición del nodo a evaluar.
     */
    private void hundir(int pos) {
        int izq = hijoIzquierdo(pos);
        int der = hijoDerecho(pos);
        int menor = pos;

        // Comparamos con el hijo izquierdo
        if (izq < tamanoActual && arreglo[izq].getEtiquetaTiempo() < arreglo[menor].getEtiquetaTiempo()) {
            menor = izq;
        }
        
        // Comparamos con el hijo derecho
        if (der < tamanoActual && arreglo[der].getEtiquetaTiempo() < arreglo[menor].getEtiquetaTiempo()) {
            menor = der;
        }

        // Si el nodo actual no es el menor, se intercambia y se sigue hundiendo
        if (menor != pos) {
            intercambiar(pos, menor);
            hundir(menor);
        }
    }

    /**
     * Duplica la capacidad del arreglo si este se llena, evitando usar librerías.
     */
    private void expandirCapacidad() {
        capacidad *= 2;
        RegistroImpresion[] nuevoArreglo = new RegistroImpresion[capacidad];
        for (int i = 0; i < tamanoActual; i++) {
            nuevoArreglo[i] = arreglo[i];
        }
        arreglo = nuevoArreglo;
    }

    /**
     * Verifica si la cola de prioridad está vacía.
     * @return true si no hay elementos, false en caso contrario.
     */
    public boolean estaVacio() {
        return tamanoActual == 0;
    }

    /**
     * Retorna el tamaño actual del montículo.
     * @return Cantidad de registros en la cola.
     */
    public int getTamanoActual() {
        return tamanoActual;
    }

    /**
     * Retorna el arreglo interno (Útil para la representación visual del árbol).
     * @return El arreglo del montículo binario.
     */
    public RegistroImpresion[] getArreglo() {
        return arreglo;
    }
}