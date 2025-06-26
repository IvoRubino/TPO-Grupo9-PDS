package Modelo.Notificacion;

public class PushNotificacionFirebase implements IEstrategiaNotificacion{
    @Override
    public void enviar(Notificacion notificacion) {
        System.out.println("Enviando notificacion push a " +
                notificacion.getUsuario() + ". " +
                notificacion.getMensaje()
        );
    }
}
