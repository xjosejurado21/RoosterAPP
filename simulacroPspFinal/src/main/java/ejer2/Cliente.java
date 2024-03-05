package ejer2;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 61000);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Crear objeto Pikachu
            Pikachu pikachu = new Pikachu();

            // Enviar objeto Pikachu al servidor
            oos.writeObject(pikachu);

            // Recibir mensaje del servidor
            String mensajeServidor = (String) ois.readObject();
            System.out.println(mensajeServidor);

            // Leer respuesta del usuario
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String respuesta = reader.readLine();

            // Enviar respuesta al servidor
            oos.writeObject(respuesta);

            // Recibir respuesta del servidor
            Object respuestaServidor = ois.readObject();
            if (respuestaServidor instanceof Pikachu) {
                Pikachu pikachuEvolucionado = (Pikachu) respuestaServidor;
                System.out.println("Nombre del Pikachu evolucionado: " + pikachuEvolucionado.getNombre());
                String mensajeEvolucion = (String) ois.readObject();
                System.out.println(mensajeEvolucion);
            } else {
                System.out.println((String) respuestaServidor);
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
