/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author noelb
 */
public class RegistroImpresion {
    
    private Documento documento;
    
    // Esta etiqueta será el tiempo del reloj del SO. 
    // Si el documento es prioritario, este valor será alterado antes de crear este objeto.
    private int etiquetaTiempo; 

    /**
     * Constructor para crear un nuevo registro de impresión.
     * * @param documento      El documento que se va a imprimir.
     * @param etiquetaTiempo El valor de tiempo (modificado o no) usado para ordenar la cola.
     */
    public RegistroImpresion(Documento documento, int etiquetaTiempo) {
        this.documento = documento;
        this.etiquetaTiempo = etiquetaTiempo;
    }

    /**
     * Obtiene el documento asociado a este registro.
     * * @return El documento a imprimir.
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Establece o cambia el documento de este registro.
     * * @param documento El nuevo documento.
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * Obtiene la etiqueta de tiempo del registro.
     * Este valor es la clave de prioridad para el ordenamiento en el Montículo Binario.
     * * @return La etiqueta de tiempo.
     */
    public int getEtiquetaTiempo() {
        return etiquetaTiempo;
    }

    /**
     * Establece una nueva etiqueta de tiempo.
     * Esto será crucial más adelante para la función de eliminar un documento de la cola,
     * ya que deberás cambiar esta etiqueta a un valor muy pequeño (mayor prioridad) 
     * para que suba a la raíz y poder sacarlo.
     * * @param etiquetaTiempo El nuevo valor de tiempo.
     */
    public void setEtiquetaTiempo(int etiquetaTiempo) {
        this.etiquetaTiempo = etiquetaTiempo;
    }

    /**
     * Representación en cadena de texto del registro para la interfaz.
     * * @return Texto con los detalles del registro de impresión.
     */
    @Override
    public String toString() {
        return "[" + etiquetaTiempo + "] " + documento.getNombre() + ".";
    }
}
