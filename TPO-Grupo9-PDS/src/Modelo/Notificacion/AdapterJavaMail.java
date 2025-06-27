package Modelo.Notificacion;

public class AdapterJavaMail implements IAdapterJavaMail {

    @Override
    public void enviar(Notificacion notificacion) {
        // Simulación de envío real
        System.out.println("[ADAPTER] Email enviado a "
                + notificacion.getUsuario().getEmail() + ": " + notificacion.getMensaje());
    }
}
