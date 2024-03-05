package ejer3Reto;
import java.io.*;
import java.net.*;
import java.security.*;

public class Servidor {
    private static final String CONTRASEÑA_ALMACENADA = "cenec";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            ServerSocket servidorSocket = new ServerSocket(61000);
            System.out.println("Servidor esperando conexión...");

            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado.");

            ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream());

            // Recibir contraseña del cliente
            String contraseñaCliente = (String) ois.readObject();

            // Verificar contraseña
            String contraseñaEncriptadaCliente = encriptarSHA256(contraseñaCliente);
            if (contraseñaEncriptadaCliente.equals(encriptarSHA256(CONTRASEÑA_ALMACENADA))) {
                oos.writeObject("Acceso permitido");

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
            } else {
                // Si la contraseña es incorrecta, enviar mensaje de "Acceso denegado"
                oos.writeObject("Acceso denegado");
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

    private static String encriptarSHA256(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(contraseña.getBytes());
        return Hexadecimal(hash);
    }

    private static String Hexadecimal(byte[] resumen) {
        StringBuilder hex = new StringBuilder();
        for (byte b : resumen) {
            String h = Integer.toHexString(b & 0xFF);
            if (h.length() == 1)
                hex.append("0");
            hex.append(h);
        }
        return hex.toString().toUpperCase();
    }
}
