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
		BLANCO=1, TABULADOR=2, FIN_LINEA=3, TRUE=4, FALSE=5, INTEGER=6, BOOLEAN=7, 
		VARIABLES=8, PROGRAMA=9, SUBPROGRAMAS=10, INSTRUCCIONES=11, FUNCION=12, 
		FFUNCION=13, PROCEDIMIENTO=14, FPROCEDIMIENTO=15, RETORNO=16, RUPTURA=17, 
		OR=18, AND=19, UL_POS=20, VACIA=21, NO=22, SEQUENCE=23, NUMERO=24, PA=25, 
		CA=26, CC=27, MOSTRAR=28, MIENTRAS=29, FMIENTRAS=30, AVANCE=31, SI=32, 
		SINO=33, FSI=34, ENTONCES=35, HACER=36, DP=37, PC=38, LLA=39, PARATODO=40, 
		EXISTE=41, LLC=42, PyC=43, COMA=44, IGUAL=45, DIGUAL=46, CIERTO=47, FALSO=48, 
		ASIG=49, MEIGUAL=50, MAIGUAL=51, MAYOR=52, MENOR=53, MAS=54, MENOS=55, 
		MULT=56, DIV=57, IDENT=58, COMENTARIO_BLOQUE=59, COMENTARIO_LINEA=60;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLANCO", "TABULADOR", "FIN_LINEA", "DIGITO", "LETRA", "UNDERSCORE", 
			"TRUE", "FALSE", "INTEGER", "BOOLEAN", "VARIABLES", "PROGRAMA", "SUBPROGRAMAS", 
			"INSTRUCCIONES", "FUNCION", "FFUNCION", "PROCEDIMIENTO", "FPROCEDIMIENTO", 
			"RETORNO", "RUPTURA", "OR", "AND", "UL_POS", "VACIA", "NO", "SEQUENCE", 
			"NUMERO", "PA", "CA", "CC", "MOSTRAR", "MIENTRAS", "FMIENTRAS", "AVANCE", 
			"SI", "SINO", "FSI", "ENTONCES", "HACER", "DP", "PC", "LLA", "PARATODO", 
			"EXISTE", "LLC", "PyC", "COMA", "IGUAL", "DIGUAL", "CIERTO", "FALSO", 
			"ASIG", "MEIGUAL", "MAIGUAL", "MAYOR", "MENOR", "MAS", "MENOS", "MULT", 
			"DIV", "IDENT", "COMENTARIO_BLOQUE", "COMENTARIO_LINEA"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\t'", null, "'T'", "'F'", "'NUM'", "'LOG'", "'VARIABLES'", 
			"'PROGRAMA'", "'SUBPROGRAMAS'", "'INSTRUCCIONES'", "'FUNCION'", "'FFUNCION'", 
			"'PROCEDIMIENTO'", "'FPROCEDIMIENTO'", "'dev'", "'ruptura'", "'||'", 
			"'&&'", "'ultima_posicion'", "'vacia'", "'!'", "'SEQ'", null, "'('", 
			"'['", "']'", "'mostrar'", "'mientras'", "'fmientras'", "'avance'", "'si'", 
			"'sino'", "'fsi'", "'entonces'", "'hacer'", "':'", "')'", "'{'", "'PARATODO'", 
			"'EXISTE'", "'}'", "';'", "','", "'=='", "'!='", "'cierto'", "'falso'", 
			"'='", "'<='", "'>='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLANCO", "TABULADOR", "FIN_LINEA", "TRUE", "FALSE", "INTEGER", 
			"BOOLEAN", "VARIABLES", "PROGRAMA", "SUBPROGRAMAS", "INSTRUCCIONES", 
			"FUNCION", "FFUNCION", "PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", 
			"RUPTURA", "OR", "AND", "UL_POS", "VACIA", "NO", "SEQUENCE", "NUMERO", 
			"PA", "CA", "CC", "MOSTRAR", "MIENTRAS", "FMIENTRAS", "AVANCE", "SI", 
			"SINO", "FSI", "ENTONCES", "HACER", "DP", "PC", "LLA", "PARATODO", "EXISTE", 
			"LLC", "PyC", "COMA", "IGUAL", "DIGUAL", "CIERTO", "FALSO", "ASIG", "MEIGUAL", 
			"MAIGUAL", "MAYOR", "MENOR", "MAS", "MENOS", "MULT", "DIV", "IDENT", 
			"COMENTARIO_BLOQUE", "COMENTARIO_LINEA"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2>\u01de\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\5\4\u008b\n\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\34\5\34"+
		"\u012e\n\34\3\34\6\34\u0131\n\34\r\34\16\34\u0132\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3%\3%\3"+
		"%\3%\3%\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3"+
		"(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3"+
		"-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3"+
		"\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\66\3\66\3"+
		"\66\3\67\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3>\3>\7>"+
		"\u01bf\n>\f>\16>\u01c2\13>\3?\3?\3?\3?\7?\u01c8\n?\f?\16?\u01cb\13?\3"+
		"?\3?\3?\3?\3?\3@\3@\3@\3@\7@\u01d6\n@\f@\16@\u01d9\13@\3@\3@\3@\3@\4\u01c9"+
		"\u01d7\2A\3\3\5\4\7\5\t\2\13\2\r\2\17\6\21\7\23\b\25\t\27\n\31\13\33\f"+
		"\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67\329"+
		"\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62i\63"+
		"k\64m\65o\66q\67s8u9w:y;{<}=\177>\3\2\4\3\2\62;\4\2C\\c|\2\u01e2\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2"+
		"\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2"+
		"\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i"+
		"\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2"+
		"\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\3\u0081"+
		"\3\2\2\2\5\u0085\3\2\2\2\7\u008a\3\2\2\2\t\u0090\3\2\2\2\13\u0092\3\2"+
		"\2\2\r\u0094\3\2\2\2\17\u0096\3\2\2\2\21\u0098\3\2\2\2\23\u009a\3\2\2"+
		"\2\25\u009e\3\2\2\2\27\u00a2\3\2\2\2\31\u00ac\3\2\2\2\33\u00b5\3\2\2\2"+
		"\35\u00c2\3\2\2\2\37\u00d0\3\2\2\2!\u00d8\3\2\2\2#\u00e1\3\2\2\2%\u00ef"+
		"\3\2\2\2\'\u00fe\3\2\2\2)\u0102\3\2\2\2+\u010a\3\2\2\2-\u010d\3\2\2\2"+
		"/\u0110\3\2\2\2\61\u0120\3\2\2\2\63\u0126\3\2\2\2\65\u0128\3\2\2\2\67"+
		"\u012d\3\2\2\29\u0134\3\2\2\2;\u0136\3\2\2\2=\u0138\3\2\2\2?\u013a\3\2"+
		"\2\2A\u0142\3\2\2\2C\u014b\3\2\2\2E\u0155\3\2\2\2G\u015c\3\2\2\2I\u015f"+
		"\3\2\2\2K\u0164\3\2\2\2M\u0168\3\2\2\2O\u0171\3\2\2\2Q\u0177\3\2\2\2S"+
		"\u0179\3\2\2\2U\u017b\3\2\2\2W\u017d\3\2\2\2Y\u0186\3\2\2\2[\u018d\3\2"+
		"\2\2]\u018f\3\2\2\2_\u0191\3\2\2\2a\u0193\3\2\2\2c\u0196\3\2\2\2e\u0199"+
		"\3\2\2\2g\u01a0\3\2\2\2i\u01a6\3\2\2\2k\u01a8\3\2\2\2m\u01ab\3\2\2\2o"+
		"\u01ae\3\2\2\2q\u01b0\3\2\2\2s\u01b2\3\2\2\2u\u01b4\3\2\2\2w\u01b6\3\2"+
		"\2\2y\u01b8\3\2\2\2{\u01ba\3\2\2\2}\u01c3\3\2\2\2\177\u01d1\3\2\2\2\u0081"+
		"\u0082\7\"\2\2\u0082\u0083\3\2\2\2\u0083\u0084\b\2\2\2\u0084\4\3\2\2\2"+
		"\u0085\u0086\7\13\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\3\2\2\u0088\6"+
		"\3\2\2\2\u0089\u008b\7\17\2\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2"+
		"\u008b\u008c\3\2\2\2\u008c\u008d\7\f\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\b\4\2\2\u008f\b\3\2\2\2\u0090\u0091\t\2\2\2\u0091\n\3\2\2\2\u0092\u0093"+
		"\t\3\2\2\u0093\f\3\2\2\2\u0094\u0095\7a\2\2\u0095\16\3\2\2\2\u0096\u0097"+
		"\7V\2\2\u0097\20\3\2\2\2\u0098\u0099\7H\2\2\u0099\22\3\2\2\2\u009a\u009b"+
		"\7P\2\2\u009b\u009c\7W\2\2\u009c\u009d\7O\2\2\u009d\24\3\2\2\2\u009e\u009f"+
		"\7N\2\2\u009f\u00a0\7Q\2\2\u00a0\u00a1\7I\2\2\u00a1\26\3\2\2\2\u00a2\u00a3"+
		"\7X\2\2\u00a3\u00a4\7C\2\2\u00a4\u00a5\7T\2\2\u00a5\u00a6\7K\2\2\u00a6"+
		"\u00a7\7C\2\2\u00a7\u00a8\7D\2\2\u00a8\u00a9\7N\2\2\u00a9\u00aa\7G\2\2"+
		"\u00aa\u00ab\7U\2\2\u00ab\30\3\2\2\2\u00ac\u00ad\7R\2\2\u00ad\u00ae\7"+
		"T\2\2\u00ae\u00af\7Q\2\2\u00af\u00b0\7I\2\2\u00b0\u00b1\7T\2\2\u00b1\u00b2"+
		"\7C\2\2\u00b2\u00b3\7O\2\2\u00b3\u00b4\7C\2\2\u00b4\32\3\2\2\2\u00b5\u00b6"+
		"\7U\2\2\u00b6\u00b7\7W\2\2\u00b7\u00b8\7D\2\2\u00b8\u00b9\7R\2\2\u00b9"+
		"\u00ba\7T\2\2\u00ba\u00bb\7Q\2\2\u00bb\u00bc\7I\2\2\u00bc\u00bd\7T\2\2"+
		"\u00bd\u00be\7C\2\2\u00be\u00bf\7O\2\2\u00bf\u00c0\7C\2\2\u00c0\u00c1"+
		"\7U\2\2\u00c1\34\3\2\2\2\u00c2\u00c3\7K\2\2\u00c3\u00c4\7P\2\2\u00c4\u00c5"+
		"\7U\2\2\u00c5\u00c6\7V\2\2\u00c6\u00c7\7T\2\2\u00c7\u00c8\7W\2\2\u00c8"+
		"\u00c9\7E\2\2\u00c9\u00ca\7E\2\2\u00ca\u00cb\7K\2\2\u00cb\u00cc\7Q\2\2"+
		"\u00cc\u00cd\7P\2\2\u00cd\u00ce\7G\2\2\u00ce\u00cf\7U\2\2\u00cf\36\3\2"+
		"\2\2\u00d0\u00d1\7H\2\2\u00d1\u00d2\7W\2\2\u00d2\u00d3\7P\2\2\u00d3\u00d4"+
		"\7E\2\2\u00d4\u00d5\7K\2\2\u00d5\u00d6\7Q\2\2\u00d6\u00d7\7P\2\2\u00d7"+
		" \3\2\2\2\u00d8\u00d9\7H\2\2\u00d9\u00da\7H\2\2\u00da\u00db\7W\2\2\u00db"+
		"\u00dc\7P\2\2\u00dc\u00dd\7E\2\2\u00dd\u00de\7K\2\2\u00de\u00df\7Q\2\2"+
		"\u00df\u00e0\7P\2\2\u00e0\"\3\2\2\2\u00e1\u00e2\7R\2\2\u00e2\u00e3\7T"+
		"\2\2\u00e3\u00e4\7Q\2\2\u00e4\u00e5\7E\2\2\u00e5\u00e6\7G\2\2\u00e6\u00e7"+
		"\7F\2\2\u00e7\u00e8\7K\2\2\u00e8\u00e9\7O\2\2\u00e9\u00ea\7K\2\2\u00ea"+
		"\u00eb\7G\2\2\u00eb\u00ec\7P\2\2\u00ec\u00ed\7V\2\2\u00ed\u00ee\7Q\2\2"+
		"\u00ee$\3\2\2\2\u00ef\u00f0\7H\2\2\u00f0\u00f1\7R\2\2\u00f1\u00f2\7T\2"+
		"\2\u00f2\u00f3\7Q\2\2\u00f3\u00f4\7E\2\2\u00f4\u00f5\7G\2\2\u00f5\u00f6"+
		"\7F\2\2\u00f6\u00f7\7K\2\2\u00f7\u00f8\7O\2\2\u00f8\u00f9\7K\2\2\u00f9"+
		"\u00fa\7G\2\2\u00fa\u00fb\7P\2\2\u00fb\u00fc\7V\2\2\u00fc\u00fd\7Q\2\2"+
		"\u00fd&\3\2\2\2\u00fe\u00ff\7f\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7x\2"+
		"\2\u0101(\3\2\2\2\u0102\u0103\7t\2\2\u0103\u0104\7w\2\2\u0104\u0105\7"+
		"r\2\2\u0105\u0106\7v\2\2\u0106\u0107\7w\2\2\u0107\u0108\7t\2\2\u0108\u0109"+
		"\7c\2\2\u0109*\3\2\2\2\u010a\u010b\7~\2\2\u010b\u010c\7~\2\2\u010c,\3"+
		"\2\2\2\u010d\u010e\7(\2\2\u010e\u010f\7(\2\2\u010f.\3\2\2\2\u0110\u0111"+
		"\7w\2\2\u0111\u0112\7n\2\2\u0112\u0113\7v\2\2\u0113\u0114\7k\2\2\u0114"+
		"\u0115\7o\2\2\u0115\u0116\7c\2\2\u0116\u0117\7a\2\2\u0117\u0118\7r\2\2"+
		"\u0118\u0119\7q\2\2\u0119\u011a\7u\2\2\u011a\u011b\7k\2\2\u011b\u011c"+
		"\7e\2\2\u011c\u011d\7k\2\2\u011d\u011e\7q\2\2\u011e\u011f\7p\2\2\u011f"+
		"\60\3\2\2\2\u0120\u0121\7x\2\2\u0121\u0122\7c\2\2\u0122\u0123\7e\2\2\u0123"+
		"\u0124\7k\2\2\u0124\u0125\7c\2\2\u0125\62\3\2\2\2\u0126\u0127\7#\2\2\u0127"+
		"\64\3\2\2\2\u0128\u0129\7U\2\2\u0129\u012a\7G\2\2\u012a\u012b\7S\2\2\u012b"+
		"\66\3\2\2\2\u012c\u012e\7/\2\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2"+
		"\u012e\u0130\3\2\2\2\u012f\u0131\5\t\5\2\u0130\u012f\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u01338\3\2\2\2\u0134"+
		"\u0135\7*\2\2\u0135:\3\2\2\2\u0136\u0137\7]\2\2\u0137<\3\2\2\2\u0138\u0139"+
		"\7_\2\2\u0139>\3\2\2\2\u013a\u013b\7o\2\2\u013b\u013c\7q\2\2\u013c\u013d"+
		"\7u\2\2\u013d\u013e\7v\2\2\u013e\u013f\7t\2\2\u013f\u0140\7c\2\2\u0140"+
		"\u0141\7t\2\2\u0141@\3\2\2\2\u0142\u0143\7o\2\2\u0143\u0144\7k\2\2\u0144"+
		"\u0145\7g\2\2\u0145\u0146\7p\2\2\u0146\u0147\7v\2\2\u0147\u0148\7t\2\2"+
		"\u0148\u0149\7c\2\2\u0149\u014a\7u\2\2\u014aB\3\2\2\2\u014b\u014c\7h\2"+
		"\2\u014c\u014d\7o\2\2\u014d\u014e\7k\2\2\u014e\u014f\7g\2\2\u014f\u0150"+
		"\7p\2\2\u0150\u0151\7v\2\2\u0151\u0152\7t\2\2\u0152\u0153\7c\2\2\u0153"+
		"\u0154\7u\2\2\u0154D\3\2\2\2\u0155\u0156\7c\2\2\u0156\u0157\7x\2\2\u0157"+
		"\u0158\7c\2\2\u0158\u0159\7p\2\2\u0159\u015a\7e\2\2\u015a\u015b\7g\2\2"+
		"\u015bF\3\2\2\2\u015c\u015d\7u\2\2\u015d\u015e\7k\2\2\u015eH\3\2\2\2\u015f"+
		"\u0160\7u\2\2\u0160\u0161\7k\2\2\u0161\u0162\7p\2\2\u0162\u0163\7q\2\2"+
		"\u0163J\3\2\2\2\u0164\u0165\7h\2\2\u0165\u0166\7u\2\2\u0166\u0167\7k\2"+
		"\2\u0167L\3\2\2\2\u0168\u0169\7g\2\2\u0169\u016a\7p\2\2\u016a\u016b\7"+
		"v\2\2\u016b\u016c\7q\2\2\u016c\u016d\7p\2\2\u016d\u016e\7e\2\2\u016e\u016f"+
		"\7g\2\2\u016f\u0170\7u\2\2\u0170N\3\2\2\2\u0171\u0172\7j\2\2\u0172\u0173"+
		"\7c\2\2\u0173\u0174\7e\2\2\u0174\u0175\7g\2\2\u0175\u0176\7t\2\2\u0176"+
		"P\3\2\2\2\u0177\u0178\7<\2\2\u0178R\3\2\2\2\u0179\u017a\7+\2\2\u017aT"+
		"\3\2\2\2\u017b\u017c\7}\2\2\u017cV\3\2\2\2\u017d\u017e\7R\2\2\u017e\u017f"+
		"\7C\2\2\u017f\u0180\7T\2\2\u0180\u0181\7C\2\2\u0181\u0182\7V\2\2\u0182"+
		"\u0183\7Q\2\2\u0183\u0184\7F\2\2\u0184\u0185\7Q\2\2\u0185X\3\2\2\2\u0186"+
		"\u0187\7G\2\2\u0187\u0188\7Z\2\2\u0188\u0189\7K\2\2\u0189\u018a\7U\2\2"+
		"\u018a\u018b\7V\2\2\u018b\u018c\7G\2\2\u018cZ\3\2\2\2\u018d\u018e\7\177"+
		"\2\2\u018e\\\3\2\2\2\u018f\u0190\7=\2\2\u0190^\3\2\2\2\u0191\u0192\7."+
		"\2\2\u0192`\3\2\2\2\u0193\u0194\7?\2\2\u0194\u0195\7?\2\2\u0195b\3\2\2"+
		"\2\u0196\u0197\7#\2\2\u0197\u0198\7?\2\2\u0198d\3\2\2\2\u0199\u019a\7"+
		"e\2\2\u019a\u019b\7k\2\2\u019b\u019c\7g\2\2\u019c\u019d\7t\2\2\u019d\u019e"+
		"\7v\2\2\u019e\u019f\7q\2\2\u019ff\3\2\2\2\u01a0\u01a1\7h\2\2\u01a1\u01a2"+
		"\7c\2\2\u01a2\u01a3\7n\2\2\u01a3\u01a4\7u\2\2\u01a4\u01a5\7q\2\2\u01a5"+
		"h\3\2\2\2\u01a6\u01a7\7?\2\2\u01a7j\3\2\2\2\u01a8\u01a9\7>\2\2\u01a9\u01aa"+
		"\7?\2\2\u01aal\3\2\2\2\u01ab\u01ac\7@\2\2\u01ac\u01ad\7?\2\2\u01adn\3"+
		"\2\2\2\u01ae\u01af\7@\2\2\u01afp\3\2\2\2\u01b0\u01b1\7>\2\2\u01b1r\3\2"+
		"\2\2\u01b2\u01b3\7-\2\2\u01b3t\3\2\2\2\u01b4\u01b5\7/\2\2\u01b5v\3\2\2"+
		"\2\u01b6\u01b7\7,\2\2\u01b7x\3\2\2\2\u01b8\u01b9\7\61\2\2\u01b9z\3\2\2"+
		"\2\u01ba\u01c0\5\13\6\2\u01bb\u01bf\5\13\6\2\u01bc\u01bf\5\t\5\2\u01bd"+
		"\u01bf\5\r\7\2\u01be\u01bb\3\2\2\2\u01be\u01bc\3\2\2\2\u01be\u01bd\3\2"+
		"\2\2\u01bf\u01c2\3\2\2\2\u01c0\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1"+
		"|\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3\u01c4\7\61\2\2\u01c4\u01c5\7,\2\2"+
		"\u01c5\u01c9\3\2\2\2\u01c6\u01c8\13\2\2\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb"+
		"\3\2\2\2\u01c9\u01ca\3\2\2\2\u01c9\u01c7\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb"+
		"\u01c9\3\2\2\2\u01cc\u01cd\7,\2\2\u01cd\u01ce\7\61\2\2\u01ce\u01cf\3\2"+
		"\2\2\u01cf\u01d0\b?\2\2\u01d0~\3\2\2\2\u01d1\u01d2\7\61\2\2\u01d2\u01d3"+
		"\7\61\2\2\u01d3\u01d7\3\2\2\2\u01d4\u01d6\13\2\2\2\u01d5\u01d4\3\2\2\2"+
		"\u01d6\u01d9\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d8\u01da"+
		"\3\2\2\2\u01d9\u01d7\3\2\2\2\u01da\u01db\5\7\4\2\u01db\u01dc\3\2\2\2\u01dc"+
		"\u01dd\b@\2\2\u01dd\u0080\3\2\2\2\n\2\u008a\u012d\u0132\u01be\u01c0\u01c9"+
		"\u01d7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}