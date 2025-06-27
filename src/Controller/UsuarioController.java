/* ===== Control/UsuarioController.java ===== */
package Controller;

import Modelo.*;
import Modelo.DTO.UsuarioDTO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Gestiona los objetos Usuario en memoria.
 *  1  UsuarioController  —▸  1..*  Usuario   (relación «administra» del UML)
 */
public class UsuarioController {

    /* ==== almacenamiento in-memory ==== */
    private final Map<Integer, Usuario> usuariosPorId = new HashMap<>();
    private final Map<String, Usuario> usuariosPorUsername = new HashMap<>();
    private int secuenciaId = 1;          // autoincremental muy simple

    /* ==== API UML ==== */

    /** Registrar usuario (DTO ▸ entidad) */
    public void registrarUsuario(UsuarioDTO dto) {
        if (usuariosPorUsername.containsKey(dto.getUsername())) {
            throw new IllegalArgumentException("Nombre de usuario ya existente");
        }
        Usuario nuevo = dtoToEntity(dto);
        nuevo.setId(secuenciaId++);
        usuariosPorId.put(nuevo.getId(), nuevo);
        usuariosPorUsername.put(nuevo.getUsername(), nuevo);
    }

    /** Login: devuelve true si user+pass coinciden */
    public boolean loginUsuario(String username, String password) {
        Usuario u = usuariosPorUsername.get(username);
        return u != null && Objects.equals(u.getPassword(), password);
    }

    /** Actualiza datos básicos del usuario indicado */
    public void actualizarUsuario(int id, UsuarioDTO dtoActualizado) {
        Usuario u = usuariosPorId.get(id);
        if (u == null) {
            throw new NoSuchElementException("Usuario id=" + id + " inexistente");
        }
        /* Solo se modifican los campos que llegan en el DTO */
        u.setUsername(dtoActualizado.getUsername());
        u.setEmail(dtoActualizado.getEmail());
        u.setPassword(dtoActualizado.getPassword());
        u.setNotificacionDePreferencia(dtoActualizado.getNotificacionDePreferencia());
        u.setDireccion(dtoActualizado.getDireccion());

        // Sincronizo map por username si cambia
        usuariosPorUsername.remove(u.getUsername());
        usuariosPorUsername.put(u.getUsername(), u);
    }

    /** Agrega un deporte favorito al usuario */
    public void agregarDeporteFavorito(int idUsuario, Deporte deporte) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) { throw new NoSuchElementException("Usuario no encontrado"); }
        if (u.getFavoritos() == null) { u.setFavoritos(new ArrayList<>()); }
        if (!u.getFavoritos().contains(deporte)) { u.getFavoritos().add(deporte); }
    }

    /** Quita un deporte favorito del usuario */
    public void removerDeporteFavorito(int idUsuario, Deporte deporte) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null) { throw new NoSuchElementException("Usuario no encontrado"); }
        if (u.getFavoritos() != null) { u.getFavoritos().remove(deporte); }
    }
     /* ------------------------------------------------------------
       imprimirUsuario()
       ------------------------------------------------------------ */
    /** Muestra en consola la info relevante de un usuario. */
public void imprimirUsuario(Usuario u) {
    System.out.println("═══════════════════════════════════════");
    System.out.println("* Usuario: " + u.getUsername());

    // Deportes favoritos
    if (u.getFavoritos() != null && !u.getFavoritos().isEmpty()) {
        System.out.print("Favoritos: ");
        System.out.println(u.getFavoritos().stream()
                .map(Deporte::getNombre)
                .reduce((a, b) -> a + ", " + b)
                .orElse("(ninguno)"));
    } else {
        System.out.println("Favoritos: (ninguno)");
    }

    // Skill level por deporte
    System.out.println("Skill level:");
    if (u.getPracticasDeporte() != null && !u.getPracticasDeporte().isEmpty()) {
        for (PracticaDeporte pd : u.getPracticasDeporte()) {
            String estrella = Boolean.TRUE.equals(pd.getFavorito()) ? "*Favorito*" : "";
            System.out.println("  * " + pd.getDeporte().getNombre()
                             + " = " + pd.getSkillLevel() + " " + estrella);
        }
    } else {
        System.out.println("  (sin prácticas registradas)");
    }

    System.out.println("═══════════════════════════════════════");
}


    /* ==== utilidades privadas ==== */

 private Usuario dtoToEntity(UsuarioDTO dto) {
    return new Usuario(
            0,                                  // id se setea luego
            dto.getUsername(),
            dto.getPassword(),
            dto.getEmail(),
            dto.getDireccion()                  // ← dirección en texto
    );
}
    /* ==== getters de consulta opcionales ==== */

    public Usuario obtenerPorId(int id) { return usuariosPorId.get(id); }

    public List<Usuario> listarUsuarios() { return new ArrayList<>(usuariosPorId.values()); }
}