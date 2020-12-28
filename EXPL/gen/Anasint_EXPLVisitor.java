// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/EXPL/src\Anasint_EXPL.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Anasint_EXPL}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Anasint_EXPLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(Anasint_EXPL.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Anasint_EXPL.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#cuantificador_universal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuantificador_universal(Anasint_EXPL.Cuantificador_universalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#cuantificador_existencial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuantificador_existencial(Anasint_EXPL.Cuantificador_existencialContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado(Anasint_EXPL.PredicadoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#elto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElto(Anasint_EXPL.EltoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#operacion_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacion_aritmetica(Anasint_EXPL.Operacion_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(Anasint_EXPL.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#variables_cuantificadas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariables_cuantificadas(Anasint_EXPL.Variables_cuantificadasContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint_EXPL#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion(Anasint_EXPL.FuncionContext ctx);
}