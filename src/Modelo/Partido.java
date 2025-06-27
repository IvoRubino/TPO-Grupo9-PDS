package Modelo;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import Modelo.EstadoPartido.IEstadoPartido;
import Modelo.EstadoPartido.NecesitamosJugadores;
import java.util.*;

public class Partido {

    /* ---------- atributos ---------- */
    private int id;
    private Date fecha;
    private Deporte deporte;

    /** Dirección textual (ej. “Av. Corrientes 1234, CABA”) */
    private String direccion;

    /** Coordenadas obtenidas al setear la dirección (lat/lon) */
    private GeoLocalizacion coordenadas;

    private IEstadoPartido estado = new NecesitamosJugadores();
    private final List<Usuario> jugadores = new ArrayList<>();

    private IEstrategiaEmparejamiento estrategia;

    /* ---------- ctor ---------- */
    public Partido(int id,
                   Date fechaHora,
                   Deporte deporte,
                   String direccion) {
        this.id        = id;
        this.fecha     = fechaHora;
        this.deporte   = deporte;
        setDireccion(direccion);           // ← genera las coordenadas
    }

    /* ---------- getters ---------- */
    public int               getId()         { return id; }
    public Date              getFecha()      { return fecha; }
    public Deporte           getDeporte()    { return deporte; }
    public String            getDireccion()  { return direccion; }
    public GeoLocalizacion   getCoordenadas(){ return coordenadas; }
    public IEstadoPartido    getEstado()     { return estado; }
    public List<Usuario>     getJugadores()  { return jugadores; }
    public IEstrategiaEmparejamiento getEstrategia() { return estrategia; }

    /* ---------- setters ---------- */
    public void setDireccion(String dir) {
        this.direccion   = dir;
        this.coordenadas = GeoLocalizacion.obtenerCoordenadas(dir);
    }
    public void setEstrategia(IEstrategiaEmparejamiento e) { this.estrategia = e; }
    public void setEstado(IEstadoPartido e)                { this.estado     = e; }

    /* ---------- lógica de dominio ---------- */
    public void agregarJugador(Usuario u)   { if (u!=null && !jugadores.contains(u)) jugadores.add(u); }
    public void removerJugador(Usuario u)   { jugadores.remove(u); }
    public void cancelarPartido()           { estado.cancelarPartido(this); }

    public List<List<Usuario>> emparejar() {
        return estrategia == null ? Collections.emptyList()
                                  : estrategia.emparejar(jugadores, this);
    }
}