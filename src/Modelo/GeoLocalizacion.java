package Modelo;


import java.io.*;
import java.util.*;


public class GeoLocalizacion {


    public GeoLocalizacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    private Double latitud;

    private Double longitud;

    private Double varianza;

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getVarianza() {
        return varianza;
    }

    public void setVarianza(Double varianza) {
        this.varianza = varianza;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public obtenerCoordenadas(String lugar) {
        // TODO implement here
        return null;
    }

}