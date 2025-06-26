package Modelo.Emparejar;
import Modelo.Partido;
import Modelo.PracticaDeporte;
import Modelo.Usuario;

import java.util.List;
import java.util.ArrayList;


public class EmparejamientoHistorial implements IEstrategiaEmparejamiento {
    private int partidosMinimos;

    public EmparejamientoHistorial(int partidosMinimos) {
        this.partidosMinimos = partidosMinimos;
    }

    @Override
    public boolean puedeUnirse(Partido partido, Usuario jugador) {
        return obtenerPartidosJugados(jugador, partido) >= partidosMinimos;
    }

    @Override
    public List<List<Usuario>> emparejar(List<Usuario> jugadores, Partido partido) {
        // 1) Filtrar usuarios según partidos jugados en el deporte
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : jugadores) {
            int partidos = obtenerPartidosJugados(u, partido);
            if (partidos >= partidosMinimos) {
                filtrados.add(u);
            }
        }

        // 2) Dividir en dos equipos alternando posición
        List<Usuario> equipoA = new ArrayList<>();
        List<Usuario> equipoB = new ArrayList<>();
        for (int i = 0; i < filtrados.size(); i++) {
            if (i % 2 == 0) equipoA.add(filtrados.get(i));
            else            equipoB.add(filtrados.get(i));
        }

        // 3) Empaquetar resultado
        List<List<Usuario>> equipos = new ArrayList<>();
        equipos.add(equipoA);
        equipos.add(equipoB);
        return equipos;
    }

    /**
     * Obtiene la cantidad de partidos jugados de un usuario
     * para el deporte del partido. Si no hay práctica, devuelve 0.
     */
    private int obtenerPartidosJugados(Usuario usuario, Partido partido) {
        for (PracticaDeporte pd : usuario.getPracticasDeporte()) {
            if (pd.getDeporte().equals(partido.getDeporte())) {
                return pd.getPartidosJugados();
            }
        }
        return 0;
    }
}
