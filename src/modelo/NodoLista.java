package modelo;

/**
 * Nodo genérico para la lista enlazada simple.
 * Almacena un dato de tipo T y una referencia al siguiente nodo.
 *
 * @param <T> Tipo del dato almacenado.
 */
public class NodoLista<T> {
    public T dato;
    public NodoLista<T> siguiente;

    public NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}