package src.com.mycompany.practica1.tokens;

/**
 *
 * @author Luis
 */
public class Lexema {

    //Variables
    private String lexema, tipo;
    private int cantidad;

    //Constructor, 
    public Lexema(String lexema, String tipo) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.cantidad = 0;
    }

    //Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
