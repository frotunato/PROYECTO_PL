// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src\Analex.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Analex extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FIN_LINEA=1, COMENTARIO_BLOQUE=2, COMENTARIO_LINEA=3, BLANCO=4, TABULADOR=5, 
		PARATODO=6, EXISTE=7, TRUE=8, FALSE=9, INTEGER=10, BOOLEAN=11, VARIABLES=12, 
		PROGRAMA=13, SUBPROGRAMAS=14, INSTRUCCIONES=15, FUNCION=16, FFUNCION=17, 
		PROCEDIMIENTO=18, FPROCEDIMIENTO=19, RETORNO=20, RUPTURA=21, OR=22, AND=23, 
		UL_POS=24, VACIA=25, NO=26, SEQUENCE=27, NUMERO=28, PA=29, CA=30, CC=31, 
		MOSTRAR=32, MIENTRAS=33, FMIENTRAS=34, AVANCE=35, SI=36, SINO=37, FSI=38, 
		ENTONCES=39, HACER=40, DP=41, PC=42, LLA=43, LLC=44, PyC=45, COMA=46, 
		IGUAL=47, DIGUAL=48, CIERTO=49, FALSO=50, ASIG=51, MEIGUAL=52, MAIGUAL=53, 
		MAYOR=54, MENOR=55, MAS=56, MENOS=57, MULT=58, IDENT=59;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FIN_LINEA", "COMENTARIO_BLOQUE", "COMENTARIO_LINEA", "BLANCO", "TABULADOR", 
			"DIGITO", "LETRA", "UNDERSCORE", "PARATODO", "EXISTE", "TRUE", "FALSE", 
			"INTEGER", "BOOLEAN", "VARIABLES", "PROGRAMA", "SUBPROGRAMAS", "INSTRUCCIONES", 
			"FUNCION", "FFUNCION", "PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", 
			"RUPTURA", "OR", "AND", "UL_POS", "VACIA", "NO", "SEQUENCE", "NUMERO", 
			"PA", "CA", "CC", "MOSTRAR", "MIENTRAS", "FMIENTRAS", "AVANCE", "SI", 
			"SINO", "FSI", "ENTONCES", "HACER", "DP", "PC", "LLA", "LLC", "PyC", 
			"COMA", "IGUAL", "DIGUAL", "CIERTO", "FALSO", "ASIG", "MEIGUAL", "MAIGUAL", 
			"MAYOR", "MENOR", "MAS", "MENOS", "MULT", "IDENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "' '", "'\t'", "'PARATODO'", "'EXISTE'", "'T'", 
			"'F'", "'NUM'", "'LOG'", "'VARIABLES'", "'PROGRAMA'", "'SUBPROGRAMAS'", 
			"'INSTRUCCIONES'", "'FUNCION'", "'FFUNCION'", "'PROCEDIMIENTO'", "'FPROCEDIMIENTO'", 
			"'dev'", "'ruptura'", "'||'", "'&&'", "'ultima_posicion'", "'vacia'", 
			"'!'", "'SEQ'", null, "'('", "'['", "']'", "'mostrar'", "'mientras'", 
			"'fmientras'", "'avance'", "'si'", "'sino'", "'fsi'", "'entonces'", "'hacer'", 
			"':'", "')'", "'{'", "'}'", "';'", "','", "'=='", "'!='", "'cierto'", 
			"'falso'", "'='", "'<='", "'>='", "'>'", "'<'", "'+'", "'-'", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FIN_LINEA", "COMENTARIO_BLOQUE", "COMENTARIO_LINEA", "BLANCO", 
			"TABULADOR", "PARATODO", "EXISTE", "TRUE", "FALSE", "INTEGER", "BOOLEAN", 
			"VARIABLES", "PROGRAMA", "SUBPROGRAMAS", "INSTRUCCIONES", "FUNCION", 
			"FFUNCION", "PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", "RUPTURA", 
			"OR", "AND", "UL_POS", "VACIA", "NO", "SEQUENCE", "NUMERO", "PA", "CA", 
			"CC", "MOSTRAR", "MIENTRAS", "FMIENTRAS", "AVANCE", "SI", "SINO", "FSI", 
			"ENTONCES", "HACER", "DP", "PC", "LLA", "LLC", "PyC", "COMA", "IGUAL", 
			"DIGUAL", "CIERTO", "FALSO", "ASIG", "MEIGUAL", "MAIGUAL", "MAYOR", "MENOR", 
			"MAS", "MENOS", "MULT", "IDENT"
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


	public Analex(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Analex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u01d7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\5\2\u0081\n\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u008b"+
		"\n\3\f\3\16\3\u008e\13\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0099"+
		"\n\4\f\4\16\4\u009c\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37"+
		"\3\37\3 \6 \u0157\n \r \16 \u0158\3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3"+
		"\61\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\38\38\38\39\39\39\3:"+
		"\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3?\3?\7?\u01d3\n?\f?\16?\u01d6\13?\4"+
		"\u008c\u009a\2@\3\3\5\4\7\5\t\6\13\7\r\2\17\2\21\2\23\b\25\t\27\n\31\13"+
		"\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67"+
		"\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62"+
		"i\63k\64m\65o\66q\67s8u9w:y;{<}=\3\2\4\3\2\62;\4\2C\\c|\2\u01da\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2"+
		"\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2"+
		"\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\3\u0080\3\2\2\2\5\u0086\3"+
		"\2\2\2\7\u0094\3\2\2\2\t\u00a1\3\2\2\2\13\u00a5\3\2\2\2\r\u00a9\3\2\2"+
		"\2\17\u00ab\3\2\2\2\21\u00ad\3\2\2\2\23\u00af\3\2\2\2\25\u00b8\3\2\2\2"+
		"\27\u00bf\3\2\2\2\31\u00c1\3\2\2\2\33\u00c3\3\2\2\2\35\u00c7\3\2\2\2\37"+
		"\u00cb\3\2\2\2!\u00d5\3\2\2\2#\u00de\3\2\2\2%\u00eb\3\2\2\2\'\u00f9\3"+
		"\2\2\2)\u0101\3\2\2\2+\u010a\3\2\2\2-\u0118\3\2\2\2/\u0127\3\2\2\2\61"+
		"\u012b\3\2\2\2\63\u0133\3\2\2\2\65\u0136\3\2\2\2\67\u0139\3\2\2\29\u0149"+
		"\3\2\2\2;\u014f\3\2\2\2=\u0151\3\2\2\2?\u0156\3\2\2\2A\u015a\3\2\2\2C"+
		"\u015c\3\2\2\2E\u015e\3\2\2\2G\u0160\3\2\2\2I\u0168\3\2\2\2K\u0171\3\2"+
		"\2\2M\u017b\3\2\2\2O\u0182\3\2\2\2Q\u0185\3\2\2\2S\u018a\3\2\2\2U\u018e"+
		"\3\2\2\2W\u0197\3\2\2\2Y\u019d\3\2\2\2[\u019f\3\2\2\2]\u01a1\3\2\2\2_"+
		"\u01a3\3\2\2\2a\u01a5\3\2\2\2c\u01a7\3\2\2\2e\u01a9\3\2\2\2g\u01ac\3\2"+
		"\2\2i\u01af\3\2\2\2k\u01b6\3\2\2\2m\u01bc\3\2\2\2o\u01be\3\2\2\2q\u01c1"+
		"\3\2\2\2s\u01c4\3\2\2\2u\u01c6\3\2\2\2w\u01c8\3\2\2\2y\u01ca\3\2\2\2{"+
		"\u01cc\3\2\2\2}\u01ce\3\2\2\2\177\u0081\7\17\2\2\u0080\177\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\f\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\u0085\b\2\2\2\u0085\4\3\2\2\2\u0086\u0087\7\61\2\2\u0087\u0088"+
		"\7,\2\2\u0088\u008c\3\2\2\2\u0089\u008b\13\2\2\2\u008a\u0089\3\2\2\2\u008b"+
		"\u008e\3\2\2\2\u008c\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008f\3\2"+
		"\2\2\u008e\u008c\3\2\2\2\u008f\u0090\7,\2\2\u0090\u0091\7\61\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\b\3\2\2\u0093\6\3\2\2\2\u0094\u0095\7\61\2"+
		"\2\u0095\u0096\7\61\2\2\u0096\u009a\3\2\2\2\u0097\u0099\13\2\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u009b\3\2\2\2\u009a\u0098\3\2"+
		"\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\5\3\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a0\b\4\2\2\u00a0\b\3\2\2\2\u00a1\u00a2\7\"\2\2"+
		"\u00a2\u00a3\3\2\2\2\u00a3\u00a4\b\5\2\2\u00a4\n\3\2\2\2\u00a5\u00a6\7"+
		"\13\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\6\2\2\u00a8\f\3\2\2\2\u00a9"+
		"\u00aa\t\2\2\2\u00aa\16\3\2\2\2\u00ab\u00ac\t\3\2\2\u00ac\20\3\2\2\2\u00ad"+
		"\u00ae\7a\2\2\u00ae\22\3\2\2\2\u00af\u00b0\7R\2\2\u00b0\u00b1\7C\2\2\u00b1"+
		"\u00b2\7T\2\2\u00b2\u00b3\7C\2\2\u00b3\u00b4\7V\2\2\u00b4\u00b5\7Q\2\2"+
		"\u00b5\u00b6\7F\2\2\u00b6\u00b7\7Q\2\2\u00b7\24\3\2\2\2\u00b8\u00b9\7"+
		"G\2\2\u00b9\u00ba\7Z\2\2\u00ba\u00bb\7K\2\2\u00bb\u00bc\7U\2\2\u00bc\u00bd"+
		"\7V\2\2\u00bd\u00be\7G\2\2\u00be\26\3\2\2\2\u00bf\u00c0\7V\2\2\u00c0\30"+
		"\3\2\2\2\u00c1\u00c2\7H\2\2\u00c2\32\3\2\2\2\u00c3\u00c4\7P\2\2\u00c4"+
		"\u00c5\7W\2\2\u00c5\u00c6\7O\2\2\u00c6\34\3\2\2\2\u00c7\u00c8\7N\2\2\u00c8"+
		"\u00c9\7Q\2\2\u00c9\u00ca\7I\2\2\u00ca\36\3\2\2\2\u00cb\u00cc\7X\2\2\u00cc"+
		"\u00cd\7C\2\2\u00cd\u00ce\7T\2\2\u00ce\u00cf\7K\2\2\u00cf\u00d0\7C\2\2"+
		"\u00d0\u00d1\7D\2\2\u00d1\u00d2\7N\2\2\u00d2\u00d3\7G\2\2\u00d3\u00d4"+
		"\7U\2\2\u00d4 \3\2\2\2\u00d5\u00d6\7R\2\2\u00d6\u00d7\7T\2\2\u00d7\u00d8"+
		"\7Q\2\2\u00d8\u00d9\7I\2\2\u00d9\u00da\7T\2\2\u00da\u00db\7C\2\2\u00db"+
		"\u00dc\7O\2\2\u00dc\u00dd\7C\2\2\u00dd\"\3\2\2\2\u00de\u00df\7U\2\2\u00df"+
		"\u00e0\7W\2\2\u00e0\u00e1\7D\2\2\u00e1\u00e2\7R\2\2\u00e2\u00e3\7T\2\2"+
		"\u00e3\u00e4\7Q\2\2\u00e4\u00e5\7I\2\2\u00e5\u00e6\7T\2\2\u00e6\u00e7"+
		"\7C\2\2\u00e7\u00e8\7O\2\2\u00e8\u00e9\7C\2\2\u00e9\u00ea\7U\2\2\u00ea"+
		"$\3\2\2\2\u00eb\u00ec\7K\2\2\u00ec\u00ed\7P\2\2\u00ed\u00ee\7U\2\2\u00ee"+
		"\u00ef\7V\2\2\u00ef\u00f0\7T\2\2\u00f0\u00f1\7W\2\2\u00f1\u00f2\7E\2\2"+
		"\u00f2\u00f3\7E\2\2\u00f3\u00f4\7K\2\2\u00f4\u00f5\7Q\2\2\u00f5\u00f6"+
		"\7P\2\2\u00f6\u00f7\7G\2\2\u00f7\u00f8\7U\2\2\u00f8&\3\2\2\2\u00f9\u00fa"+
		"\7H\2\2\u00fa\u00fb\7W\2\2\u00fb\u00fc\7P\2\2\u00fc\u00fd\7E\2\2\u00fd"+
		"\u00fe\7K\2\2\u00fe\u00ff\7Q\2\2\u00ff\u0100\7P\2\2\u0100(\3\2\2\2\u0101"+
		"\u0102\7H\2\2\u0102\u0103\7H\2\2\u0103\u0104\7W\2\2\u0104\u0105\7P\2\2"+
		"\u0105\u0106\7E\2\2\u0106\u0107\7K\2\2\u0107\u0108\7Q\2\2\u0108\u0109"+
		"\7P\2\2\u0109*\3\2\2\2\u010a\u010b\7R\2\2\u010b\u010c\7T\2\2\u010c\u010d"+
		"\7Q\2\2\u010d\u010e\7E\2\2\u010e\u010f\7G\2\2\u010f\u0110\7F\2\2\u0110"+
		"\u0111\7K\2\2\u0111\u0112\7O\2\2\u0112\u0113\7K\2\2\u0113\u0114\7G\2\2"+
		"\u0114\u0115\7P\2\2\u0115\u0116\7V\2\2\u0116\u0117\7Q\2\2\u0117,\3\2\2"+
		"\2\u0118\u0119\7H\2\2\u0119\u011a\7R\2\2\u011a\u011b\7T\2\2\u011b\u011c"+
		"\7Q\2\2\u011c\u011d\7E\2\2\u011d\u011e\7G\2\2\u011e\u011f\7F\2\2\u011f"+
		"\u0120\7K\2\2\u0120\u0121\7O\2\2\u0121\u0122\7K\2\2\u0122\u0123\7G\2\2"+
		"\u0123\u0124\7P\2\2\u0124\u0125\7V\2\2\u0125\u0126\7Q\2\2\u0126.\3\2\2"+
		"\2\u0127\u0128\7f\2\2\u0128\u0129\7g\2\2\u0129\u012a\7x\2\2\u012a\60\3"+
		"\2\2\2\u012b\u012c\7t\2\2\u012c\u012d\7w\2\2\u012d\u012e\7r\2\2\u012e"+
		"\u012f\7v\2\2\u012f\u0130\7w\2\2\u0130\u0131\7t\2\2\u0131\u0132\7c\2\2"+
		"\u0132\62\3\2\2\2\u0133\u0134\7~\2\2\u0134\u0135\7~\2\2\u0135\64\3\2\2"+
		"\2\u0136\u0137\7(\2\2\u0137\u0138\7(\2\2\u0138\66\3\2\2\2\u0139\u013a"+
		"\7w\2\2\u013a\u013b\7n\2\2\u013b\u013c\7v\2\2\u013c\u013d\7k\2\2\u013d"+
		"\u013e\7o\2\2\u013e\u013f\7c\2\2\u013f\u0140\7a\2\2\u0140\u0141\7r\2\2"+
		"\u0141\u0142\7q\2\2\u0142\u0143\7u\2\2\u0143\u0144\7k\2\2\u0144\u0145"+
		"\7e\2\2\u0145\u0146\7k\2\2\u0146\u0147\7q\2\2\u0147\u0148\7p\2\2\u0148"+
		"8\3\2\2\2\u0149\u014a\7x\2\2\u014a\u014b\7c\2\2\u014b\u014c\7e\2\2\u014c"+
		"\u014d\7k\2\2\u014d\u014e\7c\2\2\u014e:\3\2\2\2\u014f\u0150\7#\2\2\u0150"+
		"<\3\2\2\2\u0151\u0152\7U\2\2\u0152\u0153\7G\2\2\u0153\u0154\7S\2\2\u0154"+
		">\3\2\2\2\u0155\u0157\5\r\7\2\u0156\u0155\3\2\2\2\u0157\u0158\3\2\2\2"+
		"\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159@\3\2\2\2\u015a\u015b\7"+
		"*\2\2\u015bB\3\2\2\2\u015c\u015d\7]\2\2\u015dD\3\2\2\2\u015e\u015f\7_"+
		"\2\2\u015fF\3\2\2\2\u0160\u0161\7o\2\2\u0161\u0162\7q\2\2\u0162\u0163"+
		"\7u\2\2\u0163\u0164\7v\2\2\u0164\u0165\7t\2\2\u0165\u0166\7c\2\2\u0166"+
		"\u0167\7t\2\2\u0167H\3\2\2\2\u0168\u0169\7o\2\2\u0169\u016a\7k\2\2\u016a"+
		"\u016b\7g\2\2\u016b\u016c\7p\2\2\u016c\u016d\7v\2\2\u016d\u016e\7t\2\2"+
		"\u016e\u016f\7c\2\2\u016f\u0170\7u\2\2\u0170J\3\2\2\2\u0171\u0172\7h\2"+
		"\2\u0172\u0173\7o\2\2\u0173\u0174\7k\2\2\u0174\u0175\7g\2\2\u0175\u0176"+
		"\7p\2\2\u0176\u0177\7v\2\2\u0177\u0178\7t\2\2\u0178\u0179\7c\2\2\u0179"+
		"\u017a\7u\2\2\u017aL\3\2\2\2\u017b\u017c\7c\2\2\u017c\u017d\7x\2\2\u017d"+
		"\u017e\7c\2\2\u017e\u017f\7p\2\2\u017f\u0180\7e\2\2\u0180\u0181\7g\2\2"+
		"\u0181N\3\2\2\2\u0182\u0183\7u\2\2\u0183\u0184\7k\2\2\u0184P\3\2\2\2\u0185"+
		"\u0186\7u\2\2\u0186\u0187\7k\2\2\u0187\u0188\7p\2\2\u0188\u0189\7q\2\2"+
		"\u0189R\3\2\2\2\u018a\u018b\7h\2\2\u018b\u018c\7u\2\2\u018c\u018d\7k\2"+
		"\2\u018dT\3\2\2\2\u018e\u018f\7g\2\2\u018f\u0190\7p\2\2\u0190\u0191\7"+
		"v\2\2\u0191\u0192\7q\2\2\u0192\u0193\7p\2\2\u0193\u0194\7e\2\2\u0194\u0195"+
		"\7g\2\2\u0195\u0196\7u\2\2\u0196V\3\2\2\2\u0197\u0198\7j\2\2\u0198\u0199"+
		"\7c\2\2\u0199\u019a\7e\2\2\u019a\u019b\7g\2\2\u019b\u019c\7t\2\2\u019c"+
		"X\3\2\2\2\u019d\u019e\7<\2\2\u019eZ\3\2\2\2\u019f\u01a0\7+\2\2\u01a0\\"+
		"\3\2\2\2\u01a1\u01a2\7}\2\2\u01a2^\3\2\2\2\u01a3\u01a4\7\177\2\2\u01a4"+
		"`\3\2\2\2\u01a5\u01a6\7=\2\2\u01a6b\3\2\2\2\u01a7\u01a8\7.\2\2\u01a8d"+
		"\3\2\2\2\u01a9\u01aa\7?\2\2\u01aa\u01ab\7?\2\2\u01abf\3\2\2\2\u01ac\u01ad"+
		"\7#\2\2\u01ad\u01ae\7?\2\2\u01aeh\3\2\2\2\u01af\u01b0\7e\2\2\u01b0\u01b1"+
		"\7k\2\2\u01b1\u01b2\7g\2\2\u01b2\u01b3\7t\2\2\u01b3\u01b4\7v\2\2\u01b4"+
		"\u01b5\7q\2\2\u01b5j\3\2\2\2\u01b6\u01b7\7h\2\2\u01b7\u01b8\7c\2\2\u01b8"+
		"\u01b9\7n\2\2\u01b9\u01ba\7u\2\2\u01ba\u01bb\7q\2\2\u01bbl\3\2\2\2\u01bc"+
		"\u01bd\7?\2\2\u01bdn\3\2\2\2\u01be\u01bf\7>\2\2\u01bf\u01c0\7?\2\2\u01c0"+
		"p\3\2\2\2\u01c1\u01c2\7@\2\2\u01c2\u01c3\7?\2\2\u01c3r\3\2\2\2\u01c4\u01c5"+
		"\7@\2\2\u01c5t\3\2\2\2\u01c6\u01c7\7>\2\2\u01c7v\3\2\2\2\u01c8\u01c9\7"+
		"-\2\2\u01c9x\3\2\2\2\u01ca\u01cb\7/\2\2\u01cbz\3\2\2\2\u01cc\u01cd\7,"+
		"\2\2\u01cd|\3\2\2\2\u01ce\u01d4\5\17\b\2\u01cf\u01d3\5\17\b\2\u01d0\u01d3"+
		"\5\r\7\2\u01d1\u01d3\5\21\t\2\u01d2\u01cf\3\2\2\2\u01d2\u01d0\3\2\2\2"+
		"\u01d2\u01d1\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5"+
		"\3\2\2\2\u01d5~\3\2\2\2\u01d6\u01d4\3\2\2\2\t\2\u0080\u008c\u009a\u0158"+
		"\u01d2\u01d4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}