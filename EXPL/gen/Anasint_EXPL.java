// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/EXPL/src\Anasint_EXPL.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Anasint_EXPL extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLANCO=1, TABULADOR=2, FIN_LINEA=3, PARATODO=4, EXISTE=5, TRUE=6, FALSE=7, 
		INTEGER=8, BOOLEAN=9, IMPLICA=10, Y=11, O=12, DP=13, NO=14, NUMERO=15, 
		IDENT=16, PA=17, PC=18, PyC=19, COMA=20, ASIG=21, MAYOR=22, MENOR=23, 
		IGUAL=24, MAS=25, MENOS=26, MULT=27, DIV=28, COMENTARIO_BLOQUE=29, COMENTARIO_LINEA=30;
	public static final int
		RULE_sentencia = 0, RULE_expr = 1, RULE_cuantificador_universal = 2, RULE_cuantificador_existencial = 3, 
		RULE_predicado = 4, RULE_elto = 5, RULE_operacion_aritmetica = 6, RULE_variable = 7, 
		RULE_variables_cuantificadas = 8, RULE_funcion = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"sentencia", "expr", "cuantificador_universal", "cuantificador_existencial", 
			"predicado", "elto", "operacion_aritmetica", "variable", "variables_cuantificadas", 
			"funcion"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\t'", null, "'PARATODO'", "'EXISTE'", "'True'", "'False'", 
			"'Integer'", "'Boolean'", "'=>'", "'Y'", "'O'", "':'", "'NO'", null, 
			null, "'('", "')'", "';'", "','", "'='", "'>'", "'<'", "'=='", "'+'", 
			"'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLANCO", "TABULADOR", "FIN_LINEA", "PARATODO", "EXISTE", "TRUE", 
			"FALSE", "INTEGER", "BOOLEAN", "IMPLICA", "Y", "O", "DP", "NO", "NUMERO", 
			"IDENT", "PA", "PC", "PyC", "COMA", "ASIG", "MAYOR", "MENOR", "IGUAL", 
			"MAS", "MENOS", "MULT", "DIV", "COMENTARIO_BLOQUE", "COMENTARIO_LINEA"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Anasint_EXPL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Anasint_EXPL(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SentenciaContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Anasint_EXPL.EOF, 0); }
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitSentencia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitSentencia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sentencia);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			expr();
			setState(21);
			match(EOF);
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

	public static class ExprContext extends ParserRuleContext {
		public List<TerminalNode> PA() { return getTokens(Anasint_EXPL.PA); }
		public TerminalNode PA(int i) {
			return getToken(Anasint_EXPL.PA, i);
		}
		public List<TerminalNode> PC() { return getTokens(Anasint_EXPL.PC); }
		public TerminalNode PC(int i) {
			return getToken(Anasint_EXPL.PC, i);
		}
		public List<PredicadoContext> predicado() {
			return getRuleContexts(PredicadoContext.class);
		}
		public PredicadoContext predicado(int i) {
			return getRuleContext(PredicadoContext.class,i);
		}
		public List<Cuantificador_universalContext> cuantificador_universal() {
			return getRuleContexts(Cuantificador_universalContext.class);
		}
		public Cuantificador_universalContext cuantificador_universal(int i) {
			return getRuleContext(Cuantificador_universalContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		int _la;
		try {
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PA) {
					{
					{
					setState(23);
					match(PA);
					{
					setState(24);
					predicado(0);
					}
					setState(25);
					match(PC);
					}
					}
					setState(31);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PARATODO) {
					{
					{
					setState(32);
					cuantificador_universal();
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class Cuantificador_universalContext extends ParserRuleContext {
		public TerminalNode PARATODO() { return getToken(Anasint_EXPL.PARATODO, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TerminalNode COMA() { return getToken(Anasint_EXPL.COMA, 0); }
		public TerminalNode DP() { return getToken(Anasint_EXPL.DP, 0); }
		public TerminalNode PA() { return getToken(Anasint_EXPL.PA, 0); }
		public Variables_cuantificadasContext variables_cuantificadas() {
			return getRuleContext(Variables_cuantificadasContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint_EXPL.PC, 0); }
		public Cuantificador_universalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuantificador_universal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterCuantificador_universal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitCuantificador_universal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitCuantificador_universal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cuantificador_universalContext cuantificador_universal() throws RecognitionException {
		Cuantificador_universalContext _localctx = new Cuantificador_universalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cuantificador_universal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(PARATODO);
			setState(41);
			variable();
			setState(42);
			match(COMA);
			setState(43);
			variable();
			setState(44);
			match(DP);
			setState(45);
			match(PA);
			setState(46);
			variables_cuantificadas();
			setState(47);
			match(PC);
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

	public static class Cuantificador_existencialContext extends ParserRuleContext {
		public TerminalNode EXISTE() { return getToken(Anasint_EXPL.EXISTE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode DP() { return getToken(Anasint_EXPL.DP, 0); }
		public TerminalNode PA() { return getToken(Anasint_EXPL.PA, 0); }
		public PredicadoContext predicado() {
			return getRuleContext(PredicadoContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint_EXPL.PC, 0); }
		public Cuantificador_existencialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuantificador_existencial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterCuantificador_existencial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitCuantificador_existencial(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitCuantificador_existencial(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cuantificador_existencialContext cuantificador_existencial() throws RecognitionException {
		Cuantificador_existencialContext _localctx = new Cuantificador_existencialContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cuantificador_existencial);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(EXISTE);
			setState(50);
			variable();
			setState(51);
			match(DP);
			setState(52);
			match(PA);
			setState(53);
			predicado(0);
			setState(54);
			match(PC);
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

	public static class PredicadoContext extends ParserRuleContext {
		public List<EltoContext> elto() {
			return getRuleContexts(EltoContext.class);
		}
		public EltoContext elto(int i) {
			return getRuleContext(EltoContext.class,i);
		}
		public TerminalNode NO() { return getToken(Anasint_EXPL.NO, 0); }
		public TerminalNode IGUAL() { return getToken(Anasint_EXPL.IGUAL, 0); }
		public TerminalNode MENOR() { return getToken(Anasint_EXPL.MENOR, 0); }
		public TerminalNode MAYOR() { return getToken(Anasint_EXPL.MAYOR, 0); }
		public List<PredicadoContext> predicado() {
			return getRuleContexts(PredicadoContext.class);
		}
		public PredicadoContext predicado(int i) {
			return getRuleContext(PredicadoContext.class,i);
		}
		public TerminalNode Y() { return getToken(Anasint_EXPL.Y, 0); }
		public TerminalNode O() { return getToken(Anasint_EXPL.O, 0); }
		public PredicadoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicado; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterPredicado(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitPredicado(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitPredicado(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicadoContext predicado() throws RecognitionException {
		return predicado(0);
	}

	private PredicadoContext predicado(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PredicadoContext _localctx = new PredicadoContext(_ctx, _parentState);
		PredicadoContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_predicado, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(57);
				elto();
				setState(58);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAYOR) | (1L << MENOR) | (1L << IGUAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(59);
				elto();
				}
				break;
			case 2:
				{
				setState(61);
				match(NO);
				setState(62);
				elto();
				}
				break;
			case 3:
				{
				setState(63);
				elto();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PredicadoContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_predicado);
					setState(66);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(67);
					_la = _input.LA(1);
					if ( !(_la==Y || _la==O) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(68);
					predicado(3);
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class EltoContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode NUMERO() { return getToken(Anasint_EXPL.NUMERO, 0); }
		public FuncionContext funcion() {
			return getRuleContext(FuncionContext.class,0);
		}
		public Operacion_aritmeticaContext operacion_aritmetica() {
			return getRuleContext(Operacion_aritmeticaContext.class,0);
		}
		public EltoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterElto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitElto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitElto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EltoContext elto() throws RecognitionException {
		EltoContext _localctx = new EltoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_elto);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(NUMERO);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(76);
				funcion();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				operacion_aritmetica();
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

	public static class Operacion_aritmeticaContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> NUMERO() { return getTokens(Anasint_EXPL.NUMERO); }
		public TerminalNode NUMERO(int i) {
			return getToken(Anasint_EXPL.NUMERO, i);
		}
		public List<FuncionContext> funcion() {
			return getRuleContexts(FuncionContext.class);
		}
		public FuncionContext funcion(int i) {
			return getRuleContext(FuncionContext.class,i);
		}
		public List<TerminalNode> MAS() { return getTokens(Anasint_EXPL.MAS); }
		public TerminalNode MAS(int i) {
			return getToken(Anasint_EXPL.MAS, i);
		}
		public List<TerminalNode> MENOS() { return getTokens(Anasint_EXPL.MENOS); }
		public TerminalNode MENOS(int i) {
			return getToken(Anasint_EXPL.MENOS, i);
		}
		public List<TerminalNode> MULT() { return getTokens(Anasint_EXPL.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(Anasint_EXPL.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(Anasint_EXPL.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(Anasint_EXPL.DIV, i);
		}
		public Operacion_aritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacion_aritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterOperacion_aritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitOperacion_aritmetica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitOperacion_aritmetica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacion_aritmeticaContext operacion_aritmetica() throws RecognitionException {
		Operacion_aritmeticaContext _localctx = new Operacion_aritmeticaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_operacion_aritmetica);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(80);
				variable();
				}
				break;
			case 2:
				{
				setState(81);
				match(NUMERO);
				}
				break;
			case 3:
				{
				setState(82);
				funcion();
				}
				break;
			}
			setState(91); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(85);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAS) | (1L << MENOS) | (1L << MULT) | (1L << DIV))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(89);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(86);
						variable();
						}
						break;
					case 2:
						{
						setState(87);
						match(NUMERO);
						}
						break;
					case 3:
						{
						setState(88);
						funcion();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(93); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(Anasint_EXPL.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(IDENT);
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

	public static class Variables_cuantificadasContext extends ParserRuleContext {
		public List<PredicadoContext> predicado() {
			return getRuleContexts(PredicadoContext.class);
		}
		public PredicadoContext predicado(int i) {
			return getRuleContext(PredicadoContext.class,i);
		}
		public TerminalNode IMPLICA() { return getToken(Anasint_EXPL.IMPLICA, 0); }
		public Cuantificador_existencialContext cuantificador_existencial() {
			return getRuleContext(Cuantificador_existencialContext.class,0);
		}
		public Variables_cuantificadasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variables_cuantificadas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterVariables_cuantificadas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitVariables_cuantificadas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitVariables_cuantificadas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variables_cuantificadasContext variables_cuantificadas() throws RecognitionException {
		Variables_cuantificadasContext _localctx = new Variables_cuantificadasContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variables_cuantificadas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			predicado(0);
			setState(98);
			match(IMPLICA);
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NO:
			case NUMERO:
			case IDENT:
				{
				setState(99);
				predicado(0);
				}
				break;
			case EXISTE:
				{
				setState(100);
				cuantificador_existencial();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class FuncionContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode PA() { return getToken(Anasint_EXPL.PA, 0); }
		public TerminalNode PC() { return getToken(Anasint_EXPL.PC, 0); }
		public List<EltoContext> elto() {
			return getRuleContexts(EltoContext.class);
		}
		public EltoContext elto(int i) {
			return getRuleContext(EltoContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(Anasint_EXPL.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(Anasint_EXPL.COMA, i);
		}
		public FuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).enterFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Anasint_EXPLListener ) ((Anasint_EXPLListener)listener).exitFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Anasint_EXPLVisitor ) return ((Anasint_EXPLVisitor<? extends T>)visitor).visitFuncion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncionContext funcion() throws RecognitionException {
		FuncionContext _localctx = new FuncionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funcion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			variable();
			setState(104);
			match(PA);
			{
			setState(105);
			elto();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(106);
				match(COMA);
				setState(107);
				elto();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(113);
			match(PC);
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
		case 4:
			return predicado_sempred((PredicadoContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean predicado_sempred(PredicadoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 v\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\3\7\3$\n\3\f\3\16"+
		"\3\'\13\3\5\3)\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6C\n\6\3\6\3\6\3\6\7\6"+
		"H\n\6\f\6\16\6K\13\6\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b\3\b\5\bV\n\b\3\b"+
		"\3\b\3\b\3\b\5\b\\\n\b\6\b^\n\b\r\b\16\b_\3\t\3\t\3\n\3\n\3\n\3\n\5\n"+
		"h\n\n\3\13\3\13\3\13\3\13\3\13\7\13o\n\13\f\13\16\13r\13\13\3\13\3\13"+
		"\3\13\2\3\n\f\2\4\6\b\n\f\16\20\22\24\2\5\3\2\30\32\3\2\r\16\3\2\33\36"+
		"\2{\2\26\3\2\2\2\4(\3\2\2\2\6*\3\2\2\2\b\63\3\2\2\2\n:\3\2\2\2\fP\3\2"+
		"\2\2\16U\3\2\2\2\20a\3\2\2\2\22c\3\2\2\2\24i\3\2\2\2\26\27\5\4\3\2\27"+
		"\30\7\2\2\3\30\3\3\2\2\2\31\32\7\23\2\2\32\33\5\n\6\2\33\34\7\24\2\2\34"+
		"\36\3\2\2\2\35\31\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 )\3\2"+
		"\2\2!\37\3\2\2\2\"$\5\6\4\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2"+
		"&)\3\2\2\2\'%\3\2\2\2(\37\3\2\2\2(%\3\2\2\2)\5\3\2\2\2*+\7\6\2\2+,\5\20"+
		"\t\2,-\7\26\2\2-.\5\20\t\2./\7\17\2\2/\60\7\23\2\2\60\61\5\22\n\2\61\62"+
		"\7\24\2\2\62\7\3\2\2\2\63\64\7\7\2\2\64\65\5\20\t\2\65\66\7\17\2\2\66"+
		"\67\7\23\2\2\678\5\n\6\289\7\24\2\29\t\3\2\2\2:B\b\6\1\2;<\5\f\7\2<=\t"+
		"\2\2\2=>\5\f\7\2>C\3\2\2\2?@\7\20\2\2@C\5\f\7\2AC\5\f\7\2B;\3\2\2\2B?"+
		"\3\2\2\2BA\3\2\2\2CI\3\2\2\2DE\f\4\2\2EF\t\3\2\2FH\5\n\6\5GD\3\2\2\2H"+
		"K\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\13\3\2\2\2KI\3\2\2\2LQ\5\20\t\2MQ\7\21"+
		"\2\2NQ\5\24\13\2OQ\5\16\b\2PL\3\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\r"+
		"\3\2\2\2RV\5\20\t\2SV\7\21\2\2TV\5\24\13\2UR\3\2\2\2US\3\2\2\2UT\3\2\2"+
		"\2V]\3\2\2\2W[\t\4\2\2X\\\5\20\t\2Y\\\7\21\2\2Z\\\5\24\13\2[X\3\2\2\2"+
		"[Y\3\2\2\2[Z\3\2\2\2\\^\3\2\2\2]W\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2"+
		"\2`\17\3\2\2\2ab\7\22\2\2b\21\3\2\2\2cd\5\n\6\2dg\7\f\2\2eh\5\n\6\2fh"+
		"\5\b\5\2ge\3\2\2\2gf\3\2\2\2h\23\3\2\2\2ij\5\20\t\2jk\7\23\2\2kp\5\f\7"+
		"\2lm\7\26\2\2mo\5\f\7\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2"+
		"\2\2rp\3\2\2\2st\7\24\2\2t\25\3\2\2\2\r\37%(BIPU[_gp";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}