package interfaz;

/**
 * Utilidad para mostrar los menús del juego.
 */
public class Menu {

    public static void mostrarBienvenida() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    LABERINTO ALEATORIO — Dijkstra    ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    public static void mostrarPrincipal() {
        System.out.println("\n── MENÚ ──");
        System.out.println("  1) Generar laberinto");
        System.out.println("  2) Resolver con Dijkstra (camino mínimo)");
        System.out.println("  3) Ver ranking top-5");
        System.out.println("  4) Ver caché de semillas");
        System.out.println("  5) Salir");
    }
}