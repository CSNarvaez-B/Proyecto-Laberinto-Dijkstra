package estructuras;

import modelo.NodoLista;

/**
 * Lista enlazada simple genérica.
 * No usa ninguna clase de colecciones de Java.
 * Usada para almacenar el camino solución de Dijkstra.
 *
 * @param <T> Tipo del elemento almacenado.
 */
public class ListaEnlazada<T> {

    private NodoLista<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        tamanio = 0;
    }

    /** Inserta un dato al inicio de la lista. */
    public void insertarInicio(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }

    /** Inserta un dato al final de la lista. */
    public void insertarFinal(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLista<T> actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    /** Devuelve el dato en la posición indicada (0-based). */
    public T obtener(int indice) {
        NodoLista<T> actual = cabeza;
        for (int i = 0; i < indice; i++) actual = actual.siguiente;
        return actual.dato;
    }

    /** Indica si la lista está vacía. */
    public boolean estaVacia() { return cabeza == null; }

    /** Devuelve la cantidad de elementos. */
    public int tamanio() { return tamanio; }

    /** Imprime todos los elementos de la lista. */
    public void imprimir() {
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            System.out.println("  " + actual.dato);
            actual = actual.siguiente;
        }
    }
}