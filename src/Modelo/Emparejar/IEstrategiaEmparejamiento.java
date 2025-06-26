package Modelo.Emparejar;

import Modelo.Partido;
import Modelo.Usuario;

import java.util.List;

public interface IEstrategiaEmparejamiento {
    boolean puedeUnirse(Partido partido, Usuario jugador);
    List<List<Usuario>> emparejar(List<Usuario> jugadores, Partido partido);
}