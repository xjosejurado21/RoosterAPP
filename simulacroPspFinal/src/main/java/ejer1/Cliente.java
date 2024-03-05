package ejer1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println(entrada.readLine());
            String contraseña = scanner.nextLine();
            salida.println(contraseña);

            System.out.println(entrada.readLine());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
