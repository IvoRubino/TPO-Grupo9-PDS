package Modelo.Observador;

import Modelo.Notificacion.Notificacion;
import Modelo.Usuario;
import Modelo.Notificacion.JavaMail;

import java.util.Date;

public class ObservadorNotificacionEmail implements Observador {

    private JavaMail javaMail;
    private Usuario usuario;

    public ObservadorNotificacionEmail(Usuario usuario) {
        this.usuario = usuario;
        this.javaMail = new JavaMail();
    }

    @Override
    public void actualizar(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje, new Date(), usuario);
        javaMail.enviar(notificacion);
    }
}