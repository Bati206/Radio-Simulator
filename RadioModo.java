package radiosimulator;

import java.util.ArrayList;
import java.util.List;

public class RadioModo {
    
    private String banda;
    private double frecuencia;
    private List<Double> emisoras;

    public RadioModo() {
        banda = "FM";
        frecuencia = 87.5;
        emisoras = new ArrayList<>();
    }

    public void cambiarBanda() {
        banda = banda.equals("FM") ? "AM" : "FM";
    }

    public void ajustarFrecuencia(double delta) {
        frecuencia += delta;
    }

    public boolean guardarEmisora() {
        if (emisoras.size() >= 50) return false;
        emisoras.add(frecuencia);
        return true;
    }

    public boolean cargarEmisora(int index) {
        if (index >= 0 && index < emisoras.size()) {
            frecuencia = emisoras.get(index);
            return true;
        }
        return false;
    }

    public String estadoRadio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Banda: ").append(banda);
        sb.append(" | Frecuencia: ").append(frecuencia);
        sb.append(" | Emisoras guardadas: ").append(emisoras.size());
        return sb.toString();
    }

    public String getBandaFreq() {
        return banda + " " + frecuencia;
    }

    public double getFrecuencia() {
        return frecuencia;
    }
}