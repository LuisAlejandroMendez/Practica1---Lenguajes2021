package src.com.mycompany.practica1.tokens;

/**
 *
 * @author Luis
 */
public class Error {

    //Variables
    private String texto;
    private int fila, columna;

    //Constructor
    public Error(String texto, int fila, int columna) {
        this.texto = texto;
        this.fila = fila;
        this.columna = columna;
    }

    //Getters y Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
