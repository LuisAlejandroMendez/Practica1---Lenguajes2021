package src.com.mycompany.practica1.enums;

/**
 *
 * @author Luis
 */
public enum Agrupacion {
    //Estados o Tipos del Enum Agrupacion
    CORCHETE_IZQ('['), CORCHETE_DER(']'), PARENTESIS_IZQ('('),
    PARENTESIS_DER(')'), LLAVE_IZQ('{'), LLAVE_DER('}');

    //Variable
    private final char simbolo;

    //COnstructor
    private Agrupacion(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }

}
