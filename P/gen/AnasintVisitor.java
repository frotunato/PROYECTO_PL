// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src\Anasint.g4 by ANTLR 4.8
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
	 * Visit a parse tree produced by {@link Anasint#bloque_programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_programa(Anasint.Bloque_programaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#bloque_subprogramas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque_subprogramas(Anasint.Bloque_subprogramasContext ctx);
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
	 * Visit a parse tree produced by {@link Anasint#instruccion_llamada_subprograma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion_llamada_subprograma(Anasint.Instruccion_llamada_subprogramaContext ctx);
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
	 * Visit a parse tree produced by {@link Anasint#evaluaciones_variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluaciones_variables(Anasint.Evaluaciones_variablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#evaluacion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_cierto}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_cierto(Anasint.Predicado_ciertoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_negado}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_negado(Anasint.Predicado_negadoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_falso}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_falso(Anasint.Predicado_falsoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_base}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_base(Anasint.Predicado_baseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_envuelto}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_envuelto(Anasint.Predicado_envueltoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicado_rec}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicado_rec(Anasint.Predicado_recContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicion_base}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion_base(Anasint.Condicion_baseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicion_envuelta}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion_envuelta(Anasint.Condicion_envueltaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicion_rec}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion_rec(Anasint.Condicion_recContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicion_bool}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion_bool(Anasint.Condicion_boolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condicion_sec}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicion_sec(Anasint.Condicion_secContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op_aritmetica_mult}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_aritmetica_mult(Anasint.Op_aritmetica_multContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op_aritmetica_sr}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_aritmetica_sr(Anasint.Op_aritmetica_srContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op_aritmetica_envuelta}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_aritmetica_envuelta(Anasint.Op_aritmetica_envueltaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_caso_base}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_caso_base(Anasint.Operando_caso_baseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_numerico}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_numerico(Anasint.Operando_numericoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_booleano}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_booleano(Anasint.Operando_booleanoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_variable}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_variable(Anasint.Operando_variableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vle_acceso}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVle_acceso(Anasint.Vle_accesoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_subprograma}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_subprograma(Anasint.Operando_subprogramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#operador_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_2_ario(Anasint.Operador_2_arioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_secuencia_vacia}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_secuencia_vacia(Anasint.Operando_secuencia_vaciaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_secuencia_logica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_secuencia_logica(Anasint.Operando_secuencia_logicaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code operando_secuencia_numerica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperando_secuencia_numerica(Anasint.Operando_secuencia_numericaContext ctx);
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
	 * Visit a parse tree produced by {@link Anasint#operador_igualdad_logica_2_ario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperador_igualdad_logica_2_ario(Anasint.Operador_igualdad_logica_2_arioContext ctx);
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
	 * Visit a parse tree produced by the {@code valor_booleano_true}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_booleano_true(Anasint.Valor_booleano_trueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valor_booleano_false}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_booleano_false(Anasint.Valor_booleano_falseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#variable_acceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_acceso(Anasint.Variable_accesoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprograma(Anasint.SubprogramaContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link Anasint#mostrar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMostrar(Anasint.MostrarContext ctx);
}