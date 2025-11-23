package radiosimulator;
    
public class Productividad {
    
   private IRadioGUI gui;

    public Productividad(IRadioGUI gui) {
        this.gui = gui;
    }

    public void planificarViaje() {
        gui.mostrarEstado(banner("Planificación de Viaje", "Iniciando planificación..."));
    }

    public void verTarjetas() {
        gui.mostrarEstado(banner("Tarjetas de Presentación", "Mostrando tarjetas..."));
    }

    public void verPronostico() {
        gui.mostrarEstado(banner("Pronóstico del Tiempo", "Soleado, 24°C"));
    }

    private String banner(String titulo, String contenido) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("            ").append(titulo).append("\n");
        sb.append("========================================\n");
        sb.append(contenido).append("\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}