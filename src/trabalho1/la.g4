grammar la;

@header {
package trabalho1;

import java.util.ArrayList;
}


@members{
TabelaDeTipos tipos = new TabelaDeTipos();
ArrayList<String> tiposBasicos = new ArrayList<String>();
PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
}

programa
@init {
  tiposBasicos.add("literal");
  tiposBasicos.add("inteiro");
  tiposBasicos.add("real");
  tiposBasicos.add("logico");
  tipos.adicionarTipos(tiposBasicos);
}
  // Cria escopo global para as variaveis
  : { pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global")); }
    declaracoes 'algoritmo' corpo 'fim_algoritmo'
    { pilhaDeTabelas.desempilhar(); }
  ;

declaracoes
  : decl_local_global declaracoes
  | // ε
  ;

decl_local_global
  : declaracao_local
  | declaracao_global
  ;

declaracao_local
  : 'declare' variavel
  | 'constante' IDENT ':' tipo_basico '=' valor_constante
  {
    pilhaDeTabelas.topo().adicionarSimbolo($IDENT.text, $tipo_basico.text, false, true);
  }
  | 'tipo' IDENT ':' tipo
  {
    tipos.adicionarTipo($IDENT.text);
  }
  ;

variavel
@init { $nomes = new ArrayList<String>(); }
  : IDENT dimensao
    { $nomes.add($IDENT.getText()); }
    mais_var
    {
      for(String nome : $mais_var.nomes) {
        $nomes.add(nome);
      }
    }
    ':' tipo
    {
      if ( $tipo.isRegistro ) {
        tipos.adicionarTipo($tipo.text);
      }
      if ( tipos.existeTipo($tipo.text) ) {
        pilhaDeTabelas.topo().adicionarSimbolos($nomes, $tipo.text, false, false);
      } else {
        // Erro: Tipo não identificado
        Mensagens.erroTipoNaoDeclarada( $IDENT.line, $tipo.text);
      }
    }
  ;

mais_var returns [ List<String> nomes ]
@init { $nomes = new ArrayList<String>(); }
  : (',' IDENT
  { $nomes.add($IDENT.getText()); }
  dimensao )+
  | // ε
  ;

lista_identificador
@init { $nomes = new ArrayList<String>(); }
  : identificador mais_ident
  {
    $nomes.add(identificador.text);

    for(String nome : $mais_ident.nomes) {
      $nomes.add(nome);
    }

    for(String nome : $nomes ) {
      if( !pilhaDeTabelas.topo().existeSimbolo(nome) ) {
        Mensagens.erroVariavelNaoDeclarada($identificador.line, nome);
      }
    }
  }
  ;

identificador
  : ponteiros_opcionais IDENT dimensao outros_ident
  ;

ponteiros_opcionais
  : '^' ponteiros_opcionais
  | // ε
  ;

outros_ident
  : '.' identificador
  | // ε
  ;

dimensao
  : '[' exp_aritmetica ']' dimensao
  | // ε
  ;

tipo returns [boolean isRegistro]
  : registro { $isRegistro = true; }
  | tipo_estendido { $isRegistro = false; }
  ;

mais_ident returns [ List<String> nomes ]
@init { $nomes = new ArrayList<String>(); }
  : (',' identificador { $nomes.add($identificador.getText()); } )+
  | // ε
  ;

mais_variaveis
  : variavel mais_variaveis
  | // ε
  ;

tipo_basico
  : 'literal'
  | 'inteiro'
  | 'real'
  | 'logico'
  ;

tipo_basico_ident
  : tipo_basico
  | IDENT
  ;

tipo_estendido
  : ponteiros_opcionais tipo_basico_ident
  ;

valor_constante
  : CADEIA
  | NUM_INT
  | NUM_REAL
  | 'verdadeiro'
  | 'falso'
  ;

registro
  : 'registro' variavel mais_variaveis 'fim_registro'
  ;

declaracao_global
  : 'procedimento' IDENT '(' parametros_opcional ')' declaracoes_locais comandos 'fim_procedimento'
  | 'funcao' IDENT '(' parametros_opcional ')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao'
  ;

parametros_opcional
  : parametro
  | // ε
  ;

parametro
  : var_opcional lista_identificador ':' tipo_estendido mais_parametros
  ;

var_opcional
  : 'var'
  | // ε
  ;

mais_parametros
  : ',' parametro
  | // ε
  ;

declaracoes_locais
  : declaracao_local declaracoes_locais
  | // ε
  ;

corpo
  : declaracoes_locais comandos
  ;

comandos
  : cmd comandos
  | // ε
  ;

cmd
  : 'leia' '(' lista_identificador ')'
  | 'escreva' '(' expressao mais_expressao ')'
  | 'se' expressao 'entao' comandos senao_opcional 'fim_se'
  | 'caso' exp_aritmetica 'seja' selecao senao_opcional 'fim_caso'
  | 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para'
  | 'enquanto' expressao 'faca' comandos 'fim_enquanto'
  | 'faca' comandos 'ate' expressao
  | '^' IDENT outros_ident dimensao '<-' expressao
  | IDENT chamada_atribuicao
  | 'retorne' expressao
  ;

mais_expressao
  : ',' expressao mais_expressao
  | // ε
  ;

senao_opcional
  : 'senao' comandos
  | // ε
  ;

chamada_atribuicao
  : '(' argumentos_opcional ')'
  | outros_ident dimensao '<-' expressao
  {

  }
  ;

argumentos_opcional
  : expressao mais_expressao
  | // ε
  ;

selecao
  : constantes ':' comandos mais_selecao
  ;

mais_selecao
  : selecao
  | // ε
  ;

constantes
  : numero_intervalo mais_constantes
  ;

mais_constantes
  : ',' constantes
  | // ε
  ;

numero_intervalo
  : op_unario NUM_INT intervalo_opcional
  ;

intervalo_opcional
  : '..' op_unario NUM_INT
  | // ε
  ;

op_unario
  : '-'
  | // ε
  ;

exp_aritmetica
  : termo outros_termos
  ;

op_multiplicacao
  : '*'
  | '/'
  ;

op_adicao
  : '+'
  | '-'
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
  : '^' IDENT outros_ident dimensao
  | IDENT chamada_partes
  | NUM_INT
  | NUM_REAL
  | '(' expressao ')'
  ;

parcela_nao_unario
  : '&' IDENT outros_ident dimensao
  | CADEIA
  ;

outras_parcelas
  : '%' parcela outras_parcelas
  | // ε
  ;

chamada_partes
  : '(' expressao mais_expressao ')'
  | outros_ident dimensao
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
  : '='
  | '<>'
  | '>='
  | '<='
  | '>'
  | '<'
  ;


expressao
  : termo_logico outros_termos_logicos
  ;

op_nao
  : 'nao'
  | // ε
  ;

termo_logico
  : fator_logico outros_fatores_logicos
  ;

outros_termos_logicos
  : 'ou' termo_logico outros_termos_logicos
  | // ε
  ;

outros_fatores_logicos
  : 'e' fator_logico outros_fatores_logicos
  | // ε
  ;

fator_logico
  : op_nao parcela_logica
  ;

parcela_logica
  : 'verdadeiro'
  | 'falso'
  | exp_relacional
  ;

IDENT
  : NAO_DIGITO ( NAO_DIGITO | DIGITO )*
  ;

NUM_INT
  : DIGITO+
  ;

NUM_REAL
  : DIGITO+ '.' DIGITO+
  ;

CADEIA
  : '"' ~["\r\n]*? '"'
  ;

fragment
NAO_DIGITO
  : [a-zA-Z_]
  ;

fragment
DIGITO
  : [0-9]
  ;

COMENTARIO
  : '{' ~[}]*? '}'
    -> skip
  ;

Whitespace
  : [ \t]+
    -> skip
  ;

Newline
  : (   '\r' '\n'?
    |   '\n'
    )
    -> skip
  ;
