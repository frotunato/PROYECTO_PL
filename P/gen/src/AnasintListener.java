// Generated from C:/Users/user/IdeaProjects/PROYECTO_PL/P/src/src\Anasint.g4 by ANTLR 4.9
package src;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Anasint}.
 */
public interface AnasintListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Anasint#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(Anasint.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(Anasint.SentenciaContext ctx);
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
	 * Enter a parse tree produced by {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(Anasint.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(Anasint.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#instruccion_bucle}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_bucle(Anasint.Instruccion_bucleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion_bucle}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_bucle(Anasint.Instruccion_bucleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#instruccion_control}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_control(Anasint.Instruccion_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion_control}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_control(Anasint.Instruccion_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#instruccion_asig}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_asig(Anasint.Instruccion_asigContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion_asig}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_asig(Anasint.Instruccion_asigContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#instruccion_retorno}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion_retorno(Anasint.Instruccion_retornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#instruccion_retorno}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion_retorno(Anasint.Instruccion_retornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#predicado_global}.
	 * @param ctx the parse tree
	 */
	void enterPredicado_global(Anasint.Predicado_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#predicado_global}.
	 * @param ctx the parse tree
	 */
	void exitPredicado_global(Anasint.Predicado_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#lista_predicados}.
	 * @param ctx the parse tree
	 */
	void enterLista_predicados(Anasint.Lista_predicadosContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#lista_predicados}.
	 * @param ctx the parse tree
	 */
	void exitLista_predicados(Anasint.Lista_predicadosContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void enterPredicado(Anasint.PredicadoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#predicado}.
	 * @param ctx the parse tree
	 */
	void exitPredicado(Anasint.PredicadoContext ctx);
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
	 * Enter a parse tree produced by {@link Anasint#operacion_logica}.
	 * @param ctx the parse tree
	 */
	void enterOperacion_logica(Anasint.Operacion_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operacion_logica}.
	 * @param ctx the parse tree
	 */
	void exitOperacion_logica(Anasint.Operacion_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operacion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterOperacion_aritmetica(Anasint.Operacion_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operacion_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitOperacion_aritmetica(Anasint.Operacion_aritmeticaContext ctx);
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
	 * Enter a parse tree produced by {@link Anasint#operando_aritmetico}.
	 * @param ctx the parse tree
	 */
	void enterOperando_aritmetico(Anasint.Operando_aritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operando_aritmetico}.
	 * @param ctx the parse tree
	 */
	void exitOperando_aritmetico(Anasint.Operando_aritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operando_logico}.
	 * @param ctx the parse tree
	 */
	void enterOperando_logico(Anasint.Operando_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operando_logico}.
	 * @param ctx the parse tree
	 */
	void exitOperando_logico(Anasint.Operando_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operador_aritmetico}.
	 * @param ctx the parse tree
	 */
	void enterOperador_aritmetico(Anasint.Operador_aritmeticoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operador_aritmetico}.
	 * @param ctx the parse tree
	 */
	void exitOperador_aritmetico(Anasint.Operador_aritmeticoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#operador_logico}.
	 * @param ctx the parse tree
	 */
	void enterOperador_logico(Anasint.Operador_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#operador_logico}.
	 * @param ctx the parse tree
	 */
	void exitOperador_logico(Anasint.Operador_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#ultima_posicion}.
	 * @param ctx the parse tree
	 */
	void enterUltima_posicion(Anasint.Ultima_posicionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#ultima_posicion}.
	 * @param ctx the parse tree
	 */
	void exitUltima_posicion(Anasint.Ultima_posicionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Anasint#vacia}.
	 * @param ctx the parse tree
	 */
	void enterVacia(Anasint.VaciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Anasint#vacia}.
	 * @param ctx the parse tree
	 */
	void exitVacia(Anasint.VaciaContext ctx);
}