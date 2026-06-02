package algoritmos;

import java.util.Random;

/**
 * Genera el laberinto usando el algoritmo Recursive Backtracker (DFS perfecto).
 * Produce laberintos con exactamente un camino entre cualquier par de celdas.
 * La misma semilla siempre genera el mismo laberinto.
 */
public class GeneradorLaberinto {

    public static final char PARED = '█';
    public static final char CAMINO = ' ';
    public static final char INICIO = 'S';
    public static final char SALIDA = 'E';

    private int cantidadFilas;
    private int cantidadColumnas;
    private char[][] laberinto;

    public GeneradorLaberinto(int filas, int columnas, long semilla) {
        this.cantidadFilas = (filas % 2 == 0) ? filas + 1 : filas;
        this.cantidadColumnas = (columnas % 2 == 0) ? columnas + 1 : columnas;
        this.laberinto = new char[this.cantidadFilas][this.cantidadColumnas];
        generar(semilla);
    }

    private void generar(long semilla) {
         // Inicializar todo como pared
        for (int fila = 0; fila < cantidadFilas; fila++) {
            for (int columna = 0; columna < cantidadColumnas; columna++) {
                laberinto[fila][columna] = PARED;
            }
        }

        Random generadorAleatorio = new Random(semilla);
        tallar(1, 1, generadorAleatorio);

        laberinto[1][1] = INICIO;
        laberinto[cantidadFilas - 2][cantidadColumnas - 2] = SALIDA;
    }
     private void tallar(int filaActual, int columnaActual, Random generadorAleatorio) {

        laberinto[filaActual][columnaActual] = CAMINO;

        int[][] direcciones = {
            {-2, 0},
            {2, 0},
            {0, -2},
            {0, 2}
        };

        // Mezcla Fisher-Yates
        for (int indice = direcciones.length - 1; indice > 0; indice--) {
            int indiceAleatorio = generadorAleatorio.nextInt(indice + 1);

            int[] direccionTemporal = direcciones[indice];
            direcciones[indice] = direcciones[indiceAleatorio];
            direcciones[indiceAleatorio] = direccionTemporal;
        }

        for (int[] direccion : direcciones) {

            int nuevaFila = filaActual + direccion[0];
            int nuevaColumna = columnaActual + direccion[1];

            boolean dentroDeLimites =
                    nuevaFila > 0 &&
                    nuevaFila < cantidadFilas - 1 &&
                    nuevaColumna > 0 &&
                    nuevaColumna < cantidadColumnas - 1;

            if (dentroDeLimites && laberinto[nuevaFila][nuevaColumna] == PARED) {

                laberinto[
                        filaActual + direccion[0] / 2
                ][
                        columnaActual + direccion[1] / 2
                ] = CAMINO;

                tallar(nuevaFila, nuevaColumna, generadorAleatorio);
            }
        }
    }
    
    public char[][] getMatriz() {
        return laberinto;
    }

    public int getFilas() {
        return cantidadFilas;
    }

    public int getColumnas() {
        return cantidadColumnas;
    }

    public int getInicioFila() {
        return 1;
    }

    public int getInicioColumna() {
        return 1;
    }

    public int getSalidaFila() {
        return cantidadFilas  - 2;
    }

    public int getSalidaColumna() {
        return cantidadColumnas  - 2;
    }
}