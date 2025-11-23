package radiosimulator;

public interface IRadioGUI {
    
    //Metodos abstractos
   
    void mostrarEstado(String estado);
    void mostrarError(String error);
    String solicitarEntrada(String mensaje);
    int solicitarEntero(String mensaje);

}