package Modelo;

import java.io.*;
import java.util.*;

public class PracticaDeporte {

    private Usuario usuario;

    private Deporte deporte;

    private Boolean favorito;

    private SkillLevel skillLevel;

    private int partidosJugados;


    public PracticaDeporte(Usuario usuario, Deporte deporte, SkillLevel skillLevel) {
        this.usuario = usuario;
        this.deporte = deporte;
        this.skillLevel = skillLevel;
    }

    public void actualzarSkillLevel(Enum SkillLevel) {
        // TODO implement here
        return null;
    }

    public void esFavorito(boolean favorito) {
        // TODO implement here
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
}