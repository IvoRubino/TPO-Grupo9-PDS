package Modelo.EstadoPartido;


import Modelo.Partido;
import Modelo.Usuario;

public class Finalizado implements IEstadoPartido {
    @Override public void agregarJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido finalizado");
    }
    @Override public void removerJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido finalizado");
    }
    @Override public void cancelarPartido(Partido p) {
        throw new IllegalStateException("No se puede cancelar un partido finalizado");
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Ya finalizado");
    }
    @Override public void aceptarPartido(Partido p) {
        throw new IllegalStateException("Ya finalizado");
    }
}

