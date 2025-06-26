package Modelo.EstadoPartido;

import Modelo.Partido;
import Modelo.Usuario;

public class Confirmado implements IEstadoPartido{
    @Override public void agregarJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Ya confirmado");
    }
    @Override public void removerJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Ya confirmado");
    }
    @Override public void cancelarPartido(Partido p) {
        p.setEstado(new Cancelado());
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Ya creado y confirmado");
    }
    @Override
    public void aceptarPartido(Partido p) {
        p.setEstado(new EnJuego());
    }
}
