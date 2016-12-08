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
  public String visitTipo_basico(LaParser.DeclareTipo_basicoContext ctx) {
        switch (tipo) {
            case "inteiro":
                tipo = "int";
                break;
            case "real":
                tipo = "float";
                break;
            case "logico":
                tipo = "int";
                break;
            case "literal":
                tipo = "char";
                break;
            default:
                tipo = "";
        }

      return tipo
    }

  @Override
  public String visitDeclareTipo(LaParser.DeclareTipoContext ctx) {
    String simboloId = ctx.Ident().getText();
    String tipo = (String)visit(ctx.tipo());

    Lac.geradorBuffer.println(tipo + " " + simboloId + ";");

    return null
  }

  @Override
  public String visitDeclareProcedure(LaParser.DeclareProcedureContext ctx) {
    String simboloId = (String)ctx.Ident().getText();
    String parametros = (String)visit(ctx.lista_parametros());
    String declaracao_local = (String)visit(ctx.declaracao_local());
    String comando = (String)visit(ctx.comando());

    Lac.geradorBuffer.println(tab() + "void " + simboloId + " " + (String)ctx.LeftParen().getText() + parametros + (String)ctx.RightParen().getText() + " {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + declaracao_local + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitDeclareFunction(LaParser.DeclareProcedureContext ctx) {
    String tipo = (String)visit(ctx.tipo_estendido());
    String simboloId = (String)ctx.Ident().getText();
    String parametros = (String)visit(ctx.lista_parametros());
    String declaracao_local = (String)visit(ctx.declaracao_local());
    String comando = (String)visit(ctx.comando());

    Lac.geradorBuffer.println(tab() + tipo + " " + simboloId + " " + (String)ctx.LeftParen().getText() + parametros + (String)ctx.RightParen().getText() + " {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + declaracao_local + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

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

  @Override
  public String visitParametro(LaParser.ParametroContext ctx) {
    String tipo = (String)visit(ctx.tipo_estendido());
    String lista_identificador = (String)visit(ctx.lista_identificador());

    Lac.geradorBuffer.println(tipo + lista_identificador);

    return null;
  }

  private static String makeType(String type) {
    switch (type) {
        case "inteiro":
            return = "%d";
        case "real":
            return = "%f";
        case "logico":
            return = "%d";
        case "literal":
            return = "%s";
        default:
            return = "";
    }
  }

  @Override
  public String visitCmdRead(LaParser.CmdReadContext ctx) {
    Lac.geradorBuffer.println(tab() + "scanf(\"" + + "\",&" + + ");");

    return null;
  }

  @Override
  public String visitCmdWrite(LaParser.CmdWriteContext ctx) {
    return null;
  }

  @Override
  public String visitCmdIf(LaParser.CmdIfContext ctx) {
    String expressao = (String)visit(ctx.expressao());
    String comando = (String)visit(ctx.comando());

    Lac.geradorBuffer.println(tab() + "if (" + expressao + ") {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitSenao(LaParser.SenaoContext ctx) {
    String comando = (String)visit(ctx.comando());
    
    Lac.geradorBuffer.println(tab() + "else {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdCase(LaParser.CmdCaseContext ctx) {
    return null;
  }

  @Override
  public String visitCmdFor(LaParser.CmdForContext ctx) {
    String simboloId = (String)ctx.Ident().getText();
    String exp_aritmetica1 = (String)visit(ctx.exp_aritmetica1());
    String exp_aritmetica2 = (String)visit(ctx.exp_aritmetica2());

    Lac.geradorBuffer.println(tab() + "for (" + simboloId "=" exp_aritmetica1 + "; " + simboloId + "<=" + exp_aritmetica2 + "; " + simboloId + "++) {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdWhile(LaParser.CmdWhileContext ctx) {
    String expressao = (String)visit(ctx.expressao());
    String comando = (String)visit(ctx.comando());

    Lac.geradorBuffer.println(tab() + "while (" + expressao + ") {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdDo(LaParser.CmdDoContext ctx) {
    String expressao = (String)visit(ctx.expressao());
    String comando = (String)visit(ctx.comando());

    Lac.geradorBuffer.println(tab() + "do {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + comando);
    tabCount--;
    Lac.geradorBuffer.println(tab() + "} (" + expressao + ");");

    return null;
  }

  @Override
  public String visitCmdAssign(LaParser.CmdAssignContext ctx) {
    return null;
  }

  @Override
  public String visitCmdCall(LaParser.CmdCallContext ctx) {
    return null;
  }

  @Override
  public String visitCmdReturn(LaParser.CmdReturnContext ctx) {
    return null;
  }
}
