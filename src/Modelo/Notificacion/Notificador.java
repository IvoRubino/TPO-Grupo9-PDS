package Modelo.Notificacion;

public class Notificador {
    private Notificacion notificacion;
    private IEstrategiaNotificacion estrategiaNotificacion;

    public void enviar(Notificacion notificacion) {
        estrategiaNotificacion.enviar(notificacion);
    }
}


