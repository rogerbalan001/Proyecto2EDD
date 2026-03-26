/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import estructuras.MonticuloBinario;
import estructuras.TablaDispersion;
import modelos.Documento;
import modelos.RegistroImpresion;
import modelos.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author noelb
 */
public class SimuladorSO {

    private Usuario[] usuarios;
    private int cantidadUsuarios;
    private MonticuloBinario colaImpresion;
    private TablaDispersion tablaRegistros;
    private int reloj;

    /**
     * Constructor del Simulador. Inicializa las estructuras.
     */
    public SimuladorSO() {
        this.usuarios = new Usuario[20]; // Capacidad inicial
        this.cantidadUsuarios = 0;
        this.colaImpresion = new MonticuloBinario(50);
        this.tablaRegistros = new TablaDispersion(53); // Número primo para reducir colisiones
        this.reloj = 0;
    }

    /**
     * Incrementa el reloj del sistema operativo.
     * Representa el paso del tiempo en la simulación.
     */
    public void tickReloj() {
        this.reloj++;
    }

    /**
     * Agrega un nuevo usuario al sistema.
     * @param idUsuario El identificador del usuario.
     * @param tipo El tipo o nivel de prioridad del usuario.
     */
    public void agregarUsuario(String idUsuario, String tipo) {
        if (cantidadUsuarios == usuarios.length) {
            expandirUsuarios();
        }
        usuarios[cantidadUsuarios++] = new Usuario(idUsuario, tipo);
    }

    /**
     * Carga usuarios desde un archivo CSV con formato "usuario, tipo".
     * @param rutaArchivo La ruta absoluta o relativa del archivo CSV.
     * @return true si se cargó correctamente, false en caso de error.
     */
    public boolean cargarUsuariosDesdeCSV(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 2) {
                    agregarUsuario(datos[0].trim(), datos[1].trim());
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un usuario del sistema y todos los documentos que no estén en la cola.
     * @param idUsuario El ID del usuario a eliminar.
     * @return true si se eliminó exitosamente.
     */
    public boolean eliminarUsuario(String idUsuario) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getIdUsuario().equals(idUsuario)) {
                // Desplazamos para eliminar
                for (int j = i; j < cantidadUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[cantidadUsuarios - 1] = null;
                cantidadUsuarios--;
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un usuario registrado en el sistema por su ID.
     * @param idUsuario El ID a buscar.
     * @return El objeto Usuario, o null si no existe.
     */
    public Usuario buscarUsuario(String idUsuario) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getIdUsuario().equals(idUsuario)) {
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Envía un documento a la cola de impresión. Calcula la prioridad 
     * lo inserta en el Montículo Binario y registra su etiqueta en la Tabla Hash.
     * @param idUsuario El ID del usuario que envía el documento.
     * @param doc El documento a imprimir.
     */
    public void enviarAImpresion(String idUsuario, Documento doc) {
        Usuario u = buscarUsuario(idUsuario);
        if (u == null) return;

        // 1. Avanzamos el reloj para que cada documento tenga un tiempo base único
        tickReloj();
        int etiquetaAsignada = this.reloj;

        // 2. Alteramos la etiqueta de tiempo según el tipo de usuario.
        // Como es un Min-Heap, mientras MENOR (o más negativo) sea el número, MAYOR prioridad tiene.
        if (u.getTipo() != null) {
            switch (u.getTipo().toLowerCase()) {
                case "prioridad_alta":
                    etiquetaAsignada -= 1000; // Gran salto hacia la raíz
                    break;
                case "prioridad_media":
                    etiquetaAsignada -= 500;  // Salto medio
                    break;
                case "prioridad_baja":
                    etiquetaAsignada -= 100;  // Salto pequeño
                    break;
            }
        }

        // 3. Si se marcó como prioritario manualmente en la interfaz, le damos un extra
        if (doc.isEsPrioritario()) {
            etiquetaAsignada -= 50;
        }

        // Crear registro y encolar (Montículo)
        RegistroImpresion registro = new RegistroImpresion(doc, etiquetaAsignada);
        colaImpresion.insertar(registro);

        // Guardar en la Tabla Hash para acceso rápido a la etiqueta en caso de cancelación
        tablaRegistros.insertar(idUsuario, doc, etiquetaAsignada);

        // Eliminar de la lista de documentos "no enviados" del usuario
        // u.eliminarDocumento(doc.getNombre()); // Descomenta esto si manejas listas de docs internos en el usuario
    }

    /**
     * Libera la impresora sacando el documento con mayor prioridad de la cola.
     * @return El registro impreso, o null si la cola está vacía.
     */
    public RegistroImpresion liberarImpresora() {
        if (colaImpresion.estaVacio()) {
            return null;
        }
        RegistroImpresion impreso = colaImpresion.eliminarMin();
        return impreso;
    }

    // --- Método auxiliar para expandir el arreglo de usuarios ---
    private void expandirUsuarios() {
        Usuario[] nuevoArreglo = new Usuario[usuarios.length * 2];
        for (int i = 0; i < usuarios.length; i++) {
            nuevoArreglo[i] = usuarios[i];
        }
        usuarios = nuevoArreglo;
    }

    // Getters para la interfaz gráfica
    public Usuario[] getUsuarios() { return usuarios; }
    public int getCantidadUsuarios() { return cantidadUsuarios; }
    public MonticuloBinario getColaImpresion() { return colaImpresion; }
    public TablaDispersion getTablaRegistros() { return tablaRegistros; }
    public int getReloj() { return reloj; }
    
    /**
     * Cancela un documento buscando su nombre y forzando su eliminación del montículo.
     */
    public boolean cancelarImpresion(String idUsuario, String nombreDoc) {
        
        /* * Según la rúbrica del proyecto: "el proceso de eliminación consistirá en cambiar 
         * la etiqueta de tiempo del registro correspondiente, de forma que sea el de mayor 
         * prioridad (etiqueta de tiempo más pequeña) en la cola, por lo tanto, debe ser movido 
         * al inicio de la cola y luego eliminado"
         */
         
        modelos.RegistroImpresion[] arreglo = colaImpresion.getArreglo();
        int tamano = colaImpresion.getTamanoActual();
        
        // 1. Buscamos el documento en el montículo
        for (int i = 0; i < tamano; i++) {
            if (arreglo[i].getDocumento().getNombre().equals(nombreDoc)) {
                
                // 2. Forzamos la máxima prioridad dándole un valor negativo extremo
                arreglo[i].setEtiquetaTiempo(-999999); 
                
                // 3. Lo hacemos flotar hasta la raíz del árbol (índice 0)
                colaImpresion.flotar(i); 
                
                // 4. Usamos el método nativo del montículo para extraer la raíz (eliminar_min)
                colaImpresion.eliminarMin(); 
                
                return true; // Documento cancelado exitosamente
            }
        }
        
        return false; // No se encontró el documento
    }
}
