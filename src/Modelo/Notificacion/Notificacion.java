package Modelo.Notificacion;

import Modelo.Usuario;
import java.util.Date;

public class Notificacion {

    private String  fecha;
    private String  mensaje;
    private Usuario usuario;

    /* ---------- NUEVO ---------- */
    public Notificacion(String mensaje, Date fecha, Usuario usuario) {
        this.mensaje = mensaje;
        this.fecha   = fecha.toString();
        this.usuario = usuario;
    }

    /* --------- Builder opcional --------- */
    public static class Builder {
        private String  fecha;
        private String  mensaje;
        private Usuario usuario;

        public Builder conFecha(String f)            { this.fecha = f;   return this; }
        public Builder conMensaje(String m)          { this.mensaje = m; return this; }
        public Builder conUsuario(Usuario u)         { this.usuario = u; return this; }
        public Notificacion build() {
            return new Notificacion(mensaje, new Date(), usuario);
        }
    }
    /* ------------------------------------ */

    /* getters y setters */
    public String  getFecha()   { return fecha;   }
    public void    setFecha(String fecha) { this.fecha = fecha; }
    public String  getMensaje() { return mensaje; }
    public void    setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Usuario getUsuario() { return usuario; }
    public void    setUsuario(Usuario usuario) { this.usuario = usuario; }
}