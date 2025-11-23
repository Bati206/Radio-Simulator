package radiosimulator;

import java.util.Scanner;

public class ConsoleGUI implements IRadioGUI {
    
    private Scanner scanner;

    public ConsoleGUI() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void mostrarEstado(String estado) {
        System.out.println("\n" + estado);
    }

    @Override
    public void mostrarError(String error) {
        System.out.println("\n========================================");
        System.out.println("                 ERROR                  ");
        System.out.println("----------------------------------------");
        System.out.println(error);
        System.out.println("========================================\n");
    }

    @Override
    public String solicitarEntrada(String mensaje) {
        System.out.print(mensaje + " -> ");
        return scanner.nextLine();
    }

    @Override
    public int solicitarEntero(String mensaje) {
        System.out.print(mensaje + " -> ");
        while (!scanner.hasNextInt()) {
            scanner.next(); // Si no se lee un entero no continúa normalmente el programa
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); 
        return numero;
    }
}
