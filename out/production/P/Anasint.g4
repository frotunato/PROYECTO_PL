// Analizador sintáctico lenguaje P

parser grammar Anasint;
options{tokenVocab=Analex;}


declaracion_variable: (IDENT COMA)* IDENT DP tipo PyC;

lista_variables: (variable COMA)* variable;
lista_variables_tipadas: (tipo IDENT COMA)* tipo IDENT;

variable:
    IDENT #variable_simple |
    IDENT CA operacion CC #variable_acceso;

tipo:
    INTEGER #tipo_numerico |
    BOOLEAN #tipo_booleano |
    SEQUENCE PA (INTEGER | BOOLEAN) PC #tipo_secuencia
    ;

bloque_programa: PROGRAMA bloque_variables bloque_subprogramas bloque_instrucciones (COMENTARIO_LINEA* | COMENTARIO_BLOQUE?) EOF?;
bloque_subprogramas: SUBPROGRAMAS (bloque_funcion | bloque_procedimiento)*;
bloque_funcion: FUNCION IDENT PA lista_variables_tipadas? PC RETORNO PA lista_variables_tipadas PC bloque_variables bloque_instrucciones;
bloque_procedimiento: PROCEDIMIENTO IDENT PA lista_variables_tipadas? PC bloque_variables bloque_instrucciones;
bloque_instrucciones: INSTRUCCIONES instruccion+ (FFUNCION | FPROCEDIMIENTO)?;
bloque_variables: VARIABLES declaracion_variable*;


instruccion:
    subprograma PyC #instruccion_llamada_subprograma |
    MIENTRAS PA predicado PC HACER (LLA AVANCE DP subprograma LLC)? instruccion+ FMIENTRAS #instruccion_bucle  |
    SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)? FSI #instruccion_control  |
    RUPTURA PyC #instruccion_ruptura  |
    lista_variables ASIG evaluaciones_variables PyC #instruccion_asig |
    RETORNO evaluaciones_variables PyC #instruccion_retorno |
    LLA predicado LLC #instruccion_aserto_simple |
    LLA cuantificador PA variable DP CA operacion COMA operacion CC COMA predicado PC LLC #instruccion_aserto_cuantificado
;

evaluaciones_variables: evaluacion_variable (COMA evaluacion_variable)*;

evaluacion_variable:
    subprograma |
    operando |
    operacion |
    operando_secuencia |
    valor_booleano
    ;

predicado:
    condicion #predicado_base |
    PA predicado PC #predicado_envuelto |
    NO predicado #predicado_negado |
    predicado operador_condicion_2_ario predicado #predicado_rec;

condicion:
    CIERTO #condicion_cierto |
    FALSO #condicion_falso |
    condicion operador_logico_2_ario condicion #condicion_rec |
    PA condicion PC #condicion_envuelta |
    (valor_booleano | operacion | operando_secuencia) #condicion_base;

operacion:
    PA operacion PC #op_aritmetica_envuelta |
    MENOS operacion #op_aritmetica_negacion |
    operacion MULT operacion #op_aritmetica_mult |
    operacion (MAS | MENOS) operacion #op_aritmetica_sr |
    operando #operando_caso_base;

operando:
    NUMERO #operando_numerico |
    valor_booleano #operando_booleano |
    variable #operando_variable |
    //variable_acceso #op_variable_acceso |
    subprograma #operando_subprograma
;

operando_secuencia:
    (CA CC) #operando_secuencia_vacia |
    (CA ((operacion COMA)* operacion) CC) #operando_secuencia_llena

    //(CA ((operando | operando COMA)* operando | operando) CC) #operando_secuencia_logica |
;

operador_logico_2_ario: IGUAL | DIGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL;
operador_condicion_2_ario: AND | OR;

valor_booleano:
    TRUE #valor_booleano_true |
    FALSE #valor_booleano_false
;

cuantificador:
    PARATODO #cuantificador_universal |
    EXISTE   #cuantificador_existencial;
subprograma:
    MOSTRAR PA evaluacion_variable PC #subprograma_mostrar |
    UL_POS PA evaluacion_variable PC #subprograma_ultima_posicion |
    VACIA PA evaluacion_variable PC #subprograma_vacia |
    IDENT (
        (PA PC) |
        (PA evaluacion_variable PC) |
        (PA (evaluacion_variable (COMA evaluacion_variable)*) PC)
    ) #subprograma_declarado;