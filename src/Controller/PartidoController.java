/* ===== Control/PartidoController.java ===== */
package Controller;

import Modelo.*;
import Modelo.Emparejar.IEstrategiaEmparejamiento;
import Modelo.DTO.PartidoDTO;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Administra la colección in-memory de partidos.
 *  Depende de los controladores de Usuario y Deporte para validar IDs.
 */
public class PartidoController {

    private final Map<Integer, Partido> partidos = new HashMap<>();
    private int secuenciaId = 1;

    private final UsuarioController usuarioCtrl;
    private final DeporteController deporteCtrl;

    public PartidoController(UsuarioController usuarioCtrl,
                             DeporteController deporteCtrl) {
        this.usuarioCtrl = usuarioCtrl;
        this.deporteCtrl = deporteCtrl;
    }

    /* ------------------------------------------------------------
       crearPartido()
       ------------------------------------------------------------ */
    public void crearPartido(PartidoDTO dto) {

        Deporte dep = deporteCtrl.obtenerPorId(dto.getDeporteId());
    if (dep == null) throw new IllegalArgumentException("Deporte inexistente");

    Partido p = new Partido(
            secuenciaId,
            dto.getFechaHora(),
            dep,
            dto.getDireccion()     // <<--- String
    );
        
        // Establecer estrategia si viene en el DTO
        if (dto.getEstrategia() != null) {
            p.setEstrategia(dto.getEstrategia());
        }

        /* jugadores iniciales si vinieron en el DTO */
        if (dto.getJugadoresIds() != null) {
            for (Integer idU : dto.getJugadoresIds()) {
                Usuario u = usuarioCtrl.obtenerPorId(idU);
                if (u != null) p.agregarJugador(u);
            }
        }

        partidos.put(secuenciaId, p);
        secuenciaId++;
    }

    /* ------------------------------------------------------------
       buscarPartido()
       ------------------------------------------------------------ */
    public Partido buscarPartido(int partidoId) {
        Partido p = partidos.get(partidoId);
        if (p == null) throw new NoSuchElementException("No existe partido #" + partidoId);
        return p;
    }

    /* ------------------------------------------------------------
       agregar / remover jugador
       ------------------------------------------------------------ */
    public void agregarJugador(int partidoId, int usuarioId) {
        Partido p = buscarPartido(partidoId);
        Usuario u = usuarioCtrl.obtenerPorId(usuarioId);
        if (u == null) throw new NoSuchElementException("Usuario inexistente");
        p.agregarJugador(u);
    }

    public void removerJugador(int partidoId, int usuarioId) {
        Partido p = buscarPartido(partidoId);
        Usuario u = usuarioCtrl.obtenerPorId(usuarioId);
        if (u == null) throw new NoSuchElementException("Usuario inexistente");
        p.removerJugador(u);
    }

    /* ------------------------------------------------------------
       cancelarPartido()
       ------------------------------------------------------------ */
    public void cancelarPartido(int partidoId) {
        buscarPartido(partidoId).cancelarPartido();
    }

    /* ------------------------------------------------------------
       setEstrategia()
       ------------------------------------------------------------ */
    public void setEstrategia(int partidoId,
                              IEstrategiaEmparejamiento estrategia) {
        buscarPartido(partidoId).setEstrategia(estrategia);
    }
public void imprimirPartido(int partidoId) {
    Partido p = buscarPartido(partidoId);

    System.out.println("═══════════════════════════════════════");
    System.out.println("*  Partido #" + p.getId());
    System.out.println("Deporte: " + p.getDeporte().getNombre());
    System.out.println("Fecha:   " + p.getFecha());

    System.out.println("Dirección: " + p.getDireccion());
    GeoLocalizacion loc = p.getCoordenadas();
    if (loc != null)
        System.out.println("  * [lat=" + loc.getLatitud() + ", lon=" + loc.getLongitud() + "]");

    System.out.println("Estado: " + p.getEstado().getClass().getSimpleName());

    System.out.println("Jugadores:");
    if (p.getJugadores().isEmpty()) {
        System.out.println("  (sin jugadores aún)");
    } else {
        p.getJugadores().forEach(u ->
            System.out.println("  - ID: " + u.getId() + ", " + u.getUsername()));
    }

    System.out.println("Estrategia de emparejamiento: " +
        (p.getEstrategia() != null ?
                p.getEstrategia().getClass().getSimpleName() : "(ninguna)"));
    System.out.println("═══════════════════════════════════════");
}

    /* ------------------------------------------------------------
       getPartido() con filtro 
       ------------------------------------------------------------ */
//   public List<Partido> getPartido(FiltroPartido filtro) {
   //  /   Predicate<Partido> pred = filtro == null ? p -> true : filtro::cumple;
    //    return partidos.values().stream()
       //                .filter(pred)
          //             .collect(Collectors.toList());
   // }
}