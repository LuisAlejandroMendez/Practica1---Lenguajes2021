package src.com.mycompany.practica1.tokens;

import src.com.mycompany.practica1.enums.TipoToken; //Ya no lo use para el tipo de Reportes

/**
 *
 * @author Luis
 */
public class Token {

    //Variables
    private String texto, tipo;
    private int fila, columna;

    //Constructor
    public Token(String texto, String tipo, int fila, int columna) {
        this.texto = texto;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    //Getters y Setters
    public String getTexto() {
        return texto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
