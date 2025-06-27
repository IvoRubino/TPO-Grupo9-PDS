package Modelo.Notificacion;

public class JavaMail {

    private IAdapterJavaMail adapter;

    public JavaMail() {
        this.adapter = new AdapterJavaMail(); // implementación por defecto
    }

    // Opción extendida: también permitir inyección manual
    public JavaMail(IAdapterJavaMail adapter) {
        this.adapter = adapter;
    }

    public void enviar(Notificacion notificacion) {
        adapter.enviar(notificacion);

}}

