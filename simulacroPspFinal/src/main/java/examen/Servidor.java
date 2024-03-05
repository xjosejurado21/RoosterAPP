package examen;
//Clase Servidor
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

         // Recibir Pikachu del cliente
         Pikachu pikachu = (Pikachu) ois.readObject();

         // Modificar el Pikachu
         pikachu.setNombre("Raichu");
         pikachu.setObjeto(null); // El Pokémon pierde el objeto
         pikachu.setEstadisticas(pikachu.getEstadisticas() * 2); // Duplicar estadísticas

         // Mandar mensaje al cliente
         oos.writeObject("Tu Pokémon ha evolucionado a Raichu, ha perdido su objeto y sus estadísticas han aumentado.");

         // Enviar Pikachu modificado al cliente
         oos.writeObject(pikachu);

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
