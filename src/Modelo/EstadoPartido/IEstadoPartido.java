package Modelo.EstadoPartido;


import Modelo.Partido;
import Modelo.Usuario;

public interface IEstadoPartido {
    void agregarJugador(Partido partido, Usuario jugador);
    void removerJugador(Partido partido, Usuario jugador);
    void cancelarPartido(Partido partido);
    void crearPartido(Partido partido);
    void aceptarPartido(Partido partido);
}
