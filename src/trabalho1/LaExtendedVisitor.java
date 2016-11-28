package trabalho1;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import org.antlr.v4.runtime.Token;

public class LaExtendedVisitor extends LaParserBaseVisitor<Object> {

  // Uso de caracter não reconhecido pela linguagem como flag de autoGerado
  private static final String AUTOGENERATED_FLAG = "@";

  private HashMap<String, Tipo> tipos;
  private LinkedList<HashMap<String, Simbolo>> pilha;

  private int autoGeneratedTypes;
  private boolean scopeCanReturn;

  public LaExtendedVisitor() {
    super();
    tipos = new HashMap<String, Tipo>();
    pilha = new LinkedList<HashMap<String, Simbolo>>();

    autoGeneratedTypes = 0;
    scopeCanReturn = false;
  }

  private boolean pilhaContemSimbolo(String simboloId) {
    for( HashMap<String, Simbolo> tabela : pilha ) {
      if ( tabela.containsKey(simboloId) ) {
        return true;
      }
    }
    return false;
  }

  private void checarSePilhaContemSimbolo(String ident, int linha) {
    if( !pilhaContemSimbolo(ident) ) {
      Lac.errorBuffer.println(Mensagens.erroIdentificadorNaoDeclarado(linha , ident ));
    }
  }

  private void checarSePilhaContemSimbolo(Token ident) {
    checarSePilhaContemSimbolo(ident.getText(), ident.getLine());
  }

  private void checarSePilhaContemSimbolo(Simbolo simbolo) {
    checarSePilhaContemSimbolo(simbolo.getNome(), simbolo.getLinha());
  }

  private void adicionarSimbolos(List<Simbolo> novosSimbolos) {
    for(Simbolo s: novosSimbolos) {
      adicionarSimbolo(s);
    }
  }

  private void adicionarSimbolo(Simbolo simbolo) {
    if ( !pilhaContemSimbolo(simbolo.getNome() )) {
      pilha.peek().put(simbolo.getNome(), simbolo);
    } else {
      Lac.errorBuffer.println(Mensagens.erroIdentificadorJaDeclarado( simbolo.getLinha(), simbolo.getNome() ));
    }
  }

  @Override
  public Void visitPrograma(LaParser.ProgramaContext ctx) {
    tipos.put("literal", new Tipo("literal"));
    tipos.put("inteiro", new Tipo("inteiro"));
    tipos.put("real", new Tipo("real"));
    tipos.put("logico", new Tipo("logico"));

    // Adiciona tabela de escopo global na pilha de tabelas
    pilha.push( new HashMap<String, Simbolo>() );

    visitChildren(ctx);
    return null;
  }

  @Override
  public Void visitDeclareConstante(LaParser.DeclareConstanteContext ctx) {
    String simboloId = ctx.Ident().getText();
    int linha = ctx.Ident().getSymbol().getLine();
    String tipo = ctx.tipo_basico().getText();

    adicionarSimbolo(new Simbolo(simboloId, tipo, linha, LacClass.CONSTANTE));
    visitChildren(ctx);
    return null;
  }
  @Override
  public Void visitDeclareVariavel(LaParser.DeclareVariavelContext ctx) {
    ArrayList<Simbolo> simbolos = (ArrayList<Simbolo>) visit(ctx.lista_variavel());

    for( Simbolo s: simbolos ) {
      adicionarSimbolo(s);
    }

    return null;
  }

  @Override
  public Void visitDeclareTipo(LaParser.DeclareTipoContext ctx) {
    Token tipoTk = ctx.Ident().getSymbol();
    String tipoId = tipoTk.getText();
    int linha = tipoTk.getLine();
    Object tipoVisit = visit(ctx.tipo());

    if ( tipoVisit instanceof String ) {
      String tipoVisitId = (String) tipoVisit;
      String tipoSemPonteiro = tipoVisitId.replace("^", "");
      if( tipos.containsKey ( tipoSemPonteiro ) ) {
        Tipo novoTipo = new Tipo(tipoId, tipoVisitId);
        tipos.put(tipoId, novoTipo);

        // Adiciona na tabela de simbolos o novo tipo
        adicionarSimbolo(new Simbolo(tipoId, tipoVisitId, linha, LacClass.TIPO));
      } else {
        Lac.errorBuffer.println(Mensagens.erroTipoNaoDeclarado( linha, tipoId ));
      }
    } else if ( tipoVisit instanceof List ) {
      Tipo novoTipo = new Tipo(tipoId);
      tipos.put(tipoId, novoTipo);

      // Adiciona na tabela de simbolos o novo tipo
      adicionarSimbolo(new Simbolo(tipoId, tipoId, linha, LacClass.TIPO));

      List<Simbolo> simbolosTipo = (List<Simbolo>) tipoVisit;
      for( Simbolo simbolo : simbolosTipo) {
        novoTipo.addSimbolo( simbolo );
      }
    }

    return null;
  }

