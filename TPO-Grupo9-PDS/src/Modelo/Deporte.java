
package Modelo;

public class Deporte {

    private int id;               // ← NUEVO
    private String nombre;
    private String descripcion;
    private int minJugadores;
    private int maxJugadores;

    /* constructor sin id — el controller lo asigna internamente */
    public Deporte(String nombre, String descripcion,
                   int minJugadores, int maxJugadores) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
    }

    /* getters/setters */
    public int getId()              { return id; }
    public void setId(int id)       { this.id = id; }

    public String getNombre()       { return nombre; }
    public void setNombre(String n) { this.nombre = n; }

    public String getDescripcion()  { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }

    public int getMinJugadores()    { return minJugadores; }
    public void setMinJugadores(int m) { this.minJugadores = m; }

    public int getMaxJugadores()    { return maxJugadores; }
    public void setMaxJugadores(int m) { this.maxJugadores = m; }

    @Override public String toString() {
        return "[" + id + "] " + nombre;
    }
}