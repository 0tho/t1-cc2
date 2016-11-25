parser grammar LaParser;

options { tokenVocab=LaLexer; }

programa : declaracao* BeginAlgorithm corpo EndAlgorithm EOF;

declaracao
  : declaracao_local
  | declaracao_global
  ;

declaracao_local
  : Declare declare_variavel
  | Constant Ident AssignType tipo_basico Equal valor_constante
  | Type Ident AssignType tipo
  ;

declaracao_global
  : Procedure Ident LeftParen lista_parametros? RightParen declaracao_local* comando* EndProcedure
  | Function Ident LeftParen lista_parametros? RightParen AssignType tipo_estendido declaracao_local* comando* EndFunction
  ;

tipo_basico : BasicTypes;
tipo_basico_identificador: tipo_basico | Ident;
tipo_estendido: Pointer* tipo_basico_identificador;

declare_variavel: variavel_unica mais_variaveis* AssignType tipo;
mais_variaveis: Separator variavel_unica;
variavel_unica: Ident dimensao*;
identificador: Pointer* Ident dimensao* sub_identificador*;
sub_identificador: Acessor identificador;
mais_identificador: Separator identificador;
dimensao: LeftBracket exp_aritmetica RightBracket;
tipo: registro | tipo_estendido;

valor_constante: String | Int | Real | True | False;
registro: Register declare_variavel+ EndRegister;

lista_parametros: parametro mais_parametro*;
parametro: Var? identificador mais_identificador* AssignType tipo_estendido;
mais_parametro: Separator parametro;


corpo: declaracao_local* comando*;
comando
  : Read LeftParen identificador mais_identificador* RightParen
  | Write LeftParen expressao mais_expressao* RightParen
  | If expressao Then comando* senao? EndIf
  | Case exp_aritmetica Be selecao senao? EndCase
  | For Ident Assign exp_aritmetica Until exp_aritmetica Do comando* EndFor
  | While expressao Do comando* EndWhile
  | Do comando* Until expressao
  | Pointer? Ident sub_identificador* dimensao* Assign expressao
  | Ident LeftParen lista_expressao? RightParen
  | Return expressao
  ;


senao: Else comando*;
selecao: constantes AssignType comando* selecao*;
constantes: numero_intervalo mais_constantes*;
numero_intervalo: Minus? Int (Interval Minus? Int)?;

mais_expressao: Separator expressao;
/*
lista_expressao:  expressao mais_expressao*;
*/
/*-----------------------------------------------------------*/

op_unario
  : Minus
  | // ε
  ;

exp_aritmetica
  : termo outros_termos
  ;

op_multiplicacao
  : Mult
  | Div
  ;

op_adicao
  : Plus
  | Minus
  ;

termo
  : fator outros_fatores
  ;

outros_termos
  : op_adicao termo outros_termos
  | // ε
  ;

fator
  : parcela outras_parcelas
  ;

outros_fatores
  : op_multiplicacao fator outros_fatores
  | // ε
  ;

parcela
  : op_unario parcela_unario
  | parcela_nao_unario
  ;

parcela_unario
  : Pointer? Ident sub_identificador* dimensao* #parcelaUnarioVariavel
  | Ident LeftParen lista_expressao RightParen #parcelaUnarioChamadaFuncao
  | Int #parcelaUnarioInteiro
  | Real #parcelaUnarioReal
  | LeftParen expressao RightParen #parcelaUnarioParenteses
  ;

parcela_nao_unario
  : Address Ident sub_identificador* dimensao*
  | String
  ;

outras_parcelas
  : Mod parcela outras_parcelas
  | // ε
  ;

exp_relacional
  : exp_aritmetica op_opcional
  ;

op_opcional
  : op_relacional exp_aritmetica
  | // ε
  ;

op_relacional
  : Equal
  | NotEqual
  | GreaterEqual
  | LesserEqual
  | Greater
  | Lesser
  ;


expressao
  : termo_logico outros_termos_logicos
  ;

lista_expressao
  : expressao mais_expressao*
  ;

mais_constantes
  : Separator constantes
  ;

op_nao
  : Not
  | // ε
  ;

termo_logico
  : fator_logico outros_fatores_logicos
  ;

outros_termos_logicos
  : Or termo_logico outros_termos_logicos
  | // ε
  ;

outros_fatores_logicos
  : And fator_logico outros_fatores_logicos
  | // ε
  ;

fator_logico
  : op_nao parcela_logica
  ;

parcela_logica
  : True
  | False
  | exp_relacional
  ;
