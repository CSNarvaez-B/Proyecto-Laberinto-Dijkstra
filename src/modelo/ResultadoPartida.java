package modelo;

/**
 * Representa un resultado de partida para el ranking.
 */
public class ResultadoPartida {
    private int pasos;
    private long semilla;
    private long tiempoMs;
    
    public ResultadoPartida(int pasos, long semilla, long tiempoMs) {
        this.pasos = pasos;
        this.semilla = semilla;
        this.tiempoMs = tiempoMs;
    }
    
    public int getPasos() { return pasos; }
    
    public long getSemilla() { return semilla; }
    
    public long getTiempoMs() { return tiempoMs; }
    
    @Override
    public String toString() {
        return String.format("pasos=%-4d | semilla=%-12d | tiempo=%dms", 
                            pasos, semilla, tiempoMs);
    }
}