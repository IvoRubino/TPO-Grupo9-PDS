/* ===== Main.java ===== */
package AppMain;

import Controller.*;
import Modelo.*;
import Modelo.DTO.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

      /* 1. Controladores in-memory */
        DeporteController deporteCtrl  = new DeporteController();
        UsuarioController usuarioCtrl  = new UsuarioController();
        PartidoController partidoCtrl  = new PartidoController(usuarioCtrl, deporteCtrl);

        /* 2. Alta de deportes */
        System.out.println("\n=== Alta de deportes ===");
        deporteCtrl.agregarDeporte(new DeporteDTO("Fútbol 5", "Cancha reducida", 5, 10));
        deporteCtrl.agregarDeporte(new DeporteDTO("Básquet",   "5 vs 5",          5, 10));
        deporteCtrl.getTodosLosDeportes().forEach(System.out::println);

        /* 3. Alta de usuarios */
        System.out.println("\n=== Alta de usuarios ===");
        usuarioCtrl.registrarUsuario(new UsuarioDTO(
                "lucho", "lucho@mail.com", "123",
                MedioNotificacion.EMAIL,
                "Paraguay 2155, CABA"));
        esperar();

        usuarioCtrl.registrarUsuario(new UsuarioDTO(
                "maria", "maria@mail.com", "abc",
                MedioNotificacion.PUSHNOTIFICACION,
                "Av. Santa Fe 3200, CABA"));
        esperar();
         /* 3.1  ──  Asignar deportes favoritos y nivel de habilidad */
        Deporte futbol = deporteCtrl.obtenerPorId(1);   // Fútbol 5
        Deporte basket = deporteCtrl.obtenerPorId(2);   // Básquet

        Usuario lucho = usuarioCtrl.obtenerPorId(1);
        Usuario maria = usuarioCtrl.obtenerPorId(2);
         usuarioCtrl.agregarDeporteFavorito(lucho.getId(), futbol);
        PracticaDeporte pdLucho = new PracticaDeporte(lucho, futbol, SkillLevel.INTERMEDIO);
        pdLucho.esFavorito(true);
        addPracticaToUser(lucho, pdLucho);

        // b) María
        usuarioCtrl.agregarDeporteFavorito(maria.getId(), basket);
        PracticaDeporte pdMaria = new PracticaDeporte(maria, basket, SkillLevel.PRINCIPIANTE);
        pdMaria.esFavorito(false);
        addPracticaToUser(maria, pdMaria);


        usuarioCtrl.listarUsuarios()
           .forEach(usuarioCtrl::imprimirUsuario); 

        System.out.println("\nLogin Usuario:maria /password: abc = "
                + usuarioCtrl.loginUsuario("maria", "abc"));

          /* 4. Crear partido */
        System.out.println("\n=== Crear partido ===");
        PartidoDTO pDTO = new PartidoDTO();
        pDTO.setDeporteId(1);
        pDTO.setFechaHora(new Date());
        pDTO.setDireccion("Av. Corrientes 1234, CABA");
        partidoCtrl.crearPartido(pDTO);
        esperar();
        partidoCtrl.imprimirPartido(1);

        /* ─────────────────────────────────────────────
           5. Agregar / remover jugadores
           ───────────────────────────────────────────── */
        System.out.println("\n=== Agregar jugadores ===");
        partidoCtrl.agregarJugador(1, 1);   // lucho
        partidoCtrl.agregarJugador(1, 2);   // maria

        Partido partido = partidoCtrl.buscarPartido(1);
        System.out.println("Jugadores en partido #1: " +
                partido.getJugadores().stream()
                       .map(Usuario::getUsername)
                       .toList());

        System.out.println("\n=== Remover jugador ===");
        partidoCtrl.removerJugador(1, 2);   // quito a maria
        System.out.println("Jugadores ahora: " +
                partido.getJugadores().stream()
                       .map(Usuario::getUsername)
                       .toList());

        System.out.println("\nPartido:");
        partidoCtrl.imprimirPartido(1);

        /* ─────────────────────────────────────────────
           6. Cancelar partido
           ───────────────────────────────────────────── */
        System.out.println("\n=== Cancelar partido ===");
        partidoCtrl.cancelarPartido(1);
        System.out.println("Estado partido #1 → " +
                partido.getEstado().getClass().getSimpleName());

        /* ─────────────────────────────────────────────
           7. Test rápido de geocodificación
           ───────────────────────────────────────────── */
       GeoLocalizacion loc = GeoLocalizacion.obtenerCoordenadas("Av. Corrientes 1234, CABA");
        if (loc != null) {
            System.out.println("\nGeoLocalización API demo:");
            System.out.println("Lat: " + loc.getLatitud());
            System.out.println("Lon: " + loc.getLongitud());
        } else {
            System.out.println("\nNo se pudo obtener la ubicación.");
        }
    }

    //Helpers 
    private static void esperar() {
    try { Thread.sleep(3_000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
}
 private static void addPracticaToUser(Usuario u, PracticaDeporte p) {
        List<PracticaDeporte> lista = u.getPracticasDeporte();
        if (lista == null) {
            lista = new ArrayList<>();
            u.setPracticasDeporte(lista);
        }
        lista.add(p);
    }
}