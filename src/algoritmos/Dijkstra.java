package algoritmos;

import estructuras.ColaPrioridad;
import estructuras.ListaEnlazada;
import modelo.NodoColaPrioridad;

/**
 * Implementación del algoritmo de Dijkstra para encontrar
 * el camino de menor distancia dentro del laberinto.
 */
public class Dijkstra {

    private char[][] laberinto;
    private int[][] distanciasMinimas;
    private boolean[][] posicionesVisitadas;

    private int cantidadFilas;
    private int cantidadColumnas;

    private int[] desplazamientoFilas = {-1, 1, 0, 0};
    private int[] desplazamientoColumnas = {0, 0, -1, 1};

    public Dijkstra(char[][] laberinto) {
        this.laberinto = laberinto;

        this.cantidadFilas = laberinto.length;
        this.cantidadColumnas = laberinto[0].length;

        this.posicionesVisitadas = new boolean[cantidadFilas][cantidadColumnas];
        this.distanciasMinimas = new int[cantidadFilas][cantidadColumnas];

        for (int fila = 0; fila < cantidadFilas; fila++) {
            for (int columna = 0; columna < cantidadColumnas; columna++) {
                distanciasMinimas[fila][columna] = Integer.MAX_VALUE;
            }
        }
    }

    public ListaEnlazada<String> encontrarCamino(
            int filaInicio,
            int columnaInicio,
            int filaSalida,
            int columnaSalida) {

        ColaPrioridad<String> colaPrioridad = new ColaPrioridad<>();

        distanciasMinimas[filaInicio][columnaInicio] = 0;

        colaPrioridad.insertar(
                new NodoColaPrioridad<>(
                        filaInicio + "," + columnaInicio,
                        0,
                        null));

        while (!colaPrioridad.estaVacia()) {

            NodoColaPrioridad<String> nodoActual = colaPrioridad.extraer();

            String[] coordenadas = nodoActual.dato.split(",");

            int filaActual = Integer.parseInt(coordenadas[0]);
            int columnaActual = Integer.parseInt(coordenadas[1]);

            if (posicionesVisitadas[filaActual][columnaActual]) {
                continue;
            }

            posicionesVisitadas[filaActual][columnaActual] = true;

            if (filaActual == filaSalida && columnaActual == columnaSalida) {
                return reconstruirCamino(nodoActual);
            }

            for (int indiceDireccion = 0; indiceDireccion < 4; indiceDireccion++) {

                int nuevaFila =
                        filaActual + desplazamientoFilas[indiceDireccion];

                int nuevaColumna =
                        columnaActual + desplazamientoColumnas[indiceDireccion];

                if (!esPosicionValida(nuevaFila, nuevaColumna)) {
                    continue;
                }

                if (laberinto[nuevaFila][nuevaColumna]
                        == GeneradorLaberinto.PARED) {
                    continue;
                }

                if (posicionesVisitadas[nuevaFila][nuevaColumna]) {
                    continue;
                }

                int nuevaDistancia =
                        distanciasMinimas[filaActual][columnaActual] + 1;

                if (nuevaDistancia
                        < distanciasMinimas[nuevaFila][nuevaColumna]) {

                    distanciasMinimas[nuevaFila][nuevaColumna]
                            = nuevaDistancia;

                    colaPrioridad.insertar(
                            new NodoColaPrioridad<>(
                                    nuevaFila + "," + nuevaColumna,
                                    nuevaDistancia,
                                    nodoActual));
                }
            }
        }

        return null;
    }

    /**
     * Retorna la distancia mínima encontrada hasta la salida.
     * Si no existe camino, retorna -1.
     */
    public int obtenerDistanciaMinima(
            int filaSalida,
            int columnaSalida) {

        int distanciaMinima =
                distanciasMinimas[filaSalida][columnaSalida];

        return distanciaMinima == Integer.MAX_VALUE
                ? -1
                : distanciaMinima;
    }

    /**
     * Reconstruye el camino desde la salida hasta el inicio.
     */
    private ListaEnlazada<String> reconstruirCamino(
            NodoColaPrioridad<String> nodoDestino) {

        ListaEnlazada<String> caminoMasCorto =
                new ListaEnlazada<>();

        NodoColaPrioridad<String> nodoActual = nodoDestino;

        while (nodoActual != null) {
            caminoMasCorto.insertarInicio(nodoActual.dato);
            nodoActual = nodoActual.padre;
        }

        return caminoMasCorto;
    }

    /**
     * Verifica que una posición esté dentro de los límites
     * del laberinto.
     */
    private boolean esPosicionValida(
            int fila,
            int columna) {

        return fila >= 0
                && fila < cantidadFilas
                && columna >= 0
                && columna < cantidadColumnas;
    }
}