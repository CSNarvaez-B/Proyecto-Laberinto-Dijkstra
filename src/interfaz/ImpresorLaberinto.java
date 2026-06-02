package interfaz;

import algoritmos.GeneradorLaberinto;
import estructuras.ListaEnlazada;

/**
 * Utilidad para imprimir el laberinto en consola con colores ANSI.
 */
public class ImpresorLaberinto {

    public static final String COLOR_VERDE = "\u001B[32m";
    public static final String COLOR_ROJO = "\u001B[31m";
    public static final String COLOR_AMARILLO = "\u001B[33m";
    public static final String COLOR_RESET = "\u001B[0m";

    /**
     * Imprime el laberinto en consola con colores.
     * Si se pasa un camino, lo marca con '·' en amarillo.
     *
     * @param matriz Matriz del laberinto
     * @param camino Lista de coordenadas "fila,columna" del camino. Puede ser null.
     */
    public static void imprimir(char[][] matriz, ListaEnlazada<String> camino) {
        boolean[][] enCamino = new boolean[matriz.length][matriz[0].length];

        if (camino != null) {
            marcarCamino(camino, enCamino);
        }

        System.out.println();
        for (int f = 0; f < matriz.length; f++) {
            for (int c = 0; c < matriz[0].length; c++) {
                char celda = matriz[f][c];

                if (celda == GeneradorLaberinto.INICIO) {
                    System.out.print(COLOR_VERDE + "S" + COLOR_RESET);
                } else if (celda == GeneradorLaberinto.SALIDA) {
                    System.out.print(COLOR_ROJO + "E" + COLOR_RESET);
                } else if (enCamino[f][c] && celda == GeneradorLaberinto.CAMINO) {
                    System.out.print(COLOR_AMARILLO + "·" + COLOR_RESET);
                } else {
                    System.out.print(celda);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void marcarCamino(ListaEnlazada<String> camino, boolean[][] enCamino) {
        for (int i = 0; i < camino.tamanio(); i++) {
            String[] coords = camino.obtener(i).split(",");
            int f = Integer.parseInt(coords[0]);
            int c = Integer.parseInt(coords[1]);
            enCamino[f][c] = true;
        }
    }
}