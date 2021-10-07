package src.com.mycompany.practica1.tokens;

import src.com.mycompany.practica1.enums.Agrupacion;
import src.com.mycompany.practica1.enums.Operador;
import src.com.mycompany.practica1.enums.Puntuacion;
import src.com.mycompany.practica1.enums.TipoToken;
import src.com.mycompany.practica1.jframes.JFrameReportes;
import src.com.mycompany.practica1.jframes.JFramePrincipal;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class Automata {

    //Variables locales
    private JTextArea textoEntrada;
    private int fila;
    private int columna;
    private int estadoActual = 0;

    //Matrices
    private int[][] matrizAutomata;
    private final int[] ESTADOS_ACEPTACION = {1, 2, 4, 5, 6, 7};

    //Vectores
    String[] lineas;
    String[] palabras;

    //Arraylist's
    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<Error> errores = new ArrayList<>();
    private ArrayList<Lexema> lexemas = new ArrayList<>();

    public Automata(JTextArea textoEntrada) {
        this.textoEntrada = textoEntrada;
        inicializarMatriz();
    }

    //Inicializar Matriz Para guardar Tabla de Estados de Aceptacion de AFD
    private void inicializarMatriz() {
        int filas = 8;
        int columnas = 7;

        //Estados: S0=0, S1=1, S2=2, S43=3, S4=4, S5=5, S6=6, S7=7, Error=-1
        //Tabla de estados segun AFD
        matrizAutomata = new int[filas][columnas];
        matrizAutomata[0][0] = 1;
        matrizAutomata[0][1] = 2;
        matrizAutomata[0][2] = 6;
        matrizAutomata[0][3] = 5;
        matrizAutomata[0][4] = 7;
        matrizAutomata[0][5] = 5;

        matrizAutomata[1][0] = 1;
        matrizAutomata[1][1] = 1;
        matrizAutomata[1][2] = -1;
        matrizAutomata[1][3] = -1;
        matrizAutomata[1][4] = -1;
        matrizAutomata[1][5] = -1;

        matrizAutomata[2][0] = -1;
        matrizAutomata[2][1] = 2;
        matrizAutomata[2][2] = -1;
        matrizAutomata[2][3] = -1;
        matrizAutomata[2][4] = -1;
        matrizAutomata[2][5] = 3;

        matrizAutomata[3][0] = -1;
        matrizAutomata[3][1] = 4;
        matrizAutomata[3][2] = -1;
        matrizAutomata[3][3] = -1;
        matrizAutomata[3][4] = -1;
        matrizAutomata[3][5] = -1;

        matrizAutomata[4][0] = -1;
        matrizAutomata[4][1] = 4;
        matrizAutomata[4][2] = -1;
        matrizAutomata[4][3] = -1;
        matrizAutomata[4][4] = -1;
        matrizAutomata[4][5] = -1;

        matrizAutomata[5][0] = -1;
        matrizAutomata[5][1] = -1;
        matrizAutomata[5][2] = -1;
        matrizAutomata[5][3] = -1;
        matrizAutomata[5][4] = -1;
        matrizAutomata[5][5] = -1;

        matrizAutomata[6][0] = -1;
        matrizAutomata[6][1] = -1;
        matrizAutomata[6][2] = -1;
        matrizAutomata[6][3] = -1;
        matrizAutomata[6][4] = -1;
        matrizAutomata[6][5] = -1;

        matrizAutomata[7][0] = -1;
        matrizAutomata[7][1] = -1;
        matrizAutomata[7][2] = -1;
        matrizAutomata[7][3] = -1;
        matrizAutomata[7][4] = -1;
        matrizAutomata[7][5] = -1;
    }

    //Metodo para leer el contenido del TextArea
    public void leerTextArea() {
        String texto = textoEntrada.getText();
        lineas = texto.split("\n");
        for (int i = 0; i < lineas.length; i++) {
            palabras = lineas[i].split(" ");
            for (int j = 0; j < palabras.length; j++) {
                analizarPalabra(palabras[j]);
                columna++;
            }
            columna = 0;
            fila++;
        }
    }

    //Metodo para recorrer palabras
    private void analizarPalabra(String token) {
        String opcion = "";
        estadoActual = 0;
        char[] cadenaPalabra = token.toCharArray();

        //Ciclo de analisis y reconocimiento de Tokens (Programacion de AFD)
        for (int i = 0; i < token.length(); i++) {
            columna++;

            if (Character.isAlphabetic(cadenaPalabra[i])) {
                JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", a estado: " + matrizAutomata[estadoActual][0] + " con = " + cadenaPalabra[i] + "\n");
                estadoActual = matrizAutomata[estadoActual][0];
            } else {
                if (Character.isDigit(cadenaPalabra[i])) {
                    JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", a estado: " + matrizAutomata[estadoActual][1] + " con = " + cadenaPalabra[i] + "\n");
                    estadoActual = matrizAutomata[estadoActual][1];
                } else {
                    if (verificarOperador(cadenaPalabra[i])) {
                        JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", a estado: " + matrizAutomata[estadoActual][2] + " con = " + cadenaPalabra[i] + "\n");
                        estadoActual = matrizAutomata[estadoActual][2];
                    } else {
                        if (verificarPuntuacion(cadenaPalabra[i]) && estadoActual == 0) {
                            JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", a estado: " + matrizAutomata[estadoActual][3] + " con = " + cadenaPalabra[i] + "\n");
                            estadoActual = matrizAutomata[estadoActual][3];
                        } else {
                            if (verificarAgrupacion(cadenaPalabra[i])) {
                                JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", al estado: " + matrizAutomata[estadoActual][4] + " con = " + cadenaPalabra[i] + "\n");
                                estadoActual = matrizAutomata[estadoActual][4];
                            } else {
                                if (cadenaPalabra[i] == '.' && estadoActual == 2) {
                                    JFramePrincipal.textAreaTransicicion.append("Movimiento de estado: " + estadoActual + ", al estado: " + matrizAutomata[estadoActual][5] + " con = " + cadenaPalabra[i] + "\n");
                                    estadoActual = matrizAutomata[estadoActual][5];
                                }
                            }
                        }
                    }
                }
            }
            opcion += cadenaPalabra[i];
            //Guardar Errores y su Data
            if (estadoActual == -1) {
                errores.add(new Error(opcion, fila + 1, columna)); //El +1 es por que empieza en 0, y conte desde 1
                estadoActual = 0;
                opcion = "";
            }
        }
        TipoToken[] tipos = TipoToken.values();
        for (int i = 0; i < ESTADOS_ACEPTACION.length; i++) {
            //Guardar Token's y su Data
            if (ESTADOS_ACEPTACION[i] == estadoActual) {
                tokens.add(new Token(opcion, tipos[i].getTipo(), fila + 1, columna)); //El +1 es por que empieza en 0, y conte desde 1
                estadoActual = 0;
                opcion = "";
                break;
            } else {
                //Guardar Errores y su Data
                if (i == ESTADOS_ACEPTACION.length - 1) {
                    errores.add(new Error(opcion, fila + 1, columna)); //El +1 es por que empieza en 0, y conte desde 1
                    estadoActual = 0;
                    opcion = "";
                }
            }
        }
    }
    //Fin Metodo Analizar Palabra (Fin AFD)

    //Metodo para reconocer Operador
    private boolean verificarOperador(char operador) {
        boolean existente = false;
        Operador[] instanciasOperador = Operador.values();
        for (int i = 0; i < instanciasOperador.length; i++) {
            if (instanciasOperador[i].getSimbolo() == operador) {
                existente = true;
            }
        }
        return existente;
    }

    //Metodo para reconocer Puntuacion
    private boolean verificarPuntuacion(char puntuacion) {
        boolean existente = false;
        Puntuacion[] instanciasPuntuacion = Puntuacion.values();
        for (int i = 0; i < instanciasPuntuacion.length; i++) {
            if (instanciasPuntuacion[i].getSimbolo() == puntuacion) {
                existente = true;
            }
        }
        return existente;
    }

    //Metodo para reconocer Agrupacion
    private boolean verificarAgrupacion(char agrupacion) {
        boolean existente = false;
        Agrupacion[] instanciasAgrupacion = Agrupacion.values();
        for (int i = 0; i < instanciasAgrupacion.length; i++) {
            if (instanciasAgrupacion[i].getSimbolo() == agrupacion) {
                existente = true;
            }
        }
        return existente;
    }

    //Metodo para enviar Data a JFrameReportes
    /* //Descontinuado
    for (int x = 0; x < textoAAnalizar.length(); x++) {//for analiza todo el contenido del texto enviado
            if (x == (textoAAnalizar.length() - 1)) {//va SI se trata del final de la cadena
                palabraAAnalizar = palabraAAnalizar + textoAAnalizar.charAt(x);//obtenemos la letra final
                columna++;
            }
            if (textoAAnalizar.charAt(x) == '\n' || textoAAnalizar.charAt(x) == ' ' || x == (textoAAnalizar.length() - 1)) {//si se detecta un espacio entonces hasta aqui hay una palabra formada
               METODOS PARA RECONOCER UN TOKEN, DEVOLVER TRUE Y SI NINGUNO DEVUELVE TRUE, ENTONCES ERROR -1
            } else {//si no entonces sumamos el char a la palabra
                palabraAAnalizar = palabraAAnalizar + textoAAnalizar.charAt(x);
                columna++;
            }

            if (textoAAnalizar.charAt(x) == '\n') {//si llegamos a detectar un enter entonces sumamos las filas
                fila++;
                columna = 0;
            }
            if (textoAAnalizar.charAt(x) == ' ') {//si llegamos a detectar un espacio entonces sumamos las columnas
                columna++;
            }
        }
     */
    
    public void enviarReportes() {
        //Crear Tabla si la tabla actual esta vacia
        if (errores.isEmpty()) {
            DefaultTableModel model = new DefaultTableModel();
            JFrameReportes.tokensTable.setModel(model);
            model.addColumn("Token");
            model.addColumn("Lexema");
            model.addColumn("Fila");
            model.addColumn("Columna");

            for (Token token : tokens) {
                model.addRow(new Object[]{token.getTipo(), token.getTexto(), token.getFila(), token.getColumna()});
            }
            recuentoLexemas();
            DefaultTableModel model1 = new DefaultTableModel();
            JFrameReportes.recuentoTable.setModel(model1);
            model1.addColumn("Lexema");
            model1.addColumn("Token");
            model1.addColumn("Cantidad");

            for (Lexema lexema : lexemas) {
                model1.addRow(new Object[]{lexema.getLexema(), lexema.getTipo(), lexema.getCantidad()});
            }
        } else {
            DefaultTableModel modelo = new DefaultTableModel();
            JFrameReportes.errorTable.setModel(modelo);
            modelo.addColumn("Cadena");
            modelo.addColumn("Fila");
            modelo.addColumn("Columna");

            for (Error error : errores) {
                modelo.addRow(new Object[]{error.getTexto(), error.getFila(), error.getColumna()});
            }
        }
    }

    //Metodo Contador/Recuento de Lexemas Repetidos
    public void recuentoLexemas() {
        lexemas.add(new Lexema(tokens.get(0).getTexto(), tokens.get(0).getTipo()));
        for (int i = 0; i < tokens.size(); i++) {
            for (int j = 0; j < lexemas.size(); j++) {
                if (tokens.get(i).getTexto().equals(lexemas.get(j).getLexema())) {
                    lexemas.get(j).setCantidad(lexemas.get(j).getCantidad() + 1);
                    break;
                } else {
                    if (lexemas.size() - 1 == j) {
                        lexemas.add(new Lexema(tokens.get(i).getTexto(), tokens.get(i).getTipo()));
                    }
                }
            }
        }
    }
}
