// Analizador léxico lenguaje K
lexer grammar Analex_EXPL;

BLANCO: ' ' ->skip;
TABULADOR: '\t'->skip;
FIN_LINEA: '\r'?'\n' ->skip;
fragment DIGITO: [0-9];
fragment LETRA:[a-zA-Z];
PARATODO: 'PARATODO';
EXISTE: 'EXISTE';
TRUE: 'True';
FALSE: 'False';
INTEGER: 'Integer';
BOOLEAN: 'Boolean';
IMPLICA: '=>';
Y: 'Y';
O: 'O';
DP: ':';
NO: 'NO';
NUMERO: DIGITO+;
//NUMERO : ('-')?DIGITO+;
IDENT : LETRA(LETRA|DIGITO)*;
PA : '(';
PC : ')';
PyC : ';';
COMA : ',';
ASIG: '=';
MAYOR: '>';
MENOR: '<';
IGUAL: '==';
MAS: '+';
MENOS: '-';
MULT: '*';
DIV: '/';

COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;
COMENTARIO_LINEA : '//' .*? FIN_LINEA -> skip ;

