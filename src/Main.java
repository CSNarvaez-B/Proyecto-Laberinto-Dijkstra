import algoritmos.Dijkstra;
import algoritmos.GeneradorLaberinto;
import modelo.ResultadoPartida;
import estructuras.ArbolBinario;
import estructuras.ListaEnlazada;
import estructuras.TablaHash;
import interfaz.ImpresorLaberinto;
import interfaz.Menu;
import utilidades.EntradaTeclado;

/**
 * Clase principal del juego de laberinto.
 * 
 * Estructuras de datos usadas:
 * - ListaEnlazada: almacena el camino solución.
 * - ColaPrioridad: cola de prioridad para Dijkstra.
 * - TablaHash: caché de laberintos por semilla.
 * - ArbolBinario: ranking de mejores partidas.
 */
public class Main {

    private static TablaHash cache = new TablaHash();
    private static ArbolBinario ranking = new ArbolBinario();
    private static GeneradorLaberinto laberintoActual = null;
    private static long semillaActual = 0;

    public static void main(String[] args) {
        Menu.mostrarBienvenida();
        boolean activo = true;

        while (activo) {
            Menu.mostrarPrincipal();
            int opcion = EntradaTeclado.leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    generarLaberinto();
                    break;
                case 2:
                    resolverConDijkstra();
                    break;
                case 3:
                    mostrarRanking();
                    break;
                case 4:
                    mostrarCache();
                    break;
                case 5:
                    activo = false;
                    break;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        }

        System.out.println("\n¡Hasta luego!");
        EntradaTeclado.cerrar();
    }

    private static void generarLaberinto() {
        System.out.println("\n── GENERAR LABERINTO ──");
        long semilla = EntradaTeclado.leerLong("Ingresa una semilla (número entero): ");

        // Verificar caché primero
        if (cache.contiene(semilla)) {
            System.out.println("✓ Laberinto recuperado de caché (semilla " + semilla + ")");
            char[][] matrizCacheada = cache.obtener(semilla);
            laberintoActual = new GeneradorLaberinto(matrizCacheada.length, matrizCacheada[0].length, semilla);
            semillaActual = semilla;
            ImpresorLaberinto.imprimir(laberintoActual.getMatriz(), null);
            return;
        }

        // Seleccionar tamaño
        int tam = seleccionarTamanio();

        // Generar y cachear
        laberintoActual = new GeneradorLaberinto(tam, tam, semilla);
        semillaActual = semilla;
        cache.guardar(semilla, laberintoActual.getMatriz());

        System.out.println("✓ Laberinto generado (" + tam + "x" + tam + ", semilla=" + semilla + ")");
        ImpresorLaberinto.imprimir(laberintoActual.getMatriz(), null);
    }

    private static int seleccionarTamanio() {
        System.out.println("Tamaño del laberinto:");
        System.out.println("  1) 11x11  (pequeño)");
        System.out.println("  2) 15x15  (mediano)");
        System.out.println("  3) 21x21  (grande)");
        int opTam = EntradaTeclado.leerEntero("Opción: ");
        return opTam == 2 ? 15 : opTam == 3 ? 21 : 11;
    }

    private static void resolverConDijkstra() {
        if (laberintoActual == null) {
            System.out.println("Primero debes generar un laberinto (opción 1).");
            return;
        }

        System.out.println("\n── DIJKSTRA — CAMINO MÍNIMO ──");

        char[][] matriz = laberintoActual.getMatriz();
        int inicioF = laberintoActual.getInicioFila();
        int inicioC = laberintoActual.getInicioColumna();
        int salidaF = laberintoActual.getSalidaFila();
        int salidaC = laberintoActual.getSalidaColumna();

        // Medir tiempo de ejecución
        long tiempoInicio = System.currentTimeMillis();
        Dijkstra dijkstra = new Dijkstra(matriz);
        ListaEnlazada<String> camino = dijkstra.encontrarCamino(inicioF, inicioC, salidaF, salidaC);
        long tiempoMs = System.currentTimeMillis() - tiempoInicio;

        if (camino == null) {
            System.out.println("✗ No existe camino a la salida en este laberinto.");
            return;
        }

        int pasos = camino.tamanio() - 1;
        int distancia = dijkstra.getDistancia(salidaF, salidaC);

        // Mostrar resultados
        System.out.println("✓ Camino encontrado con Dijkstra");
        System.out.println("  Pasos     : " + pasos);
        System.out.println("  Distancia : " + distancia);
        System.out.println("  Tiempo    : " + tiempoMs + " ms");

        // Imprimir coordenadas del camino
        System.out.print("  Camino    : ");
        for (int i = 0; i < camino.tamanio(); i++) {
            System.out.print(camino.obtener(i));
            if (i < camino.tamanio() - 1) System.out.print(" → ");
        }
        System.out.println();

        // Mostrar laberinto con el camino marcado
        ImpresorLaberinto.imprimir(laberintoActual.getMatriz(), camino);

        // Guardar en ranking
        ranking.insertar(new ResultadoPartida(pasos, semillaActual, tiempoMs));
        System.out.println("✓ Resultado guardado en el ranking.");
    }

    private static void mostrarRanking() {
        System.out.println("\n── TOP 5 RANKING (menor pasos) ──");
        if (ranking.estaVacio()) {
            System.out.println("Aún no hay partidas registradas.");
            return;
        }
        ranking.imprimirTop(5);
    }

    private static void mostrarCache() {
        System.out.println("\n── CACHÉ DE LABERINTOS ──");
        if (cache.tamanio() == 0) {
            System.out.println("La caché está vacía.");
            return;
        }
        cache.imprimirSemillas();
    }
} 