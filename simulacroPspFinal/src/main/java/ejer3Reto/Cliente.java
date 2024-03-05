package ejer3Reto;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 61000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Solicitar contraseña al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese la contraseña:");
            String contraseña = scanner.nextLine();

            // Enviar contraseña al servidor
            oos.writeObject(contraseña);

            // Recibir respuesta del servidor
            String respuestaServidor = (String) ois.readObject();
            System.out.println(respuestaServidor);

            if (respuestaServidor.equals("Acceso permitido")) {
                // Si la contraseña es correcta, crear objeto Pikachu y enviar al servidor
                Pikachu pikachu = new Pikachu();
                oos.writeObject(pikachu);

                // Recibir y mostrar mensajes del servidor
                String mensajeServidor;
                while ((mensajeServidor = (String) ois.readObject()) != null) {
                    System.out.println(mensajeServidor);
                }
            }

            // Cerrar conexiones
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
