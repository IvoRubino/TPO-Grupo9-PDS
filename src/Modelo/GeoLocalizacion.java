package Modelo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.URI;

import java.util.Objects;

/** Valor-objeto con coordenadas geográficas. */
public class GeoLocalizacion {

    private Double latitud;
    private Double longitud;
    private Double varianza;

    public GeoLocalizacion(Double latitud, Double longitud) {
        this.latitud  = latitud;
        this.longitud = longitud;
    }

    // -------- getters / setters ----------
    public Double getLatitud()  { return latitud;  }
    public void   setLatitud(Double latitud)  { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void   setLongitud(Double longitud) { this.longitud = longitud; }

    public Double getVarianza() { return varianza; }
    public void   setVarianza(Double varianza) { this.varianza = varianza; }

    /**
     * Devuelve una ubicación aproximada para un nombre de lugar.
     */
public static GeoLocalizacion obtenerCoordenadas(String lugar) {
    System.out.println("Timeout, esperando para volver a solicitar latitud y longitud...");
    try {
        String encodedLugar = URLEncoder.encode(lugar, StandardCharsets.UTF_8);
        String url = "https://nominatim.openstreetmap.org/search?q=" + encodedLugar
                   + "&format=json&limit=1";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "JavaGeolocalizacionApp/1.0")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONArray results = (JSONArray) parser.parse(response.body());

        if (!results.isEmpty()) {
            JSONObject obj = (JSONObject) results.get(0);
            double lat = Double.parseDouble((String) obj.get("lat"));
            double lon = Double.parseDouble((String) obj.get("lon"));
            return new GeoLocalizacion(lat, lon);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    /* equals/hashCode para poder usar contains, HashMap, etc. */
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoLocalizacion)) return false;
        GeoLocalizacion that = (GeoLocalizacion) o;
        return Objects.equals(latitud, that.latitud)
            && Objects.equals(longitud, that.longitud);
    }
    @Override public int hashCode() { return Objects.hash(latitud, longitud); }

    @Override public String toString() {
        return String.format("(%.6f, %.6f)", latitud, longitud);
    }
}