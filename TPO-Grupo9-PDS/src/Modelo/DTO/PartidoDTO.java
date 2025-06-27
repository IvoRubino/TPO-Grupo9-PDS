package Modelo.DTO;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import java.util.Date;
import java.util.List;
import Modelo.GeoLocalizacion;

/**
 *  Transporta los datos mínimos para crear un Partido desde la capa Controller.
 */
public class PartidoDTO {

    private int deporteId;
    private Date fechaHora;                       // ⇢ java.util.Date para encajar con tu clase Partido
    private GeoLocalizacion ubicacion;
    private List<Integer> jugadoresIds;           // opcional, pueden venir vacíos
    private IEstrategiaEmparejamiento estrategia; // opcional, se puede setear luego

    /* --- getters / setters --- */
    public int getDeporteId()                         { return deporteId; }
    public void setDeporteId(int deporteId)           { this.deporteId = deporteId; }

    public Date getFechaHora()                        { return fechaHora; }
    public void setFechaHora(Date fechaHora)          { this.fechaHora = fechaHora; }

    public GeoLocalizacion getUbicacion()             { return ubicacion; }
    public void setUbicacion(GeoLocalizacion ubicacion){ this.ubicacion = ubicacion; }

    public List<Integer> getJugadoresIds()            { return jugadoresIds; }
    public void setJugadoresIds(List<Integer> ids)    { this.jugadoresIds = ids; }

    public IEstrategiaEmparejamiento getEstrategia()  { return estrategia; }
    public void setEstrategia(IEstrategiaEmparejamiento e){ this.estrategia = e; }
}