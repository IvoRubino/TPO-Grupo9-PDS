package Modelo.EstadoPartido;

import Modelo.Partido;
import Modelo.Usuario;

public class EnJuego implements IEstadoPartido{
    @Override public void agregarJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido en curso");
    }
    @Override public void removerJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido en curso");
    }
    @Override public void cancelarPartido(Partido p) {
        p.setEstado(new Cancelado());
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Ya creado y en juego");
    }
    @Override
    public void aceptarPartido(Partido p) {
        throw new IllegalStateException("Ya en juego");
    }
}

