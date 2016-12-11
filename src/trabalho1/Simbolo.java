package trabalho1;

import java.util.ArrayList;

public class Simbolo {
  private String nome;
  private String tipo;
  private LacClass classe;
  private int linha;
  private ArrayList<Simbolo> simbolos;
  private ArrayList<Integer> dimensoes;

  public Simbolo (String nome, String tipo, int linha, LacClass classe ) {
    this.nome = nome;
    this.tipo = tipo;
    this.classe = classe;
    this.linha = linha;
    this.simbolos = new ArrayList<Simbolo>();
    this.dimensoes = new ArrayList<Integer>();
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

  public void addSimbolos(ArrayList<Simbolo> simbolos) {
    this.simbolos.addAll(simbolos);
  }

  public boolean temSimbolo(Simbolo simbolo) {
    for( Simbolo  sim : simbolos ) {
      if ( simbolo.getNome() == sim.getNome() ) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Simbolo> getSimbolos() {
    return simbolos;
  }

  public ArrayList<Integer> getDimensoes() {
    return dimensoes;
  }

  public void setTipo( String tipo ) {
    this.tipo = tipo;
  }

  public void setClasse( LacClass classe ) {
    this.classe = classe;
  }

  public void setDimensoes( ArrayList<Integer> dimensoes ) {
    this.dimensoes = dimensoes;
  }

  @Override
  public String toString() {
    return nome + " " + tipo + " " + linha + " " + classe;
  }
}
