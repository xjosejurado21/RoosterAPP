// Clase Cliente
package examen;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 61000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Solicitar datos del Pokémon al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del Pokémon:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el objeto del Pokémon:");
            String objeto = scanner.nextLine();
            System.out.println("Ingrese las estadísticas del Pokémon:");
            int estadisticas = scanner.nextInt();

            // Crear objeto Pikachu con los datos proporcionados
            Pikachu pikachu = new Pikachu(nombre, objeto, estadisticas);

            // Enviar el Pikachu al servidor
            oos.writeObject(pikachu);

            // Recibir y mostrar mensajes del servidor
            String mensajeServidor = (String) ois.readObject();
            System.out.println(mensajeServidor);

            // Recibir el Pikachu modificado del servidor
            Pikachu pikachuModificado = (Pikachu) ois.readObject();
            System.out.println("Pokémon evolucionado recibido: ");
            System.out.println("Nombre: " + pikachuModificado.getNombre());
            System.out.println("Objeto: " + pikachuModificado.getObjeto());
            System.out.println("Estadísticas: " + pikachuModificado.getEstadisticas());

            // Cerrar conexiones
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
