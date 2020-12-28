// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/TEST/src\Analex.g4 by ANTLR 4.8
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Analex extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLANCO=1, TABULADOR=2, FIN_LINEA=3, TRUE=4, FALSE=5, INTEGER=6, BOOLEAN=7, 
		Y=8, O=9, NO=10, NUMERO=11, IDENT=12, PA=13, PC=14, PyC=15, COMA=16, ASIG=17, 
		MAYOR=18, MENOR=19, IGUAL=20, MAS=21, MENOS=22, MULT=23, DIV=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BLANCO", "TABULADOR", "FIN_LINEA", "DIGITO", "LETRA", "TRUE", "FALSE", 
			"INTEGER", "BOOLEAN", "Y", "O", "NO", "NUMERO", "IDENT", "PA", "PC", 
			"PyC", "COMA", "ASIG", "MAYOR", "MENOR", "IGUAL", "MAS", "MENOS", "MULT", 
			"DIV"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'\t'", null, "'True'", "'False'", "'Integer'", "'Boolean'", 
			"'O'", "'Y'", "'NO'", null, null, "'('", "')'", "';'", "','", "'='", 
			"'>'", "'<'", "'=='", "'+'", "'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLANCO", "TABULADOR", "FIN_LINEA", "TRUE", "FALSE", "INTEGER", 
			"BOOLEAN", "Y", "O", "NO", "NUMERO", "IDENT", "PA", "PC", "PyC", "COMA", 
			"ASIG", "MAYOR", "MENOR", "IGUAL", "MAS", "MENOS", "MULT", "DIV"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u0095\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\5\4A\n\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\5\16n\n\16\3\16\6\16q\n\16\r\16\16"+
		"\16r\3\17\3\17\3\17\7\17x\n\17\f\17\16\17{\13\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\2\2\34\3\3\5\4\7\5\t\2\13\2\r\6\17\7"+
		"\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-"+
		"\26/\27\61\30\63\31\65\32\3\2\4\3\2\62;\4\2C\\c|\2\u0097\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\3\67\3\2\2\2\5;\3\2\2\2\7@\3\2\2\2\tF\3\2\2\2\13H\3\2\2\2\rJ\3\2\2"+
		"\2\17O\3\2\2\2\21U\3\2\2\2\23]\3\2\2\2\25e\3\2\2\2\27g\3\2\2\2\31i\3\2"+
		"\2\2\33m\3\2\2\2\35t\3\2\2\2\37|\3\2\2\2!~\3\2\2\2#\u0080\3\2\2\2%\u0082"+
		"\3\2\2\2\'\u0084\3\2\2\2)\u0086\3\2\2\2+\u0088\3\2\2\2-\u008a\3\2\2\2"+
		"/\u008d\3\2\2\2\61\u008f\3\2\2\2\63\u0091\3\2\2\2\65\u0093\3\2\2\2\67"+
		"8\7\"\2\289\3\2\2\29:\b\2\2\2:\4\3\2\2\2;<\7\13\2\2<=\3\2\2\2=>\b\3\2"+
		"\2>\6\3\2\2\2?A\7\17\2\2@?\3\2\2\2@A\3\2\2\2AB\3\2\2\2BC\7\f\2\2CD\3\2"+
		"\2\2DE\b\4\2\2E\b\3\2\2\2FG\t\2\2\2G\n\3\2\2\2HI\t\3\2\2I\f\3\2\2\2JK"+
		"\7V\2\2KL\7t\2\2LM\7w\2\2MN\7g\2\2N\16\3\2\2\2OP\7H\2\2PQ\7c\2\2QR\7n"+
		"\2\2RS\7u\2\2ST\7g\2\2T\20\3\2\2\2UV\7K\2\2VW\7p\2\2WX\7v\2\2XY\7g\2\2"+
		"YZ\7i\2\2Z[\7g\2\2[\\\7t\2\2\\\22\3\2\2\2]^\7D\2\2^_\7q\2\2_`\7q\2\2`"+
		"a\7n\2\2ab\7g\2\2bc\7c\2\2cd\7p\2\2d\24\3\2\2\2ef\7Q\2\2f\26\3\2\2\2g"+
		"h\7[\2\2h\30\3\2\2\2ij\7P\2\2jk\7Q\2\2k\32\3\2\2\2ln\7/\2\2ml\3\2\2\2"+
		"mn\3\2\2\2np\3\2\2\2oq\5\t\5\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2"+
		"s\34\3\2\2\2ty\5\13\6\2ux\5\13\6\2vx\5\t\5\2wu\3\2\2\2wv\3\2\2\2x{\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z\36\3\2\2\2{y\3\2\2\2|}\7*\2\2} \3\2\2\2~\177"+
		"\7+\2\2\177\"\3\2\2\2\u0080\u0081\7=\2\2\u0081$\3\2\2\2\u0082\u0083\7"+
		".\2\2\u0083&\3\2\2\2\u0084\u0085\7?\2\2\u0085(\3\2\2\2\u0086\u0087\7@"+
		"\2\2\u0087*\3\2\2\2\u0088\u0089\7>\2\2\u0089,\3\2\2\2\u008a\u008b\7?\2"+
		"\2\u008b\u008c\7?\2\2\u008c.\3\2\2\2\u008d\u008e\7-\2\2\u008e\60\3\2\2"+
		"\2\u008f\u0090\7/\2\2\u0090\62\3\2\2\2\u0091\u0092\7,\2\2\u0092\64\3\2"+
		"\2\2\u0093\u0094\7\61\2\2\u0094\66\3\2\2\2\b\2@mrwy\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}