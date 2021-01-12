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

bloque_programa: PROGRAMA bloque_variables bloque_subprogramas bloque_instrucciones;
bloque_subprogramas: SUBPROGRAMAS (bloque_funcion | bloque_procedimiento)*;
//bloque_funcion: FUNCION IDENT args_funcion_procedimiento RETORNO args_funcion_procedimiento bloque_variables bloque_instrucciones;
bloque_funcion: FUNCION IDENT PA lista_variables_tipadas? PC RETORNO PA lista_variables_tipadas PC bloque_variables bloque_instrucciones;
bloque_procedimiento: PROCEDIMIENTO IDENT PA lista_variables_tipadas? PC bloque_variables bloque_instrucciones;
bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | (FFUNCION | FPROCEDIMIENTO));
bloque_variables: VARIABLES declaracion_variable*;

//separar en bloques?
instruccion:
    instruccion_llamada_subprograma |
    instruccion_aserto  |
    instruccion_bucle  |
    instruccion_control  |
    instruccion_ruptura  |
    instruccion_asig |
    instruccion_retorno;

instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP subprograma LLC)? instruccion+ FMIENTRAS;

//instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP IDENT PA lista_variables PC LLC)? instruccion+ FMIENTRAS;
instruccion_llamada_subprograma: (mostrar | subprograma) PyC;
instruccion_control: SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)? FSI;
instruccion_ruptura: RUPTURA PyC;
instruccion_asig: lista_variables ASIG evaluaciones_variables PyC;
instruccion_retorno: RETORNO evaluaciones_variables PyC;
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

//: (operando_logico)
/*
predicado:
    (operacion) #operacion_simple |
    ((NO? (PA predicado PC))) #predicado_anidado |
    (operacion operador_condicion_2_ario predicado) #predicado_compuesto_doble |
    (NO? (operacion operador_condicion_2_ario operacion)) #predicado_simple |
    (operacion operador_condicion_2_ario (NO? predicado)) #predicado_compuesto
*/

/*
predicado:
    (igualdades_desigualdades) #basico |
    (igualdades_desigualdades operador_condicion_2_ario igualdades_desigualdades) #predicado_simple |
    (igualdades_desigualdades operador_condicion_2_ario NO? predicado) #predicado_compuesto |
    ((PA igualdades_desigualdades PC) operador_condicion_2_ario NO? (PA predicado PC))  #predicado_compuesto_doble |
    ((PA predicado PC) operador_condicion_2_ario NO? (PA igualdades_desigualdades PC)) #predicado_compuesto_doble2 |
    (NO? (PA predicado PC)) #predicado_anidado

;
*/

/* este era el bueno predicado
predicado:
    NO? ((PA igualdades_desigualdades PC) | igualdades_desigualdades) #operacion_simple |
    NO? (PA predicado PC) #predicado_anidado |
    (igualdades_desigualdades operador_condicion_2_ario igualdades_desigualdades) #predicado_simple |
    (igualdades_desigualdades operador_condicion_2_ario predicado) #predicado_compuesto |
    (NO? (PA predicado PC) operador_condicion_2_ario predicado)  #predicado_compuesto_doble
;
*/
/*
    CIERTO #predicado_cierto |
    FALSO #predicado_falso |
    NO predicado #predicado_negado |
    PA predicado PC #predicado_envuelto |
    digualdad #predicado_caso_base |
    //predicado #predicado_envuelto |
    predicado operador_condicion_2_ario predicado #predicado_doble
*/
//este es el beno 2,.

predicado:
    condicion #predicado_base |
    CIERTO #predicado_cierto |
    FALSO #predicado_falso |
    NO predicado #predicado_negado |
    PA predicado PC #predicado_envuelto |
    predicado operador_condicion_2_ario predicado #predicado_rec
    //condicion+ #predicado_rec;
    //condicion (operador_condicion_2_ario condicion)* #predicado_rec
;/*
predicado:
    CIERTO #predicado_cierto |
    FALSO #predicado_falso |
    NO predicado #predicado_negado |
    PA predicado PC #predicado_envuelto |
    condicion #predicado_base |
    predicado (operador_condicion_2_ario predicado) #predicado_rec
    ;
*/




//operaciones con operadores logicos
/*
digualdad:
    PA digualdad PC #digualdad_envuelto |
    valor_booleano operador_logico_2_ario valor_booleano #digualdad_caso_base_bool |
    operando_secuencia operador_logico_2_ario operando_secuencia #digualdad_caso_base_se |
    operacion (operador_logico_2_ario operacion) #digualdad_caso_base_num |
    digualdad (operador_logico_2_ario digualdad) #digualdad_doble

;
*/



