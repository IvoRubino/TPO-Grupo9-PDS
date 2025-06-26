package Modelo.EstadoPartido;

import Modelo.Partido;
import Modelo.Usuario;

public class Cancelado implements IEstadoPartido {
    @Override public void agregarJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido cancelado");
    }
    @Override public void removerJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido cancelado");
    }
    @Override public void cancelarPartido(Partido p) {
        // ya cancelado
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Partido cancelado");
    }
    @Override public void aceptarPartido(Partido p) {
        throw new IllegalStateException("Partido cancelado");
    }
}
