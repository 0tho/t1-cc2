package trabalho1;

public class LaCGeneratorVisitor extends LaParserBaseVisitor<String> {
  public LaCGeneratorVisitor() {
    super();
  }

  private int tabCount = 0;

  private String tab() {
    String tabs = "";
    for (int i = 0; i < tabCount; i++ ) {
      tabs += "\t";
    }
    return tabs;
  }

  @Override
  public String visitPrograma(LaParser.ProgramaContext ctx) {
    Lac.geradorBuffer.println(tab() + "#include <stdio.h>");
    Lac.geradorBuffer.println(tab() + "#include <stdlib.h>");
    Lac.geradorBuffer.println(tab() + "");
    Lac.geradorBuffer.println(tab() + "int main() {");
    tabCount++;
    visitChildren(ctx);
    Lac.geradorBuffer.println(tab() + "return 0;");
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");
    return null;
  }

  @Override
  public String visitDeclareConstante(LaParser.DeclareConstanteContext ctx) {
    String simboloId = ctx.Ident().getText();
    Lac.geradorBuffer.println("#define " + simboloId + (String) visit(ctx.valor_constante));
    return null;
  }

  @Override
  public String visitDeclareVariavel(LaParser.DeclareVariavelContext ctx) {
    ArrayList<Simbolo> simbolos = (ArrayList<Simbolo>) visit(ctx.lista_variavel());

    for( Simbolo s: simbolos ) {
      Lac.geradorBuffer.println(tab() + s.getTipo() + " " + s.getNome() + ";");
    }

    return null;
  }

  @Override
  public String visitDimensao(LaParser.DimensaoContext ctx) {
    Lac.geradorBuffer.print("["+ (String) visit(ctx.exp_aritmetica()) +"]");
    return null;
  }

  @Override
  public String visitTipoReferencia(LaParser.TipoReferenciaContext ctx) {
    Lac.geradorBuffer.println((String) visitChildren(ctx) + (ctx.Pointer() != null ? "":"* ")  + ctx.tipo_basico_identificador().getText());
    return null;
  }

  
}
