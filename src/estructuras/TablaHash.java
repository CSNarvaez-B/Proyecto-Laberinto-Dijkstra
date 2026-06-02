package estructuras;

import modelo.EntradaHash;

/**
 * Tabla hash con encadenamiento separado.
 * No usa HashMap ni ninguna clase de colecciones de Java.
 * Usada para cachear laberintos ya generados por semilla.
 */
public class TablaHash {

    private static final int CAPACIDAD = 16;
    private EntradaHash[] cubos;
    private int tamanio;

    public TablaHash() {
        cubos = new EntradaHash[CAPACIDAD];
        tamanio = 0;
    }

    /**
     * Guarda un laberinto asociado a una semilla.
     */
    public void guardar(long semilla, char[][] matriz) {
        int indice = calcularIndice(semilla);
        EntradaHash actual = cubos[indice];

        while (actual != null) {
            if (actual.getClave() == semilla) {
                actual.setValor(matriz);
                return;
            }
            actual = actual.getSiguiente();
        }

        EntradaHash nueva = new EntradaHash(semilla, matriz);
        nueva.setSiguiente(cubos[indice]);
        cubos[indice] = nueva;
        tamanio++;
    }

    /**
     * Recupera el laberinto asociado a la semilla.
     */
    public char[][] obtener(long semilla) {
        EntradaHash actual = cubos[calcularIndice(semilla)];
        while (actual != null) {
            if (actual.getClave() == semilla) return actual.getValor();
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean contiene(long semilla) {
        return obtener(semilla) != null;
    }

    public int tamanio() {
        return tamanio;
    }

    private int calcularIndice(long semilla) {
        return Math.abs(Long.hashCode(semilla)) % CAPACIDAD;
    }

    public void imprimirSemillas() {
        System.out.println("Semillas en caché (" + tamanio + "):");
        for (int i = 0; i < CAPACIDAD; i++) {
            EntradaHash actual = cubos[i];
            while (actual != null) {
                System.out.println("  semilla=" + actual.getClave());
                actual = actual.getSiguiente();
            }
        }
    }
}