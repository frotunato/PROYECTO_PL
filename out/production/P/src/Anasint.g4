// Analizador sintáctico lenguaje expr
parser grammar Anasint;
options{
 tokenVocab=Analex;
}
sentencia: bloque_variables | bloque_funcion | bloque_instrucciones;

bloque_variables: VARIABLES declaracion_variable* (EOF | SUBPROGRAMAS | bloque_instrucciones);
declaracion_variable: lista_variables DP tipo PyC;
lista_variables: (IDENT COMA)* IDENT;
tipo: tipo_elemental | tipo_no_elemental;
tipo_no_elemental: SEQUENCE PA tipo_elemental PC;
tipo_elemental: INTEGER | BOOLEAN;

bloque_funcion: FUNCION IDENT PA lista_variables_tipadas PC RETORNO PA lista_variables_tipadas PC (EOF | bloque_instrucciones | bloque_variables);
lista_variables_tipadas: (tipo IDENT COMA)* tipo IDENT;

bloque_instrucciones: INSTRUCCIONES instruccion+ (EOF | FFUNCION | FPROCEDIMIENTO);

instruccion: instruccion_asig | instruccion_bucle | instruccion_control | instruccion_retorno;
instruccion_bucle: MIENTRAS PA predicado PC HACER instruccion+ FMIENTRAS;
instruccion_control: SI PA predicado PC ENTONCES instruccion+ FSI;
instruccion_asig: IDENT ASIG variable PyC;
instruccion_retorno: NO NO NO NO NO NO;

predicado: variable | (variable (IGUAL | MAYOR | MENOR | MAIGUAL | MEIGUAL) variable);
variable: variable_operacion | variable_acceso | variable_unitaria | ultima_posicion | vacia;
variable_operacion: (IDENT | NUMERO) (MAS | MENOS) variable;
variable_unitaria: (IDENT | NUMERO | TRUE | FALSE);
variable_acceso: IDENT CA variable CC;


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