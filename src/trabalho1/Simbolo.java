package trabalho1;

public class Simbolo {
  private String nome;
  private Tipo tipo;

  public Simbolo (String nome, Tipo tipo) {
    this.nome = nome;
    this.tipo = tipo;
  }

  public String getNome() {
    return nome;
  }

  public Tipo getTipo() {
    return tipo;
  }
}
