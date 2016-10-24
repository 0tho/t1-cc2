// Generated from /Users/daniellucredio/Dropbox/syncDropBox/ufscar/ensino/graduação/2014.1/compiladores1/t2/T2/src/trabalho2/Luazinha.g4 by ANTLR 4.1
package trabalho2;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LuazinhaParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__46=1, T__45=2, T__44=3, T__43=4, T__42=5, T__41=6, T__40=7, T__39=8, 
		T__38=9, T__37=10, T__36=11, T__35=12, T__34=13, T__33=14, T__32=15, T__31=16, 
		T__30=17, T__29=18, T__28=19, T__27=20, T__26=21, T__25=22, T__24=23, 
		T__23=24, T__22=25, T__21=26, T__20=27, T__19=28, T__18=29, T__17=30, 
		T__16=31, T__15=32, T__14=33, T__13=34, T__12=35, T__11=36, T__10=37, 
		T__9=38, T__8=39, T__7=40, T__6=41, T__5=42, T__4=43, T__3=44, T__2=45, 
		T__1=46, T__0=47, NOME=48, CADEIA=49, NUMERO=50, COMENTARIO=51, WS=52;
	public static final String[] tokenNames = {
		"<INVALID>", "']'", "'in'", "'local'", "','", "'['", "'while'", "'-'", 
		"'*'", "'or'", "':'", "'('", "'not'", "'if'", "'<'", "'false'", "'<='", 
		"'until'", "'nil'", "'~='", "'...'", "'{'", "'break'", "'and'", "'else'", 
		"'}'", "'true'", "'elseif'", "'do'", "'%'", "'^'", "'.'", "'function'", 
		"')'", "'+'", "'for'", "'return'", "'='", "';'", "'repeat'", "'>'", "'then'", 
		"'/'", "'=='", "'>='", "'#'", "'end'", "'..'", "NOME", "CADEIA", "NUMERO", 
		"COMENTARIO", "WS"
	};
	public static final int
		RULE_programa = 0, RULE_trecho = 1, RULE_bloco = 2, RULE_comando = 3, 
		RULE_ultimocomando = 4, RULE_nomedafuncao = 5, RULE_listavar = 6, RULE_var = 7, 
		RULE_listadenomes = 8, RULE_listaexp = 9, RULE_exp = 10, RULE_expprefixo = 11, 
		RULE_expprefixo2 = 12, RULE_chamadadefuncao = 13, RULE_args = 14, RULE_funcao = 15, 
		RULE_corpodafuncao = 16, RULE_listapar = 17, RULE_construtortabela = 18, 
		RULE_listadecampos = 19, RULE_campo = 20, RULE_separadordecampos = 21, 
		RULE_opbin = 22, RULE_opunaria = 23;
	public static final String[] ruleNames = {
		"programa", "trecho", "bloco", "comando", "ultimocomando", "nomedafuncao", 
		"listavar", "var", "listadenomes", "listaexp", "exp", "expprefixo", "expprefixo2", 
		"chamadadefuncao", "args", "funcao", "corpodafuncao", "listapar", "construtortabela", 
		"listadecampos", "campo", "separadordecampos", "opbin", "opunaria"
	};

	@Override
	public String getGrammarFileName() { return "Luazinha.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	static String grupo = "<Coloque os RAs do seu grupo aqui>"; 
	PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();

	public LuazinhaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramaContext extends ParserRuleContext {
		public TrechoContext trecho() {
			return getRuleContext(TrechoContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global")); 
			setState(49); trecho();
			 pilhaDeTabelas.desempilhar(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrechoContext extends ParserRuleContext {
		public UltimocomandoContext ultimocomando() {
			return getRuleContext(UltimocomandoContext.class,0);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public TrechoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trecho; }
	}

	public final TrechoContext trecho() throws RecognitionException {
		TrechoContext _localctx = new TrechoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_trecho);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 6) | (1L << 13) | (1L << 28) | (1L << 32) | (1L << 35) | (1L << 39) | (1L << NOME))) != 0)) {
				{
				{
				setState(52); comando();
				setState(54);
				_la = _input.LA(1);
				if (_la==38) {
					{
					setState(53); match(38);
					}
				}

				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			_la = _input.LA(1);
			if (_la==22 || _la==36) {
				{
				setState(61); ultimocomando();
				setState(63);
				_la = _input.LA(1);
				if (_la==38) {
					{
					setState(62); match(38);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public TrechoContext trecho() {
			return getRuleContext(TrechoContext.class,0);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); trecho();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public ChamadadefuncaoContext chamadadefuncao() {
			return getRuleContext(ChamadadefuncaoContext.class,0);
		}
		public ListavarContext listavar() {
			return getRuleContext(ListavarContext.class,0);
		}
		public NomedafuncaoContext nomedafuncao() {
			return getRuleContext(NomedafuncaoContext.class,0);
		}
		public CorpodafuncaoContext corpodafuncao() {
			return getRuleContext(CorpodafuncaoContext.class,0);
		}
		public ListaexpContext listaexp() {
			return getRuleContext(ListaexpContext.class,0);
		}
		public TerminalNode NOME() { return getToken(LuazinhaParser.NOME, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public BlocoContext bloco(int i) {
			return getRuleContext(BlocoContext.class,i);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ListadenomesContext listadenomes() {
			return getRuleContext(ListadenomesContext.class,0);
		}
		public List<BlocoContext> bloco() {
			return getRuleContexts(BlocoContext.class);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comando);
		int _la;
		try {
			setState(145);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69); listavar();
				setState(70); match(37);
				setState(71); listaexp();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73); chamadadefuncao();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74); match(28);
				setState(75); bloco();
				setState(76); match(46);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78); match(6);
				setState(79); exp(0);
				setState(80); match(28);
				setState(81); bloco();
				setState(82); match(46);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(84); match(39);
				setState(85); bloco();
				setState(86); match(17);
				setState(87); exp(0);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(89); match(13);
				setState(90); exp(0);
				setState(91); match(41);
				setState(92); bloco();
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==27) {
					{
					{
					setState(93); match(27);
					setState(94); exp(0);
					setState(95); match(41);
					setState(96); bloco();
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(105);
				_la = _input.LA(1);
				if (_la==24) {
					{
					setState(103); match(24);
					setState(104); bloco();
					}
				}

				setState(107); match(46);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(109); match(35);
				setState(110); match(NOME);
				setState(111); match(37);
				setState(112); exp(0);
				setState(113); match(4);
				setState(114); exp(0);
				setState(117);
				_la = _input.LA(1);
				if (_la==4) {
					{
					setState(115); match(4);
					setState(116); exp(0);
					}
				}

				setState(119); match(28);
				setState(120); bloco();
				setState(121); match(46);
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(123); match(35);
				setState(124); listadenomes();
				setState(125); match(2);
				setState(126); listaexp();
				setState(127); match(28);
				setState(128); bloco();
				setState(129); match(46);
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(131); match(32);
				setState(132); nomedafuncao();
				setState(133); corpodafuncao();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(135); match(3);
				setState(136); match(32);
				setState(137); match(NOME);
				setState(138); corpodafuncao();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(139); match(3);
				setState(140); listadenomes();
				setState(143);
				_la = _input.LA(1);
				if (_la==37) {
					{
					setState(141); match(37);
					setState(142); listaexp();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UltimocomandoContext extends ParserRuleContext {
		public ListaexpContext listaexp() {
			return getRuleContext(ListaexpContext.class,0);
		}
		public UltimocomandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ultimocomando; }
	}

	public final UltimocomandoContext ultimocomando() throws RecognitionException {
		UltimocomandoContext _localctx = new UltimocomandoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ultimocomando);
		int _la;
		try {
			setState(152);
			switch (_input.LA(1)) {
			case 36:
				enterOuterAlt(_localctx, 1);
				{
				setState(147); match(36);
				setState(149);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 11) | (1L << 12) | (1L << 15) | (1L << 18) | (1L << 20) | (1L << 21) | (1L << 26) | (1L << 32) | (1L << 45) | (1L << NOME) | (1L << CADEIA) | (1L << NUMERO))) != 0)) {
					{
					setState(148); listaexp();
					}
				}

				}
				break;
			case 22:
				enterOuterAlt(_localctx, 2);
				{
				setState(151); match(22);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NomedafuncaoContext extends ParserRuleContext {
		public String nome;
		public boolean metodo;
		public Token n1;
		public Token n2;
		public Token n3;
		public List<TerminalNode> NOME() { return getTokens(LuazinhaParser.NOME); }
		public TerminalNode NOME(int i) {
			return getToken(LuazinhaParser.NOME, i);
		}
		public NomedafuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nomedafuncao; }
	}

	public final NomedafuncaoContext nomedafuncao() throws RecognitionException {
		NomedafuncaoContext _localctx = new NomedafuncaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_nomedafuncao);
		 ((NomedafuncaoContext)_localctx).metodo =  false; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); ((NomedafuncaoContext)_localctx).n1 = match(NOME);
			 ((NomedafuncaoContext)_localctx).nome =  ((NomedafuncaoContext)_localctx).n1.getText(); 
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==31) {
				{
				{
				setState(156); match(31);
				setState(157); ((NomedafuncaoContext)_localctx).n2 = match(NOME);
				 _localctx.nome += "." + ((NomedafuncaoContext)_localctx).n2.getText(); 
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(167);
			_la = _input.LA(1);
			if (_la==10) {
				{
				setState(164); match(10);
				setState(165); ((NomedafuncaoContext)_localctx).n3 = match(NOME);
				 ((NomedafuncaoContext)_localctx).metodo =  true; _localctx.nome += "." + ((NomedafuncaoContext)_localctx).n3.getText(); 
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListavarContext extends ParserRuleContext {
		public List<String> nomes;
		public VarContext v1;
		public VarContext v2;
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public ListavarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listavar; }
	}

	public final ListavarContext listavar() throws RecognitionException {
		ListavarContext _localctx = new ListavarContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_listavar);
		 ((ListavarContext)_localctx).nomes =  new ArrayList<String>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); ((ListavarContext)_localctx).v1 = var();
			 _localctx.nomes.add(((ListavarContext)_localctx).v1.nome); 
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(171); match(4);
				setState(172); ((ListavarContext)_localctx).v2 = var();
				 _localctx.nomes.add(((ListavarContext)_localctx).v2.nome); 
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public String nome;
		public int linha;
		public int coluna;
		public Token NOME;
		public TerminalNode NOME() { return getToken(LuazinhaParser.NOME, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpprefixoContext expprefixo() {
			return getRuleContext(ExpprefixoContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var);
		try {
			setState(191);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180); ((VarContext)_localctx).NOME = match(NOME);
				 ((VarContext)_localctx).nome =  ((VarContext)_localctx).NOME.getText(); ((VarContext)_localctx).linha =  (((VarContext)_localctx).NOME!=null?((VarContext)_localctx).NOME.getLine():0); ((VarContext)_localctx).coluna =  (((VarContext)_localctx).NOME!=null?((VarContext)_localctx).NOME.getCharPositionInLine():0); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(182); expprefixo();
				setState(183); match(5);
				setState(184); exp(0);
				setState(185); match(1);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(187); expprefixo();
				setState(188); match(31);
				setState(189); match(NOME);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListadenomesContext extends ParserRuleContext {
		public List<String> nomes;
		public Token n1;
		public Token n2;
		public List<TerminalNode> NOME() { return getTokens(LuazinhaParser.NOME); }
		public TerminalNode NOME(int i) {
			return getToken(LuazinhaParser.NOME, i);
		}
		public ListadenomesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listadenomes; }
	}

	public final ListadenomesContext listadenomes() throws RecognitionException {
		ListadenomesContext _localctx = new ListadenomesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_listadenomes);
		 ((ListadenomesContext)_localctx).nomes =  new ArrayList<String>(); 
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(193); ((ListadenomesContext)_localctx).n1 = match(NOME);
			 _localctx.nomes.add(((ListadenomesContext)_localctx).n1.getText()); 
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(195); match(4);
					setState(196); ((ListadenomesContext)_localctx).n2 = match(NOME);
					 _localctx.nomes.add(((ListadenomesContext)_localctx).n2.getText()); 
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaexpContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ListaexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaexp; }
	}

	public final ListaexpContext listaexp() throws RecognitionException {
		ListaexpContext _localctx = new ListaexpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_listaexp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(203); exp(0);
					setState(204); match(4);
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(211); exp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public int _p;
		public OpunariaContext opunaria() {
			return getRuleContext(OpunariaContext.class,0);
		}
		public ConstrutortabelaContext construtortabela() {
			return getRuleContext(ConstrutortabelaContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode NUMERO() { return getToken(LuazinhaParser.NUMERO, 0); }
		public FuncaoContext funcao() {
			return getRuleContext(FuncaoContext.class,0);
		}
		public TerminalNode CADEIA() { return getToken(LuazinhaParser.CADEIA, 0); }
		public Expprefixo2Context expprefixo2() {
			return getRuleContext(Expprefixo2Context.class,0);
		}
		public OpbinContext opbin() {
			return getRuleContext(OpbinContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState, _p);
		ExpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, RULE_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			switch (_input.LA(1)) {
			case 7:
			case 12:
			case 45:
				{
				setState(214); opunaria();
				setState(215); exp(1);
				}
				break;
			case 18:
				{
				setState(217); match(18);
				}
				break;
			case 15:
				{
				setState(218); match(15);
				}
				break;
			case 26:
				{
				setState(219); match(26);
				}
				break;
			case NUMERO:
				{
				setState(220); match(NUMERO);
				}
				break;
			case CADEIA:
				{
				setState(221); match(CADEIA);
				}
				break;
			case 20:
				{
				setState(222); match(20);
				}
				break;
			case 32:
				{
				setState(223); funcao();
				}
				break;
			case 11:
			case NOME:
				{
				setState(224); expprefixo2();
				}
				break;
			case 21:
				{
				setState(225); construtortabela();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(234);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(228);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(229); opbin();
					setState(230); exp(0);
					}
					} 
				}
				setState(236);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpprefixoContext extends ParserRuleContext {
		public List<TerminalNode> NOME() { return getTokens(LuazinhaParser.NOME); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode NOME(int i) {
			return getToken(LuazinhaParser.NOME, i);
		}
		public ExpprefixoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expprefixo; }
	}

	public final ExpprefixoContext expprefixo() throws RecognitionException {
		ExpprefixoContext _localctx = new ExpprefixoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expprefixo);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237); match(NOME);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					setState(244);
					switch (_input.LA(1)) {
					case 5:
						{
						setState(238); match(5);
						setState(239); exp(0);
						setState(240); match(1);
						}
						break;
					case 31:
						{
						setState(242); match(31);
						setState(243); match(NOME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expprefixo2Context extends ParserRuleContext {
		public ChamadadefuncaoContext chamadadefuncao() {
			return getRuleContext(ChamadadefuncaoContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Expprefixo2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expprefixo2; }
	}

	public final Expprefixo2Context expprefixo2() throws RecognitionException {
		Expprefixo2Context _localctx = new Expprefixo2Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_expprefixo2);
		try {
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249); var();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(250); chamadadefuncao();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(251); match(11);
				setState(252); exp(0);
				setState(253); match(33);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChamadadefuncaoContext extends ParserRuleContext {
		public TerminalNode NOME() { return getToken(LuazinhaParser.NOME, 0); }
		public ExpprefixoContext expprefixo() {
			return getRuleContext(ExpprefixoContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ChamadadefuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chamadadefuncao; }
	}

	public final ChamadadefuncaoContext chamadadefuncao() throws RecognitionException {
		ChamadadefuncaoContext _localctx = new ChamadadefuncaoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_chamadadefuncao);
		try {
			setState(265);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(257); expprefixo();
				setState(258); args();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(260); expprefixo();
				setState(261); match(10);
				setState(262); match(NOME);
				setState(263); args();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public ListaexpContext listaexp() {
			return getRuleContext(ListaexpContext.class,0);
		}
		public ConstrutortabelaContext construtortabela() {
			return getRuleContext(ConstrutortabelaContext.class,0);
		}
		public TerminalNode CADEIA() { return getToken(LuazinhaParser.CADEIA, 0); }
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_args);
		int _la;
		try {
			setState(274);
			switch (_input.LA(1)) {
			case 11:
				enterOuterAlt(_localctx, 1);
				{
				setState(267); match(11);
				setState(269);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 11) | (1L << 12) | (1L << 15) | (1L << 18) | (1L << 20) | (1L << 21) | (1L << 26) | (1L << 32) | (1L << 45) | (1L << NOME) | (1L << CADEIA) | (1L << NUMERO))) != 0)) {
					{
					setState(268); listaexp();
					}
				}

				setState(271); match(33);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 2);
				{
				setState(272); construtortabela();
				}
				break;
			case CADEIA:
				enterOuterAlt(_localctx, 3);
				{
				setState(273); match(CADEIA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoContext extends ParserRuleContext {
		public CorpodafuncaoContext corpodafuncao() {
			return getRuleContext(CorpodafuncaoContext.class,0);
		}
		public FuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcao; }
	}

	public final FuncaoContext funcao() throws RecognitionException {
		FuncaoContext _localctx = new FuncaoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_funcao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); match(32);
			setState(277); corpodafuncao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CorpodafuncaoContext extends ParserRuleContext {
		public ListaparContext listapar() {
			return getRuleContext(ListaparContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public CorpodafuncaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_corpodafuncao; }
	}

	public final CorpodafuncaoContext corpodafuncao() throws RecognitionException {
		CorpodafuncaoContext _localctx = new CorpodafuncaoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_corpodafuncao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279); match(11);
			setState(281);
			_la = _input.LA(1);
			if (_la==20 || _la==NOME) {
				{
				setState(280); listapar();
				}
			}

			setState(283); match(33);
			setState(284); bloco();
			setState(285); match(46);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListaparContext extends ParserRuleContext {
		public ListadenomesContext listadenomes() {
			return getRuleContext(ListadenomesContext.class,0);
		}
		public ListaparContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listapar; }
	}

	public final ListaparContext listapar() throws RecognitionException {
		ListaparContext _localctx = new ListaparContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_listapar);
		int _la;
		try {
			setState(293);
			switch (_input.LA(1)) {
			case NOME:
				enterOuterAlt(_localctx, 1);
				{
				setState(287); listadenomes();
				setState(290);
				_la = _input.LA(1);
				if (_la==4) {
					{
					setState(288); match(4);
					setState(289); match(20);
					}
				}

				}
				break;
			case 20:
				enterOuterAlt(_localctx, 2);
				{
				setState(292); match(20);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstrutortabelaContext extends ParserRuleContext {
		public ListadecamposContext listadecampos() {
			return getRuleContext(ListadecamposContext.class,0);
		}
		public ConstrutortabelaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_construtortabela; }
	}

	public final ConstrutortabelaContext construtortabela() throws RecognitionException {
		ConstrutortabelaContext _localctx = new ConstrutortabelaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_construtortabela);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295); match(21);
			setState(297);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 7) | (1L << 11) | (1L << 12) | (1L << 15) | (1L << 18) | (1L << 20) | (1L << 21) | (1L << 26) | (1L << 32) | (1L << 45) | (1L << NOME) | (1L << CADEIA) | (1L << NUMERO))) != 0)) {
				{
				setState(296); listadecampos();
				}
			}

			setState(299); match(25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListadecamposContext extends ParserRuleContext {
		public SeparadordecamposContext separadordecampos(int i) {
			return getRuleContext(SeparadordecamposContext.class,i);
		}
		public List<CampoContext> campo() {
			return getRuleContexts(CampoContext.class);
		}
		public CampoContext campo(int i) {
			return getRuleContext(CampoContext.class,i);
		}
		public List<SeparadordecamposContext> separadordecampos() {
			return getRuleContexts(SeparadordecamposContext.class);
		}
		public ListadecamposContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listadecampos; }
	}

	public final ListadecamposContext listadecampos() throws RecognitionException {
		ListadecamposContext _localctx = new ListadecamposContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_listadecampos);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(301); campo();
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(302); separadordecampos();
					setState(303); campo();
					}
					} 
				}
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(311);
			_la = _input.LA(1);
			if (_la==4 || _la==38) {
				{
				setState(310); separadordecampos();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CampoContext extends ParserRuleContext {
		public TerminalNode NOME() { return getToken(LuazinhaParser.NOME, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public CampoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_campo; }
	}

	public final CampoContext campo() throws RecognitionException {
		CampoContext _localctx = new CampoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_campo);
		try {
			setState(323);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313); match(5);
				setState(314); exp(0);
				setState(315); match(1);
				setState(316); match(37);
				setState(317); exp(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(319); match(NOME);
				setState(320); match(37);
				setState(321); exp(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(322); exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SeparadordecamposContext extends ParserRuleContext {
		public SeparadordecamposContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_separadordecampos; }
	}

	public final SeparadordecamposContext separadordecampos() throws RecognitionException {
		SeparadordecamposContext _localctx = new SeparadordecamposContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_separadordecampos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_la = _input.LA(1);
			if ( !(_la==4 || _la==38) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpbinContext extends ParserRuleContext {
		public OpbinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opbin; }
	}

	public final OpbinContext opbin() throws RecognitionException {
		OpbinContext _localctx = new OpbinContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_opbin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 8) | (1L << 9) | (1L << 14) | (1L << 16) | (1L << 19) | (1L << 23) | (1L << 29) | (1L << 30) | (1L << 34) | (1L << 40) | (1L << 42) | (1L << 43) | (1L << 44) | (1L << 47))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpunariaContext extends ParserRuleContext {
		public OpunariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opunaria; }
	}

	public final OpunariaContext opunaria() throws RecognitionException {
		OpunariaContext _localctx = new OpunariaContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_opunaria);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 7) | (1L << 12) | (1L << 45))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10: return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\66\u014e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\5\39\n\3\7\3;\n\3\f\3\16\3>\13\3\3\3\3\3\5\3"+
		"B\n\3\5\3D\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7"+
		"\5e\n\5\f\5\16\5h\13\5\3\5\3\5\5\5l\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\5\5x\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0092\n\5\5\5\u0094"+
		"\n\5\3\6\3\6\5\6\u0098\n\6\3\6\5\6\u009b\n\6\3\7\3\7\3\7\3\7\3\7\7\7\u00a2"+
		"\n\7\f\7\16\7\u00a5\13\7\3\7\3\7\3\7\5\7\u00aa\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u00b2\n\b\f\b\16\b\u00b5\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u00c2\n\t\3\n\3\n\3\n\3\n\3\n\7\n\u00c9\n\n\f\n\16\n"+
		"\u00cc\13\n\3\13\3\13\3\13\7\13\u00d1\n\13\f\13\16\13\u00d4\13\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e5\n"+
		"\f\3\f\3\f\3\f\3\f\7\f\u00eb\n\f\f\f\16\f\u00ee\13\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\7\r\u00f7\n\r\f\r\16\r\u00fa\13\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u0102\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u010c"+
		"\n\17\3\20\3\20\5\20\u0110\n\20\3\20\3\20\3\20\5\20\u0115\n\20\3\21\3"+
		"\21\3\21\3\22\3\22\5\22\u011c\n\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\5\23\u0125\n\23\3\23\5\23\u0128\n\23\3\24\3\24\5\24\u012c\n\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\7\25\u0134\n\25\f\25\16\25\u0137\13\25\3\25\5"+
		"\25\u013a\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26"+
		"\u0146\n\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\2\32\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\2\5\4\2\6\6((\f\2\t\13\20\20\22\22\25"+
		"\25\31\31\37 $$**,.\61\61\5\2\t\t\16\16//\u016a\2\62\3\2\2\2\4<\3\2\2"+
		"\2\6E\3\2\2\2\b\u0093\3\2\2\2\n\u009a\3\2\2\2\f\u009c\3\2\2\2\16\u00ab"+
		"\3\2\2\2\20\u00c1\3\2\2\2\22\u00c3\3\2\2\2\24\u00d2\3\2\2\2\26\u00e4\3"+
		"\2\2\2\30\u00ef\3\2\2\2\32\u0101\3\2\2\2\34\u010b\3\2\2\2\36\u0114\3\2"+
		"\2\2 \u0116\3\2\2\2\"\u0119\3\2\2\2$\u0127\3\2\2\2&\u0129\3\2\2\2(\u012f"+
		"\3\2\2\2*\u0145\3\2\2\2,\u0147\3\2\2\2.\u0149\3\2\2\2\60\u014b\3\2\2\2"+
		"\62\63\b\2\1\2\63\64\5\4\3\2\64\65\b\2\1\2\65\3\3\2\2\2\668\5\b\5\2\67"+
		"9\7(\2\28\67\3\2\2\289\3\2\2\29;\3\2\2\2:\66\3\2\2\2;>\3\2\2\2<:\3\2\2"+
		"\2<=\3\2\2\2=C\3\2\2\2><\3\2\2\2?A\5\n\6\2@B\7(\2\2A@\3\2\2\2AB\3\2\2"+
		"\2BD\3\2\2\2C?\3\2\2\2CD\3\2\2\2D\5\3\2\2\2EF\5\4\3\2F\7\3\2\2\2GH\5\16"+
		"\b\2HI\7\'\2\2IJ\5\24\13\2J\u0094\3\2\2\2K\u0094\5\34\17\2LM\7\36\2\2"+
		"MN\5\6\4\2NO\7\60\2\2O\u0094\3\2\2\2PQ\7\b\2\2QR\5\26\f\2RS\7\36\2\2S"+
		"T\5\6\4\2TU\7\60\2\2U\u0094\3\2\2\2VW\7)\2\2WX\5\6\4\2XY\7\23\2\2YZ\5"+
		"\26\f\2Z\u0094\3\2\2\2[\\\7\17\2\2\\]\5\26\f\2]^\7+\2\2^f\5\6\4\2_`\7"+
		"\35\2\2`a\5\26\f\2ab\7+\2\2bc\5\6\4\2ce\3\2\2\2d_\3\2\2\2eh\3\2\2\2fd"+
		"\3\2\2\2fg\3\2\2\2gk\3\2\2\2hf\3\2\2\2ij\7\32\2\2jl\5\6\4\2ki\3\2\2\2"+
		"kl\3\2\2\2lm\3\2\2\2mn\7\60\2\2n\u0094\3\2\2\2op\7%\2\2pq\7\62\2\2qr\7"+
		"\'\2\2rs\5\26\f\2st\7\6\2\2tw\5\26\f\2uv\7\6\2\2vx\5\26\f\2wu\3\2\2\2"+
		"wx\3\2\2\2xy\3\2\2\2yz\7\36\2\2z{\5\6\4\2{|\7\60\2\2|\u0094\3\2\2\2}~"+
		"\7%\2\2~\177\5\22\n\2\177\u0080\7\4\2\2\u0080\u0081\5\24\13\2\u0081\u0082"+
		"\7\36\2\2\u0082\u0083\5\6\4\2\u0083\u0084\7\60\2\2\u0084\u0094\3\2\2\2"+
		"\u0085\u0086\7\"\2\2\u0086\u0087\5\f\7\2\u0087\u0088\5\"\22\2\u0088\u0094"+
		"\3\2\2\2\u0089\u008a\7\5\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7\62\2\2"+
		"\u008c\u0094\5\"\22\2\u008d\u008e\7\5\2\2\u008e\u0091\5\22\n\2\u008f\u0090"+
		"\7\'\2\2\u0090\u0092\5\24\13\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2"+
		"\u0092\u0094\3\2\2\2\u0093G\3\2\2\2\u0093K\3\2\2\2\u0093L\3\2\2\2\u0093"+
		"P\3\2\2\2\u0093V\3\2\2\2\u0093[\3\2\2\2\u0093o\3\2\2\2\u0093}\3\2\2\2"+
		"\u0093\u0085\3\2\2\2\u0093\u0089\3\2\2\2\u0093\u008d\3\2\2\2\u0094\t\3"+
		"\2\2\2\u0095\u0097\7&\2\2\u0096\u0098\5\24\13\2\u0097\u0096\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u009b\7\30\2\2\u009a\u0095\3"+
		"\2\2\2\u009a\u0099\3\2\2\2\u009b\13\3\2\2\2\u009c\u009d\7\62\2\2\u009d"+
		"\u00a3\b\7\1\2\u009e\u009f\7!\2\2\u009f\u00a0\7\62\2\2\u00a0\u00a2\b\7"+
		"\1\2\u00a1\u009e\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a9\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7\f"+
		"\2\2\u00a7\u00a8\7\62\2\2\u00a8\u00aa\b\7\1\2\u00a9\u00a6\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\r\3\2\2\2\u00ab\u00ac\5\20\t\2\u00ac\u00b3\b\b\1"+
		"\2\u00ad\u00ae\7\6\2\2\u00ae\u00af\5\20\t\2\u00af\u00b0\b\b\1\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00ad\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\17\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7"+
		"\7\62\2\2\u00b7\u00c2\b\t\1\2\u00b8\u00b9\5\30\r\2\u00b9\u00ba\7\7\2\2"+
		"\u00ba\u00bb\5\26\f\2\u00bb\u00bc\7\3\2\2\u00bc\u00c2\3\2\2\2\u00bd\u00be"+
		"\5\30\r\2\u00be\u00bf\7!\2\2\u00bf\u00c0\7\62\2\2\u00c0\u00c2\3\2\2\2"+
		"\u00c1\u00b6\3\2\2\2\u00c1\u00b8\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c2\21"+
		"\3\2\2\2\u00c3\u00c4\7\62\2\2\u00c4\u00ca\b\n\1\2\u00c5\u00c6\7\6\2\2"+
		"\u00c6\u00c7\7\62\2\2\u00c7\u00c9\b\n\1\2\u00c8\u00c5\3\2\2\2\u00c9\u00cc"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\23\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00ce\5\26\f\2\u00ce\u00cf\7\6\2\2\u00cf\u00d1\3"+
		"\2\2\2\u00d0\u00cd\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2"+
		"\u00d3\3\2\2\2\u00d3\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\5\26"+
		"\f\2\u00d6\25\3\2\2\2\u00d7\u00d8\b\f\1\2\u00d8\u00d9\5\60\31\2\u00d9"+
		"\u00da\5\26\f\2\u00da\u00e5\3\2\2\2\u00db\u00e5\7\24\2\2\u00dc\u00e5\7"+
		"\21\2\2\u00dd\u00e5\7\34\2\2\u00de\u00e5\7\64\2\2\u00df\u00e5\7\63\2\2"+
		"\u00e0\u00e5\7\26\2\2\u00e1\u00e5\5 \21\2\u00e2\u00e5\5\32\16\2\u00e3"+
		"\u00e5\5&\24\2\u00e4\u00d7\3\2\2\2\u00e4\u00db\3\2\2\2\u00e4\u00dc\3\2"+
		"\2\2\u00e4\u00dd\3\2\2\2\u00e4\u00de\3\2\2\2\u00e4\u00df\3\2\2\2\u00e4"+
		"\u00e0\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3\3\2"+
		"\2\2\u00e5\u00ec\3\2\2\2\u00e6\u00e7\6\f\2\3\u00e7\u00e8\5.\30\2\u00e8"+
		"\u00e9\5\26\f\2\u00e9\u00eb\3\2\2\2\u00ea\u00e6\3\2\2\2\u00eb\u00ee\3"+
		"\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\27\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ef\u00f8\7\62\2\2\u00f0\u00f1\7\7\2\2\u00f1\u00f2\5"+
		"\26\f\2\u00f2\u00f3\7\3\2\2\u00f3\u00f7\3\2\2\2\u00f4\u00f5\7!\2\2\u00f5"+
		"\u00f7\7\62\2\2\u00f6\u00f0\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00fa\3"+
		"\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\31\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fb\u0102\5\20\t\2\u00fc\u0102\5\34\17\2\u00fd\u00fe"+
		"\7\r\2\2\u00fe\u00ff\5\26\f\2\u00ff\u0100\7#\2\2\u0100\u0102\3\2\2\2\u0101"+
		"\u00fb\3\2\2\2\u0101\u00fc\3\2\2\2\u0101\u00fd\3\2\2\2\u0102\33\3\2\2"+
		"\2\u0103\u0104\5\30\r\2\u0104\u0105\5\36\20\2\u0105\u010c\3\2\2\2\u0106"+
		"\u0107\5\30\r\2\u0107\u0108\7\f\2\2\u0108\u0109\7\62\2\2\u0109\u010a\5"+
		"\36\20\2\u010a\u010c\3\2\2\2\u010b\u0103\3\2\2\2\u010b\u0106\3\2\2\2\u010c"+
		"\35\3\2\2\2\u010d\u010f\7\r\2\2\u010e\u0110\5\24\13\2\u010f\u010e\3\2"+
		"\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0115\7#\2\2\u0112"+
		"\u0115\5&\24\2\u0113\u0115\7\63\2\2\u0114\u010d\3\2\2\2\u0114\u0112\3"+
		"\2\2\2\u0114\u0113\3\2\2\2\u0115\37\3\2\2\2\u0116\u0117\7\"\2\2\u0117"+
		"\u0118\5\"\22\2\u0118!\3\2\2\2\u0119\u011b\7\r\2\2\u011a\u011c\5$\23\2"+
		"\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e"+
		"\7#\2\2\u011e\u011f\5\6\4\2\u011f\u0120\7\60\2\2\u0120#\3\2\2\2\u0121"+
		"\u0124\5\22\n\2\u0122\u0123\7\6\2\2\u0123\u0125\7\26\2\2\u0124\u0122\3"+
		"\2\2\2\u0124\u0125\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0128\7\26\2\2\u0127"+
		"\u0121\3\2\2\2\u0127\u0126\3\2\2\2\u0128%\3\2\2\2\u0129\u012b\7\27\2\2"+
		"\u012a\u012c\5(\25\2\u012b\u012a\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d"+
		"\3\2\2\2\u012d\u012e\7\33\2\2\u012e\'\3\2\2\2\u012f\u0135\5*\26\2\u0130"+
		"\u0131\5,\27\2\u0131\u0132\5*\26\2\u0132\u0134\3\2\2\2\u0133\u0130\3\2"+
		"\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u013a\5,\27\2\u0139\u0138\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a)\3\2\2\2\u013b\u013c\7\7\2\2\u013c\u013d"+
		"\5\26\f\2\u013d\u013e\7\3\2\2\u013e\u013f\7\'\2\2\u013f\u0140\5\26\f\2"+
		"\u0140\u0146\3\2\2\2\u0141\u0142\7\62\2\2\u0142\u0143\7\'\2\2\u0143\u0146"+
		"\5\26\f\2\u0144\u0146\5\26\f\2\u0145\u013b\3\2\2\2\u0145\u0141\3\2\2\2"+
		"\u0145\u0144\3\2\2\2\u0146+\3\2\2\2\u0147\u0148\t\2\2\2\u0148-\3\2\2\2"+
		"\u0149\u014a\t\3\2\2\u014a/\3\2\2\2\u014b\u014c\t\4\2\2\u014c\61\3\2\2"+
		"\2\"8<ACfkw\u0091\u0093\u0097\u009a\u00a3\u00a9\u00b3\u00c1\u00ca\u00d2"+
		"\u00e4\u00ec\u00f6\u00f8\u0101\u010b\u010f\u0114\u011b\u0124\u0127\u012b"+
		"\u0135\u0139\u0145";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}