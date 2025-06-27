package Modelo;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import Modelo.EstadoPartido.*;
import Modelo.GeoLocalizacion;
import Modelo.Notificacion.Notificador;
import Modelo.Notificacion.Notificacion;

import java.util.*;

/**
 *  Entidad principal del dominio. Maneja su propio ciclo de vida
 *  delegando reglas a:
 *    – IEstrategiaEmparejamiento  (Strategy)
 *    – IEstadoPartido            (State)
 *    – Notificador               (Strategy de notificación)
 */
public class Partido {

    /* --------- atributos ---------- */
    private int id;
    private Date fecha;
    private Deporte deporte;
    private GeoLocalizacion lugar;
    private IEstadoPartido estado;
    private final List<Usuario> jugadores = new ArrayList<>();
    private final Notificacion.Builder notiBuilder = new Notificacion.Builder(); // util. interna
    private final Notificador notificador = new Notificador();
    private IEstrategiaEmparejamiento estrategia; // Agregar este campo

    /* --------- ctor --------- */
    public Partido(int id,
                   Date fechaHora,
                   Deporte deporte,
                   GeoLocalizacion lugar) {
        this.id = id;
        this.fecha = fechaHora;
        this.deporte = deporte;
        this.lugar = lugar;
        this.estado = new NecesitamosJugadores();
    }

    /* ============================================================
       ==============  getters / setters  =========================
       ============================================================ */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Deporte getDeporte() { return deporte; }
    public void setDeporte(Deporte deporte) { this.deporte = deporte; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public GeoLocalizacion getLugar() { return lugar; }
    public void setLugar(GeoLocalizacion lugar) { this.lugar = lugar; }

    public IEstadoPartido getEstado() { return estado; }
    public void setEstado(IEstadoPartido estado) { this.estado = estado; }

    public List<Usuario> getJugadores() { return jugadores; }
    
    public IEstrategiaEmparejamiento getEstrategia() { return estrategia; }
    public void setEstrategia(IEstrategiaEmparejamiento estrategia) { this.estrategia = estrategia; }

    /* ============================================================
                        Métodos faltantes
       ============================================================ */
    public void agregarJugador(Usuario usuario) {
        if (!jugadores.contains(usuario)) {
            jugadores.add(usuario);
        }
    }
    
    public void removerJugador(Usuario usuario) {
        jugadores.remove(usuario);
    }
    
    public void cancelarPartido() {
        // Implementar lógica de cancelación
        // Por ejemplo, cambiar estado o notificar
    }

    /* ============================================================
                        Emparejar equipos
       ============================================================ */
    public List<List<Usuario>> emparejar() {
        if (estrategia != null) {
            return estrategia.emparejar(jugadores, this);
        }
        // Si no hay estrategia, devolver lista vacía o lanzar excepción
        return new ArrayList<>();
    }
}
