package Modelo;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import Modelo.EstadoPartido.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 */
public class Partido extends Obersvado implements IEstrategiaEmparejamiento {


    private int id;
    private Deporte deporte;
    private Date Fecha;
    private GeoLocalizacion lugar;
    private IEstadoPartido estado;
    private List<Usuario> jugadores;
    private IEstrategiaEmparejamiento estrategia;
    private Notificador notificador;



    public Partido(int id,
                   Date fechaHora,
                   Deporte deporte,
                   GeoLocalizacion ubicacion,
                   List<Usuario> jugadores,
                   IEstrategiaEmparejamiento estrategiaEmparejamiento) {
        this.id = id;
        this.Fecha = fechaHora;
        this.deporte = deporte;
        this.lugar = ubicacion;
        this.jugadores = jugadores;
        this.estrategia = estrategiaEmparejamiento;
        this.estado = new NecesitamosJugadores();
    }


    public List<List<Usuario>> emparejar(Partido partido, List<Usuario> jugadores) {
        if (estrategia == null) {
            throw new IllegalStateException("No se ha asignado ninguna estrategia de emparejamiento");
        }
        return estrategia.emparejar(this.jugadores, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public GeoLocalizacion getLugar() {
        return lugar;
    }

    public void setLugar(GeoLocalizacion lugar) {
        this.lugar = lugar;
    }

    public IEstadoPartido getEstado() {
        return estado;
    }

    public void setEstado(IEstadoPartido estado) {
        this.estado = estado;
    }

    public List<Usuario> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Usuario> jugadores) {
        this.jugadores = jugadores;
    }

    public IEstrategiaEmparejamiento getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(IEstrategiaEmparejamiento estrategia) {
        this.estrategia = estrategia;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public void agregarJugador(Usuario jugador) {
        estado.agregarJugador(this, jugador);
    }

    public void removerJugador(Usuario jugador) {
        estado.removerJugador(this, jugador);
    }

    public void cancelarPartido() {
        estado.cancelarPartido(this);
    }

    public void crearPartido() {
        estado.crearPartido(this);
    }

    public void aceptarPartido() {
        estado.aceptarPartido(this);
    }

}
