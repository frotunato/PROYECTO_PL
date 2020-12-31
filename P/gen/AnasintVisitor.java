// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src\Anasint.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Anasint}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnasintVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Anasint#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(Anasint.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_variable(Anasint.Declaracion_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#lista_variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_variables(Anasint.Lista_variablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#lista_variables_tipadas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_variables_tipadas(Anasint.Lista_variables_tipadasContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(Anasint.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#variable_tipada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_tipada(Anasint.Variable_tipadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(Anasint.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#tipo_no_elemental}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_no_elemental(Anasint.Tipo_no_elementalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#tipo_elemental}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_elemental(Anasint.Tipo_elementalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#args_funcion_procedimiento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs_funcion_procedimiento(Anasint.Args_funcion_procedimientoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloque_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_funcion(Anasint.Bloque_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloque_procedimiento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_procedimiento(Anasint.Bloque_procedimientoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloque_instrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_instrucciones(Anasint.Bloque_instruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloque_variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_variables(Anasint.Bloque_variablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(Anasint.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_bucle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_bucle(Anasint.Instruccion_bucleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_control(Anasint.Instruccion_controlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_ruptura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_ruptura(Anasint.Instruccion_rupturaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_asig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_asig(Anasint.Instruccion_asigContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_retorno(Anasint.Instruccion_retornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#instruccion_aserto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_aserto(Anasint.Instruccion_asertoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado(Anasint.PredicadoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#evaluacion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operacion_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacion_logica(Anasint.Operacion_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operacion_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacion_aritmetica(Anasint.Operacion_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operando_universal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_universal(Anasint.Operando_universalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operando_aritmetico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_aritmetico(Anasint.Operando_aritmeticoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operando_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_logico(Anasint.Operando_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_2_ario(Anasint.Operador_2_arioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_aritmetico_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_aritmetico_2_ario(Anasint.Operador_aritmetico_2_arioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_logico_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_logico_2_ario(Anasint.Operador_logico_2_arioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_condicion_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_condicion_2_ario(Anasint.Operador_condicion_2_arioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_condicion_1_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_condicion_1_ario(Anasint.Operador_condicion_1_arioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#variable_acceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_acceso(Anasint.Variable_accesoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion(Anasint.FuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#ultima_posicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUltima_posicion(Anasint.Ultima_posicionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#vacia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVacia(Anasint.VaciaContext ctx);
}