// Analizador sintáctico lenguaje expr
parser grammar Anasint;
options{
 tokenVocab=Analex;
}
sentencia: (bloque_variables | bloque_funcion | bloque_procedimiento | bloque_instrucciones)*;

declaracion_variable: lista_variables DP tipo PyC;

lista_variables: (IDENT COMA)* IDENT;
lista_variables_tipadas: (tipo IDENT COMA)* tipo IDENT;

tipo: tipo_elemental | tipo_no_elemental;
tipo_no_elemental: SEQUENCE PA tipo_elemental PC;
tipo_elemental: INTEGER | BOOLEAN;

args_funcion_procedimiento: PA lista_variables_tipadas PC;
bloque_funcion: FUNCION IDENT args_funcion_procedimiento RETORNO args_funcion_procedimiento bloque_variables bloque_instrucciones;
bloque_procedimiento: PROCEDIMIENTO IDENT args_funcion_procedimiento (EOF | bloque_variables);
bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | FFUNCION | FPROCEDIMIENTO);
bloque_variables: VARIABLES declaracion_variable*;

//separar en bloques?
instruccion: instruccion_aserto | instruccion_bucle | instruccion_control | instruccion_asig | instruccion_retorno;
instruccion_bucle: MIENTRAS PA predicado PC HACER (LLA AVANCE DP IDENT PA lista_variables PC LLC)? instruccion+ FMIENTRAS;
instruccion_control: SI PA predicado PC ENTONCES instruccion+ (SINO instruccion+)* FSI;
instruccion_asig: IDENT ASIG evaluacion_variable PyC;
instruccion_retorno: RETORNO evaluacion_variable PyC;
instruccion_aserto:
    LLA (
        (CIERTO | FALSO) |
        ((PARATODO | EXISTE) PA IDENT DP CA operando_universal COMA operando_universal CC COMA predicado PC))
    LLC;
//predicado_global: lista_predicados;
//lista_predicados: predicado | ((predicado operador_logico)+ (lista_predicados | (PA lista_predicados PC)));
//variable_global: evaluacion_variable | (PA variable_global PC);

predicado: operacion_logica (operador_condicion_2_ario (predicado | (PA predicado PC)))*;
evaluacion_variable: NO? operacion_logica | operacion_aritmetica | variable_acceso | ultima_posicion | NO? vacia; //estos dos ultimos sobran?
//variable_operacion: ((PA (IDENT | NUMERO) PC) | (IDENT | NUMERO)) operador_logico variable | (PA variable PC);

operacion_logica: (NO? (PA evaluacion_variable PC) | NO? operando_logico) (operador_logico_2_ario evaluacion_variable)*;
operacion_aritmetica: ((PA operacion_aritmetica PC) | operando_aritmetico) (operador_aritmetico_2_ario operacion_aritmetica)*;
//variable_operacion: (IDENT | NUMERO) (MAS | MENOS) (variable | (PA variable PC));
//variable_unitaria: (IDENT | NUMERO | TRUE | FALSE);
variable_acceso: IDENT CA operando_aritmetico CC;

//esto se deberia arreglar, realmente operandos deberian ser de cualquier tipo... eso es del semantico!
operando_universal: IDENT | NUMERO | variable_acceso | ultima_posicion;
operando_aritmetico: operando_universal;
operando_logico: TRUE | FALSE | CIERTO | FALSO | operando_universal | vacia;

operador_2_ario: operador_aritmetico_2_ario | operador_logico_2_ario;

operador_aritmetico_2_ario: MULT | (MAS | MENOS);
operador_logico_2_ario: IGUAL | DIGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL;

operador_condicion_2_ario: operador_logico_2_ario | AND | OR;
operador_condicion_1_ario: NO;

ultima_posicion: UL_POS PA IDENT PC;
vacia: VACIA PA IDENT PC;

/*
expr: (expr_asign PyC)*;
expr_asign: IDENT ASIG expr1;
expr1: expr2 (MAYOR expr1 | MENOR expr1 | IGUAL expr1) | expr2;
expr2: expr3 (MAS expr2 | MENOS expr2 | MULT expr2 | DIV expr2) | expr3;
expr3: (IDENT | NUMERO | TRUE | FALSE);



expr: expr1 (Y expr |O expr) | NO expr | expr1;
expr1: expr2 (MAYOR expr2 | MENOR expr2 | IGUAL expr2);
expr2: expr3 (MAS expr2 | MENOS expr2 | MULT expr2 | DIV expr2);
expr3: IDENT | NUMERO | TRUE | FALSE;
*/
//expr_def: IDENT

/*
expr_asig: IDENT ASIG (expr_booleano | expr_aritmetica | expr_logico);
expr_booleano: TRUE | FALSE;
expr_logico: MAYOR | MENOR | IGUAL | NO;
expr_aritmetica: IDENT operador (IDENT |
                       operador NUMERO |
                       operador expr_aritmetica);
operador: MAS | MENOS | MULT | DIV;
*/
/*
sentencia : variables expr EOF;
variables : decl_vars PyC ;
tipo: ENTERO
 | BOOLEANO
 ;
decl_vars : IDENT tipo COMA decl_vars
 | IDENT tipo
 ;
expr : expr1 (Y expr | O expr) #Expr_Y_O
 | NO expr #Expr_NO
 | expr1 #Rel

 ;
expr1 : expr2 (MAYOR expr2 | MENOR expr2 | IGUAL expr2)
#Rel_MAYOR_MENOR_IGUAL
 | expr2 #Term
 ;
expr2 : expr3 (MAS expr2 | MENOS expr2 | POR expr2) #Term_MAS_MENOS_POR
 | expr3 (DIV expr2) #Term_DIV
 | expr3 #Term_Base
 ;
expr3 : IDENT #Id
 | NUMERO #Num
 | CIERTO #T
 | FALSO #F
 | PA expr PC #ParExpr
 ;
*/







