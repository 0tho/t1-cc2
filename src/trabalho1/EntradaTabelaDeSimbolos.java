package trabalho1;

public class EntradaTabelaDeSimbolos extends Simbolo{
  private Boolean isParameter, isConstant;

  public EntradaTabelaDeSimbolos(String nome, String tipo, Boolean isParameter, Boolean isConstant) {
    super(nome, tipo);
    this.isParameter = isParameter;
    this.isConstant = isConstant;
  }

  public Boolean isParameter() {
    return isParameter;
  }

  public Boolean isConstant() {
    return isConstant;
  }

  @Override
  public String toString() {
    return nome+":"+ tipo + " Parametro:" + isParameter;
  }
}
