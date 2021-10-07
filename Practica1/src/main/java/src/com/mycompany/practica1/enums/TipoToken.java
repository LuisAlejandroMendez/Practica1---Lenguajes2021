package src.com.mycompany.practica1.enums;

/**
 *
 * @author Luis
 */
public enum TipoToken {
    //Estados o Tipos del Enum TipoToken
    IDENTIFICADOR("ID"), NUMERO("ENTERO"), DECIMAL("DECIMAL"),
    PUNTUACION("PUNTUACION"), OPERADOR("OPERADOR"), AGRUPACION("AGRUPACION");
    
    //Variable
    String tipo;

    //Constructor
    private TipoToken(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
