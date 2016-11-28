lexer grammar LaLexer;

BeginAlgorithm: 'algoritmo';
EndAlgorithm: 'fim_algoritmo';
Declare: 'declare';
Constant: 'constante';
Type: 'tipo';

AssignType: ':';
Separator: ',';
Acessor: '.';

LeftParen: '(';
RightParen: ')';
LeftBracket: '[';
RightBracket: ']';

True : 'verdadeiro';
False : 'falso';
Register: 'registro';
EndRegister: 'fim_registro';
Procedure: 'procedimento';
EndProcedure: 'fim_procedimento';
Function: 'funcao';
EndFunction: 'fim_funcao';
Var: 'var';

Read: 'leia';
Write: 'escreva';
If: 'se';
Then: 'entao';
Else: 'senao';
EndIf: 'fim_se';
Case: 'caso';
Be: 'seja';
EndCase: 'fim_caso';
For: 'para';
Until: 'ate';
EndFor: 'fim_para';
While: 'enquanto';
Do: 'faca';
EndWhile: 'fim_enquanto';
Return: 'retorne';

Plus: '+';
Minus: '-';
Mult: '*';
Div: '/';
Mod: '%';
Address: '&';
Pointer: '^';

Equal: '=';
NotEqual: '<>';
GreaterEqual: '>=';
LesserEqual: '<=';
Greater: '>';
Lesser: '<';

Not: 'nao';
Or: 'ou';
And: 'e';

Assign: '<-';
Interval: '..';

BasicTypes
  : 'literal'
  | 'inteiro'
  | 'real'
  | 'logico'
  ;

Ident: NotDigit ( NotDigit | Digit )*;

Int: Digit+;

Real: Digit+ '.' Digit+;

String: '"' ~["\r\n]*? '"';

fragment NotDigit: [a-zA-Z_];

fragment Digit: [0-9];

Comment: '{' ~[}]*? '}' -> skip;

WrongComment: '{' ~[}]*;

Whitespace: [ \t]+ -> skip;

NewLine: (   '\r' '\n'? |   '\n' ) -> skip;

WrongCharacter: .;
