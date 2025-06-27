package AppMain;

import Controller.*;
import Modelo.*;
import Modelo.DTO.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /* ─────────────────────────────────────────────
           1. Crear controladores “in-memory”
           ───────────────────────────────────────────── */
        DeporteController  deporteCtrl  = new DeporteController();
        UsuarioController  usuarioCtrl  = new UsuarioController();
        PartidoController  partidoCtrl  = new PartidoController(usuarioCtrl, deporteCtrl);

        /* ─────────────────────────────────────────────
           2. Alta de deportes
           ───────────────────────────────────────────── */
        System.out.println("\n=== Alta de deportes ===");
        deporteCtrl.agregarDeporte(new DeporteDTO("Fútbol 5", "Cancha reducida", 5, 10));
        deporteCtrl.agregarDeporte(new DeporteDTO("Básquet",   "5 vs 5",          5, 10));
        deporteCtrl.getTodosLosDeportes().forEach(System.out::println);

        /* ─────────────────────────────────────────────
           3. Alta de usuarios y login
           ───────────────────────────────────────────── */
        System.out.println("\n=== Alta de usuarios ===");
        GeoLocalizacion caba = new GeoLocalizacion(-34.60, -58.38);

        usuarioCtrl.registrarUsuario(new UsuarioDTO(
                "lucho",  "lucho@mail.com",  "123",
                MedioNotificacion.EMAIL, caba));

        usuarioCtrl.registrarUsuario(new UsuarioDTO(
                "maria",  "maria@mail.com",  "abc",
                MedioNotificacion.PUSHNOTIFICACION, caba));

        usuarioCtrl.listarUsuarios()
                   .forEach(u -> System.out.println(u.getId() + " → " + u.getUsername()));

        System.out.println("\nLogin maria / abc → "
                + usuarioCtrl.loginUsuario("maria", "abc"));

        /* ─────────────────────────────────────────────
           4. Crear partido (sin jugadores inicialmente)
           ───────────────────────────────────────────── */
        System.out.println("\n=== Crear partido ===");
        PartidoDTO pDTO = new PartidoDTO();
        pDTO.setDeporteId(1);                 // Fútbol 5
        pDTO.setFechaHora(new Date());
        pDTO.setUbicacion(caba);

        partidoCtrl.crearPartido(pDTO);

        System.out.println("Partidos creados: " +
                partidoCtrl.buscarPartido(1).getId());

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

        /* ─────────────────────────────────────────────
           6. Cancelar partido
           ───────────────────────────────────────────── */
        System.out.println("\n=== Cancelar partido ===");
        partidoCtrl.cancelarPartido(1);
        System.out.println("Estado partido #1 → " +
                partido.getEstado().getClass().getSimpleName());

        /* ─────────────────────────────────────────────
           7. Test de geocodificación
           ───────────────────────────────────────────── */
        GeoLocalizacion loc =
                GeoLocalizacion.obtenerCoordenadas("Avenida Corrientes 1234, CABA");

        if (loc != null) {
            System.out.println("Lat: " + loc.getLatitud());
            System.out.println("Lon: " + loc.getLongitud());
        } else {
            System.out.println("No se pudo obtener la ubicación.");
        }
    }
}