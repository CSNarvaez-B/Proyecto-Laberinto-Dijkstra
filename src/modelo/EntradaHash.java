package modelo;

/**
 * Entrada para la tabla hash.
 */
public class EntradaHash {
    private long clave;
    private char[][] valor;
    private EntradaHash siguiente;
    
    public EntradaHash(long clave, char[][] valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
    
    public long getClave() { return clave; }
    
    public char[][] getValor() { return valor; }
    
    public void setValor(char[][] valor) { this.valor = valor; }
    
    public EntradaHash getSiguiente() { return siguiente; }
    
    public void setSiguiente(EntradaHash siguiente) { this.siguiente = siguiente; }
}