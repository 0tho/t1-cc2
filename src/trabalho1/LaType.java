package trabalho1;

class LaType {

  private static final String INTEIRO = "inteiro";
  private static final String REAL = "real";
  private static final String LOGICO = "logico";
  private static final String LITERAL = "literal";


  public static String getType(LaParser.Lista_expressaoContext ctx) {
    return "NU";
  }

  public static String getType(LaParser.Mais_expressaoContext ctx) {
    return "NOT";
  }

  public static String getType(LaParser.ExpressaoContext ctx) {
    if ( ctx.outros_termos_logicos().termo_logico() != null ) {
      return LOGICO;
    }
    return getType(ctx.termo_logico());
  }

  public static String getType(LaParser.Termo_logicoContext ctx) {
    if( ctx.outros_fatores_logicos().fator_logico() != null ) {
      return LOGICO;
    }
    return getType(ctx.fator_logico());
  }

  public static String getType(LaParser.Fator_logicoContext ctx) {
    return getType(ctx.parcela_logica());
  }

  public static String getType(LaParser.Parcela_logicaContext ctx) {
    if ( ctx instanceof LaParser.ParcelaLogicaTrueContext ) {
      return getType((LaParser.ParcelaLogicaTrueContext) ctx);
    } else if ( ctx instanceof LaParser.ParcelaLogicaFalseContext) {
      return getType((LaParser.ParcelaLogicaFalseContext) ctx);
    } else {
      return getType((LaParser.ParcelaLogicaExpRelacionalContext) ctx);
    }
  }

  public static String getType(LaParser.ParcelaLogicaTrueContext ctx) {
    return LOGICO;
  }

  public static String getType(LaParser.ParcelaLogicaFalseContext ctx) {
    return LOGICO;
  }

  public static String getType(LaParser.ParcelaLogicaExpRelacionalContext ctx) {
    return getType(ctx.exp_relacional());
  }

  public static String getType(LaParser.Exp_relacionalContext ctx) {
    if ( ctx.op_opcional().op_relacional() != null ) {
      return LOGICO;
    }
    return getType(ctx.exp_aritmetica());
  }

  public static String getType(LaParser.Exp_aritmeticaContext ctx) {
    if( ctx.outros_termos().termo() != null ) {
      // TODO: verificar se inteiro ou real
      return REAL;
    }
    return getType(ctx.termo());
  }

  public static String getType(LaParser.TermoContext ctx ) {
    if ( ctx.outros_fatores().fator() != null ) {
      // TODO: verificar se inteiro ou real
      return REAL;
    }
    return getType(ctx.fator());
  }

  public static String getType(LaParser.FatorContext ctx) {
    if ( ctx.outras_parcelas().parcela() != null ) {
      return INTEIRO;
    }
    return getType(ctx.parcela());
  }

  public static String getType(LaParser.ParcelaContext ctx) {
    if( ctx instanceof LaParser.ParcelaParcelaUnarioContext ) {
      return getType((LaParser.ParcelaParcelaUnarioContext) ctx);
    } else {
      return getType((LaParser.ParcelaParcelaNaoUnarioContext) ctx);
    }
  }

  public static String getType(LaParser.ParcelaParcelaUnarioContext ctx) {
    return getType(ctx.parcela_unario());
  }

  public static String getType(LaParser.Parcela_unarioContext ctx) {
    if( ctx instanceof LaParser.ParcelaUnarioVariavelContext ) {
      return getType((LaParser.ParcelaUnarioVariavelContext) ctx);
    } else if ( ctx instanceof LaParser.ParcelaUnarioChamadaFuncaoContext ) {
      return getType((LaParser.ParcelaUnarioChamadaFuncaoContext) ctx);
    } else if ( ctx instanceof LaParser.ParcelaUnarioInteiroContext ) {
      return getType((LaParser.ParcelaUnarioInteiroContext) ctx);
    } else if ( ctx instanceof LaParser.ParcelaUnarioRealContext ) {
      return getType((LaParser.ParcelaUnarioRealContext) ctx);
    } else {
      return getType((LaParser.ParcelaUnarioParentesesContext) ctx);
    }
  }

  public static String getType(LaParser.ParcelaParcelaNaoUnarioContext ctx) {
    return getType(ctx.parcela_nao_unario());
  }

  public static String getType(LaParser.Parcela_nao_unarioContext ctx) {
    if( ctx instanceof LaParser.ParcelaNaoUnarioVetorContext ) {
      return getType((LaParser.ParcelaNaoUnarioVetorContext) ctx);
    } else {
      return getType((LaParser.ParcelaNaoUnarioStringContext) ctx);
    }
  }

  public static String getType(LaParser.ParcelaUnarioVariavelContext ctx) {
    return ctx.getText();
  }

  public static String getType(LaParser.ParcelaUnarioChamadaFuncaoContext ctx) {
    return ctx.getText();
  }

  public static String getType(LaParser.ParcelaUnarioInteiroContext ctx) {
    return INTEIRO;
  }

  public static String getType(LaParser.ParcelaUnarioRealContext ctx) {
    return REAL;
  }

  public static String getType(LaParser.ParcelaUnarioParentesesContext ctx) {
    return getType(ctx.expressao());
  }

  public static String getType(LaParser.ParcelaNaoUnarioVetorContext ctx) {
    return ctx.getText();
  }

  public static String getType(LaParser.ParcelaNaoUnarioStringContext ctx) {
    return LITERAL;
  }

}
