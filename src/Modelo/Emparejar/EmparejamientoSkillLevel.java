package Modelo.Emparejar;

import Modelo.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Empareja usuarios cuyo skillLevel (enum) caiga entre skillMinimo y skillMaximo.
 */
public class EmparejamientoSkillLevel implements IEstrategiaEmparejamiento {
    private SkillLevel skillMinimo;
    private SkillLevel skillMaximo;

    public EmparejamientoSkillLevel(SkillLevel skillMinimo, SkillLevel skillMaximo) {
        this.skillMinimo = skillMinimo;
        this.skillMaximo = skillMaximo;
    }

    @Override
    public boolean puedeUnirse(Partido partido, Usuario jugador) {
        SkillLevel nivelUsuario = obtenerSkill(jugador, partido);
        return nivelUsuario.ordinal() >= skillMinimo.ordinal()
                && nivelUsuario.ordinal() <= skillMaximo.ordinal();
    }

    @Override
    public List<List<Usuario>> emparejar(List<Usuario> jugadores, Partido partido) {
        // 1) Filtrar usuarios por rango de skill
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : jugadores) {
            SkillLevel nivelUsuario = obtenerSkill(u, partido);
            if (nivelUsuario.ordinal() >= skillMinimo.ordinal()
                    && nivelUsuario.ordinal() <= skillMaximo.ordinal()) {
                filtrados.add(u);
            }
        }

        // 2) Ordenamiento manual (bubble sort) de mayor a menor skill
        int n = filtrados.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                SkillLevel nivelJ = obtenerSkill(filtrados.get(j), partido);
                SkillLevel nivelJ1 = obtenerSkill(filtrados.get(j + 1), partido);
                // si el siguiente tiene mayor nivel, intercambiamos
                if (nivelJ1.ordinal() > nivelJ.ordinal()) {
                    Usuario temp = filtrados.get(j);
                    filtrados.set(j, filtrados.get(j + 1));
                    filtrados.set(j + 1, temp);
                }
            }
        }

        // 3) Dividir en dos equipos alternando posición
        List<Usuario> equipoA = new ArrayList<>();
        List<Usuario> equipoB = new ArrayList<>();
        for (int i = 0; i < filtrados.size(); i++) {
            if (i % 2 == 0) {
                equipoA.add(filtrados.get(i));
            } else {
                equipoB.add(filtrados.get(i));
            }
        }

        // 4) Devolver los dos equipos agrupados
        List<List<Usuario>> equipos = new ArrayList<>();
        equipos.add(equipoA);
        equipos.add(equipoB);
        return equipos;
    }

    /**
     * Obtiene el nivel de skill de un usuario para el deporte del partido.
     * Si no tiene práctica registrada, devuelve PRINCIPIANTE.
     */
    private SkillLevel obtenerSkill(Usuario usuario, Partido partido) {
        for (PracticaDeporte pd : usuario.getPracticasDeporte()) {
            if (pd.getDeporte().equals(partido.getDeporte())) {
                return pd.getSkillLevel();
            }
        }
        // Default si no existe práctica para el deporte
        return SkillLevel.PRINCIPIANTE;
    }
}