  @Override
  public Object visitTipoRegistro(LaParser.TipoRegistroContext ctx) {
    return visit(ctx.registro());
  }

  @Override
  public Object visitRegistro(LaParser.RegistroContext ctx) {
    ArrayList<Simbolo> registroSimbolos = new ArrayList<Simbolo>();

    for (LaParser.Lista_variavelContext variavel : ctx.lista_variavel()) {
      List<Simbolo> simbolos = (List<Simbolo>) visit(variavel);
      registroSimbolos.addAll(simbolos);
    }

    return registroSimbolos;
  }

  @Override
  public Object visitTipoReferencia(LaParser.TipoReferenciaContext ctx) {
    return ctx.tipo_estendido().getText();
  }

  @Override
  public Simbolo visitVariavel_unica(LaParser.Variavel_unicaContext ctx) {
    Token ident = ctx.Ident().getSymbol();
    Simbolo simbolo = new Simbolo( ident.getText(), "undefined", ident.getLine(), LacClass.PARSER );
    simbolo.setDimensoes( (ArrayList<Integer>) visit(ctx.lista_dimensao() ));
    return simbolo;
  }

  @Override
  public Integer visitDimensao(LaParser.DimensaoContext ctx) {
    return 7;
    // return visit(ctx.exp_aritmetica());
  }

  @Override
  public ArrayList<Integer> visitLista_dimensao(LaParser.Lista_dimensaoContext ctx) {
    ArrayList<Integer> listaDeDimensoes = new ArrayList<Integer>();
    for (LaParser.DimensaoContext dimensao : ctx.dimensao()) {
      listaDeDimensoes.add((Integer) visit(dimensao));
    }
    return listaDeDimensoes;
  }

  @Override
  public Object visitLista_variavel(LaParser.Lista_variavelContext ctx) {
    ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
    // Get name list
    ArrayList<Simbolo>  parserSimbolos = new ArrayList<Simbolo>();
    parserSimbolos.add( (Simbolo) visit(ctx.variavel_unica() ));

    for (LaParser.Mais_variaveisContext mais_var : ctx.mais_variaveis()) {
      Simbolo simbolo = (Simbolo) visit(mais_var);
      parserSimbolos.add(simbolo);
    }

    // Get Type
    Object visitTipo = visit(ctx.tipo());
    String tipoId = "undefined";

    if ( visitTipo instanceof String ) {
      tipoId = (String) visitTipo;
      // Remove ponteiros da verificação do tipo
      tipoId = tipoId.replace("^", "");
      if( !tipos.containsKey ( tipoId ) ) {
        // else warn error
        int linha = ctx.getStart().getLine();
        Lac.errorBuffer.println(Mensagens.erroTipoNaoDeclarado( linha, tipoId ));
      }
    } else if ( visitTipo instanceof List ) {
      tipoId = AUTOGENERATED_FLAG + autoGeneratedTypes;
      autoGeneratedTypes++;

      Tipo novoTipo = new Tipo(tipoId);
      tipos.put(tipoId, novoTipo);

      List<Simbolo> simbolosTipo = (List<Simbolo>) visitTipo;
      for( Simbolo simbolo : simbolosTipo) {
        novoTipo.addSimbolo( simbolo );
      }
    }

    // if type is registered add symbols
    for( Simbolo ps : parserSimbolos ) {
      Simbolo simbolo = new Simbolo(ps.getNome(), tipoId, ps.getLinha());
      simbolo.setDimensoes(ps.getDimensoes());
      simbolos.add(simbolo);
    }

    return simbolos;
  }

  @Override
  public Simbolo visitMais_variaveis(LaParser.Mais_variaveisContext ctx) {
    return (Simbolo) visit(ctx.variavel_unica());
  }

  @Override
  public Void visitCmdRead(LaParser.CmdReadContext ctx) {
    ArrayList<Simbolo> listaIdentificadores = (ArrayList<Simbolo>) visit(ctx.lista_identificador());

    for( Simbolo s : listaIdentificadores ) {
      checarSePilhaContemSimbolo(s);
    }

    visitChildren(ctx);
    return null;
  }

  @Override
  public Void visitCmdReturn(LaParser.CmdReturnContext ctx) {
    if( !scopeCanReturn ) {
      int linha = ctx.getStart().getLine();
      Lac.errorBuffer.println( Mensagens.erroRetorneEmEscopoIncorreto( linha ) );
    }
    visitChildren(ctx);
    return null;
  }

