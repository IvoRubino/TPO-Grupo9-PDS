package Modelo.EstadoPartido;

import Modelo.Partido;
import Modelo.Usuario;

import java.util.List;

public class NecesitamosJugadores implements IEstadoPartido {
    @Override
    public void agregarJugador(Partido p, Usuario jugador) {
        if (!p.getEstrategia().puedeUnirse(p, jugador)) {
            throw new IllegalArgumentException("No cumple criterio de emparejamiento");
        }

        p.getJugadores().add(jugador);
        // reemparejamos manteniendo la estructura de equipos “aplanada”
        List<List<Usuario>> equipos = p.getEstrategia()
                .emparejar(p.getJugadores(), p);
        // si ya alcanza el mínimo, paso a Armado
        if (p.getJugadores().size() >= p.getDeporte().getMinJugadores() * 2) {
            p.setEstado(new PartidoArmado());
        }
    }

    @Override public void removerJugador(Partido p, Usuario jugador) {
        p.getJugadores().remove(jugador);
    }
    @Override public void cancelarPartido(Partido p) {
        p.setEstado(new Cancelado());
    }
    @Override public void crearPartido(Partido p) {
        throw new IllegalStateException("Aún no puede crearse el partido");
    }
    @Override public void aceptarPartido(Partido p) {
        throw new IllegalStateException("No se puede aceptar sin estar armado");
    }
}

