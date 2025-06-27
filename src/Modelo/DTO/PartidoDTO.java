package Modelo.DTO;

import Modelo.Emparejar.IEstrategiaEmparejamiento;
import java.util.Date;
import java.util.List;
import Modelo.GeoLocalizacion;

/**
 *  Transporta los datos mínimos para crear un Partido desde la capa Controller.
 */
public class PartidoDTO {

    private int    deporteId;
    private Date   fechaHora;
    private String direccion;              
    private List<Integer> jugadoresIds;
    private IEstrategiaEmparejamiento estrategia;

    /* getters / setters */
    public String getDireccion()                { return direccion; }
    public void   setDireccion(String d)        { this.direccion = d; }
    
    public int getDeporteId()                         { return deporteId; }
    public void setDeporteId(int deporteId)           { this.deporteId = deporteId; }

    public Date getFechaHora()                        { return fechaHora; }
    public void setFechaHora(Date fechaHora)          { this.fechaHora = fechaHora; }

   
    public List<Integer> getJugadoresIds()            { return jugadoresIds; }
    public void setJugadoresIds(List<Integer> ids)    { this.jugadoresIds = ids; }

    public IEstrategiaEmparejamiento getEstrategia()  { return estrategia; }
    public void setEstrategia(IEstrategiaEmparejamiento e){ this.estrategia = e; }
}