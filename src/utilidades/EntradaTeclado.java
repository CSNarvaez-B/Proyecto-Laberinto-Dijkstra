package utilidades;

import java.util.Scanner;

/**
 * Utilidad para leer datos del teclado de forma segura.
 */
public class EntradaTeclado {
    private static Scanner scanner = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long leerLong(String mensaje) {
        System.out.print(mensaje);
        try {
            return Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 42L;
        }
    }

    public static void cerrar() {
        scanner.close();
    }
}