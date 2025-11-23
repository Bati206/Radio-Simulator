package radiosimulator;

public class Radio {
    
    private boolean encendido;
    private int volumen;
    private IRadioGUI gui;

    private RadioModo radioModo;
    private Reproduccion reproduccion;
    private Telefono telefono;
    private Productividad productividad;

    public Radio(IRadioGUI gui) {
        this.gui = gui;
        this.encendido = true;
        this.volumen = 10;

        radioModo = new RadioModo();
        reproduccion = new Reproduccion();
        telefono = new Telefono();
        productividad = new Productividad(gui);
    }

    public void apagar() {
        encendido = false;
        gui.mostrarEstado(panelApagado());
    }

    private String panelApagado() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("                    INFORMACIÓN GENERAL      \n");
        sb.append("========================================\n");
        sb.append("El sistema se ha apagado.\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    public String getEstadoSimple() {
        Cancion c = reproduccion.cancionActual();
        StringBuilder s = new StringBuilder();
        s.append("Encendido: ").append(encendido ? "ON" : "OFF").append("\n");
        s.append("Volumen: ").append(volumen).append("\n");
        s.append("Modo Radio: ").append(radioModo.getBandaFreq()).append("\n");
        s.append("Canción: ").append(c != null ? c.toString() : "Ninguna").append("\n");
        s.append("Teléfono conectado: ").append(telefono.isConectado() ? "SI" : "NO");
        return s.toString();
    }

    public void cambiarVolumenDesdeConsola() {
        if (!encendido) { gui.mostrarError("Radio apagado"); return; }
        int nuevo = gui.solicitarEntero("Ingrese volumen (0-100)");
        if (nuevo >= 0 && nuevo <= 100) {
            volumen = nuevo;
            gui.mostrarEstado(resumenVolumen());
        } else {
            gui.mostrarError("Volumen fuera de rango");
        }
    }

    private String resumenVolumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("             AJUSTE DE VOLUMEN          \n");
        sb.append("----------------------------------------\n");
        sb.append("Volumen ajustado a ").append(volumen).append("\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    // ---------- MODO RADIO ----------
    public void modoRadio() {
        if (!encendido) { gui.mostrarError("Radio apagado"); return; }

        int opcion;
        do {
            gui.mostrarEstado(panelRadio());
            opcion = gui.solicitarEntero("Seleccione una opción (1-6)");

            switch (opcion) {
                case 1: radioModo.cambiarBanda(); break;
                case 2: radioModo.ajustarFrecuencia(0.5); break;
                case 3: radioModo.ajustarFrecuencia(-0.5); break;
                case 4:
                    if (radioModo.guardarEmisora()) {
                        gui.mostrarEstado("Emisora guardada.");
                    } else {
                        gui.mostrarError("Límite de emisoras alcanzado");
                    }
                    break;
                case 5:
                    int idx = gui.solicitarEntero("Índice a cargar");
                    if (!radioModo.cargarEmisora(idx)) gui.mostrarError("Índice inválido");
                    break;
                case 6: break;
                default: gui.mostrarError("Opción inválida");
            }

        } while (opcion != 6);
    }

    private String panelRadio() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("               MODO RADIO               \n");
        sb.append("========================================\n");
        sb.append(radioModo.estadoRadio()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("1. Cambiar banda (AM/FM)\n");
        sb.append("2. Subir frecuencia (+0.5)\n");
        sb.append("3. Bajar frecuencia (-0.5)\n");
        sb.append("4. Guardar emisora\n");
        sb.append("5. Cargar emisora\n");
        sb.append("6. Volver\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    // ---------- MODO REPRODUCCIÓN ----------
    public void modoReproduccion() {
        if (!encendido) { gui.mostrarError("Radio apagado"); return; }

        int opcion;
        do {
            gui.mostrarEstado(panelReproduccion());
            opcion = gui.solicitarEntero("Seleccione una opción (1-3)");

            switch (opcion) {
                case 1: reproduccion.siguiente(); break;
                case 2: reproduccion.anterior(); break;
                case 3: break;
                default: gui.mostrarError("Opción inválida");
            }

        } while (opcion != 3);
    }

    private String panelReproduccion() {
        Cancion c = reproduccion.cancionActual();
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("             MODO REPRODUCCIÓN          \n");
        sb.append("========================================\n");
        sb.append("Reproduciendo: ").append(c != null ? c.toString() : "Ninguna").append("\n");
        sb.append("----------------------------------------\n");
        sb.append("1. Siguiente canción\n");
        sb.append("2. Canción anterior\n");
        sb.append("3. Volver\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    // ---------- MODO TELÉFONO ----------
    public void modoTelefono() {
        if (!encendido) { gui.mostrarError("Radio apagado"); return; }

        int opcion;
        do {
            gui.mostrarEstado(panelTelefono());
            opcion = gui.solicitarEntero("Seleccione una opción (1-6)");

            switch (opcion) {
                case 1: telefono.conectar(); break;
                case 2: telefono.desconectar(); break;
                case 3: telefono.mostrarContactos(gui); break;
                case 4: telefono.llamarContacto(gui); break;
                case 5: telefono.finalizar(); break;
                case 6: break;
                default: gui.mostrarError("Opción inválida");
            }

        } while (opcion != 6);
    }

    private String panelTelefono() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("               MODO TELÉFONO            \n");
        sb.append("========================================\n");
        sb.append("Teléfono conectado: ").append(telefono.isConectado() ? "SI" : "NO").append("\n");
        sb.append("----------------------------------------\n");
        sb.append("1. Conectar teléfono\n");
        sb.append("2. Desconectar teléfono\n");
        sb.append("3. Ver contactos\n");
        sb.append("4. Llamar a un contacto\n");
        sb.append("5. Finalizar llamada\n");
        sb.append("6. Volver\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }

    // ---------- MODO PRODUCTIVIDAD ----------
    public void modoProductividad() {
        if (!encendido) { gui.mostrarError("Radio apagado"); return; }

        int opcion;
        do {
            gui.mostrarEstado(panelProductividad());
            opcion = gui.solicitarEntero("Seleccione una opción (1-2)");

            switch (opcion) {
                case 1: productividad.verPronostico(); break;
                case 2: break;
                default: gui.mostrarError("Opción inválida");
            }

        } while (opcion != 2);
    }

    private String panelProductividad() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("            MODO PRODUCTIVIDAD          \n");
        sb.append("========================================\n");
        sb.append("1. Ver pronóstico del tiempo\n");
        sb.append("2. Volver\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}