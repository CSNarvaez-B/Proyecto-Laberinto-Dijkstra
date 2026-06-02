package modelo;

/**
 * Nodo de la cola de prioridad usado en Dijkstra.
 * Almacena las coordenadas de la celda, la distancia acumulada
 * y un puntero al nodo padre para reconstruir el camino.
 *
 * @param <T> Tipo del dato (en este proyecto, String "fila,columna").
 */
public class NodoColaPrioridad<T> {
    public T dato;                          
    public int prioridad;                   
    public NodoColaPrioridad<T> siguiente;  
    public NodoColaPrioridad<T> padre;      

    public NodoColaPrioridad(T dato, int prioridad, NodoColaPrioridad<T> padre) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.padre = padre;
        this.siguiente = null;
    }
}