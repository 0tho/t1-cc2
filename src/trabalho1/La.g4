parser grammar La;

programa : declaracao* BeginAlgorithm corpo EndAlgorithm;

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
  : Procedure Ident LeftParen lista_parametros? RightParen declaracao_local* comando* EndProcedure;
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

valor_constante: Cadeia | Int | Real | True | False;
registro: Register declare_variavel+ EndRegister;

lista_parametros: parametro mais_parametro*;
parametro: Var? identificador mais_identificador* AssignType tipo_estendido;
mais_parametro: Separator parametro;


corpo: declaracao_local* comando*;
comando
  : Read LeftParen identificador mais_identificador* RightParen
  | Write LeftParen expressao mais_expressao* RightParen
  | If expressao Then cmd* senao? EndIf
  | Case exp_aritmetica Be selecao senao? EndCase
  | For Ident Assign exp_aritmetica Until exp_aritmetica Do comando* EndFor
  | While expressao Do comando* EndWhile
  | Do comando* Until expressao
  | Pointer? Ident sub_identificador* dimensao* Assign expressao
  | Ident LeftParen lista_expressao? RightParen
  | Return expressao
  ;


senao: Else cmd*;
selecao: constantes AssignType comando* selecao*;
constantes: numero_intervalo mais_constantes*;
numero_intervalo: Minus? Int (Interval Minus? Int)?;

mais_expressao: Separator expressao;
lista_expressao:  expressao mais_expressao*;
/*-----------------------------------------------------------*/

exp_aritmetica: termo outros_termos;
outros_termos: op_adicao termo outros_termos;
fator: parcela outras_parcelas;
outros_fatores: op_multiplicacao fator outros_fatores;
parcela: Minus? parcela_unario | parcela_nao_unario;
parcela_unario
  : Ident LeftParen expressao mais_expressao* RightParen
  | Pointer? Ident sub_identificador* dimensao*
  | Int
  | Real
  | LeftParen expressao RightParen
  ;

parcela_nao_unario
  : & Ident sub_identificador* dimensao*
  | String;
  ;

outras_parcelas
  : % parcela outras_parcelas
  | // e
  ;

exp_relacional
  : exp_aritmetica op_opcional
  ;

op_opcional
  : op_relacional exp_aritmetica
  | // e
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

termo_logico
  : fator_logico outros_fatores_logicos
  ;

outros_termos_logicos
  : Or termo_logico outros_termos_logicos
  | // e
  ;

outros_fatores_logicos
  : And fator_logico outros_fatores_logicos
  | // e
  ;
  

/*
expressao
  : expressao Or expressao
  | expressao And expressao
  | expressao Mult expressao
  | expressao Div expressao
  | expresaso Mod expressao
  | expressao Plus expressao
  | expressao Minus expressao
  ;
*/
