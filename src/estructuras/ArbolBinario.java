package estructuras;

import modelo.ResultadoPartida;

/**
 * Árbol binario de búsqueda (BST) para el ranking de partidas.
 * La clave es la cantidad de pasos del camino encontrado.
 * El recorrido inOrden produce el ranking de menor a mayor pasos.
 * No usa ninguna clase de colecciones de Java.
 */
public class ArbolBinario {

    private static class Nodo {
        ResultadoPartida dato;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(ResultadoPartida dato) {
            this.dato = dato;
        }
    }

    private Nodo raiz;
    private int tamanio;

    public ArbolBinario() {
        raiz = null;
        tamanio = 0;
    }

    /**
     * Inserta un resultado de partida en el árbol.
     * Si ya existe esa cantidad de pasos, solo actualiza si el tiempo es menor.
     */
    public void insertar(ResultadoPartida resultado) {
        raiz = insertarRec(raiz, resultado);
    }

    private Nodo insertarRec(Nodo nodo, ResultadoPartida resultado) {
        if (nodo == null) {
            tamanio++;
            return new Nodo(resultado);
        }

        if (resultado.getPasos() < nodo.dato.getPasos()) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, resultado);
        } else if (resultado.getPasos() > nodo.dato.getPasos()) {
            nodo.derecho = insertarRec(nodo.derecho, resultado);
        } else {
            // Misma cantidad de pasos: conservar el de menor tiempo
            if (resultado.getTiempoMs() < nodo.dato.getTiempoMs()) {
                nodo.dato = resultado;
            }
        }
        return nodo;
    }

    /**
     * Imprime el top N del ranking en orden ascendente de pasos.
     */
    public void imprimirTop(int n) {
        int[] contador = {0};
        inOrden(raiz, contador, n);
    }

    private void inOrden(Nodo nodo, int[] contador, int limite) {
        if (nodo == null || contador[0] >= limite) return;
        inOrden(nodo.izquierdo, contador, limite);
        if (contador[0] < limite) {
            contador[0]++;
            System.out.printf("  %d. %s%n", contador[0], nodo.dato);
        }
        inOrden(nodo.derecho, contador, limite);
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }
}