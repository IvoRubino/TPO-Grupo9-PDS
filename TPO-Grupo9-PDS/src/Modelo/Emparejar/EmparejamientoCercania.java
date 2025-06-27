package Modelo.Emparejar;

import Modelo.GeoLocalizacion;
import Modelo.Partido;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EmparejamientoCercania implements IEstrategiaEmparejamiento {
    private double cercaniaKM;

    public EmparejamientoCercania(double cercaniaKM) {
        this.cercaniaKM = cercaniaKM;
    }

    @Override
    public boolean puedeUnirse(Partido partido, Usuario jugador) {
        GeoLocalizacion locPartido = partido.getLugar();
        GeoLocalizacion locUsuario = jugador.getUbicacion();
        return locUsuario != null
                && distanceKM(locPartido, locUsuario) <= cercaniaKM;
    }

    @Override
    public List<List<Usuario>> emparejar(List<Usuario> jugadores, Partido partido) {

        List<Usuario> filtrados = new ArrayList<>();
        GeoLocalizacion locPartido = partido.getLugar();
        for (Usuario u : jugadores) {
            GeoLocalizacion locUsuario = u.getUbicacion();
            if (locUsuario != null && distanceKM(locPartido, locUsuario) <= cercaniaKM) {
                filtrados.add(u);
            }
        }


        List<Usuario> equipoA = new ArrayList<>();
        List<Usuario> equipoB = new ArrayList<>();
        for (int i = 0; i < filtrados.size(); i++) {
            if (i % 2 == 0) {
                equipoA.add(filtrados.get(i));
            } else {
                equipoB.add(filtrados.get(i));
            }
        }


        List<List<Usuario>> equipos = new ArrayList<>();
        equipos.add(equipoA);
        equipos.add(equipoB);
        return equipos;
    }

    /**
     * Calcula la distancia en kilómetros entre dos puntos geográficos usando fórmula de Haversine.
     */
    private double distanceKM(GeoLocalizacion a, GeoLocalizacion b) {
        final int R = 6371; // Radio de la Tierra en km
        double latDistance = Math.toRadians(b.getLatitud() - a.getLatitud());
        double lonDistance = Math.toRadians(b.getLongitud() - a.getLongitud());
        double sinLat = Math.sin(latDistance / 2);
        double sinLon = Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(
                Math.sqrt(sinLat * sinLat +
                        Math.cos(Math.toRadians(a.getLatitud())) *
                                Math.cos(Math.toRadians(b.getLatitud())) * sinLon * sinLon),
                Math.sqrt(1 - (sinLat * sinLat +
                        Math.cos(Math.toRadians(a.getLatitud())) *
                                Math.cos(Math.toRadians(b.getLatitud())) * sinLon * sinLon))
        );
        return R * c;
    }
}