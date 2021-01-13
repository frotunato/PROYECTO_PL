// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src\Anasint.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Anasint}.
 */
public interface AnasintListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Anasint#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_variable(Anasint.Declaracion_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_variable(Anasint.Declaracion_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#lista_variables}.
	 * @param ctx the parse tree
	 */
	void enterLista_variables(Anasint.Lista_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#lista_variables}.
	 * @param ctx the parse tree
	 */
	void exitLista_variables(Anasint.Lista_variablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#lista_variables_tipadas}.
	 * @param ctx the parse tree
	 */
	void enterLista_variables_tipadas(Anasint.Lista_variables_tipadasContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#lista_variables_tipadas}.
	 * @param ctx the parse tree
	 */
	void exitLista_variables_tipadas(Anasint.Lista_variables_tipadasContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(Anasint.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(Anasint.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#variable_tipada}.
	 * @param ctx the parse tree
	 */
	void enterVariable_tipada(Anasint.Variable_tipadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#variable_tipada}.
	 * @param ctx the parse tree
	 */
	void exitVariable_tipada(Anasint.Variable_tipadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(Anasint.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(Anasint.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#tipo_no_elemental}.
	 * @param ctx the parse tree
	 */
	void enterTipo_no_elemental(Anasint.Tipo_no_elementalContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#tipo_no_elemental}.
	 * @param ctx the parse tree
	 */
	void exitTipo_no_elemental(Anasint.Tipo_no_elementalContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#tipo_elemental}.
	 * @param ctx the parse tree
	 */
	void enterTipo_elemental(Anasint.Tipo_elementalContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#tipo_elemental}.
	 * @param ctx the parse tree
	 */
	void exitTipo_elemental(Anasint.Tipo_elementalContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_programa}.
	 * @param ctx the parse tree
	 */
	void enterBloque_programa(Anasint.Bloque_programaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_programa}.
	 * @param ctx the parse tree
	 */
	void exitBloque_programa(Anasint.Bloque_programaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_subprogramas}.
	 * @param ctx the parse tree
	 */
	void enterBloque_subprogramas(Anasint.Bloque_subprogramasContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_subprogramas}.
	 * @param ctx the parse tree
	 */
	void exitBloque_subprogramas(Anasint.Bloque_subprogramasContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_funcion}.
	 * @param ctx the parse tree
	 */
	void enterBloque_funcion(Anasint.Bloque_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_funcion}.
	 * @param ctx the parse tree
	 */
	void exitBloque_funcion(Anasint.Bloque_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_procedimiento}.
	 * @param ctx the parse tree
	 */
	void enterBloque_procedimiento(Anasint.Bloque_procedimientoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_procedimiento}.
	 * @param ctx the parse tree
	 */
	void exitBloque_procedimiento(Anasint.Bloque_procedimientoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_instrucciones}.
	 * @param ctx the parse tree
	 */
	void enterBloque_instrucciones(Anasint.Bloque_instruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_instrucciones}.
	 * @param ctx the parse tree
	 */
	void exitBloque_instrucciones(Anasint.Bloque_instruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#bloque_variables}.
	 * @param ctx the parse tree
	 */
	void enterBloque_variables(Anasint.Bloque_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#bloque_variables}.
	 * @param ctx the parse tree
	 */
	void exitBloque_variables(Anasint.Bloque_variablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_llamada_subprograma}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_llamada_subprograma(Anasint.Instruccion_llamada_subprogramaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_llamada_subprograma}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_llamada_subprograma(Anasint.Instruccion_llamada_subprogramaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_bucle}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_bucle(Anasint.Instruccion_bucleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_bucle}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_bucle(Anasint.Instruccion_bucleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_control}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_control(Anasint.Instruccion_controlContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_control}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_control(Anasint.Instruccion_controlContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_ruptura}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_ruptura(Anasint.Instruccion_rupturaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_ruptura}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_ruptura(Anasint.Instruccion_rupturaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_asig}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_asig(Anasint.Instruccion_asigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_asig}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_asig(Anasint.Instruccion_asigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instruccion_retorno}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_retorno(Anasint.Instruccion_retornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instruccion_retorno}
	 * labeled alternative in {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_retorno(Anasint.Instruccion_retornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#instruccion_aserto}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_aserto(Anasint.Instruccion_asertoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion_aserto}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_aserto(Anasint.Instruccion_asertoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#evaluaciones_variables}.
	 * @param ctx the parse tree
	 */
	void enterEvaluaciones_variables(Anasint.Evaluaciones_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#evaluaciones_variables}.
	 * @param ctx the parse tree
	 */
	void exitEvaluaciones_variables(Anasint.Evaluaciones_variablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#evaluacion_variable}.
	 * @param ctx the parse tree
	 */
	void enterEvaluacion_variable(Anasint.Evaluacion_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#evaluacion_variable}.
	 * @param ctx the parse tree
	 */
	void exitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicado_negado}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado_negado(Anasint.Predicado_negadoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicado_negado}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado_negado(Anasint.Predicado_negadoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicado_base}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado_base(Anasint.Predicado_baseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicado_base}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado_base(Anasint.Predicado_baseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicado_envuelto}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado_envuelto(Anasint.Predicado_envueltoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicado_envuelto}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado_envuelto(Anasint.Predicado_envueltoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicado_rec}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado_rec(Anasint.Predicado_recContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicado_rec}
	 * labeled alternative in {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado_rec(Anasint.Predicado_recContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicion_base}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion_base(Anasint.Condicion_baseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicion_base}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion_base(Anasint.Condicion_baseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicion_falso}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion_falso(Anasint.Condicion_falsoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicion_falso}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion_falso(Anasint.Condicion_falsoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicion_cierto}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion_cierto(Anasint.Condicion_ciertoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicion_cierto}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion_cierto(Anasint.Condicion_ciertoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicion_envuelta}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion_envuelta(Anasint.Condicion_envueltaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicion_envuelta}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion_envuelta(Anasint.Condicion_envueltaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condicion_rec}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void enterCondicion_rec(Anasint.Condicion_recContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condicion_rec}
	 * labeled alternative in {@link Anasint#condicion}.
	 * @param ctx the parse tree
	 */
	void exitCondicion_rec(Anasint.Condicion_recContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op_aritmetica_mult}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOp_aritmetica_mult(Anasint.Op_aritmetica_multContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op_aritmetica_mult}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOp_aritmetica_mult(Anasint.Op_aritmetica_multContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op_aritmetica_negacion}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOp_aritmetica_negacion(Anasint.Op_aritmetica_negacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op_aritmetica_negacion}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOp_aritmetica_negacion(Anasint.Op_aritmetica_negacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op_aritmetica_sr}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOp_aritmetica_sr(Anasint.Op_aritmetica_srContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op_aritmetica_sr}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOp_aritmetica_sr(Anasint.Op_aritmetica_srContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op_aritmetica_envuelta}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOp_aritmetica_envuelta(Anasint.Op_aritmetica_envueltaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op_aritmetica_envuelta}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOp_aritmetica_envuelta(Anasint.Op_aritmetica_envueltaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_caso_base}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void enterOperando_caso_base(Anasint.Operando_caso_baseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_caso_base}
	 * labeled alternative in {@link Anasint#operacion}.
	 * @param ctx the parse tree
	 */
	void exitOperando_caso_base(Anasint.Operando_caso_baseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_numerico}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando_numerico(Anasint.Operando_numericoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_numerico}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando_numerico(Anasint.Operando_numericoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_variable}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando_variable(Anasint.Operando_variableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_variable}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando_variable(Anasint.Operando_variableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vle_acceso}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void enterVle_acceso(Anasint.Vle_accesoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vle_acceso}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void exitVle_acceso(Anasint.Vle_accesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_subprograma}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void enterOperando_subprograma(Anasint.Operando_subprogramaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_subprograma}
	 * labeled alternative in {@link Anasint#operando}.
	 * @param ctx the parse tree
	 */
	void exitOperando_subprograma(Anasint.Operando_subprogramaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_secuencia_vacia}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void enterOperando_secuencia_vacia(Anasint.Operando_secuencia_vaciaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_secuencia_vacia}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void exitOperando_secuencia_vacia(Anasint.Operando_secuencia_vaciaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_secuencia_logica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void enterOperando_secuencia_logica(Anasint.Operando_secuencia_logicaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_secuencia_logica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void exitOperando_secuencia_logica(Anasint.Operando_secuencia_logicaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operando_secuencia_numerica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void enterOperando_secuencia_numerica(Anasint.Operando_secuencia_numericaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operando_secuencia_numerica}
	 * labeled alternative in {@link Anasint#operando_secuencia}.
	 * @param ctx the parse tree
	 */
	void exitOperando_secuencia_numerica(Anasint.Operando_secuencia_numericaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operador_logico_2_ario}.
	 * @param ctx the parse tree
	 */
	void enterOperador_logico_2_ario(Anasint.Operador_logico_2_arioContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operador_logico_2_ario}.
	 * @param ctx the parse tree
	 */
	void exitOperador_logico_2_ario(Anasint.Operador_logico_2_arioContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operador_condicion_2_ario}.
	 * @param ctx the parse tree
	 */
	void enterOperador_condicion_2_ario(Anasint.Operador_condicion_2_arioContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operador_condicion_2_ario}.
	 * @param ctx the parse tree
	 */
	void exitOperador_condicion_2_ario(Anasint.Operador_condicion_2_arioContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valor_booleano_true}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 */
	void enterValor_booleano_true(Anasint.Valor_booleano_trueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valor_booleano_true}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 */
	void exitValor_booleano_true(Anasint.Valor_booleano_trueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valor_booleano_false}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 */
	void enterValor_booleano_false(Anasint.Valor_booleano_falseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valor_booleano_false}
	 * labeled alternative in {@link Anasint#valor_booleano}.
	 * @param ctx the parse tree
	 */
	void exitValor_booleano_false(Anasint.Valor_booleano_falseContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#variable_acceso}.
	 * @param ctx the parse tree
	 */
	void enterVariable_acceso(Anasint.Variable_accesoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#variable_acceso}.
	 * @param ctx the parse tree
	 */
	void exitVariable_acceso(Anasint.Variable_accesoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subprograma_ultima_posicion}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void enterSubprograma_ultima_posicion(Anasint.Subprograma_ultima_posicionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subprograma_ultima_posicion}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void exitSubprograma_ultima_posicion(Anasint.Subprograma_ultima_posicionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subprograma_vacia}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void enterSubprograma_vacia(Anasint.Subprograma_vaciaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subprograma_vacia}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void exitSubprograma_vacia(Anasint.Subprograma_vaciaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subprograma_declarado}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void enterSubprograma_declarado(Anasint.Subprograma_declaradoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subprograma_declarado}
	 * labeled alternative in {@link Anasint#subprograma}.
	 * @param ctx the parse tree
	 */
	void exitSubprograma_declarado(Anasint.Subprograma_declaradoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#mostrar}.
	 * @param ctx the parse tree
	 */
	void enterMostrar(Anasint.MostrarContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#mostrar}.
	 * @param ctx the parse tree
	 */
	void exitMostrar(Anasint.MostrarContext ctx);
}