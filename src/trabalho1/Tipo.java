package trabalho1;

import java.util.ArrayList;

public class Tipo {
  private String nome;
  private ArrayList<Simbolo> simbolos;

  public Tipo(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public boolean isSimple() {
    return simbolos.isEmpty();
  }

  public void addSimbolo(Simbolo simbolo) {
    if( simbolo.getTipo().isSimple()) {
      simbolos.add(simbolo);
    } else {
      throw new Error("Registros só podem conter tipos simples");
    }
  }

  public boolean hasSimbolo(String nome) {
    for( Simbolo s: simbolos) {
      if (s.getNome() == nome ) {
        return true;
      }
    }
    return false;
  }

}
