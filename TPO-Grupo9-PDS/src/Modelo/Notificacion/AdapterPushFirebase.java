package Modelo.Notificacion;

public class AdapterPushFirebase implements IAdapterPush {

    @Override
    public void enviar(Notificacion notificacion) {
        // Simulación de envío push
        System.out.println("[ADAPTER] Push enviado a "
                + notificacion.getUsuario().getEmail() + ": " + notificacion.getMensaje());
    }
}
