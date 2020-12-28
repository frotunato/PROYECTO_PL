// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/EXPL/src\Anasint_EXPL.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Anasint_EXPL}.
 */
public interface Anasint_EXPLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(Anasint_EXPL.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(Anasint_EXPL.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Anasint_EXPL.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Anasint_EXPL.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#cuantificador_universal}.
	 * @param ctx the parse tree
	 */
	void enterCuantificador_universal(Anasint_EXPL.Cuantificador_universalContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#cuantificador_universal}.
	 * @param ctx the parse tree
	 */
	void exitCuantificador_universal(Anasint_EXPL.Cuantificador_universalContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#cuantificador_existencial}.
	 * @param ctx the parse tree
	 */
	void enterCuantificador_existencial(Anasint_EXPL.Cuantificador_existencialContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#cuantificador_existencial}.
	 * @param ctx the parse tree
	 */
	void exitCuantificador_existencial(Anasint_EXPL.Cuantificador_existencialContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado(Anasint_EXPL.PredicadoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado(Anasint_EXPL.PredicadoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#elto}.
	 * @param ctx the parse tree
	 */
	void enterElto(Anasint_EXPL.EltoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#elto}.
	 * @param ctx the parse tree
	 */
	void exitElto(Anasint_EXPL.EltoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#operacion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterOperacion_aritmetica(Anasint_EXPL.Operacion_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#operacion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitOperacion_aritmetica(Anasint_EXPL.Operacion_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(Anasint_EXPL.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(Anasint_EXPL.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#variables_cuantificadas}.
	 * @param ctx the parse tree
	 */
	void enterVariables_cuantificadas(Anasint_EXPL.Variables_cuantificadasContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#variables_cuantificadas}.
	 * @param ctx the parse tree
	 */
	void exitVariables_cuantificadas(Anasint_EXPL.Variables_cuantificadasContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint_EXPL#funcion}.
	 * @param ctx the parse tree
	 */
	void enterFuncion(Anasint_EXPL.FuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint_EXPL#funcion}.
	 * @param ctx the parse tree
	 */
	void exitFuncion(Anasint_EXPL.FuncionContext ctx);
}