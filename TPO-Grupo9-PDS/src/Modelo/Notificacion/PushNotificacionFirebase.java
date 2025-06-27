package Modelo.Notificacion;

public class PushNotificacionFirebase {

    private IAdapterPush adapter;

    public PushNotificacionFirebase() {
        this.adapter = new AdapterPushFirebase(); // ← aquí usamos el nuevo Adapter
    }

    public void enviar(Notificacion notificacion) {
        adapter.enviar(notificacion);
    }
}