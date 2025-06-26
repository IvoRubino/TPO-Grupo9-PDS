package Modelo.EstadoPartido;

import Modelo.Partido;
import Modelo.Usuario;

public class PartidoArmado implements IEstadoPartido {
    @Override public void agregarJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido ya armado");
    }
    @Override public void removerJugador(Partido p, Usuario j) {
        throw new IllegalStateException("Partido ya armado");
    }
    @Override public void cancelarPartido(Partido p) {
        p.setEstado(new Cancelado());
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Partido ya existe");
    }
    @Override
    public void aceptarPartido(Partido p) {
        p.setEstado(new Confirmado());
    }
}
