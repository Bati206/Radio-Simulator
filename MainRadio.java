package radiosimulator;

public class MainRadio {

    public static void main(String[] args) {

        
        ConsoleGUI gui = new ConsoleGUI();
        Radio radio = new Radio(gui);

        int opcion;
        do {
            gui.mostrarEstado(panelPrincipal(radio));
            opcion = gui.solicitarEntero("Seleccione una opción (1-6)");

            switch (opcion) {
                case 1: radio.modoRadio(); break;
                case 2: radio.modoReproduccion(); break;
                case 3: radio.modoTelefono(); break;
                case 4: radio.modoProductividad(); break;
                case 5: radio.cambiarVolumenDesdeConsola(); break;
                case 6: radio.apagar(); break;
                default: gui.mostrarError("Opción inválida");
            }

        } while (opcion != 6);
    }

    private static String panelPrincipal(Radio radio) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("        SIMULADOR GENERAL DE RADIO       \n");
        sb.append("========================================\n");
        sb.append("Estado del Sistema\n");
        sb.append("----------------------------------------\n");
        sb.append(radio.getEstadoSimple()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("1. Modo Radio\n");
        sb.append("2. Modo Reproducción\n");
        sb.append("3. Modo Teléfono\n");
        sb.append("4. Modo Productividad\n");
        sb.append("5. Ajustar Volumen\n");
        sb.append("6. Apagar y Salir\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}