package radiosimulator;

import java.util.ArrayList;
import java.util.List;

public class Telefono {
    
    //Atributos
    private List<Contacto> contactos;
    private boolean conectado;
    private Contacto llamadaActual;
    private Contacto ultimoContacto;

    public Telefono() {
        contactos = new ArrayList<>(); //ALgunos contactos de prueba
        contactos.add(new Contacto("Maria", "1111"));
        contactos.add(new Contacto("Juan", "2222"));
        contactos.add(new Contacto("Carlos", "3333"));
        conectado = false;
        llamadaActual = null;
        ultimoContacto = null;
    }

    public void conectar() {
        conectado = true;
    }

    public void desconectar() {
        conectado = false;
        llamadaActual = null;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void mostrarContactos(IRadioGUI gui) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("               CONTACTOS                \n");
        sb.append("========================================\n");
        for (int i = 0; i < contactos.size(); i++) {
            sb.append(i).append(" - ").append(contactos.get(i).toString()).append("\n");
        }
        sb.append("----------------------------------------");
        gui.mostrarEstado(sb.toString());
    }

    public void llamarContacto(IRadioGUI gui) {
        if (!conectado) { gui.mostrarError("Teléfono no conectado"); return; }

        int i = gui.solicitarEntero("Índice del contacto a llamar");
        if (i >= 0 && i < contactos.size()) {
            llamadaActual = contactos.get(i);
            ultimoContacto = llamadaActual;
            gui.mostrarEstado("Llamando a " + llamadaActual.toString());
        } else {
            gui.mostrarError("Índice inválido");
        }
    }

    public void finalizar() {
        llamadaActual = null;
    }

    public Contacto getUltimoContacto() {
        return ultimoContacto;
    }
}