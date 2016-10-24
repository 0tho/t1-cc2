grammar la;

programa
  : declaracao* 'algoritmo' corpo 'fim_algoritmo'
  ;

declaracao
  : declaracao_local
  | declaracao_global
  ;

declaracao_local
 : 'declare' variavel
 | 'constante' ident ':' tipo_basico '=' valor_constante
 | 'tipo' ident ':' tipo
 ;

variavel
  : ident dimensao mais_var* ':' tipo
  ;

mais_var
  : separador ident dimensao
  ;

identificador
  : ponteiros_opcionais? ident dimensao outros_ident?
  ;

ponteiros_opcionais
  : '^' ponteiros_opcionais?
  ;

outros_ident
  : '.' identificador
  ;

dimensao
  : ('[' exp_aritmetica ']')*
  ;

tipo
  : registro
  | tipo_estendido
  ;

mais_ident
  : separador identificador
  ;

mais_variaveis
  : variavel mais_variaveis?
  ;

tipo_basico
  : 'literal'
  | 'inteiro'
  | 'real'
  | 'logico'
  ;

tipo_basico_ident
  : tipo_basico
  | ident
  ;

tipo_estendido
  : ponteiros_opcionais? tipo_basico_ident
  ;

valor_constante
  : cadeia
  | num_int
  | num_real
  | true
  | false
  ;

registro
  : 'registro' variavel mais_variaveis? 'fim_registro'
  ;

declaracao_global
  : 'procedimento' ident ap parametro? fp declaracoes_locais? comandos 'fim_procedimento'
  | 'funcao' ident ap parametro? fp ':' tipo_estendido declaracoes_locais? comandos 'fim_funcao'
  ;

parametro
  : var? identificador mais_ident* ':' tipo_estendido mais_parametros?
  ;

mais_parametros
  : separador parametro
  ;

declaracoes_locais
  : declaracao_local declaracoes_locais?
  ;

corpo
  : declaracoes_locais? comandos
  ;

comandos
  : cmd+
  ;

cmd
  : 'leia' ap identificador mais_ident* fp
  | 'escreva' ap expressao mais_expressao? fp
  | 'se' expressao 'entao' comandos senao_opcional? 'fim_se'
  | 'caso' exp_aritmetica 'seja' selecao senao_opcional? 'fim_caso'
  | 'para' ident '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para'
  | 'enquanto' expressao 'faca' comandos 'fim_enquanto'
  | 'faca' comandos 'ate' expressao
  | '^' ident outros_ident? dimensao '<-' expressao
  | ident chamada_atribuicao
  | 'retorne' expressao
  ;

mais_expressao
  : separador expressao mais_expressao?
  ;

senao_opcional
  : 'senao' comandos
  ;

chamada_atribuicao
  : ap argumentos_opcional? fp
  | outros_ident? dimensao '-' expressao
  ;

argumentos_opcional
  : expressao mais_expressao?
  ;

selecao
  : constantes ':' comandos mais_selecao?
  ;

mais_selecao
  : selecao
  ;

constantes
  : numero_intervalo mais_constantes?
  ;

mais_constantes
  : separador constantes
  ;

numero_intervalo
  : op_unario? num_int intervalo_opcional?
  ;

intervalo_opcional
  : '..' op_unario? num_int
  ;

exp_aritmetica
  : termo outros_termos?
  ;


termo
  : fator outros_fatores*
  ;

outros_termos
  : op_adicao termo outros_termos?
  ;

fator
  : parcela outras_parcelas*
  ;

outros_fatores
  : op_multiplicacao fator
  ;

parcela
  : op_unario? parcela_unario
  | parcela_nao_unario
  ;

parcela_unario
  : '^' ident outros_ident? dimensao
  | ident chamada_partes?
  | num_int
  | num_real
  | ap expressao fp
  ;

parcela_nao_unario
  : '&' ident outros_ident? dimensao
  | cadeia
  ;

outras_parcelas
  : '%' parcela
  ;

chamada_partes
  : ap expressao mais_expressao? fp
  | outros_ident? dimensao
  ;

exp_relacional
  : exp_aritmetica op_opcional?
  ;

op_opcional
  : op_relacional exp_aritmetica
  ;

expressao
  : termo_logico outros_termos_logicos*
  ;

termo_logico
  : fator_logico outros_fatores_logicos*
  ;

outros_termos_logicos
  : 'ou' termo_logico
  ;

outros_fatores_logicos
  : 'e' fator_logico
  ;

fator_logico
  : op_nao? parcela_logica
  ;

parcela_logica
  : true
  | false
  | exp_relacional
  ;

var
  : 'var'
  ;

op_unario
  : '-'
  ;

op_relacional
  : '='
  | '<>'
  | '>='
  | '<='
  | '>'
  | '<'
  ;

op_nao
  : 'nao'
  ;

op_multiplicacao
  : '*'
  | '/'
  ;

op_adicao
  : '+'
  | '-'
  ;

true
  : 'verdadeiro'
  ;

false
  : 'falso'
  ;

ap
  : '('
  ;

fp
  : ')'
  ;

separador
  : ','
  ;

ident
  : nao_digito ( nao_digito | digito )*
  ;

num_int
  : digito+
  ;

num_real
  : digito+ '.' digito+
  ;

cadeia
  : '"' ~["\\\r\n]* '"'
  ;

nao_digito
  : [a-zA-z_]
  ;

digito
  : [0-9]
  ;

COMENTARIO
  : '{' ~[}]* '}'
    -> skip
  ;