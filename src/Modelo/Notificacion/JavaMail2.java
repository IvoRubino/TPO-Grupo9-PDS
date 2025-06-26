package Modelo.Notificacion;

public class JavaMail2 implements IAdapterJavaMail {

    @Override
    public void enviar(Notificacion notificacion) {
        System.out.println("Enviando notificacion push a " +
                notificacion.getUsuario() + ". " +
                notificacion.getMensaje()
        );
    }
}
