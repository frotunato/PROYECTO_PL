// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src\Analex.g4 by ANTLR 4.9
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
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLANCO=1, TABULADOR=2, FIN_LINEA=3, TRUE=4, FALSE=5, INTEGER=6, BOOLEAN=7, 
		VARIABLES=8, SUBPROGRAMAS=9, INSTRUCCIONES=10, FUNCION=11, FFUNCION=12, 
		PROCEDIMIENTO=13, FPROCEDIMIENTO=14, RETORNO=15, RUPTURA=16, OR=17, AND=18, 
		UL_POS=19, VACIA=20, NO=21, SEQUENCE=22, NUMERO=23, PA=24, CA=25, CC=26, 
		MIENTRAS=27, FMIENTRAS=28, AVANCE=29, SI=30, SINO=31, FSI=32, ENTONCES=33, 
		HACER=34, DP=35, PC=36, LLA=37, PARATODO=38, EXISTE=39, LLC=40, PyC=41, 
		COMA=42, IGUAL=43, DIGUAL=44, CIERTO=45, FALSO=46, ASIG=47, MEIGUAL=48, 
		MAIGUAL=49, MAYOR=50, MENOR=51, MAS=52, MENOS=53, MULT=54, DIV=55, IDENT=56, 
		COMENTARIO_BLOQUE=57, COMENTARIO_LINEA=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLANCO", "TABULADOR", "FIN_LINEA", "DIGITO", "LETRA", "UNDERSCORE", 
			"TRUE", "FALSE", "INTEGER", "BOOLEAN", "VARIABLES", "SUBPROGRAMAS", "INSTRUCCIONES", 
			"FUNCION", "FFUNCION", "PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", 
			"RUPTURA", "OR", "AND", "UL_POS", "VACIA", "NO", "SEQUENCE", "NUMERO", 
			"PA", "CA", "CC", "MIENTRAS", "FMIENTRAS", "AVANCE", "SI", "SINO", "FSI", 
			"ENTONCES", "HACER", "DP", "PC", "LLA", "PARATODO", "EXISTE", "LLC", 
			"PyC", "COMA", "IGUAL", "DIGUAL", "CIERTO", "FALSO", "ASIG", "MEIGUAL", 
			"MAIGUAL", "MAYOR", "MENOR", "MAS", "MENOS", "MULT", "DIV", "IDENT", 
			"COMENTARIO_BLOQUE", "COMENTARIO_LINEA"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\t'", null, "'T'", "'F'", "'NUM'", "'LOG'", "'VARIABLES'", 
			"'SUBPROGRAMAS'", "'INSTRUCCIONES'", "'FUNCION'", "'FFUNCION'", "'PROCEDIMIENTO'", 
			"'FPROCEDIMIENTO'", "'dev'", "'ruptura'", "'||'", "'&&'", "'ultima_posicion'", 
			"'vacia'", "'!'", "'SEQ'", null, "'('", "'['", "']'", "'mientras'", "'fmientras'", 
			"'avance'", "'si'", "'sino'", "'fsi'", "'entonces'", "'hacer'", "':'", 
			"')'", "'{'", "'PARATODO'", "'EXISTE'", "'}'", "';'", "','", "'=='", 
			"'!='", "'cierto'", "'falso'", "'='", "'<='", "'>='", "'>'", "'<'", "'+'", 
			"'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLANCO", "TABULADOR", "FIN_LINEA", "TRUE", "FALSE", "INTEGER", 
			"BOOLEAN", "VARIABLES", "SUBPROGRAMAS", "INSTRUCCIONES", "FUNCION", "FFUNCION", 
			"PROCEDIMIENTO", "FPROCEDIMIENTO", "RETORNO", "RUPTURA", "OR", "AND", 
			"UL_POS", "VACIA", "NO", "SEQUENCE", "NUMERO", "PA", "CA", "CC", "MIENTRAS", 
			"FMIENTRAS", "AVANCE", "SI", "SINO", "FSI", "ENTONCES", "HACER", "DP", 
			"PC", "LLA", "PARATODO", "EXISTE", "LLC", "PyC", "COMA", "IGUAL", "DIGUAL", 
			"CIERTO", "FALSO", "ASIG", "MEIGUAL", "MAIGUAL", "MAYOR", "MENOR", "MAS", 
			"MENOS", "MULT", "DIV", "IDENT", "COMENTARIO_BLOQUE", "COMENTARIO_LINEA"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u01c9\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\5\4\u0087\n\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\33\5\33\u0121\n\33\3\33\6\33\u0124\n\33\r\33\16\33\u0125\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3"+
		"#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'"+
		"\3(\3(\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3"+
		"\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3<\3<\7<\u01aa\n<\f<"+
		"\16<\u01ad\13<\3=\3=\3=\3=\7=\u01b3\n=\f=\16=\u01b6\13=\3=\3=\3=\3=\3"+
		"=\3>\3>\3>\3>\7>\u01c1\n>\f>\16>\u01c4\13>\3>\3>\3>\3>\4\u01b4\u01c2\2"+
		"?\3\3\5\4\7\5\t\2\13\2\r\2\17\6\21\7\23\b\25\t\27\n\31\13\33\f\35\r\37"+
		"\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67\329\33;\34="+
		"\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62i\63k\64m\65"+
		"o\66q\67s8u9w:y;{<\3\2\4\3\2\62;\4\2C\\c|\2\u01cd\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2"+
		"\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2"+
		"y\3\2\2\2\2{\3\2\2\2\3}\3\2\2\2\5\u0081\3\2\2\2\7\u0086\3\2\2\2\t\u008c"+
		"\3\2\2\2\13\u008e\3\2\2\2\r\u0090\3\2\2\2\17\u0092\3\2\2\2\21\u0094\3"+
		"\2\2\2\23\u0096\3\2\2\2\25\u009a\3\2\2\2\27\u009e\3\2\2\2\31\u00a8\3\2"+
		"\2\2\33\u00b5\3\2\2\2\35\u00c3\3\2\2\2\37\u00cb\3\2\2\2!\u00d4\3\2\2\2"+
		"#\u00e2\3\2\2\2%\u00f1\3\2\2\2\'\u00f5\3\2\2\2)\u00fd\3\2\2\2+\u0100\3"+
		"\2\2\2-\u0103\3\2\2\2/\u0113\3\2\2\2\61\u0119\3\2\2\2\63\u011b\3\2\2\2"+
		"\65\u0120\3\2\2\2\67\u0127\3\2\2\29\u0129\3\2\2\2;\u012b\3\2\2\2=\u012d"+
		"\3\2\2\2?\u0136\3\2\2\2A\u0140\3\2\2\2C\u0147\3\2\2\2E\u014a\3\2\2\2G"+
		"\u014f\3\2\2\2I\u0153\3\2\2\2K\u015c\3\2\2\2M\u0162\3\2\2\2O\u0164\3\2"+
		"\2\2Q\u0166\3\2\2\2S\u0168\3\2\2\2U\u0171\3\2\2\2W\u0178\3\2\2\2Y\u017a"+
		"\3\2\2\2[\u017c\3\2\2\2]\u017e\3\2\2\2_\u0181\3\2\2\2a\u0184\3\2\2\2c"+
		"\u018b\3\2\2\2e\u0191\3\2\2\2g\u0193\3\2\2\2i\u0196\3\2\2\2k\u0199\3\2"+
		"\2\2m\u019b\3\2\2\2o\u019d\3\2\2\2q\u019f\3\2\2\2s\u01a1\3\2\2\2u\u01a3"+
		"\3\2\2\2w\u01a5\3\2\2\2y\u01ae\3\2\2\2{\u01bc\3\2\2\2}~\7\"\2\2~\177\3"+
		"\2\2\2\177\u0080\b\2\2\2\u0080\4\3\2\2\2\u0081\u0082\7\13\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u0084\b\3\2\2\u0084\6\3\2\2\2\u0085\u0087\7\17\2\2\u0086"+
		"\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\7\f"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\b\4\2\2\u008b\b\3\2\2\2\u008c\u008d"+
		"\t\2\2\2\u008d\n\3\2\2\2\u008e\u008f\t\3\2\2\u008f\f\3\2\2\2\u0090\u0091"+
		"\7a\2\2\u0091\16\3\2\2\2\u0092\u0093\7V\2\2\u0093\20\3\2\2\2\u0094\u0095"+
		"\7H\2\2\u0095\22\3\2\2\2\u0096\u0097\7P\2\2\u0097\u0098\7W\2\2\u0098\u0099"+
		"\7O\2\2\u0099\24\3\2\2\2\u009a\u009b\7N\2\2\u009b\u009c\7Q\2\2\u009c\u009d"+
		"\7I\2\2\u009d\26\3\2\2\2\u009e\u009f\7X\2\2\u009f\u00a0\7C\2\2\u00a0\u00a1"+
		"\7T\2\2\u00a1\u00a2\7K\2\2\u00a2\u00a3\7C\2\2\u00a3\u00a4\7D\2\2\u00a4"+
		"\u00a5\7N\2\2\u00a5\u00a6\7G\2\2\u00a6\u00a7\7U\2\2\u00a7\30\3\2\2\2\u00a8"+
		"\u00a9\7U\2\2\u00a9\u00aa\7W\2\2\u00aa\u00ab\7D\2\2\u00ab\u00ac\7R\2\2"+
		"\u00ac\u00ad\7T\2\2\u00ad\u00ae\7Q\2\2\u00ae\u00af\7I\2\2\u00af\u00b0"+
		"\7T\2\2\u00b0\u00b1\7C\2\2\u00b1\u00b2\7O\2\2\u00b2\u00b3\7C\2\2\u00b3"+
		"\u00b4\7U\2\2\u00b4\32\3\2\2\2\u00b5\u00b6\7K\2\2\u00b6\u00b7\7P\2\2\u00b7"+
		"\u00b8\7U\2\2\u00b8\u00b9\7V\2\2\u00b9\u00ba\7T\2\2\u00ba\u00bb\7W\2\2"+
		"\u00bb\u00bc\7E\2\2\u00bc\u00bd\7E\2\2\u00bd\u00be\7K\2\2\u00be\u00bf"+
		"\7Q\2\2\u00bf\u00c0\7P\2\2\u00c0\u00c1\7G\2\2\u00c1\u00c2\7U\2\2\u00c2"+
		"\34\3\2\2\2\u00c3\u00c4\7H\2\2\u00c4\u00c5\7W\2\2\u00c5\u00c6\7P\2\2\u00c6"+
		"\u00c7\7E\2\2\u00c7\u00c8\7K\2\2\u00c8\u00c9\7Q\2\2\u00c9\u00ca\7P\2\2"+
		"\u00ca\36\3\2\2\2\u00cb\u00cc\7H\2\2\u00cc\u00cd\7H\2\2\u00cd\u00ce\7"+
		"W\2\2\u00ce\u00cf\7P\2\2\u00cf\u00d0\7E\2\2\u00d0\u00d1\7K\2\2\u00d1\u00d2"+
		"\7Q\2\2\u00d2\u00d3\7P\2\2\u00d3 \3\2\2\2\u00d4\u00d5\7R\2\2\u00d5\u00d6"+
		"\7T\2\2\u00d6\u00d7\7Q\2\2\u00d7\u00d8\7E\2\2\u00d8\u00d9\7G\2\2\u00d9"+
		"\u00da\7F\2\2\u00da\u00db\7K\2\2\u00db\u00dc\7O\2\2\u00dc\u00dd\7K\2\2"+
		"\u00dd\u00de\7G\2\2\u00de\u00df\7P\2\2\u00df\u00e0\7V\2\2\u00e0\u00e1"+
		"\7Q\2\2\u00e1\"\3\2\2\2\u00e2\u00e3\7H\2\2\u00e3\u00e4\7R\2\2\u00e4\u00e5"+
		"\7T\2\2\u00e5\u00e6\7Q\2\2\u00e6\u00e7\7E\2\2\u00e7\u00e8\7G\2\2\u00e8"+
		"\u00e9\7F\2\2\u00e9\u00ea\7K\2\2\u00ea\u00eb\7O\2\2\u00eb\u00ec\7K\2\2"+
		"\u00ec\u00ed\7G\2\2\u00ed\u00ee\7P\2\2\u00ee\u00ef\7V\2\2\u00ef\u00f0"+
		"\7Q\2\2\u00f0$\3\2\2\2\u00f1\u00f2\7f\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4"+
		"\7x\2\2\u00f4&\3\2\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7w\2\2\u00f7\u00f8"+
		"\7r\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7w\2\2\u00fa\u00fb\7t\2\2\u00fb"+
		"\u00fc\7c\2\2\u00fc(\3\2\2\2\u00fd\u00fe\7~\2\2\u00fe\u00ff\7~\2\2\u00ff"+
		"*\3\2\2\2\u0100\u0101\7(\2\2\u0101\u0102\7(\2\2\u0102,\3\2\2\2\u0103\u0104"+
		"\7w\2\2\u0104\u0105\7n\2\2\u0105\u0106\7v\2\2\u0106\u0107\7k\2\2\u0107"+
		"\u0108\7o\2\2\u0108\u0109\7c\2\2\u0109\u010a\7a\2\2\u010a\u010b\7r\2\2"+
		"\u010b\u010c\7q\2\2\u010c\u010d\7u\2\2\u010d\u010e\7k\2\2\u010e\u010f"+
		"\7e\2\2\u010f\u0110\7k\2\2\u0110\u0111\7q\2\2\u0111\u0112\7p\2\2\u0112"+
		".\3\2\2\2\u0113\u0114\7x\2\2\u0114\u0115\7c\2\2\u0115\u0116\7e\2\2\u0116"+
		"\u0117\7k\2\2\u0117\u0118\7c\2\2\u0118\60\3\2\2\2\u0119\u011a\7#\2\2\u011a"+
		"\62\3\2\2\2\u011b\u011c\7U\2\2\u011c\u011d\7G\2\2\u011d\u011e\7S\2\2\u011e"+
		"\64\3\2\2\2\u011f\u0121\7/\2\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2\2\2"+
		"\u0121\u0123\3\2\2\2\u0122\u0124\5\t\5\2\u0123\u0122\3\2\2\2\u0124\u0125"+
		"\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\66\3\2\2\2\u0127"+
		"\u0128\7*\2\2\u01288\3\2\2\2\u0129\u012a\7]\2\2\u012a:\3\2\2\2\u012b\u012c"+
		"\7_\2\2\u012c<\3\2\2\2\u012d\u012e\7o\2\2\u012e\u012f\7k\2\2\u012f\u0130"+
		"\7g\2\2\u0130\u0131\7p\2\2\u0131\u0132\7v\2\2\u0132\u0133\7t\2\2\u0133"+
		"\u0134\7c\2\2\u0134\u0135\7u\2\2\u0135>\3\2\2\2\u0136\u0137\7h\2\2\u0137"+
		"\u0138\7o\2\2\u0138\u0139\7k\2\2\u0139\u013a\7g\2\2\u013a\u013b\7p\2\2"+
		"\u013b\u013c\7v\2\2\u013c\u013d\7t\2\2\u013d\u013e\7c\2\2\u013e\u013f"+
		"\7u\2\2\u013f@\3\2\2\2\u0140\u0141\7c\2\2\u0141\u0142\7x\2\2\u0142\u0143"+
		"\7c\2\2\u0143\u0144\7p\2\2\u0144\u0145\7e\2\2\u0145\u0146\7g\2\2\u0146"+
		"B\3\2\2\2\u0147\u0148\7u\2\2\u0148\u0149\7k\2\2\u0149D\3\2\2\2\u014a\u014b"+
		"\7u\2\2\u014b\u014c\7k\2\2\u014c\u014d\7p\2\2\u014d\u014e\7q\2\2\u014e"+
		"F\3\2\2\2\u014f\u0150\7h\2\2\u0150\u0151\7u\2\2\u0151\u0152\7k\2\2\u0152"+
		"H\3\2\2\2\u0153\u0154\7g\2\2\u0154\u0155\7p\2\2\u0155\u0156\7v\2\2\u0156"+
		"\u0157\7q\2\2\u0157\u0158\7p\2\2\u0158\u0159\7e\2\2\u0159\u015a\7g\2\2"+
		"\u015a\u015b\7u\2\2\u015bJ\3\2\2\2\u015c\u015d\7j\2\2\u015d\u015e\7c\2"+
		"\2\u015e\u015f\7e\2\2\u015f\u0160\7g\2\2\u0160\u0161\7t\2\2\u0161L\3\2"+
		"\2\2\u0162\u0163\7<\2\2\u0163N\3\2\2\2\u0164\u0165\7+\2\2\u0165P\3\2\2"+
		"\2\u0166\u0167\7}\2\2\u0167R\3\2\2\2\u0168\u0169\7R\2\2\u0169\u016a\7"+
		"C\2\2\u016a\u016b\7T\2\2\u016b\u016c\7C\2\2\u016c\u016d\7V\2\2\u016d\u016e"+
		"\7Q\2\2\u016e\u016f\7F\2\2\u016f\u0170\7Q\2\2\u0170T\3\2\2\2\u0171\u0172"+
		"\7G\2\2\u0172\u0173\7Z\2\2\u0173\u0174\7K\2\2\u0174\u0175\7U\2\2\u0175"+
		"\u0176\7V\2\2\u0176\u0177\7G\2\2\u0177V\3\2\2\2\u0178\u0179\7\177\2\2"+
		"\u0179X\3\2\2\2\u017a\u017b\7=\2\2\u017bZ\3\2\2\2\u017c\u017d\7.\2\2\u017d"+
		"\\\3\2\2\2\u017e\u017f\7?\2\2\u017f\u0180\7?\2\2\u0180^\3\2\2\2\u0181"+
		"\u0182\7#\2\2\u0182\u0183\7?\2\2\u0183`\3\2\2\2\u0184\u0185\7e\2\2\u0185"+
		"\u0186\7k\2\2\u0186\u0187\7g\2\2\u0187\u0188\7t\2\2\u0188\u0189\7v\2\2"+
		"\u0189\u018a\7q\2\2\u018ab\3\2\2\2\u018b\u018c\7h\2\2\u018c\u018d\7c\2"+
		"\2\u018d\u018e\7n\2\2\u018e\u018f\7u\2\2\u018f\u0190\7q\2\2\u0190d\3\2"+
		"\2\2\u0191\u0192\7?\2\2\u0192f\3\2\2\2\u0193\u0194\7>\2\2\u0194\u0195"+
		"\7?\2\2\u0195h\3\2\2\2\u0196\u0197\7@\2\2\u0197\u0198\7?\2\2\u0198j\3"+
		"\2\2\2\u0199\u019a\7@\2\2\u019al\3\2\2\2\u019b\u019c\7>\2\2\u019cn\3\2"+
		"\2\2\u019d\u019e\7-\2\2\u019ep\3\2\2\2\u019f\u01a0\7/\2\2\u01a0r\3\2\2"+
		"\2\u01a1\u01a2\7,\2\2\u01a2t\3\2\2\2\u01a3\u01a4\7\61\2\2\u01a4v\3\2\2"+
		"\2\u01a5\u01ab\5\13\6\2\u01a6\u01aa\5\13\6\2\u01a7\u01aa\5\t\5\2\u01a8"+
		"\u01aa\5\r\7\2\u01a9\u01a6\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01a8\3\2"+
		"\2\2\u01aa\u01ad\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac"+
		"x\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ae\u01af\7\61\2\2\u01af\u01b0\7,\2\2"+
		"\u01b0\u01b4\3\2\2\2\u01b1\u01b3\13\2\2\2\u01b2\u01b1\3\2\2\2\u01b3\u01b6"+
		"\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b5\u01b7\3\2\2\2\u01b6"+
		"\u01b4\3\2\2\2\u01b7\u01b8\7,\2\2\u01b8\u01b9\7\61\2\2\u01b9\u01ba\3\2"+
		"\2\2\u01ba\u01bb\b=\2\2\u01bbz\3\2\2\2\u01bc\u01bd\7\61\2\2\u01bd\u01be"+
		"\7\61\2\2\u01be\u01c2\3\2\2\2\u01bf\u01c1\13\2\2\2\u01c0\u01bf\3\2\2\2"+
		"\u01c1\u01c4\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c3\u01c5"+
		"\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5\u01c6\5\7\4\2\u01c6\u01c7\3\2\2\2\u01c7"+
		"\u01c8\b>\2\2\u01c8|\3\2\2\2\n\2\u0086\u0120\u0125\u01a9\u01ab\u01b4\u01c2"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}