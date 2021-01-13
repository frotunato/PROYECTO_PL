// Analizador sintáctico lenguaje P

parser grammar Anasint;
options{tokenVocab=Analex;}


declaracion_variable: (IDENT COMA)* IDENT DP tipo PyC;

lista_variables: (variable COMA)* variable;
lista_variables_tipadas: (variable_tipada COMA)* variable_tipada;

variable: IDENT;
variable_tipada: tipo IDENT;

tipo: tipo_elemental | tipo_no_elemental;
tipo_no_elemental: SEQUENCE PA tipo_elemental PC;
tipo_elemental: INTEGER | BOOLEAN;

bloque_programa: PROGRAMA bloque_variables bloque_subprogramas bloque_instrucciones;
bloque_subprogramas: SUBPROGRAMAS (bloque_funcion | bloque_procedimiento)*;
bloque_funcion: FUNCION IDENT PA lista_variables_tipadas? PC RETORNO PA lista_variables_tipadas PC bloque_variables bloque_instrucciones;
bloque_procedimiento: PROCEDIMIENTO IDENT PA lista_variables_tipadas? PC bloque_variables bloque_instrucciones;
bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | (FFUNCION | FPROCEDIMIENTO));
bloque_variables: VARIABLES declaracion_variable*;

//separar en bloques?
/*
instruccion:
    instruccion_llamada_subprograma |
    instruccion_aserto  |
    instruccion_bucle  |
    instruccion_control  |
    instruccion_ruptura  |
    instruccion_asig |
    instruccion_retorno;
*/

instruccion:
    (mostrar | subprograma) PyC #instruccion_llamada_subprograma |
    //instruccion_aserto  |
    MIENTRAS PA predicado PC HACER (LLA AVANCE DP subprograma LLC)? instruccion+ FMIENTRAS #instruccion_bucle  |
    SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)? FSI #instruccion_control  |
    RUPTURA PyC #instruccion_ruptura  |
    lista_variables ASIG evaluaciones_variables PyC #instruccion_asig |
    RETORNO evaluaciones_variables PyC #instruccion_retorno;

/*
instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP subprograma LLC)? instruccion+ FMIENTRAS;
instruccion_llamada_subprograma: (mostrar | subprograma) PyC;
instruccion_control: SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)? FSI;
instruccion_ruptura: RUPTURA PyC;
instruccion_asig: lista_variables ASIG evaluaciones_variables PyC;
instruccion_retorno: RETORNO evaluaciones_variables PyC;
*/

instruccion_aserto:
    LLA (
        (CIERTO | FALSO) |
        ((PARATODO | EXISTE) PA IDENT DP CA operando COMA operando CC COMA predicado PC))
    LLC;


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
    predicado operador_condicion_2_ario predicado #predicado_rec
;

condicion:
    //valor_booleano operador_logico_2_ario condicion #opbol |
    //operacion operador_logico_2_ario operacion #condicion_base2 |
    //operacion operador_logico_2_ario condicion #condicion_base |
    //    condicion operador_logico_2_ario (valor_booleano | operacion) #babase |
    CIERTO #condicion_cierto |
    FALSO #condicion_falso |
    condicion operador_logico_2_ario condicion #condicion_rec |
    PA condicion PC #condicion_envuelta |
    (valor_booleano | operacion | operando_secuencia) #condicion_base;
    //operador_logico_2_ario operando_secuencia
    //valor_booleano operador_logico_2_ario operacion #condicion_rec2 |


    //condicion operador_logico_2_ario (valor_booleano | operacion) #babase |
    //(valor_booleano | operacion) operador_logico_2_ario valor_booleano #condicion_rec2 |
    //condicion operador_logico_2_ario condicion #condicion_rec |

    //operacion #opop |
    //valor_booleano #condicion_bol | //operador_logico_2_ario valor_booleano

operacion:
    PA operacion PC #op_aritmetica_envuelta |
    MENOS operacion #op_negacion |
    operacion MULT operacion #op_aritmetica_mult |
    operacion (MAS | MENOS) operacion #op_aritmetica_sr |
    operando #operando_caso_base;

operando:
    NUMERO #operando_numerico |
    //valor_booleano #operando_booleano |
    //vacia #operando_vacia |
    variable #operando_variable |
    variable_acceso  #vle_acceso |
    //ultima_posicion #operando_ultima_posicion |
    subprograma #operando_subprograma
;

operador_2_ario: operador_aritmetico_2_ario | operador_logico_2_ario;


operando_secuencia:
    (CA CC) #operando_secuencia_vacia |
    //(CA evaluacion_variable CC) #operando_secuencia_|
    (CA ((valor_booleano COMA)* valor_booleano) CC) #operando_secuencia_logica |
    (CA ((NUMERO COMA)* NUMERO) CC) #operando_secuencia_numerica
;

operador_aritmetico_2_ario: MULT | (MAS | MENOS);
operador_logico_2_ario: IGUAL | DIGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL;
operador_igualdad_logica_2_ario: IGUAL | DIGUAL;

operador_condicion_2_ario: AND | OR;
operador_condicion_1_ario: NO;

valor_booleano:
    TRUE #valor_booleano_true |
    FALSE #valor_booleano_false
;

variable_acceso: variable CA operacion CC;

subprograma: IDENT (
    (PA PC) |
    (PA evaluacion_variable PC) |
    (PA (evaluacion_variable (COMA evaluacion_variable)*) PC)
);

//PA (evaluacion_variable (COMA evaluacion_variable)*)? PC;
ultima_posicion:
    UL_POS PA (variable | operando_secuencia) PC
    //UL_POS PA operando_secuencia PC #ultima_posicion_secuencia
;


vacia: VACIA PA evaluacion_variable PC; //esto tambien acepta solo listas
mostrar: MOSTRAR PA evaluacion_variable PC;