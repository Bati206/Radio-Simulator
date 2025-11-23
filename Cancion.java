package radiosimulator;


public class Cancion {
   
    private String nombre;
    private double duracion;
    private String autor;
    private String genero;

    public Cancion(String nombre, double duracion, String autor, String genero) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.autor = autor;
        this.genero = genero;
    }

    public String getNombre() { return nombre; }
    public double getDuracion() { return duracion; }
    public String getAutor() { return autor; }
    public String getGenero() { return genero; }

    @Override
    public String toString() {
        return nombre + " - " + autor + " (" + duracion + "min, " + genero + ")";
    }
}