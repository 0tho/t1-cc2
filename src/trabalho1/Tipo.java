package trabalho1;

import java.util.ArrayList;

public class Tipo {
  private String nome;
  private ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
  private String referencia = null;

  public Tipo(String nome) {
    this.nome = nome;
  }

  public Tipo(String nome, String referencia) {
    this(nome);
    this.referencia = referencia;
  }

  public String getNome() {
    return nome;
  }

  public String getReferencia() {
    return referencia;
  }

  public boolean isSimple() {
    return simbolos.isEmpty();
  }

  public void addSimbolo(Simbolo simbolo) {
    if( referencia == null ) {
      simbolos.add(simbolo);
    } else {
      throw new Error("Referencias a outros tipos não podem conter subvalores");
    }
  }

  public boolean temSimbolo(String nome) {
    for( Simbolo s: simbolos) {
      if (s.getNome().equals(nome) ) {
        return true;
      }
    }
    return false;
  }

  public Simbolo getSimbolo(String nome) {
    for( Simbolo s: simbolos) {
      if (s.getNome().equals(nome) ) {
        return s;
      }
    }
    return null;
  }

  public ArrayList<Simbolo> getSimbolos () {
    return simbolos;
  }

  @Override
  public String toString() {
    return "TIPO: " + getNome() + (referencia != null ? referencia : "");
  }

}
