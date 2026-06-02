package estructuras;

import modelo.NodoColaPrioridad;

/**
 * Cola de prioridad mínima implementada con lista enlazada ordenada.
 * Siempre extrae el nodo de menor prioridad (menor distancia acumulada).
 * No usa PriorityQueue ni ninguna clase de Java.
 * Usada exclusivamente por el algoritmo de Dijkstra.
 *
 * @param <T> Tipo del dato almacenado en cada nodo.
 */
public class ColaPrioridad<T> {

    private NodoColaPrioridad<T> cabeza;

    public ColaPrioridad() {
        cabeza = null;
    }

    /**
     * Inserta un nodo manteniendo el orden ascendente por prioridad.
     * El nodo de menor prioridad siempre queda al frente.
     *
     * @param nodo Nodo a insertar con su prioridad (distancia acumulada).
     */
    public void insertar(NodoColaPrioridad<T> nodo) {
        if (cabeza == null || nodo.prioridad < cabeza.prioridad) {
            nodo.siguiente = cabeza;
            cabeza = nodo;
        } else {
            NodoColaPrioridad<T> actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.prioridad <= nodo.prioridad) {
                actual = actual.siguiente;
            }
            nodo.siguiente = actual.siguiente;
            actual.siguiente = nodo;
        }
    }

    /**
     * Extrae y devuelve el nodo de menor prioridad (el del frente).
     */
    public NodoColaPrioridad<T> extraer() {
        if (cabeza == null) return null;
        NodoColaPrioridad<T> temporal = cabeza;
        cabeza = cabeza.siguiente;
        return temporal;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}