package src.com.mycompany.practica1.enums;

/**
 *
 * @author Luis
 */
public enum Puntuacion {
    //Estados o Tipos del Enum Puntuacion
    PUNTO('.'), COMA(','), PUNTO_COMA(';'), DOS_PUNTOS(':');

    //Variable
    private final char simbolo;

    //COnstructor
    private Puntuacion(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}
