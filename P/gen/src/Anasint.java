// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src/src\Anasint.g4 by ANTLR 4.9
package src;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Anasint extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLANCO=1, TABULADOR=2, FIN_LINEA=3, TRUE=4, FALSE=5, INTEGER=6, BOOLEAN=7, 
		VARIABLES=8, SUBPROGRAMAS=9, INSTRUCCIONES=10, FUNCION=11, FFUNCION=12, 
		PROCEDIMIENTO=13, FPROCEDIMIENTO=14, RETORNO=15, OR=16, AND=17, UL_POS=18, 
		VACIA=19, NO=20, SEQUENCE=21, NUMERO=22, PA=23, CA=24, CC=25, MIENTRAS=26, 
		FMIENTRAS=27, SI=28, SINO=29, FSI=30, ENTONCES=31, HACER=32, DP=33, PC=34, 
		PyC=35, COMA=36, IGUAL=37, DIGUAL=38, CIERTO=39, FALSO=40, ASIG=41, MEIGUAL=42, 
		MAIGUAL=43, MAYOR=44, MENOR=45, MAS=46, MENOS=47, MULT=48, DIV=49, IDENT=50, 
		COMENTARIO_BLOQUE=51, COMENTARIO_LINEA=52;
	public static final int
		RULE_sentencia = 0, RULE_bloque_variables = 1, RULE_declaracion_variable = 2, 
		RULE_lista_variables = 3, RULE_lista_variables_tipadas = 4, RULE_tipo = 5, 
		RULE_tipo_no_elemental = 6, RULE_tipo_elemental = 7, RULE_bloque_funcion = 8, 
		RULE_bloque_instrucciones = 9, RULE_instruccion = 10, RULE_instruccion_bucle = 11, 
		RULE_instruccion_control = 12, RULE_instruccion_asig = 13, RULE_instruccion_retorno = 14, 
		RULE_predicado = 15, RULE_evaluacion_variable = 16, RULE_operacion_logica = 17, 
		RULE_operacion_aritmetica = 18, RULE_variable_acceso = 19, RULE_operando_universal = 20, 
		RULE_operando_aritmetico = 21, RULE_operando_logico = 22, RULE_operador_2_ario = 23, 
		RULE_operador_aritmetico_2_ario = 24, RULE_operador_logico_2_ario = 25, 
		RULE_operador_condicion_2_ario = 26, RULE_operador_condicion_1_ario = 27, 
		RULE_ultima_posicion = 28, RULE_vacia = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"sentencia", "bloque_variables", "declaracion_variable", "lista_variables", 
			"lista_variables_tipadas", "tipo", "tipo_no_elemental", "tipo_elemental", 
			"bloque_funcion", "bloque_instrucciones", "instruccion", "instruccion_bucle", 
			"instruccion_control", "instruccion_asig", "instruccion_retorno", "predicado", 
			"evaluacion_variable", "operacion_logica", "operacion_aritmetica", "variable_acceso", 
			"operando_universal", "operando_aritmetico", "operando_logico", "operador_2_ario", 
			"operador_aritmetico_2_ario", "operador_logico_2_ario", "operador_condicion_2_ario", 
			"operador_condicion_1_ario", "ultima_posicion", "vacia"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\t'", null, "'T'", "'F'", "'NUM'", "'LOG'", "'VARIABLES'", 
			"'SUBPROGRAMAS'", "'INSTRUCCIONES'", "'FUNCION'", "'FFUNCION'", "'PROCEDIMIENTO'", 
			"'FPROCEDIMIENTO'", "'dev'", "'||'", "'&&'", "'ultima_posicion'", "'vacia'", 
			"'!'", "'SEQ'", null, "'('", "'['", "']'", "'mientras'", "'fmientras'", 
			"'si'", "'sino'", "'fsi'", "'entonces'", "'hacer'", "':'", "')'", "';'", 
			"','", "'=='", "'!='", "'cierto'", "'falso'", "'='", "'<='", "'>='", 
			"'>'", "'<'", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLANCO", "TABULADOR", "FIN_LINEA", "TRUE", "FALSE", "INTEGER", 
			"BOOLEAN", "VARIABLES", "SUBPROGRAMAS", "INSTRUCCIONES", "FUNCION", "FFUNCION", 
			"PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", "OR", "AND", "UL_POS", 
			"VACIA", "NO", "SEQUENCE", "NUMERO", "PA", "CA", "CC", "MIENTRAS", "FMIENTRAS", 
			"SI", "SINO", "FSI", "ENTONCES", "HACER", "DP", "PC", "PyC", "COMA", 
			"IGUAL", "DIGUAL", "CIERTO", "FALSO", "ASIG", "MEIGUAL", "MAIGUAL", "MAYOR", 
			"MENOR", "MAS", "MENOS", "MULT", "DIV", "IDENT", "COMENTARIO_BLOQUE", 
			"COMENTARIO_LINEA"
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
	public String getGrammarFileName() { return "Anasint.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Anasint(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SentenciaContext extends ParserRuleContext {
		public List<Bloque_variablesContext> bloque_variables() {
			return getRuleContexts(Bloque_variablesContext.class);
		}
		public Bloque_variablesContext bloque_variables(int i) {
			return getRuleContext(Bloque_variablesContext.class,i);
		}
		public List<Bloque_funcionContext> bloque_funcion() {
			return getRuleContexts(Bloque_funcionContext.class);
		}
		public Bloque_funcionContext bloque_funcion(int i) {
			return getRuleContext(Bloque_funcionContext.class,i);
		}
		public List<Bloque_instruccionesContext> bloque_instrucciones() {
			return getRuleContexts(Bloque_instruccionesContext.class);
		}
		public Bloque_instruccionesContext bloque_instrucciones(int i) {
			return getRuleContext(Bloque_instruccionesContext.class,i);
		}
		public SentenciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentencia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterSentencia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitSentencia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitSentencia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenciaContext sentencia() throws RecognitionException {
		SentenciaContext _localctx = new SentenciaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sentencia);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLES) | (1L << INSTRUCCIONES) | (1L << FUNCION))) != 0)) {
				{
				setState(63);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VARIABLES:
					{
					setState(60);
					bloque_variables();
					}
					break;
				case FUNCION:
					{
					setState(61);
					bloque_funcion();
					}
					break;
				case INSTRUCCIONES:
					{
					setState(62);
					bloque_instrucciones();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(67);
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

	public static class Bloque_variablesContext extends ParserRuleContext {
		public TerminalNode VARIABLES() { return getToken(Anasint.VARIABLES, 0); }
		public TerminalNode EOF() { return getToken(Anasint.EOF, 0); }
		public TerminalNode SUBPROGRAMAS() { return getToken(Anasint.SUBPROGRAMAS, 0); }
		public Bloque_instruccionesContext bloque_instrucciones() {
			return getRuleContext(Bloque_instruccionesContext.class,0);
		}
		public List<Declaracion_variableContext> declaracion_variable() {
			return getRuleContexts(Declaracion_variableContext.class);
		}
		public Declaracion_variableContext declaracion_variable(int i) {
			return getRuleContext(Declaracion_variableContext.class,i);
		}
		public Bloque_variablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterBloque_variables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitBloque_variables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitBloque_variables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bloque_variablesContext bloque_variables() throws RecognitionException {
		Bloque_variablesContext _localctx = new Bloque_variablesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bloque_variables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(VARIABLES);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(69);
				declaracion_variable();
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				setState(75);
				match(EOF);
				}
				break;
			case SUBPROGRAMAS:
				{
				setState(76);
				match(SUBPROGRAMAS);
				}
				break;
			case INSTRUCCIONES:
				{
				setState(77);
				bloque_instrucciones();
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

	public static class Declaracion_variableContext extends ParserRuleContext {
		public Lista_variablesContext lista_variables() {
			return getRuleContext(Lista_variablesContext.class,0);
		}
		public TerminalNode DP() { return getToken(Anasint.DP, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode PyC() { return getToken(Anasint.PyC, 0); }
		public Declaracion_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterDeclaracion_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitDeclaracion_variable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitDeclaracion_variable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracion_variableContext declaracion_variable() throws RecognitionException {
		Declaracion_variableContext _localctx = new Declaracion_variableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaracion_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			lista_variables();
			setState(81);
			match(DP);
			setState(82);
			tipo();
			setState(83);
			match(PyC);
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

	public static class Lista_variablesContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(Anasint.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(Anasint.IDENT, i);
		}
		public List<TerminalNode> COMA() { return getTokens(Anasint.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(Anasint.COMA, i);
		}
		public Lista_variablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterLista_variables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitLista_variables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitLista_variables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_variablesContext lista_variables() throws RecognitionException {
		Lista_variablesContext _localctx = new Lista_variablesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lista_variables);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(85);
					match(IDENT);
					setState(86);
					match(COMA);
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(92);
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

	public static class Lista_variables_tipadasContext extends ParserRuleContext {
		public List<TipoContext> tipo() {
			return getRuleContexts(TipoContext.class);
		}
		public TipoContext tipo(int i) {
			return getRuleContext(TipoContext.class,i);
		}
		public List<TerminalNode> IDENT() { return getTokens(Anasint.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(Anasint.IDENT, i);
		}
		public List<TerminalNode> COMA() { return getTokens(Anasint.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(Anasint.COMA, i);
		}
		public Lista_variables_tipadasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_variables_tipadas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterLista_variables_tipadas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitLista_variables_tipadas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitLista_variables_tipadas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_variables_tipadasContext lista_variables_tipadas() throws RecognitionException {
		Lista_variables_tipadasContext _localctx = new Lista_variables_tipadasContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_lista_variables_tipadas);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94);
					tipo();
					setState(95);
					match(IDENT);
					setState(96);
					match(COMA);
					}
					} 
				}
				setState(102);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(103);
			tipo();
			setState(104);
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

	public static class TipoContext extends ParserRuleContext {
		public Tipo_elementalContext tipo_elemental() {
			return getRuleContext(Tipo_elementalContext.class,0);
		}
		public Tipo_no_elementalContext tipo_no_elemental() {
			return getRuleContext(Tipo_no_elementalContext.class,0);
		}
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitTipo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipo);
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				tipo_elemental();
				}
				break;
			case SEQUENCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				tipo_no_elemental();
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

	public static class Tipo_no_elementalContext extends ParserRuleContext {
		public TerminalNode SEQUENCE() { return getToken(Anasint.SEQUENCE, 0); }
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public Tipo_elementalContext tipo_elemental() {
			return getRuleContext(Tipo_elementalContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public Tipo_no_elementalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_no_elemental; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterTipo_no_elemental(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitTipo_no_elemental(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitTipo_no_elemental(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_no_elementalContext tipo_no_elemental() throws RecognitionException {
		Tipo_no_elementalContext _localctx = new Tipo_no_elementalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tipo_no_elemental);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(SEQUENCE);
			setState(111);
			match(PA);
			setState(112);
			tipo_elemental();
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

	public static class Tipo_elementalContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(Anasint.INTEGER, 0); }
		public TerminalNode BOOLEAN() { return getToken(Anasint.BOOLEAN, 0); }
		public Tipo_elementalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_elemental; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterTipo_elemental(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitTipo_elemental(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitTipo_elemental(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_elementalContext tipo_elemental() throws RecognitionException {
		Tipo_elementalContext _localctx = new Tipo_elementalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tipo_elemental);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==BOOLEAN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Bloque_funcionContext extends ParserRuleContext {
		public TerminalNode FUNCION() { return getToken(Anasint.FUNCION, 0); }
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public List<TerminalNode> PA() { return getTokens(Anasint.PA); }
		public TerminalNode PA(int i) {
			return getToken(Anasint.PA, i);
		}
		public List<Lista_variables_tipadasContext> lista_variables_tipadas() {
			return getRuleContexts(Lista_variables_tipadasContext.class);
		}
		public Lista_variables_tipadasContext lista_variables_tipadas(int i) {
			return getRuleContext(Lista_variables_tipadasContext.class,i);
		}
		public List<TerminalNode> PC() { return getTokens(Anasint.PC); }
		public TerminalNode PC(int i) {
			return getToken(Anasint.PC, i);
		}
		public TerminalNode RETORNO() { return getToken(Anasint.RETORNO, 0); }
		public TerminalNode EOF() { return getToken(Anasint.EOF, 0); }
		public Bloque_instruccionesContext bloque_instrucciones() {
			return getRuleContext(Bloque_instruccionesContext.class,0);
		}
		public Bloque_variablesContext bloque_variables() {
			return getRuleContext(Bloque_variablesContext.class,0);
		}
		public Bloque_funcionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque_funcion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterBloque_funcion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitBloque_funcion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitBloque_funcion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bloque_funcionContext bloque_funcion() throws RecognitionException {
		Bloque_funcionContext _localctx = new Bloque_funcionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_bloque_funcion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(FUNCION);
			setState(118);
			match(IDENT);
			setState(119);
			match(PA);
			setState(120);
			lista_variables_tipadas();
			setState(121);
			match(PC);
			setState(122);
			match(RETORNO);
			setState(123);
			match(PA);
			setState(124);
			lista_variables_tipadas();
			setState(125);
			match(PC);
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
				{
				setState(126);
				match(EOF);
				}
				break;
			case INSTRUCCIONES:
				{
				setState(127);
				bloque_instrucciones();
				}
				break;
			case VARIABLES:
				{
				setState(128);
				bloque_variables();
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

	public static class Bloque_instruccionesContext extends ParserRuleContext {
		public TerminalNode INSTRUCCIONES() { return getToken(Anasint.INSTRUCCIONES, 0); }
		public TerminalNode EOF() { return getToken(Anasint.EOF, 0); }
		public TerminalNode FFUNCION() { return getToken(Anasint.FFUNCION, 0); }
		public TerminalNode FPROCEDIMIENTO() { return getToken(Anasint.FPROCEDIMIENTO, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Bloque_instruccionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque_instrucciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterBloque_instrucciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitBloque_instrucciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitBloque_instrucciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bloque_instruccionesContext bloque_instrucciones() throws RecognitionException {
		Bloque_instruccionesContext _localctx = new Bloque_instruccionesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bloque_instrucciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(INSTRUCCIONES);
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				instruccion();
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETORNO) | (1L << MIENTRAS) | (1L << SI) | (1L << IDENT))) != 0) );
			setState(137);
			_la = _input.LA(1);
			if ( !(((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (FFUNCION - -1)) | (1L << (FPROCEDIMIENTO - -1)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class InstruccionContext extends ParserRuleContext {
		public Instruccion_asigContext instruccion_asig() {
			return getRuleContext(Instruccion_asigContext.class,0);
		}
		public Instruccion_bucleContext instruccion_bucle() {
			return getRuleContext(Instruccion_bucleContext.class,0);
		}
		public Instruccion_controlContext instruccion_control() {
			return getRuleContext(Instruccion_controlContext.class,0);
		}
		public Instruccion_retornoContext instruccion_retorno() {
			return getRuleContext(Instruccion_retornoContext.class,0);
		}
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterInstruccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitInstruccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitInstruccion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_instruccion);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				instruccion_asig();
				}
				break;
			case MIENTRAS:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				instruccion_bucle();
				}
				break;
			case SI:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				instruccion_control();
				}
				break;
			case RETORNO:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
				instruccion_retorno();
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

	public static class Instruccion_bucleContext extends ParserRuleContext {
		public TerminalNode MIENTRAS() { return getToken(Anasint.MIENTRAS, 0); }
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public PredicadoContext predicado() {
			return getRuleContext(PredicadoContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public TerminalNode HACER() { return getToken(Anasint.HACER, 0); }
		public TerminalNode FMIENTRAS() { return getToken(Anasint.FMIENTRAS, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Instruccion_bucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_bucle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterInstruccion_bucle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitInstruccion_bucle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitInstruccion_bucle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_bucleContext instruccion_bucle() throws RecognitionException {
		Instruccion_bucleContext _localctx = new Instruccion_bucleContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_instruccion_bucle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(MIENTRAS);
			setState(146);
			match(PA);
			setState(147);
			predicado();
			setState(148);
			match(PC);
			setState(149);
			match(HACER);
			setState(151); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(150);
				instruccion();
				}
				}
				setState(153); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETORNO) | (1L << MIENTRAS) | (1L << SI) | (1L << IDENT))) != 0) );
			setState(155);
			match(FMIENTRAS);
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

	public static class Instruccion_controlContext extends ParserRuleContext {
		public TerminalNode SI() { return getToken(Anasint.SI, 0); }
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public PredicadoContext predicado() {
			return getRuleContext(PredicadoContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public TerminalNode ENTONCES() { return getToken(Anasint.ENTONCES, 0); }
		public TerminalNode FSI() { return getToken(Anasint.FSI, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public List<TerminalNode> SINO() { return getTokens(Anasint.SINO); }
		public TerminalNode SINO(int i) {
			return getToken(Anasint.SINO, i);
		}
		public Instruccion_controlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterInstruccion_control(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitInstruccion_control(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitInstruccion_control(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_controlContext instruccion_control() throws RecognitionException {
		Instruccion_controlContext _localctx = new Instruccion_controlContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_instruccion_control);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(SI);
			setState(158);
			match(PA);
			setState(159);
			predicado();
			setState(160);
			match(PC);
			setState(161);
			match(ENTONCES);
			setState(163); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(162);
				instruccion();
				}
				}
				setState(165); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETORNO) | (1L << MIENTRAS) | (1L << SI) | (1L << IDENT))) != 0) );
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SINO) {
				{
				{
				setState(167);
				match(SINO);
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(168);
					instruccion();
					}
					}
					setState(171); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RETORNO) | (1L << MIENTRAS) | (1L << SI) | (1L << IDENT))) != 0) );
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
			match(FSI);
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

	public static class Instruccion_asigContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public TerminalNode ASIG() { return getToken(Anasint.ASIG, 0); }
		public Evaluacion_variableContext evaluacion_variable() {
			return getRuleContext(Evaluacion_variableContext.class,0);
		}
		public TerminalNode PyC() { return getToken(Anasint.PyC, 0); }
		public Instruccion_asigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_asig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterInstruccion_asig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitInstruccion_asig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitInstruccion_asig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_asigContext instruccion_asig() throws RecognitionException {
		Instruccion_asigContext _localctx = new Instruccion_asigContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_instruccion_asig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(IDENT);
			setState(181);
			match(ASIG);
			setState(182);
			evaluacion_variable();
			setState(183);
			match(PyC);
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

	public static class Instruccion_retornoContext extends ParserRuleContext {
		public TerminalNode RETORNO() { return getToken(Anasint.RETORNO, 0); }
		public Evaluacion_variableContext evaluacion_variable() {
			return getRuleContext(Evaluacion_variableContext.class,0);
		}
		public TerminalNode PyC() { return getToken(Anasint.PyC, 0); }
		public Instruccion_retornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_retorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterInstruccion_retorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitInstruccion_retorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitInstruccion_retorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_retornoContext instruccion_retorno() throws RecognitionException {
		Instruccion_retornoContext _localctx = new Instruccion_retornoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_instruccion_retorno);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(RETORNO);
			setState(186);
			evaluacion_variable();
			setState(187);
			match(PyC);
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
		public Operacion_logicaContext operacion_logica() {
			return getRuleContext(Operacion_logicaContext.class,0);
		}
		public List<Operador_condicion_2_arioContext> operador_condicion_2_ario() {
			return getRuleContexts(Operador_condicion_2_arioContext.class);
		}
		public Operador_condicion_2_arioContext operador_condicion_2_ario(int i) {
			return getRuleContext(Operador_condicion_2_arioContext.class,i);
		}
		public List<PredicadoContext> predicado() {
			return getRuleContexts(PredicadoContext.class);
		}
		public PredicadoContext predicado(int i) {
			return getRuleContext(PredicadoContext.class,i);
		}
		public List<TerminalNode> PA() { return getTokens(Anasint.PA); }
		public TerminalNode PA(int i) {
			return getToken(Anasint.PA, i);
		}
		public List<TerminalNode> PC() { return getTokens(Anasint.PC); }
		public TerminalNode PC(int i) {
			return getToken(Anasint.PC, i);
		}
		public PredicadoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicado; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterPredicado(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitPredicado(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitPredicado(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicadoContext predicado() throws RecognitionException {
		PredicadoContext _localctx = new PredicadoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_predicado);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			operacion_logica();
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(190);
					operador_condicion_2_ario();
					setState(196);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						setState(191);
						predicado();
						}
						break;
					case 2:
						{
						{
						setState(192);
						match(PA);
						setState(193);
						predicado();
						setState(194);
						match(PC);
						}
						}
						break;
					}
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

	public static class Evaluacion_variableContext extends ParserRuleContext {
		public PredicadoContext predicado() {
			return getRuleContext(PredicadoContext.class,0);
		}
		public TerminalNode NO() { return getToken(Anasint.NO, 0); }
		public Operacion_aritmeticaContext operacion_aritmetica() {
			return getRuleContext(Operacion_aritmeticaContext.class,0);
		}
		public Variable_accesoContext variable_acceso() {
			return getRuleContext(Variable_accesoContext.class,0);
		}
		public Ultima_posicionContext ultima_posicion() {
			return getRuleContext(Ultima_posicionContext.class,0);
		}
		public VaciaContext vacia() {
			return getRuleContext(VaciaContext.class,0);
		}
		public Evaluacion_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evaluacion_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterEvaluacion_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitEvaluacion_variable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitEvaluacion_variable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Evaluacion_variableContext evaluacion_variable() throws RecognitionException {
		Evaluacion_variableContext _localctx = new Evaluacion_variableContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_evaluacion_variable);
		int _la;
		try {
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NO) {
					{
					setState(203);
					match(NO);
					}
				}

				setState(206);
				predicado();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				operacion_aritmetica();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(208);
				variable_acceso();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(209);
				ultima_posicion();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NO) {
					{
					setState(210);
					match(NO);
					}
				}

				setState(213);
				vacia();
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

	public static class Operacion_logicaContext extends ParserRuleContext {
		public Operando_logicoContext operando_logico() {
			return getRuleContext(Operando_logicoContext.class,0);
		}
		public List<Operador_logico_2_arioContext> operador_logico_2_ario() {
			return getRuleContexts(Operador_logico_2_arioContext.class);
		}
		public Operador_logico_2_arioContext operador_logico_2_ario(int i) {
			return getRuleContext(Operador_logico_2_arioContext.class,i);
		}
		public List<Evaluacion_variableContext> evaluacion_variable() {
			return getRuleContexts(Evaluacion_variableContext.class);
		}
		public Evaluacion_variableContext evaluacion_variable(int i) {
			return getRuleContext(Evaluacion_variableContext.class,i);
		}
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public Operacion_logicaContext operacion_logica() {
			return getRuleContext(Operacion_logicaContext.class,0);
		}
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public Operacion_logicaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacion_logica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperacion_logica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperacion_logica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperacion_logica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacion_logicaContext operacion_logica() throws RecognitionException {
		Operacion_logicaContext _localctx = new Operacion_logicaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_operacion_logica);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PA:
				{
				{
				setState(216);
				match(PA);
				setState(217);
				operacion_logica();
				setState(218);
				match(PC);
				}
				}
				break;
			case TRUE:
			case FALSE:
			case UL_POS:
			case VACIA:
			case NUMERO:
			case IDENT:
				{
				setState(220);
				operando_logico();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(228);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(223);
					operador_logico_2_ario();
					setState(224);
					evaluacion_variable();
					}
					} 
				}
				setState(230);
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

	public static class Operacion_aritmeticaContext extends ParserRuleContext {
		public Operando_aritmeticoContext operando_aritmetico() {
			return getRuleContext(Operando_aritmeticoContext.class,0);
		}
		public List<Operador_aritmetico_2_arioContext> operador_aritmetico_2_ario() {
			return getRuleContexts(Operador_aritmetico_2_arioContext.class);
		}
		public Operador_aritmetico_2_arioContext operador_aritmetico_2_ario(int i) {
			return getRuleContext(Operador_aritmetico_2_arioContext.class,i);
		}
		public List<Operacion_aritmeticaContext> operacion_aritmetica() {
			return getRuleContexts(Operacion_aritmeticaContext.class);
		}
		public Operacion_aritmeticaContext operacion_aritmetica(int i) {
			return getRuleContext(Operacion_aritmeticaContext.class,i);
		}
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public Operacion_aritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacion_aritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperacion_aritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperacion_aritmetica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperacion_aritmetica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacion_aritmeticaContext operacion_aritmetica() throws RecognitionException {
		Operacion_aritmeticaContext _localctx = new Operacion_aritmeticaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_operacion_aritmetica);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PA:
				{
				{
				setState(231);
				match(PA);
				setState(232);
				operacion_aritmetica();
				setState(233);
				match(PC);
				}
				}
				break;
			case UL_POS:
			case NUMERO:
			case IDENT:
				{
				setState(235);
				operando_aritmetico();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(238);
					operador_aritmetico_2_ario();
					setState(239);
					operacion_aritmetica();
					}
					} 
				}
				setState(245);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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

	public static class Variable_accesoContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public TerminalNode CA() { return getToken(Anasint.CA, 0); }
		public Operando_aritmeticoContext operando_aritmetico() {
			return getRuleContext(Operando_aritmeticoContext.class,0);
		}
		public TerminalNode CC() { return getToken(Anasint.CC, 0); }
		public Variable_accesoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_acceso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterVariable_acceso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitVariable_acceso(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitVariable_acceso(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_accesoContext variable_acceso() throws RecognitionException {
		Variable_accesoContext _localctx = new Variable_accesoContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variable_acceso);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(IDENT);
			setState(247);
			match(CA);
			setState(248);
			operando_aritmetico();
			setState(249);
			match(CC);
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

	public static class Operando_universalContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public TerminalNode NUMERO() { return getToken(Anasint.NUMERO, 0); }
		public Variable_accesoContext variable_acceso() {
			return getRuleContext(Variable_accesoContext.class,0);
		}
		public Ultima_posicionContext ultima_posicion() {
			return getRuleContext(Ultima_posicionContext.class,0);
		}
		public Operando_universalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando_universal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperando_universal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperando_universal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperando_universal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operando_universalContext operando_universal() throws RecognitionException {
		Operando_universalContext _localctx = new Operando_universalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_operando_universal);
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				match(NUMERO);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(253);
				variable_acceso();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(254);
				ultima_posicion();
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

	public static class Operando_aritmeticoContext extends ParserRuleContext {
		public Operando_universalContext operando_universal() {
			return getRuleContext(Operando_universalContext.class,0);
		}
		public Operando_aritmeticoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando_aritmetico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperando_aritmetico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperando_aritmetico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperando_aritmetico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operando_aritmeticoContext operando_aritmetico() throws RecognitionException {
		Operando_aritmeticoContext _localctx = new Operando_aritmeticoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_operando_aritmetico);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			operando_universal();
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

	public static class Operando_logicoContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(Anasint.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Anasint.FALSE, 0); }
		public Operando_universalContext operando_universal() {
			return getRuleContext(Operando_universalContext.class,0);
		}
		public VaciaContext vacia() {
			return getRuleContext(VaciaContext.class,0);
		}
		public Operando_logicoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operando_logico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperando_logico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperando_logico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperando_logico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operando_logicoContext operando_logico() throws RecognitionException {
		Operando_logicoContext _localctx = new Operando_logicoContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_operando_logico);
		try {
			setState(263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(FALSE);
				}
				break;
			case UL_POS:
			case NUMERO:
			case IDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(261);
				operando_universal();
				}
				break;
			case VACIA:
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				vacia();
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

	public static class Operador_2_arioContext extends ParserRuleContext {
		public Operador_aritmetico_2_arioContext operador_aritmetico_2_ario() {
			return getRuleContext(Operador_aritmetico_2_arioContext.class,0);
		}
		public Operador_logico_2_arioContext operador_logico_2_ario() {
			return getRuleContext(Operador_logico_2_arioContext.class,0);
		}
		public Operador_2_arioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operador_2_ario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperador_2_ario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperador_2_ario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperador_2_ario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operador_2_arioContext operador_2_ario() throws RecognitionException {
		Operador_2_arioContext _localctx = new Operador_2_arioContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_operador_2_ario);
		try {
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MAS:
			case MENOS:
			case MULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				operador_aritmetico_2_ario();
				}
				break;
			case IGUAL:
			case DIGUAL:
			case MEIGUAL:
			case MAIGUAL:
			case MAYOR:
			case MENOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				operador_logico_2_ario();
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

	public static class Operador_aritmetico_2_arioContext extends ParserRuleContext {
		public TerminalNode MAS() { return getToken(Anasint.MAS, 0); }
		public TerminalNode MENOS() { return getToken(Anasint.MENOS, 0); }
		public TerminalNode MULT() { return getToken(Anasint.MULT, 0); }
		public Operador_aritmetico_2_arioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operador_aritmetico_2_ario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperador_aritmetico_2_ario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperador_aritmetico_2_ario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperador_aritmetico_2_ario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operador_aritmetico_2_arioContext operador_aritmetico_2_ario() throws RecognitionException {
		Operador_aritmetico_2_arioContext _localctx = new Operador_aritmetico_2_arioContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_operador_aritmetico_2_ario);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MAS) | (1L << MENOS) | (1L << MULT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Operador_logico_2_arioContext extends ParserRuleContext {
		public TerminalNode IGUAL() { return getToken(Anasint.IGUAL, 0); }
		public TerminalNode DIGUAL() { return getToken(Anasint.DIGUAL, 0); }
		public TerminalNode MAYOR() { return getToken(Anasint.MAYOR, 0); }
		public TerminalNode MENOR() { return getToken(Anasint.MENOR, 0); }
		public TerminalNode MAIGUAL() { return getToken(Anasint.MAIGUAL, 0); }
		public TerminalNode MEIGUAL() { return getToken(Anasint.MEIGUAL, 0); }
		public Operador_logico_2_arioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operador_logico_2_ario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperador_logico_2_ario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperador_logico_2_ario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperador_logico_2_ario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operador_logico_2_arioContext operador_logico_2_ario() throws RecognitionException {
		Operador_logico_2_arioContext _localctx = new Operador_logico_2_arioContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_operador_logico_2_ario);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IGUAL) | (1L << DIGUAL) | (1L << MEIGUAL) | (1L << MAIGUAL) | (1L << MAYOR) | (1L << MENOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Operador_condicion_2_arioContext extends ParserRuleContext {
		public Operador_logico_2_arioContext operador_logico_2_ario() {
			return getRuleContext(Operador_logico_2_arioContext.class,0);
		}
		public TerminalNode AND() { return getToken(Anasint.AND, 0); }
		public TerminalNode OR() { return getToken(Anasint.OR, 0); }
		public Operador_condicion_2_arioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operador_condicion_2_ario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperador_condicion_2_ario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperador_condicion_2_ario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperador_condicion_2_ario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operador_condicion_2_arioContext operador_condicion_2_ario() throws RecognitionException {
		Operador_condicion_2_arioContext _localctx = new Operador_condicion_2_arioContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_operador_condicion_2_ario);
		try {
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IGUAL:
			case DIGUAL:
			case MEIGUAL:
			case MAIGUAL:
			case MAYOR:
			case MENOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				operador_logico_2_ario();
				}
				break;
			case AND:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				match(AND);
				}
				break;
			case OR:
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				match(OR);
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

	public static class Operador_condicion_1_arioContext extends ParserRuleContext {
		public TerminalNode NO() { return getToken(Anasint.NO, 0); }
		public Operador_condicion_1_arioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operador_condicion_1_ario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterOperador_condicion_1_ario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitOperador_condicion_1_ario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitOperador_condicion_1_ario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operador_condicion_1_arioContext operador_condicion_1_ario() throws RecognitionException {
		Operador_condicion_1_arioContext _localctx = new Operador_condicion_1_arioContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_operador_condicion_1_ario);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(NO);
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

	public static class Ultima_posicionContext extends ParserRuleContext {
		public TerminalNode UL_POS() { return getToken(Anasint.UL_POS, 0); }
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public Ultima_posicionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ultima_posicion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterUltima_posicion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitUltima_posicion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitUltima_posicion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ultima_posicionContext ultima_posicion() throws RecognitionException {
		Ultima_posicionContext _localctx = new Ultima_posicionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ultima_posicion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(UL_POS);
			setState(281);
			match(PA);
			setState(282);
			match(IDENT);
			setState(283);
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

	public static class VaciaContext extends ParserRuleContext {
		public TerminalNode VACIA() { return getToken(Anasint.VACIA, 0); }
		public TerminalNode PA() { return getToken(Anasint.PA, 0); }
		public TerminalNode IDENT() { return getToken(Anasint.IDENT, 0); }
		public TerminalNode PC() { return getToken(Anasint.PC, 0); }
		public VaciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vacia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).enterVacia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AnasintListener ) ((AnasintListener)listener).exitVacia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnasintVisitor ) return ((AnasintVisitor<? extends T>)visitor).visitVacia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VaciaContext vacia() throws RecognitionException {
		VaciaContext _localctx = new VaciaContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_vacia);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(VACIA);
			setState(286);
			match(PA);
			setState(287);
			match(IDENT);
			setState(288);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u0125\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\7\2B\n\2\f\2\16\2E\13\2\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\3"+
		"\5\3Q\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\7\5Z\n\5\f\5\16\5]\13\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\7\6e\n\6\f\6\16\6h\13\6\3\6\3\6\3\6\3\7\3\7\5\7o\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\5\n\u0084\n\n\3\13\3\13\6\13\u0088\n\13\r\13\16\13\u0089\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\5\f\u0092\n\f\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u009a\n"+
		"\r\r\r\16\r\u009b\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\6\16\u00a6\n\16"+
		"\r\16\16\16\u00a7\3\16\3\16\6\16\u00ac\n\16\r\16\16\16\u00ad\7\16\u00b0"+
		"\n\16\f\16\16\16\u00b3\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c7\n\21\7\21"+
		"\u00c9\n\21\f\21\16\21\u00cc\13\21\3\22\5\22\u00cf\n\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u00d6\n\22\3\22\5\22\u00d9\n\22\3\23\3\23\3\23\3\23\3"+
		"\23\5\23\u00e0\n\23\3\23\3\23\3\23\7\23\u00e5\n\23\f\23\16\23\u00e8\13"+
		"\23\3\24\3\24\3\24\3\24\3\24\5\24\u00ef\n\24\3\24\3\24\3\24\7\24\u00f4"+
		"\n\24\f\24\16\24\u00f7\13\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\5\26\u0102\n\26\3\27\3\27\3\30\3\30\3\30\3\30\5\30\u010a\n\30\3\31"+
		"\3\31\5\31\u010e\n\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\5\34\u0117\n"+
		"\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\2"+
		"\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<\2\6"+
		"\3\2\b\t\4\3\16\16\20\20\3\2\60\62\4\2\'(,/\2\u012e\2C\3\2\2\2\4F\3\2"+
		"\2\2\6R\3\2\2\2\b[\3\2\2\2\nf\3\2\2\2\fn\3\2\2\2\16p\3\2\2\2\20u\3\2\2"+
		"\2\22w\3\2\2\2\24\u0085\3\2\2\2\26\u0091\3\2\2\2\30\u0093\3\2\2\2\32\u009f"+
		"\3\2\2\2\34\u00b6\3\2\2\2\36\u00bb\3\2\2\2 \u00bf\3\2\2\2\"\u00d8\3\2"+
		"\2\2$\u00df\3\2\2\2&\u00ee\3\2\2\2(\u00f8\3\2\2\2*\u0101\3\2\2\2,\u0103"+
		"\3\2\2\2.\u0109\3\2\2\2\60\u010d\3\2\2\2\62\u010f\3\2\2\2\64\u0111\3\2"+
		"\2\2\66\u0116\3\2\2\28\u0118\3\2\2\2:\u011a\3\2\2\2<\u011f\3\2\2\2>B\5"+
		"\4\3\2?B\5\22\n\2@B\5\24\13\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2BE\3\2\2\2"+
		"CA\3\2\2\2CD\3\2\2\2D\3\3\2\2\2EC\3\2\2\2FJ\7\n\2\2GI\5\6\4\2HG\3\2\2"+
		"\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KP\3\2\2\2LJ\3\2\2\2MQ\7\2\2\3NQ\7\13"+
		"\2\2OQ\5\24\13\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\5\3\2\2\2RS\5\b\5\2ST"+
		"\7#\2\2TU\5\f\7\2UV\7%\2\2V\7\3\2\2\2WX\7\64\2\2XZ\7&\2\2YW\3\2\2\2Z]"+
		"\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7\64\2\2_\t\3\2\2"+
		"\2`a\5\f\7\2ab\7\64\2\2bc\7&\2\2ce\3\2\2\2d`\3\2\2\2eh\3\2\2\2fd\3\2\2"+
		"\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\5\f\7\2jk\7\64\2\2k\13\3\2\2\2lo\5"+
		"\20\t\2mo\5\16\b\2nl\3\2\2\2nm\3\2\2\2o\r\3\2\2\2pq\7\27\2\2qr\7\31\2"+
		"\2rs\5\20\t\2st\7$\2\2t\17\3\2\2\2uv\t\2\2\2v\21\3\2\2\2wx\7\r\2\2xy\7"+
		"\64\2\2yz\7\31\2\2z{\5\n\6\2{|\7$\2\2|}\7\21\2\2}~\7\31\2\2~\177\5\n\6"+
		"\2\177\u0083\7$\2\2\u0080\u0084\7\2\2\3\u0081\u0084\5\24\13\2\u0082\u0084"+
		"\5\4\3\2\u0083\u0080\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\23\3\2\2\2\u0085\u0087\7\f\2\2\u0086\u0088\5\26\f\2\u0087\u0086\3\2\2"+
		"\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b"+
		"\3\2\2\2\u008b\u008c\t\3\2\2\u008c\25\3\2\2\2\u008d\u0092\5\34\17\2\u008e"+
		"\u0092\5\30\r\2\u008f\u0092\5\32\16\2\u0090\u0092\5\36\20\2\u0091\u008d"+
		"\3\2\2\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\27\3\2\2\2\u0093\u0094\7\34\2\2\u0094\u0095\7\31\2\2\u0095\u0096\5 \21"+
		"\2\u0096\u0097\7$\2\2\u0097\u0099\7\"\2\2\u0098\u009a\5\26\f\2\u0099\u0098"+
		"\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\7\35\2\2\u009e\31\3\2\2\2\u009f\u00a0\7\36"+
		"\2\2\u00a0\u00a1\7\31\2\2\u00a1\u00a2\5 \21\2\u00a2\u00a3\7$\2\2\u00a3"+
		"\u00a5\7!\2\2\u00a4\u00a6\5\26\f\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00b1\3\2\2\2\u00a9"+
		"\u00ab\7\37\2\2\u00aa\u00ac\5\26\f\2\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af"+
		"\u00a9\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7 \2\2\u00b5"+
		"\33\3\2\2\2\u00b6\u00b7\7\64\2\2\u00b7\u00b8\7+\2\2\u00b8\u00b9\5\"\22"+
		"\2\u00b9\u00ba\7%\2\2\u00ba\35\3\2\2\2\u00bb\u00bc\7\21\2\2\u00bc\u00bd"+
		"\5\"\22\2\u00bd\u00be\7%\2\2\u00be\37\3\2\2\2\u00bf\u00ca\5$\23\2\u00c0"+
		"\u00c6\5\66\34\2\u00c1\u00c7\5 \21\2\u00c2\u00c3\7\31\2\2\u00c3\u00c4"+
		"\5 \21\2\u00c4\u00c5\7$\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c1\3\2\2\2\u00c6"+
		"\u00c2\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c0\3\2\2\2\u00c9\u00cc\3\2"+
		"\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb!\3\2\2\2\u00cc\u00ca"+
		"\3\2\2\2\u00cd\u00cf\7\26\2\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2"+
		"\u00cf\u00d0\3\2\2\2\u00d0\u00d9\5 \21\2\u00d1\u00d9\5&\24\2\u00d2\u00d9"+
		"\5(\25\2\u00d3\u00d9\5:\36\2\u00d4\u00d6\7\26\2\2\u00d5\u00d4\3\2\2\2"+
		"\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\5<\37\2\u00d8\u00ce"+
		"\3\2\2\2\u00d8\u00d1\3\2\2\2\u00d8\u00d2\3\2\2\2\u00d8\u00d3\3\2\2\2\u00d8"+
		"\u00d5\3\2\2\2\u00d9#\3\2\2\2\u00da\u00db\7\31\2\2\u00db\u00dc\5$\23\2"+
		"\u00dc\u00dd\7$\2\2\u00dd\u00e0\3\2\2\2\u00de\u00e0\5.\30\2\u00df\u00da"+
		"\3\2\2\2\u00df\u00de\3\2\2\2\u00e0\u00e6\3\2\2\2\u00e1\u00e2\5\64\33\2"+
		"\u00e2\u00e3\5\"\22\2\u00e3\u00e5\3\2\2\2\u00e4\u00e1\3\2\2\2\u00e5\u00e8"+
		"\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7%\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e9\u00ea\7\31\2\2\u00ea\u00eb\5&\24\2\u00eb\u00ec\7"+
		"$\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00ef\5,\27\2\u00ee\u00e9\3\2\2\2\u00ee"+
		"\u00ed\3\2\2\2\u00ef\u00f5\3\2\2\2\u00f0\u00f1\5\62\32\2\u00f1\u00f2\5"+
		"&\24\2\u00f2\u00f4\3\2\2\2\u00f3\u00f0\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\'\3\2\2\2\u00f7\u00f5\3\2\2\2"+
		"\u00f8\u00f9\7\64\2\2\u00f9\u00fa\7\32\2\2\u00fa\u00fb\5,\27\2\u00fb\u00fc"+
		"\7\33\2\2\u00fc)\3\2\2\2\u00fd\u0102\7\64\2\2\u00fe\u0102\7\30\2\2\u00ff"+
		"\u0102\5(\25\2\u0100\u0102\5:\36\2\u0101\u00fd\3\2\2\2\u0101\u00fe\3\2"+
		"\2\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102+\3\2\2\2\u0103\u0104"+
		"\5*\26\2\u0104-\3\2\2\2\u0105\u010a\7\6\2\2\u0106\u010a\7\7\2\2\u0107"+
		"\u010a\5*\26\2\u0108\u010a\5<\37\2\u0109\u0105\3\2\2\2\u0109\u0106\3\2"+
		"\2\2\u0109\u0107\3\2\2\2\u0109\u0108\3\2\2\2\u010a/\3\2\2\2\u010b\u010e"+
		"\5\62\32\2\u010c\u010e\5\64\33\2\u010d\u010b\3\2\2\2\u010d\u010c\3\2\2"+
		"\2\u010e\61\3\2\2\2\u010f\u0110\t\4\2\2\u0110\63\3\2\2\2\u0111\u0112\t"+
		"\5\2\2\u0112\65\3\2\2\2\u0113\u0117\5\64\33\2\u0114\u0117\7\23\2\2\u0115"+
		"\u0117\7\22\2\2\u0116\u0113\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0115\3"+
		"\2\2\2\u0117\67\3\2\2\2\u0118\u0119\7\26\2\2\u01199\3\2\2\2\u011a\u011b"+
		"\7\24\2\2\u011b\u011c\7\31\2\2\u011c\u011d\7\64\2\2\u011d\u011e\7$\2\2"+
		"\u011e;\3\2\2\2\u011f\u0120\7\25\2\2\u0120\u0121\7\31\2\2\u0121\u0122"+
		"\7\64\2\2\u0122\u0123\7$\2\2\u0123=\3\2\2\2\35ACJP[fn\u0083\u0089\u0091"+
		"\u009b\u00a7\u00ad\u00b1\u00c6\u00ca\u00ce\u00d5\u00d8\u00df\u00e6\u00ee"+
		"\u00f5\u0101\u0109\u010d\u0116";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}