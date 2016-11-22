package trabalho1;

import java.util.ArrayList;

public class Simbolo {
  private String nome;
  private String tipo;
  private LacClass classe;
  private int linha;
  private ArrayList<Simbolo> simbolos;

  public Simbolo (String nome, String tipo, int linha, LacClass classe ) {
    this.nome = nome;
    this.tipo = tipo;
    this.classe = classe;
    this.linha = linha;
    this.simbolos = new ArrayList<Simbolo>();
  }

  public Simbolo (String nome, String tipo, int linha) {
    this( nome, tipo, linha, LacClass.VARIAVEL );
  }

  public String getNome() {
    return nome;
  }

  public String getTipo() {
    return tipo;
  }

  public LacClass getClasse() {
    return classe;
  }

  public int getLinha() {
    return linha;
  }

  public void addSimbolo(Simbolo simbolo) {
    simbolos.add(simbolo);
  }

  public ArrayList<Simbolo> getSimbolos() {
    return simbolos;
  }
}
