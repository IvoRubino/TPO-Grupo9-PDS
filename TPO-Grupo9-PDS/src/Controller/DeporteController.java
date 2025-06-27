package Controller;

import Modelo.*;
import Modelo.DTO.DeporteDTO;

import java.util.*;

public class DeporteController {

    /* almacenamiento en memoria */
    private final Map<Integer, Deporte> deportesPorId = new HashMap<>();
    private int secuenciaId = 1;

    /** +agregarDeporte(DeporteDTO): void */
    public void agregarDeporte(DeporteDTO dto) {

        validarDto(dto);                                    // reglas básicas

        // Previene duplicados por nombre (opcional)
        boolean duplicado = deportesPorId.values().stream()
                             .anyMatch(d -> d.getNombre().equalsIgnoreCase(dto.getNombre()));
        if (duplicado) {
            throw new IllegalArgumentException(
                    "Ya existe un deporte con nombre '" + dto.getNombre() + "'.");
        }

        Deporte nuevo = dtoToEntity(dto);
        nuevo.setId(secuenciaId++);                         // id autoincremental
        deportesPorId.put(nuevo.getId(), nuevo);
    }

    /** +removerDeporte(int deporteId): void */
    public void removerDeporte(int deporteId) {
        if (deportesPorId.remove(deporteId) == null) {
            throw new NoSuchElementException(
                    "No se encontró deporte con id=" + deporteId);
        }
    }

    /** +getTodosLosDeportes(): List<Deporte> */
    public List<Deporte> getTodosLosDeportes() {
        return new ArrayList<>(deportesPorId.values());
    }

    /* utilidades privadas */

    private Deporte dtoToEntity(DeporteDTO dto) {
        return new Deporte(
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getMinJugadores(),
                dto.getMaxJugadores()
        );
    }

    private void validarDto(DeporteDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (dto.getMinJugadores() <= 0 || dto.getMaxJugadores() <= 0) {
            throw new IllegalArgumentException("Los valores de jugadores deben ser positivos");
        }
        if (dto.getMinJugadores() > dto.getMaxJugadores()) {
            throw new IllegalArgumentException("minJugadores no puede ser mayor que maxJugadores");
        }
    }

    /* ---- métodos de consulta opcionales ---- */
    public Deporte obtenerPorId(int id) { return deportesPorId.get(id); }
    public boolean existe(int id)       { return deportesPorId.containsKey(id); }
}