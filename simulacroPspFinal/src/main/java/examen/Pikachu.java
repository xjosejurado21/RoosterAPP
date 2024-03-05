 package examen;

import java.io.Serializable;
//Clase Pikachu
public class Pikachu implements Serializable {
  private String nombre;
  private String objeto;
  private int estadisticas;

  public Pikachu(String nombre, String objeto, int estadisticas) {
      this.nombre = nombre;
      this.objeto = objeto;
      this.estadisticas = estadisticas;
  }

  public String getNombre() {
      return nombre;
  }

  public String getObjeto() {
      return objeto;
  }

  public int getEstadisticas() {
      return estadisticas;
  }

  public void setNombre(String nombre) {
      this.nombre = nombre;
  }

  public void setObjeto(String objeto) {
      this.objeto = objeto;
  }

  public void setEstadisticas(int estadisticas) {
      this.estadisticas = estadisticas;
  }
}
