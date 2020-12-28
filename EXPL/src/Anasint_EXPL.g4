// Analizador sintáctico lenguaje expr
parser grammar Anasint_EXPL;
options{
 tokenVocab=Analex_EXPL;
}
sentencia: expr EOF;
expr: (PA (predicado) PC)* | cuantificador_universal*;
cuantificador_universal: PARATODO variable COMA variable DP PA variables_cuantificadas PC;
cuantificador_existencial: EXISTE variable DP PA predicado PC;

//predicado: predicado_logico | predicado (Y|O) predicado+;
predicado: predicado (Y | O) predicado | (elto (IGUAL | MENOR | MAYOR) elto | NO elto | elto);
elto: variable | NUMERO | funcion | operacion_aritmetica;
//predicado_logico: elto | (elto IGUAL elto) | (elto MENOR elto) | (elto MAYOR elto) | NO elto;
//predicado_logico: elto | predicado_logico (IGUAL | MENOR | MAYOR) predicado_logico | NO predicado_logico;
//elto: variable | NUMERO | funcion | operacion_aritmetica;
operacion_aritmetica: (variable | NUMERO | funcion) ((MAS | MENOS | MULT | DIV) (variable | NUMERO | funcion))+;
//operacion_aritmetica: (variable | NUMERO | funcion) | operacion_aritmetica (MAS | MENOS | MULT | DIV) operacion_aritmetica;
variable: IDENT;
variables_cuantificadas: predicado IMPLICA (predicado | cuantificador_existencial);
funcion: variable PA (elto (COMA elto)*) PC;
/*
sentencia: variables expr EOF;
variables: decl_vars PyC;
decl_vars: tipo IDENT COMA decl_vars | tipo IDENT;
tipo: INTEGER | BOOLEAN;
expr: (expr_asign PyC)*;
expr_asign: IDENT ASIG expr1;
expr1: expr2 (MAYOR expr1 | MENOR expr1 | IGUAL expr1) | expr2;
expr2: expr3 (MAS expr2 | MENOS expr2 | MULT expr2 | DIV expr2) | expr3;
expr3: (IDENT | NUMERO | TRUE | FALSE);
*/

/*
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