  @Override
  public Void visitDeclareProcedure(LaParser.DeclareProcedureContext ctx) {
    scopeCanReturn = false;
    Token procedimentoTk = ctx.Ident().getSymbol();
    String simboloId = procedimentoTk.getText();
    int linha = procedimentoTk.getLine();
    Simbolo procedimento = new Simbolo(simboloId, "", linha, LacClass.PROCEDIMENTO);
    adicionarSimbolo(procedimento);

    pilha.push( new HashMap<String, Simbolo>() );
    visit(ctx.lista_parametros());
    for( LaParser.Declaracao_localContext local : ctx.declaracao_local()) {
      visit(local);
    }
    for( LaParser.ComandoContext comando : ctx.comando()) {
      visit(comando);
    }
    pilha.pop();

    return null;
  }

	@Override
  public Void visitDeclareFunction(LaParser.DeclareFunctionContext ctx) {
    scopeCanReturn = true;
    Token funcaoTk = ctx.Ident().getSymbol();
    String simboloId = funcaoTk.getText();
    int linha = funcaoTk.getLine();
    String tipoFuncao = ctx.tipo_estendido().getText();
    Simbolo funcao = new Simbolo(simboloId, tipoFuncao, linha, LacClass.FUNCAO);
    adicionarSimbolo(funcao);

    pilha.push( new HashMap<String, Simbolo>() );
    visit(ctx.lista_parametros());
    for( LaParser.Declaracao_localContext local : ctx.declaracao_local()) {
      visit(local);
    }
    for( LaParser.ComandoContext comando : ctx.comando()) {
      visit(comando);
    }
    pilha.pop();

    visitChildren(ctx);
    return null;
  }

  @Override
  public ArrayList<Simbolo> visitParametro(LaParser.ParametroContext ctx) {
    ArrayList<Simbolo> parametros = new ArrayList<Simbolo>();
    String tipo = ctx.tipo_estendido().getText();
    int linha = ctx.getStart().getLine();
    String tipoSemPonteiro = tipo.replace("^", "");

    if( !tipos.containsKey ( tipoSemPonteiro ) ) {
      Lac.errorBuffer.println(Mensagens.erroTipoNaoDeclarado( linha, tipo ));
    }

    ArrayList<Simbolo> identificadores = new ArrayList<Simbolo>();

    for( Simbolo s: (ArrayList<Simbolo>) visit(ctx.lista_identificador()) ) {
      Simbolo simbolo = new Simbolo(s.getNome(), tipo, s.getLinha(), LacClass.PARAMETRO);
      simbolo.setDimensoes(s.getDimensoes());

      parametros.add(simbolo);
    }


    return parametros;
  }
  @Override
  public Void visitLista_parametros(LaParser.Lista_parametrosContext ctx) {
    ArrayList<Simbolo> parametros = (ArrayList<Simbolo>) visit(ctx.parametro());

    for( LaParser.Mais_parametroContext mais_parametro : ctx.mais_parametro()) {
      ArrayList<Simbolo> mais_parametros = (ArrayList<Simbolo>) visit(mais_parametro);

      parametros.addAll(mais_parametros);
    }

    adicionarSimbolos(parametros);
    return null;
  }

  @Override
  public Void visitCorpo(LaParser.CorpoContext ctx) {
    scopeCanReturn = false;
    visitChildren(ctx);
    return null;
  }

  @Override
  public Simbolo visitIdentificador(LaParser.IdentificadorContext ctx) {
    Token ident = ctx.Ident().getSymbol();
    ArrayList<Integer> dimensoes = (ArrayList<Integer>) visit(ctx.lista_dimensao());

    Simbolo simbolo = new Simbolo( ident.getText(), "undefined", ident.getLine(), LacClass.PARSER );
    simbolo.setDimensoes(dimensoes);

    visitChildren(ctx);
    return simbolo;
  }

  @Override
  public ArrayList<Simbolo> visitLista_identificador(LaParser.Lista_identificadorContext ctx) {
    ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
    simbolos.add( (Simbolo) visit(ctx.identificador() ));

    for( LaParser.Mais_identificadorContext identificador : ctx.mais_identificador() ) {
      Simbolo simbolo = (Simbolo) visit(identificador);
      simbolos.add(simbolo);
    }

    visitChildren(ctx);
    return simbolos;
  }

  @Override
  public Void visitParcelaUnarioChamadaFuncao(LaParser.ParcelaUnarioChamadaFuncaoContext ctx) {
    Token ident = ctx.Ident().getSymbol();
    checarSePilhaContemSimbolo(ident);
    visitChildren(ctx);
    return null;
  }

  @Override
  public Simbolo visitSub_identificador(LaParser.Sub_identificadorContext ctx) {
    return (Simbolo) visit(ctx.identificador());
  }

  @Override
  public Void visitParcelaUnarioVariavel(LaParser.ParcelaUnarioVariavelContext ctx) {
    Token ident = ctx.Ident().getSymbol();
    checarSePilhaContemSimbolo(ident);
    visitChildren(ctx);
    return null;
  }

  @Override
  public Void visitExpressao(LaParser.ExpressaoContext ctx) {
    Lac.errorBuffer.println(ctx.getText() + "/" + LaType.getType(ctx));

    return null;
  }

 }
