package src.com.mycompany.practica1.enums;

/**
 *
 * @author Luis
 */
public enum Operador {
    //Estados o Tipos del Enum Operador
    RESTA('-'), SUMA('+'), MULTIPLICACION('*'), DIVISION('/'), MODULO('%');

    //Variable
    private final char simbolo;

    //Constructor
    private Operador(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
