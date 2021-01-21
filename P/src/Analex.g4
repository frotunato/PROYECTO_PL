// Analizador léxico lenguaje K
lexer grammar Analex;

FIN_LINEA: '\r'?'\n' ->skip;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;
COMENTARIO_LINEA : '//' .*? FIN_LINEA -> skip ;
BLANCO: ' ' ->skip;
TABULADOR: '\t'->skip;
fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];
fragment UNDERSCORE:'_';
TRUE: 'T';
FALSE: 'F';
INTEGER: 'NUM';
BOOLEAN: 'LOG';
VARIABLES: 'VARIABLES';
PROGRAMA: 'PROGRAMA';
SUBPROGRAMAS: 'SUBPROGRAMAS';
INSTRUCCIONES: 'INSTRUCCIONES';
FUNCION: 'FUNCION';
FFUNCION: 'FFUNCION';
PROCEDIMIENTO: 'PROCEDIMIENTO';
FPROCEDIMIENTO: 'FPROCEDIMIENTO';
RETORNO: 'dev';
RUPTURA: 'ruptura';
OR: '||';
AND: '&&';
UL_POS: 'ultima_posicion';
VACIA: 'vacia';
NO: '!';
SEQUENCE: 'SEQ';
NUMERO : DIGITO+;
PA : '(';
CA: '[';
CC: ']';
MOSTRAR: 'mostrar';
MIENTRAS: 'mientras';
FMIENTRAS: 'fmientras';
SI: 'si';
SINO: 'sino';
FSI: 'fsi';
ENTONCES: 'entonces';
HACER: 'hacer';
DP: ':';
PC : ')';
LLA: '{';
LLC: '}';
PyC : ';';
COMA : ',';
IGUAL: '==';
DIGUAL: '!=';
CIERTO: 'cierto';
FALSO: 'falso';
ASIG: '=';
MEIGUAL: '<=';
MAIGUAL: '>=';
MAYOR: '>';
MENOR: '<';
MAS: '+';
MENOS: '-';
MULT: '*';
IDENT : LETRA(LETRA|DIGITO|UNDERSCORE)*;

