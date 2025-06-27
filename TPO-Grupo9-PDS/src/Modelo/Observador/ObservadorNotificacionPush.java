package Modelo.Observador;

import Modelo.Notificacion.Notificacion;
import Modelo.Usuario;
import Modelo.Notificacion.PushNotificacionFirebase;

import java.util.Date;

public class ObservadorNotificacionPush implements Observador {

    private PushNotificacionFirebase push;
    private Usuario usuario;

    public ObservadorNotificacionPush(Usuario usuario) {
        this.usuario = usuario;
        this.push = new PushNotificacionFirebase();
    }

    @Override
    public void actualizar(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje, new Date(), usuario);
        push.enviar(notificacion);
    }
}