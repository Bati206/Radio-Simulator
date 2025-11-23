package radiosimulator;

import java.util.ArrayList;
import java.util.List;

public class Reproduccion {
        
    private List<Cancion> canciones;
    private int indice;

    public Reproduccion() {
        canciones = new ArrayList<>();
        canciones.add(new Cancion("Camino", 3.0, "Autor A", "Pop"));
        canciones.add(new Cancion("Luz", 4.0, "Autor B", "Rock"));
        canciones.add(new Cancion("Viento", 2.5, "Autor C", "Instrumental"));
        indice = 0;
    }

    public void siguiente() {
        if (canciones.isEmpty()) return;
        indice = (indice + 1) % canciones.size();
    }

    public void anterior() {
        if (canciones.isEmpty()) return;
        indice = (indice - 1 + canciones.size()) % canciones.size();
    }

    public Cancion cancionActual() {
        if (canciones.isEmpty()) return null;
        return canciones.get(indice);
    }
}