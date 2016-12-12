package trabalho1;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static trabalho1.LaSemanticVisitor.*;

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
    // Pega constantes

    for( LaParser.DeclaracaoContext declaracao : ctx.declaracao()) {
      visit(declaracao.declaracao_local());
    }

    Lac.geradorBuffer.println(tab() + "");
    Lac.geradorBuffer.println(tab() + "int main() {");
    tabCount++;
    // Pega variaveis
    ArrayList<Simbolo> variaveis = pegaSimbolosDoTipo( LacClass.VARIAVEL );
    //Lac.geradorBuffer.println(Arrays.toString(variaveis.toArray()));
    // Pega procedimentos
    ArrayList<Simbolo> procedimentos = pegaSimbolosDoTipo( LacClass.PROCEDIMENTO );
    //Lac.geradorBuffer.println(Arrays.toString(procedimentos.toArray()));
    // Pega funcoes
    ArrayList<Simbolo> funcoes = pegaSimbolosDoTipo( LacClass.FUNCAO );
    //Lac.geradorBuffer.println(Arrays.toString(funcoes.toArray()));

    visitChildren(ctx.corpo());
    Lac.geradorBuffer.println(tab() + "return 0;");
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");
    return null;
  }


  @Override
  public String visitDeclareConstante(LaParser.DeclareConstanteContext ctx) {
    Lac.geradorBuffer.println("#define " + ctx.Ident().getText() + " " + ctx.valor_constante().getText());
    return null;
  }

  @Override
  public String visitDeclareVariavel(LaParser.DeclareVariavelContext ctx) {
    String maisVariaveis = "";
    for (LaParser.Mais_variaveisContext mais_var : ctx.lista_variavel().mais_variaveis()) {
      maisVariaveis += ", " + mais_var.variavel_unica().getText();
    }
    Lac.geradorBuffer.println(tab() + getTipoStr(ctx.lista_variavel().tipo().getText()) + " " + ctx.lista_variavel().variavel_unica().getText() + maisVariaveis + (getTipoStr(ctx.lista_variavel().tipo().getText()).equals("char") ? "[80]": "") + ";");
    return null;
  }

  private String getTipoStr(String tipo) {
    switch (tipo) {
        case "inteiro":
            tipo = "int";
            break;
        case "real":
            tipo = "float";
            break;
        case "logico":
            tipo = "bool";
            break;
        case "literal":
            tipo = "char";
            break;
    }
    return tipo;
  }

  private String getTipoParamStr(String tipo) {
    switch (tipo) {
        case "inteiro":
            tipo = "d";
            break;
        case "real":
            tipo = "f";
            break;
        case "logico":
            tipo = "d";
            break;
        case "literal":
            tipo = "s";
            break;
    }
    return tipo;
  }

  @Override
  public String visitTipo_basico(LaParser.Tipo_basicoContext ctx) {
    return getTipoStr(ctx.BasicTypes().getText());
  }

  @Override
  public String visitDeclareTipo(LaParser.DeclareTipoContext ctx) {
    String tipo = (String)visit(ctx.tipo());

    Lac.geradorBuffer.println(tab() + tipo + " " + ctx.Ident().getText() + "\n");

    return null;
  }

  @Override
  public String visitDeclareProcedure(LaParser.DeclareProcedureContext ctx) {
    String simboloId = (String)ctx.Ident().getText();

    String declaration = tab() + "void " + simboloId + " (";
    LaParser.ParametroContext parametro = ctx.lista_parametros().parametro();
    declaration+= (String)visit(parametro.tipo_estendido()) + " " + parametro.lista_identificador();
    for (LaParser.Mais_parametroContext mais_parametro : ctx.lista_parametros().mais_parametro()) {
      declaration+= (String)visit(mais_parametro.parametro().tipo_estendido()) + " " + mais_parametro.parametro().getText();
    }
    declaration+= ") {";
    Lac.geradorBuffer.println(declaration);
    tabCount++;
    for (LaParser.Declaracao_localContext declaracao_local : ctx.declaracao_local()) {
      Lac.geradorBuffer.println(tab() + visit(declaracao_local));
    }
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitDeclareFunction(LaParser.DeclareFunctionContext ctx) {
    String tipo = visit(ctx.tipo_estendido());
    String simboloId = (String)ctx.Ident().getText();

    String declaration = tab() + tipo + " " + simboloId + " (";
    LaParser.ParametroContext parametro = ctx.lista_parametros().parametro();
    declaration+= (String)visit(parametro.tipo_estendido()) + " " + parametro.lista_identificador();
    for (LaParser.Mais_parametroContext mais_parametro : ctx.lista_parametros().mais_parametro()) {
      declaration+= (String)visit(mais_parametro.parametro().tipo_estendido()) + " " + mais_parametro.parametro().getText();
    }
    declaration+= ") {";
    Lac.geradorBuffer.println(declaration);
    tabCount++;
    for (LaParser.Declaracao_localContext declaracao_local : ctx.declaracao_local()) {
      Lac.geradorBuffer.println(tab() + visit(declaracao_local));
    }
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitLista_dimensao(LaParser.Lista_dimensaoContext ctx) {
    String dimensao = "";
    for(LaParser.DimensaoContext dim : ctx.dimensao())
      dimensao += "["+ dim.exp_aritmetica().getText() +"]";
    return dimensao;
  }

  @Override
  public String visitTipoReferencia(LaParser.TipoReferenciaContext ctx) {

    return ctx.tipo_estendido().tipo_basico_identificador().getText() + (ctx.tipo_estendido().Pointer() != null ? "*" : "");
  }

  @Override
  public String visitTipoRegistro(LaParser.TipoRegistroContext ctx) {
    return null;
  }

  @Override
  public String visitParametro(LaParser.ParametroContext ctx) {
    String tipo = ctx.tipo_estendido().getText();
    String lista_identificador = (String)visit(ctx.lista_identificador());

    Lac.geradorBuffer.println(tipo + lista_identificador);

    return null;
  }

  @Override
  public String visitIdentificador(LaParser.IdentificadorContext ctx) {
    String sub_identificador = "";
    for( LaParser.Sub_identificadorContext sub : ctx.sub_identificador()) {
      sub_identificador += visit(sub);
    }
    return (ctx.Pointer() != null ? "*" : "") + ctx.Ident().getText() + visit(ctx.lista_dimensao()) + sub_identificador;
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
    return null;
  }

  @Override
  public String visitCmdRead(LaParser.CmdReadContext ctx) {
    Simbolo variable = Lac.semantic.pegarSimbolo(ctx.lista_identificador().identificador().Ident().getText());
    if (getTipoStr(variable.getTipo()).equals("char")) {
      Lac.geradorBuffer.println(tab() + "gets(" + variable.getNome() + ");");
    }
    else
    {
      Lac.geradorBuffer.println(tab() + "scanf(\"%"+ getTipoParamStr(variable.getTipo()) + "\", &"+ variable.getNome() + ");");
    }

    for( LaParser.Mais_identificadorContext id : ctx.lista_identificador().mais_identificador()) {
      //Lac.geradorBuffer.println(id.identificador().Ident().getText());
      variable = Lac.semantic.pegarSimbolo(id.getText());
      //Lac.geradorBuffer.println(variable.getNome());
    }
    //Lac.geradorBuffer.println(variable.getNome());
    return null;
  }

  @Override
  public String visitCmdWrite(LaParser.CmdWriteContext ctx) {
    Simbolo simbolo = null;
    String outrosTermos = "";
    String cadeia = "";
    String tipos = "";
    String expressoes = "";
    if (ctx.lista_expressao().expressao().outros_termos_logicos() == null) {
      simbolo = Lac.semantic.pegarSimbolo(ctx.lista_expressao().expressao().getText());
    } else {
      for(LaParser.Outros_termos_logicosContext other : ctx.lista_expressao().expressao().outros_termos_logicos()) {
        outrosTermos += visit(other);
      }
    }
    if (simbolo == null) {
      expressoes = ctx.lista_expressao().expressao().getText() + outrosTermos;
      tipos += "%" + getTipoParamStr(Lac.semantic.getType((ctx.lista_expressao().expressao())).getNome());
    }
    else {
      if (simbolo.getNome() == "ERRO") {
        cadeia = ctx.lista_expressao().expressao().getText();
      }
      else {
        expressoes += simbolo.getNome();
        tipos += "%" + getTipoParamStr(simbolo.getTipo());
      }
    }

    for( LaParser.Mais_expressaoContext mais_exp : ctx.lista_expressao().mais_expressao()) {
      Simbolo exp = Lac.semantic.pegarSimbolo(mais_exp.expressao().getText());
      expressoes += "," + exp.getNome();
      tipos += "%" + getTipoParamStr(exp.getTipo());
    }

    if (!cadeia.isEmpty()) {
      cadeia = cadeia.substring(0, cadeia.length() -1);
    } else {
      tipos = "\"" + tipos;
    }
    if (!tipos.isEmpty()) {
      tipos += "\",";
    }
    Lac.geradorBuffer.println(tab() + "printf(" + cadeia + tipos + expressoes + ");");
    return null;
  }

  @Override
  public String visitCmdIf(LaParser.CmdIfContext ctx) {
    Lac.geradorBuffer.println(tab() + "if (" + ctx.expressao().getText() + ") {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    if (ctx.senao() != null) {
      Lac.geradorBuffer.println(tab() + " else {");
      tabCount++;
      Lac.geradorBuffer.println(tab() + (String)visit(ctx.senao()));
      tabCount--;
      Lac.geradorBuffer.println(tab() + "}");
    }

    return null;
  }

  @Override
  public String visitSenao(LaParser.SenaoContext ctx) {
    //Lac.geradorBuffer.println("else {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitCmdCase(LaParser.CmdCaseContext ctx) {
    Lac.geradorBuffer.println(tab() + "switch (" + ctx.exp_aritmetica().getText() + ") {");
    tabCount++;
    Lac.geradorBuffer.println(tab() + (String)visit(ctx.selecao()));
    if (ctx.senao() != null) {
      Lac.geradorBuffer.println(tab() + "default:");
      tabCount++;
      Lac.geradorBuffer.println(tab() + (String)visit(ctx.senao()));
      tabCount--;
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");

    return null;
  }

  @Override
  public String visitSelecao(LaParser.SelecaoContext ctx) {
    if (ctx.constantes().getText().contains("..")) {
      String caseCondition = ctx.constantes().getText();

      int ini = Integer.valueOf(caseCondition.substring(0, caseCondition.indexOf("..")));
      int fim = Integer.valueOf(caseCondition.substring(caseCondition.indexOf("..") + 2));
      for (int i = ini; i <= fim ;i++ ) {
        Lac.geradorBuffer.println(tab() + "case " + i + ":");
      }
    }
    else {
      Lac.geradorBuffer.println(tab() + "case " + ctx.constantes().getText() + ":");
    }

    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    Lac.geradorBuffer.println(tab() + "break;");
    tabCount--;

    for (LaParser.SelecaoContext selecao : ctx.selecao()) {
      Lac.geradorBuffer.println(visit(selecao));
    }

    return null;
  }

  @Override
  public String visitCmdFor(LaParser.CmdForContext ctx) {
    String simboloId = (String)ctx.Ident().getText();
    String exp_aritmetica1 = ctx.exp_aritmetica1().getText();
    String exp_aritmetica2 = ctx.exp_aritmetica2().getText();

    Lac.geradorBuffer.println(tab() + "for (" + simboloId + " = " + exp_aritmetica1 + "; " + simboloId + " <= " + exp_aritmetica2 + "; " + simboloId + "++) {");
    tabCount++;
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
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
      Lac.geradorBuffer.println(tab() + visit(comando));
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
    for (LaParser.ComandoContext comando : ctx.comando()) {
      Lac.geradorBuffer.println(tab() + visit(comando));
    }
    tabCount--;
    Lac.geradorBuffer.println(tab() + "} while (" + expressao + ");");

    return null;
  }

  @Override
  public String visitCmdAssign(LaParser.CmdAssignContext ctx) {
    String sub_identificador = "";
    for( LaParser.Sub_identificadorContext sub : ctx.sub_identificador()) {
      sub_identificador += visit(sub);
    }
    Lac.geradorBuffer.println(tab() + (ctx.Pointer() != null ? "*":"") + ctx.Ident().getText() + visit(ctx.lista_dimensao()) + sub_identificador + " = " + ctx.expressao().getText() + ";");
    return null;
  }

  @Override
  public String visitOp_adicao(LaParser.Op_adicaoContext ctx) {
    return " " + ctx.getText() + " ";
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
    String variaveis = visit(ctx.tipo()) + " " + ctx.variavel_unica().getText();
    for (LaParser.Mais_variaveisContext mais_var : ctx.mais_variaveis()) {
      variaveis += ", " + mais_var.getText();
    }
    Lac.geradorBuffer.println(variaveis);
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
  public String visitOp_relacional(LaParser.Op_relacionalContext ctx) {
    Lac.geradorBuffer.println(ctx.getText());
    if (ctx.Equal() != null) {
      Lac.geradorBuffer.println("==");
    }
    else {
      Lac.geradorBuffer.println(ctx.getText());
    }
    return null;
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

  @Override
  public String visitParcelaUnarioVariavel(LaParser.ParcelaUnarioVariavelContext ctx) {
    String sub_identificador = "";
    for( LaParser.Sub_identificadorContext sub : ctx.sub_identificador()) {
      sub_identificador += visit(sub);
    }

    String lista_dimensao = (String)visit(ctx.lista_dimensao());
    return (ctx.Pointer() != null ? "*" : "") + ctx.Ident().getText() + sub_identificador + lista_dimensao;
  }
}
