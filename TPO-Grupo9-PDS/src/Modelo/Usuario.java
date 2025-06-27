package Modelo;

import java.io.*;
import java.util.*;

public class Usuario {

    private int id;
    private String username;
    private String email;
    private String password;
    private MedioNotificacion notificacionDePreferencia;
    private List <Deporte> favoritos;
    private GeoLocalizacion ubicacion;
    private List<PracticaDeporte> practicasDeporte;


    public Usuario(int id, String username, String password, String email, GeoLocalizacion ubicacion) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
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

    public GeoLocalizacion getUbicacion() {
        return ubicacion;
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