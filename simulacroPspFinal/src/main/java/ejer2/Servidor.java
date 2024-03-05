package ejer2;
import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidorSocket = new ServerSocket(61000);
            System.out.println("Servidor esperando conexión...");

            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado.");

            ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());

            // Recibir objeto Pikachu del cliente
            Pikachu pikachu = (Pikachu) ois.readObject();
            System.out.println("Pikachu recibido con nombre: " + pikachu.getNombre());

            // Mandar mensaje al cliente
            oos.writeObject("¿Quieres usar piedratrueno en tu " + pikachu.getNombre() + "? (Y/N)");

            // Recibir respuesta del cliente
            String respuesta = (String) ois.readObject();

            if (respuesta.equalsIgnoreCase("Y")) {
                // Si el cliente responde "Y", evolucionar Pikachu a Raichu
                pikachu.setNombre("Raichu");
                oos.writeObject(pikachu);
                oos.writeObject("Felicidades, tu Pikachu ha evolucionado a Raichu");
            } else if (respuesta.equalsIgnoreCase("N")) {
                // Si el cliente responde "N", enviar mensaje de "Qué lástima."
                oos.writeObject("Qué lástima.");
            }

            // Cerrar conexiones
            ois.close();
            oos.close();
            clienteSocket.close();
            servidorSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
