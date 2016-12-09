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
    Lac.geradorBuffer.println("#define " + ctx.Ident().getText() + " " + visit(ctx.valor_constante()));
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
    String = (String) ctx.BasicTypes().getText();
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

      return tipo;
    }

  @Override
  public String visitDeclareTipo(LaParser.DeclareTipoContext ctx) {
    String tipo = (String)visit(ctx.tipo());

    Lac.geradorBuffer.println(tab() + tipo + " " + ctx.Ident().getText() + ";");

    return null;
  }

  @Override
  public String visitDeclareProcedure(LaParser.DeclareProcedureContext ctx) {
    String simboloId = (String)ctx.Ident().getText();

    Lac.geradorBuffer.println(tab() + "void " + simboloId + " (");
    for (LaParser.ParametroContext parametro : ctx.lista_parametros()) {
      Lac.geradorBuffer.println((String)visit(parametro.tipo_estendido() + " " + parametro.lista_identificador()));
    }
    Lac.geradorBuffer.println(") {");
    tabCount++;
    for (LaParser.Declaracao_localContext declaracao_local : ctx.declaracao_local()) {
      Lac.geradorBuffer.println(tab() + (String)visit(declaracao_local));
    }
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitDeclareFunction(LaParser.DeclareProcedureContext ctx) {
    String tipo = (String)visit(ctx.tipo_estendido());
    String simboloId = (String)ctx.Ident().getText();

    Lac.geradorBuffer.println(tab() + tipo + " " + simboloId + " (");
    for (LaParser.ParametroContext parametro : ctx.lista_parametros()) {
      Lac.geradorBuffer.println((String)visit(parametro.tipo_estendido() + " " + parametro.lista_identificador()));
    }
    Lac.geradorBuffer.println(") {");
    tabCount++;
    for (LaParser.Declaracao_localContext declaracao_local : ctx.declaracao_local()) {
      Lac.geradorBuffer.println(tab() + (String)visit(declaracao_local));
    }
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitDimensao(LaParser.DimensaoContext ctx) {
    return "["+ ctx.exp_aritmetica().getText() +"]";
  }

  @Override
  public String visitTipoReferencia(LaParser.TipoReferenciaContext ctx) {
    return ctx.tipo_basico_identificador().getText() + (ctx.Pointer() != null ? "":"*");
  }

  @Override
  public String visitTipoRegistro(LaParser.TipoRegistroContext ctx) {
    return null;
  }

  @Override
  public String visitParametro(LaParser.ParametroContext ctx) {
    String tipo = (String)visit(ctx.tipo_estendido());
    String lista_identificador = (String)visit(ctx.lista_identificador());

    Lac.geradorBuffer.println(tipo + lista_identificador);

    return null;
  }

  @Override
  public String visitIdentificador(LaParser.IdentificadorContext ctx) {
    return (ctx.Pointer() != null ? "":"*") + ctx.Ident.getText() + visit(ctx.lista_dimensao()) + ctx.sub_identificador().getText();
  }

  @Override
  public String visitValor_constante(LaParser.Valor_constanteContext ctx) {
    if(ctx.String() != null){
      return ctx.String().getText();
    } else
      if(ctx.Int() != null)
      {
        return ctx.Int().getText();
      } else
        if(ctx.Real() != null)
        {
          return ctx.Real().getText();
        } else
          if(ctx.True() != null)
          {
            return visit(ctx.True());
          } else
            if(ctx.False() != null)
            {
              return visit(ctx.False());
            }
  }

  @Override
  public String visitCmdRead(LaParser.CmdReadContext ctx) {
    ArrayList<Simbolo> listaIdentificadores = (ArrayList<Simbolo>) visit(ctx.lista_identificador());

    for( Simbolo s : listaIdentificadores ) {
      if (s.getTipo().equals("int")) {
        Lac.geradorBuffer.println("scanf(\"%d\",&" + s.getNome() + ");");
      }
      if (s.getTipo().equals("float")) {
        Lac.geradorBuffer.println("scanf(\"%f\",&" + s.getNome() + ");");
      }
    }

    return null;
  }

  @Override
  public String visitCmdWrite(LaParser.CmdWriteContext ctx) {
    return null;
  }

  @Override
  public String visitCmdIf(LaParser.CmdIfContext ctx) {
    String expressao = (String)visit(ctx.expressao());

    Lac.geradorBuffer.println("if (" + expressao + ") {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitSenao(LaParser.SenaoContext ctx) {

    Lac.geradorBuffer.println("else {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
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

    Lac.geradorBuffer.println(tab() + "for (" + simboloId + " = " + exp_aritmetica1 + "; " + simboloId + " <= " + exp_aritmetica2 + "; " + simboloId + "++) {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdWhile(LaParser.CmdWhileContext ctx) {
    String expressao = (String)visit(ctx.expressao());

    Lac.geradorBuffer.println(tab() + "while (" + expressao + ") {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdDo(LaParser.CmdDoContext ctx) {
    String expressao = (String)visit(ctx.expressao());

    Lac.geradorBuffer.println(tab() + "do {");
    tabCount++;
    for (ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + (String)visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "} (" + expressao + ");");

    return null;
  }

  @Override
  public String visitCmdAssign(LaParser.CmdAssignContext ctx) {
    return (ctx.Pointer() != null ? "":"*") + ctx.Ident().getText() + visit(ctx.lista_dimensao()) + visit(ctx.sub_identificador()) + " = " + (String)visit(ctx.expressao());
  }

  @Override
  public String visitCmdCall(LaParser.CmdCallContext ctx) {
    return ctx.Ident().getText() + "(" + visit(ctx.lista_expressao()) + ");";
  }

  @Override
  public String visitCmdReturn(LaParser.CmdReturnContext ctx) {
    return "return " + visit(ctx.expressao()) + ";";
  }

  @Override
  public String visitLista_variavel(LaParser.Lista_variavelContext ctx) {
    Lac.geradorBuffer.print(visit(ctx.tipo()) + " " + ctx.variavel_unica().getText());
    for (LaParser.Mais_variaveisContext mais_var : ctx.mais_variaveis()) {
      Simbolo simbolo = (Simbolo) visit(mais_var);
      Lac.geradorBuffer.print(", " + simbolo.getNome());
    }
    Lac.geradorBuffer.print(";");
    return null;
  }

  @Override
  public String visitParcelaLogicaTrue(LaParser.ParcelaLogicaTrueContext ctx) {
    return "true";
  }

  @Override
  public String visitParcelaLogicaFalse(LaParser.ParcelaLogicaFalseContext ctx) {
    return "false";
  }

  @Override
  public String visitEqualRelacional(LaParser.EqualRelacionalContext ctx) {
    return "==";
  }

  @Override
  public String visitOp_nao(LaParser.Op_naoContext ctx) {
    return "!";
  }

  @Override
  public String visitOutros_fatores_logicos(LaParser.Outros_fatores_logicosContext ctx) {
    return " && " + ctx.fator_logico().getText();
  }

  @Override
  public String visitOutros_termos_logicos(LaParser.Outros_termos_logicosContext ctx) {
    return " || " + ctx.termo_logico().getText();
  }
}
