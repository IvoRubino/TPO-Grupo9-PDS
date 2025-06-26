package Modelo.DTO;

import Modelo.GeoLocalizacion;
import Modelo.MedioNotificacion;

public class UsuarioDTO {
    private String username;
    private String email;
    private String password;
    private MedioNotificacion notificacionDePreferencia;
    private GeoLocalizacion   ubicacion;

    public UsuarioDTO(String username,
                      String email,
                      String password,
                      MedioNotificacion notificacionDePreferencia,
                      GeoLocalizacion   ubicacion) {
        this.username  = username;
        this.email     = email;
        this.password  = password;
        this.notificacionDePreferencia = notificacionDePreferencia;
        this.ubicacion = ubicacion;
    }

    /* getters / setters ↓ */
    public String getUsername()                         { return username; }
    public void   setUsername(String username)          { this.username = username; }
    public String getEmail()                            { return email; }
    public void   setEmail(String email)                { this.email = email; }
    public String getPassword()                         { return password; }
    public void   setPassword(String password)          { this.password = password; }
    public MedioNotificacion getNotificacionDePreferencia() { return notificacionDePreferencia; }
    public void   setNotificacionDePreferencia(MedioNotificacion n) { this.notificacionDePreferencia = n; }
    public GeoLocalizacion getUbicacion()               { return ubicacion; }
    public void   setUbicacion(GeoLocalizacion ubicacion){ this.ubicacion = ubicacion; }
}