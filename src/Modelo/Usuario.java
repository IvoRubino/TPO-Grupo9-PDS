package Modelo;

import java.util.*;

public class Usuario {

    private int    id;
    private String username;
    private String email;
    private String password;

    /* NUEVOS campos */
    private String            direccion;      // texto
    private GeoLocalizacion   ubicacion;      // lat/lon derivada

    private MedioNotificacion notificacionDePreferencia;
    private List<Deporte>     favoritos       = new ArrayList<>();
    private List<PracticaDeporte> practicasDeporte = new ArrayList<>();

    public Usuario(int id,
                   String username,
                   String password,
                   String email,
                   String direccion) {
        this.id       = id;
        this.username = username;
        this.password = password;
        this.email    = email;
        setDireccion(direccion);             // genera ubicacion
    }

    /* getters / setters principales */
    public int    getId()            { return id; }
    public String getUsername()      { return username; }
    public String getEmail()         { return email; }
    public String getDireccion()     { return direccion; }
    public GeoLocalizacion getUbicacion() { return ubicacion; }

    public void setDireccion(String dir) {
        this.direccion = dir;
        this.ubicacion = GeoLocalizacion.obtenerCoordenadas(dir);
    }

  

    public void setId(int id) {
        this.id = id;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MedioNotificacion getNotificacionDePreferencia() {
        return notificacionDePreferencia;
    }

    public void setNotificacionDePreferencia(MedioNotificacion notificacionDePreferencia) {
        this.notificacionDePreferencia = notificacionDePreferencia;
    }

    public List<Deporte> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Deporte> favoritos) {
        this.favoritos = favoritos;
    }

 

    public void setUbicacion(GeoLocalizacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<PracticaDeporte> getPracticasDeporte() {
        return practicasDeporte;
    }

    public void setPracticasDeporte(List<PracticaDeporte> practicasDeporte) {
        this.practicasDeporte = practicasDeporte;
    }

}