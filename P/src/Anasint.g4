// Analizador sintáctico lenguaje P

parser grammar Anasint;
options{tokenVocab=Analex;}

//sentencia: (bloque_variables | bloque_funcion | bloque_procedimiento | bloque_instrucciones)*;

//declaracion_variable: lista_variables DP tipo PyC;
declaracion_variable: (IDENT COMA)* IDENT DP tipo PyC;

//lista_variables: (IDENT COMA)* IDENT;
lista_variables: (variable COMA)* variable;
//lista_variables_tipadas: (tipo IDENT COMA)* tipo IDENT;
lista_variables_tipadas: (variable_tipada COMA)* variable_tipada;

variable: IDENT;
variable_tipada: tipo IDENT;

tipo: tipo_elemental | tipo_no_elemental;
tipo_no_elemental: SEQUENCE PA tipo_elemental PC;
tipo_elemental: INTEGER | BOOLEAN;

args_funcion_procedimiento: PA lista_variables_tipadas PC;

bloque_programa: PROGRAMA bloque_variables bloque_subprogramas bloque_instrucciones;
bloque_subprogramas: SUBPROGRAMAS (bloque_funcion | bloque_procedimiento)*;
bloque_funcion: FUNCION IDENT args_funcion_procedimiento RETORNO args_funcion_procedimiento bloque_variables bloque_instrucciones;
bloque_procedimiento: PROCEDIMIENTO IDENT args_funcion_procedimiento (EOF | bloque_variables);
bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | (FFUNCION | FPROCEDIMIENTO));
bloque_variables: VARIABLES declaracion_variable*;

//separar en bloques?
instruccion:
    instruccion_aserto  |
    instruccion_bucle  |
    instruccion_control  |
    instruccion_ruptura  |
    instruccion_asig |
    instruccion_retorno;
/*
instruccion:
    instruccion_aserto #instrAserto |
    instruccion_bucle #instrBucle |
    instruccion_control #instrControl |
    instruccion_ruptura #instrRuptura |
    instruccion_asig #instrAsign |
    instruccion_retorno #instrRetorno
;*/
instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP funcion LLC)? instruccion+ FMIENTRAS;

//instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP IDENT PA lista_variables PC LLC)? instruccion+ FMIENTRAS;
instruccion_control: SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)* FSI;
instruccion_ruptura: RUPTURA PyC;
instruccion_asig: lista_variables ASIG evaluaciones_variables PyC;
instruccion_retorno: RETORNO evaluacion_variable PyC;
instruccion_aserto:
    LLA (
        (CIERTO | FALSO) |
        ((PARATODO | EXISTE) PA IDENT DP CA operando_universal COMA operando_universal CC COMA predicado PC))
    LLC;

//predicado: operacion_logica (operador_condicion_2_ario (predicado | (PA predicado PC)))*;
predicado: NO? operacion_logica (operador_condicion_2_ario NO? (predicado | (PA predicado PC)))*;

evaluaciones_variables: evaluacion_variable (COMA evaluacion_variable)*;
/*
evaluacion_variable:
    operando_secuencia #evalSecuencia |
    (NO? operacion_logica) #evalOperacionLogica |
    operacion_aritmetica #evalOperacionAritmetica |
    variable_acceso #evalVariableAcceso |
    ultima_posicion #evalUltimaPosicion |
    (NO? vacia) #evalVacia
;
*/
evaluacion_variable:
    operando_aritmetico |
    operando_logico |
    operando_universal |
    operando_secuencia |
    operacion_aritmetica |
    //operacion_2_aria |
    (NO? operacion_logica) |
    variable_acceso |
    ultima_posicion |
    (NO? vacia)
    ;

operacion_logica: (operando_logico | (PA (operacion_logica) PC)) (operador_logico_2_ario (operacion_logica | (PA (operacion_logica) PC) ))?;
operacion_aritmetica: (operando_aritmetico | (PA operacion_aritmetica PC)) (operador_aritmetico_2_ario (operacion_aritmetica | (PA operacion_aritmetica PC) ))*;
operacion_2_aria: ((operando_logico | operando_aritmetico) | (PA operacion_2_aria PC)) (operador_2_ario (operacion_2_aria | (PA operacion_2_aria PC)))*;
//esto se deberia arreglar, realmente operandos deberian ser de cualquier tipo... eso es del semantico!
operando_universal: variable | variable_acceso | ultima_posicion | funcion;
operando_aritmetico: NUMERO | operando_universal;
operando_logico: TRUE | FALSE | CIERTO | FALSO | operacion_aritmetica | vacia;

operando_secuencia: CA (operando_secuencia_logica | operando_secuencia_aritmetica) CC;
    //WTF¿?
    //((operacion_aritmetica COMA)* operacion_aritmetica)+ |
    //((operacion_logica COMA)* operacion_logica)+) CC;

operando_secuencia_logica: (predicado COMA)* predicado;
operando_secuencia_aritmetica: (operacion_aritmetica COMA)* operacion_aritmetica;

operador_2_ario: operador_aritmetico_2_ario | operador_logico_2_ario;

operador_aritmetico_2_ario: MULT | (MAS | MENOS);
operador_logico_2_ario: IGUAL | DIGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL;

operador_condicion_2_ario: operador_logico_2_ario | AND | OR;
operador_condicion_1_ario: NO;

variable_acceso: variable CA operacion_aritmetica CC;
funcion: IDENT PA (evaluacion_variable (COMA evaluacion_variable)*) PC;
ultima_posicion: UL_POS PA variable PC; //esto deberia solo aceptar listas
vacia: VACIA PA variable PC; //esto tambien acepta solo listas