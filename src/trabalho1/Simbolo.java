package trabalho1;

public class Simbolo {
  private String nome;
  private String tipo;
  private LacClass classe;
  private int linha;

  public Simbolo (String nome, String tipo, int linha, LacClass classe ) {
    this.nome = nome;
    this.tipo = tipo;
    this.classe = classe;
    this.linha = linha;
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
}
