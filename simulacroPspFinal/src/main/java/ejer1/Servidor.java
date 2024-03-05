package ejer1;
import java.io.*;
import java.net.*;
import java.security.*;

public class Servidor {
    private static final String CONTRASEÑA_ALMACENADA = "cenec";

    public static void main(String[] args) {
        try {
            ServerSocket servidorSocket = new ServerSocket(6000);
            System.out.println("Servidor esperando conexión...");

            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            salida.println("Ingrese la contraseña:");
            String contraseñaCliente = entrada.readLine();

            String contraseñaEncriptadaCliente = encriptarSHA256(contraseñaCliente);

            if (contraseñaEncriptadaCliente.equals(encriptarSHA256(CONTRASEÑA_ALMACENADA))) {
                salida.println("Acceso permitido");
            } else {
                salida.println("Acceso denegado");
            }

            clienteSocket.close();
            servidorSocket.close();
        } catch (IOException | NoSuchAlgorithmException e) {
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