condicion:
    valor_booleano operador_logico_2_ario valor_booleano #condicion_bool |
    operando_secuencia operador_logico_2_ario operando_secuencia #condicion_sec |
    operacion #condicion_base |
    PA condicion PC #condicion_envuelta |
    condicion operador_logico_2_ario condicion #condicion_rec;




    //operando_secuencia #condicion_sec |
    //PA condicion PC #condicion_envuelta
    //PA condicion PC #condicion_envuelta |
    //condicion (operador_logico_2_ario condicion)+ #condicion_rec

    //condicion ((IGUAL | DIGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL) condicion) #asd;
    //operacion (operador_logico_2_ario condicion)+ #condicion_rec;
        //(operacion | (PA operacion PC)) (operador_logico_2_ario operacion)+;


//operaciones con operadores aritmeticos

operacion:
    operando #operando_caso_base |
    PA operacion PC #op_aritmetica_envuelta |
    operacion MULT operacion #op_aritmetica_mult |
    operacion (MAS | MENOS) operacion #op_aritmetica_sr;

   // operacion operador_aritmetico_2_ario operacion #asd;

    //operando (operador_aritmetico_2_ario operacion)*;
/*
operacion:
    operando #operando_caso_base |
    PA operacion PC #op_aritmetica_envuelta |
    operacion operador_aritmetico_2_ario operacion #op_aritmetica_doble
    //operacion operador_aritmetico_2_ario operacion |
    //PA operacion PC (MAS | MENOS) PA operacion PC |
    //operacion (MAS | MENOS) operacion
    ;
    */
/*
operacion_mm:
    operando |
        PA operacion_mm PC (MAS | MENOS) PA operacion_mm PC |
        operacion_mm (MAS | MENOS) operacion_mm
;
*/
/*
operacion:
    (operando | (PA operando PC)) #operando_simple |
    (PA operacion PC ) #mult_operacion_anidada |
    (operando MULT operando) #op_mult_aritmetica_simple |
    (operando MULT operacion) #op_mult_aritmetica_compuesta |
    ((PA operacion PC) MULT operacion) #op_mult_aritmetica_compuesta_doble |

        //(PA operacion PC ) #mm_operacion_anidada |
        (operando (MAS | MENOS) operando) #op_mm_aritmetica_simple |
        (operando (MAS | MENOS) operacion) #op_mm_aritmetica_compuesta |
        ((PA operacion PC) (MAS | MENOS) operacion) #op_mm_aritmetica_compuesta_doble

;
*/
/*
operacion:
    (operando | (PA operando PC)) #operando_simple |
    (PA operacion PC ) #operacion_anidada |
    (operando operador_aritmetico_2_ario operando) #op_aritmetica_simple |
    (operando operador_aritmetico_2_ario operacion) #op_aritmetica_compuesta |
    ((PA operacion PC) operador_aritmetico_2_ario operacion) #op_aritmetica_compuesta_doble
;
*/


//operando_numerico: NUMERO;

operando:
    NUMERO #operando_numerico |

    valor_booleano #operando_booleano |
    //vacia #operando_vacia |
    variable #operando_variable |
    variable_acceso  #vle_acceso |
    //ultima_posicion #operando_ultima_posicion |
    subprograma #operando_subprograma
;

operador_2_ario: operador_aritmetico_2_ario | operador_logico_2_ario;


//operacion_logica: (operando_logico | (PA (operacion_logica) PC)) (operador_logico_2_ario (operacion_logica | (PA (operacion_logica) PC) ))?;
//operacion_aritmetica: (operando_aritmetico | (PA operacion_aritmetica PC)) (operador_aritmetico_2_ario (operacion_aritmetica | (PA operacion_aritmetica PC) ))?; //he cambiado * por ?
//operacion_2_aria: ((operando_logico | operando_aritmetico) | (PA operacion_2_aria PC)) (operador_2_ario (operacion_2_aria | (PA operacion_2_aria PC)))*;
//esto se deberia arreglar, realmente operandos deberian ser de cualquier tipo... eso es del semantico!
//operando_universal: variable | variable_acceso | ultima_posicion | subprograma;
//operando_aritmetico: NUMERO | operando_universal;
//operando_logico: TRUE | FALSE | CIERTO | FALSO | operando_universal | operacion_aritmetica | vacia;



operando_secuencia:
(CA CC) #operando_secuencia_vacia |
//(CA evaluacion_variable CC) #operando_secuencia_|
(CA ((valor_booleano COMA)* valor_booleano) CC) #operando_secuencia_logica |
(CA ((NUMERO COMA)* NUMERO) CC) #operando_secuencia_numerica
;
//operando_secuencia: CA evaluacion_variable CC;

//operador_2_ario: operador_aritmetico_2_ario | operador_logico_2_ario;


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

//variable_acceso: variable CA operacion_aritmetica CC;
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


 //esto deberia solo aceptar listas
vacia: VACIA PA evaluacion_variable PC; //esto tambien acepta solo listas
mostrar: MOSTRAR PA evaluacion_variable PC;