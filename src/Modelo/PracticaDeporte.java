package Modelo;

public class PracticaDeporte {

    private Usuario    usuario;
    private Deporte    deporte;
    private boolean    favorito = false;
    private SkillLevel skillLevel;
    private int        partidosJugados = 0;

    /* ---------- ctor ---------- */
    public PracticaDeporte(Usuario usuario,
                           Deporte deporte,
                           SkillLevel skillLevel) {
        this.usuario    = usuario;
        this.deporte    = deporte;
        this.skillLevel = skillLevel;
    }

    /* ---------- operaciones de dominio ---------- */

    /** Cambia el nivel de habilidad */
    public void actualizarSkillLevel(SkillLevel nuevoNivel) {
        this.skillLevel = nuevoNivel;
    }

    /** Marca (o desmarca) como deporte favorito del usuario */
    public void esFavorito(boolean esFav) {
        this.favorito = esFav;
    }

    /** Incrementa el contador de partidos jugados */
    public void registrarPartidoJugado() {
        this.partidosJugados++;
    }

    /* ---------- getters / setters ---------- */
    public Usuario getUsuario()           { return usuario; }
    public void    setUsuario(Usuario u)  { this.usuario = u; }

    public Deporte getDeporte()           { return deporte; }
    public void    setDeporte(Deporte d)  { this.deporte = d; }

    public boolean isFavorito()           { return favorito; }
    public void    setFavorito(boolean f) { this.favorito = f; }

    public SkillLevel getSkillLevel()     { return skillLevel; }
    public void      setSkillLevel(SkillLevel s){ this.skillLevel = s; }

    public int getPartidosJugados()       { return partidosJugados; }
    public void setPartidosJugados(int p) { this.partidosJugados = p; }
    public Boolean getFavorito() {
    return favorito;
}
}