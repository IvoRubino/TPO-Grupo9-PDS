package Modelo.DTO;

import Modelo.GeoLocalizacion;
import Modelo.MedioNotificacion;

public class UsuarioDTO {

    private String username;
    private String email;
    private String password;
    private MedioNotificacion notificacionDePreferencia;
    private String direccion;

    /* nuevo ctor */
    public UsuarioDTO(String username,
                      String email,
                      String password,
                      MedioNotificacion medio,
                      String direccion) {
        this.username  = username;
        this.email     = email;
        this.password  = password;
        this.notificacionDePreferencia = medio;
        this.direccion = direccion;
    }

    /* getters / setters */
    public String getUsername()                     { return username; }
    public String getEmail()                        { return email; }
    public String getPassword()                     { return password; }
    public MedioNotificacion getNotificacionDePreferencia() { return notificacionDePreferencia; }
    public String getDireccion()                    { return direccion; }
    public void setDireccion(String d)              { this.direccion = d; }
    public void   setUsername(String username)          { this.username = username; }
    public void   setEmail(String email)                { this.email = email; }
    public void   setPassword(String password)          { this.password = password; }

    public void   setNotificacionDePreferencia(MedioNotificacion n) { this.notificacionDePreferencia = n; }
   
}