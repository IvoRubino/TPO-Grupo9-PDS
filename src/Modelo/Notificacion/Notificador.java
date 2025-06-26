package Modelo.Notificacion;

public class Notificador {

    private IEstrategiaNotificacion estrategiaNotificacion;

    /** Constructor vacío: deja la estrategia para más adelante */
    public Notificador() {}

    /** Constructor directo por conveniencia */
    public Notificador(IEstrategiaNotificacion estrategia) {
        this.estrategiaNotificacion = estrategia;
    }

    /* ---------- NUEVO ---------- */
    public void setEstrategiaNotificacion(IEstrategiaNotificacion estrategia) {
        this.estrategiaNotificacion = estrategia;
    }
    /* --------------------------- */

    public void enviar(Notificacion notificacion) {
        if (estrategiaNotificacion == null) {
            throw new IllegalStateException("Estrategia de notificación no definida");
        }
        estrategiaNotificacion.enviar(notificacion);
    }
}