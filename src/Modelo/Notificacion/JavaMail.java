package Modelo.Notificacion;

public class JavaMail {

    private IAdapterJavaMail adapter;


    public void enviar(Notificacion notificacion) {
        adapter.enviar(notificacion);
    }

}