/*
// Analizador sintáctico lenguaje expr
parser grammar Anasint;
options{
 tokenVocab=Analex;
}
sentencia: (bloque_variables | bloque_funcion | bloque_instrucciones)*;

bloque_variables: VARIABLES declaracion_variable* (EOF | SUBPROGRAMAS | bloque_instrucciones);
declaracion_variable: lista_variables DP tipo PyC;

lista_variables: (IDENT COMA)* IDENT;
lista_variables_tipadas: (tipo IDENT COMA)* tipo IDENT;

tipo: tipo_elemental | tipo_no_elemental;
tipo_no_elemental: SEQUENCE PA tipo_elemental PC;
tipo_elemental: INTEGER | BOOLEAN;

bloque_funcion: FUNCION IDENT PA lista_variables_tipadas PC RETORNO PA lista_variables_tipadas PC (EOF | bloque_instrucciones | bloque_variables);
bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | FFUNCION | FPROCEDIMIENTO);

instruccion: instruccion_asig | instruccion_bucle | instruccion_control | instruccion_retorno;
instruccion_bucle: MIENTRAS PA predicado PC HACER instruccion+ FMIENTRAS;
instruccion_control: SI PA predicado_global PC ENTONCES instruccion+ (SINO instruccion+)* FSI;
instruccion_asig: IDENT ASIG variable PyC;
instruccion_retorno: RETORNO variable PyC;

predicado_global: lista_predicados;
lista_predicados: predicado | ((predicado (AND | OR))+ (lista_predicados | (PA lista_predicados PC)));
predicado: variable_global | (variable_global (IGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL) (predicado | (PA predicado PC)));

variable_global: variable | (PA variable_global PC);
variable: (PA variable_operacion PC) | variable_operacion | variable_acceso | variable_unitaria | ultima_posicion | vacia;
variable_operacion: ((PA (IDENT | NUMERO) PC) | (IDENT | NUMERO)) operador_logico variable | (PA variable PC);
operacion_logica: operando_logico operador_logico operando_logico;
//variable_operacion: (IDENT | NUMERO) (MAS | MENOS) (variable | (PA variable PC));
variable_unitaria: (IDENT | NUMERO | TRUE | FALSE);
variable_acceso: IDENT CA variable CC;

operando_aritmetico: IDENT | NUMERO;
operando_logico: TRUE | FALSE | IDENT;
operador_aritmetico: MAS | MENOS;
operador_logico: IGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL;

ultima_posicion: UL_POS PA (IDENT) PC;
vacia: VACIA PA (IDENT) PC;

/*
expr: (expr_asign PyC)*;
expr_asign: IDENT ASIG expr1;
expr1: expr2 (MAYOR expr1 | MENOR expr1 | IGUAL expr1) | expr2;
expr2: expr3 (MAS expr2 | MENOS expr2 | MULT expr2 | DIV expr2) | expr3;
expr3: (IDENT | NUMERO | TRUE | FALSE);



expr: expr1 (Y expr |O expr) | NO expr | expr1;
expr1: expr2 (MAYOR expr2 | MENOR expr2 | IGUAL expr2);
expr2: expr3 (MAS expr2 | MENOS expr2 | MULT expr2 | DIV expr2);
expr3: IDENT | NUMERO | TRUE | FALSE;
*/
//expr_def: IDENT

/*
expr_asig: IDENT ASIG (expr_booleano | expr_aritmetica | expr_logico);
expr_booleano: TRUE | FALSE;
expr_logico: MAYOR | MENOR | IGUAL | NO;
expr_aritmetica: IDENT operador (IDENT |
                       operador NUMERO |
                       operador expr_aritmetica);
operador: MAS | MENOS | MULT | DIV;
*/
/*
sentencia : variables expr EOF;
variables : decl_vars PyC ;
tipo: ENTERO
 | BOOLEANO
 ;
decl_vars : IDENT tipo COMA decl_vars
 | IDENT tipo
 ;
expr : expr1 (Y expr | O expr) #Expr_Y_O
 | NO expr #Expr_NO
 | expr1 #Rel

 ;
expr1 : expr2 (MAYOR expr2 | MENOR expr2 | IGUAL expr2)
#Rel_MAYOR_MENOR_IGUAL
 | expr2 #Term
 ;
expr2 : expr3 (MAS expr2 | MENOS expr2 | POR expr2) #Term_MAS_MENOS_POR
 | expr3 (DIV expr2) #Term_DIV
 | expr3 #Term_Base
 ;
expr3 : IDENT #Id
 | NUMERO #Num
 | CIERTO #T
 | FALSO #F
 | PA expr PC #ParExpr
 ;
*/
