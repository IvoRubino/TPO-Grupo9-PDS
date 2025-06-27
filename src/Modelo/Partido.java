package Modelo;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import Modelo.EstadoPartido.IEstadoPartido;
import Modelo.EstadoPartido.NecesitamosJugadores;
import Modelo.Notificacion.Notificacion;
import Modelo.Notificacion.Notificador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Entidad raíz del dominio.
 */
public class Partido {

    // -------- atributos --------
    private int id;
    private Date fecha;
    private Deporte deporte;
    private GeoLocalizacion lugar;
    private IEstadoPartido estado;

    private final List<Usuario> jugadores = new ArrayList<>();

    /* Infra-utilidades para notificar cambios de estado */
    private final Notificacion.Builder notiBuilder = new Notificacion.Builder();
    private final Notificador          notificador = new Notificador();

    private IEstrategiaEmparejamiento  estrategia;

    // -------- ctor --------
    public Partido(int id, Date fechaHora, Deporte deporte, GeoLocalizacion lugar) {
        this.id      = id;
        this.fecha   = fechaHora;
        this.deporte = deporte;
        this.lugar   = lugar;
        this.estado  = new NecesitamosJugadores();
    }

    // -------- getters / setters (solo los que uses) --------
    public List<Usuario> getJugadores() { return jugadores; }

    public IEstrategiaEmparejamiento getEstrategia() { return estrategia; }
    public void setEstrategia(IEstrategiaEmparejamiento estrategia) {
        this.estrategia = estrategia;
    }

    // -------------------------------------------------------
    public void agregarJugador(Usuario usuario) {
        if (usuario != null && !jugadores.contains(usuario)) {
            jugadores.add(usuario);
        }
    }

    public void removerJugador(Usuario usuario) {
        jugadores.remove(usuario);
    }

    public void cancelarPartido() {
        // delegás al estado para que ejecute su propia lógica de cancelación
       estado.cancelarPartido(this);
    }

    /** Devuelve los equipos emparejados o lista vacía si no hay estrategia. */
    public List<List<Usuario>> emparejar() {
        if (estrategia == null) return Collections.emptyList();
        return estrategia.emparejar(jugadores, this);
    }
    // ---------- getters que faltaban ----------
public int getId() {
    return id;
}

public IEstadoPartido getEstado() {
    return estado;
}

// (opcional) si querés seguir pudiendo cambiarlos:
public void setId(int id) {
    this.id = id;
}

public void setEstado(IEstadoPartido estado) {
    this.estado = estado;
}
}