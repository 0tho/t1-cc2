grammar Luazinha;

@header {
package trabalho2;
}


@members{
static String grupo = "<407933, 408000>";
PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
}

// Cria tabela do escopo global
programa : { pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global")); }
           trecho
           { pilhaDeTabelas.desempilhar(); }
         ;

trecho : (comando ';'?)* (ultimocomando ';'?)?
       ;

bloco : trecho
      ;

comando :  listavar
            '=' listaexp
            // Empilha as variaveis que não existem
            // após verificar que as variaveis do lado direito da atribuição
            // já estão declaradas
            {
              // Cria novo vetor vazio
              List<String> nomes = new ArrayList<String>();

              // Copia todos os nomes que não constam em nenhuma tabela
              for ( String nome:$listavar.nomes ) {
                if ( !pilhaDeTabelas.existeSimbolo( nome ) ) {
                  nomes.add( nome );
                }
              }

              // Adiciona novos simbolos
              pilhaDeTabelas.topo().adicionarSimbolos( nomes, "variavel" );
            }

        |  chamadadefuncao
        |  'do' bloco 'end'
        |  'while' exp 'do' bloco 'end'
        |  'repeat' bloco 'until' exp
        |  'if' exp 'then' bloco ('elseif' exp 'then' bloco)* ('else' bloco)? 'end'

        |  'for'
            // Criar novo escopo para o for (empilha nova tabela)
            { pilhaDeTabelas.empilhar( new TabelaDeSimbolos("for") ); }
            NOME
            // Declare a variavel $NOME caso não exista tal variavel
            {
              if ( !pilhaDeTabelas.existeSimbolo( $NOME.getText() )) {
                pilhaDeTabelas.topo().adicionarSimbolo( $NOME.getText(), "variavel" );
              }
            }
            '=' exp ',' exp (',' exp)? 'do' bloco
            // Termina escopo para o for (desempilha tabela)
            { pilhaDeTabelas.desempilhar(); }
            'end'

        |  'for'
            // Criar novo escopo para o for (empilha nova tabela)
            { pilhaDeTabelas.empilhar( new TabelaDeSimbolos("for") ); }
            listadenomes
            'in' listaexp 'do'
            // Adiciona novos simbolos nas tabelas após verificar
            // que as variaveis em listaexp já foram declaradas previamente
            {
              pilhaDeTabelas.topo().adicionarSimbolos( $listadenomes.nomes, "variavel" );
            }
            bloco
            // Termina escopo para o for (desempilha tabela)
            { pilhaDeTabelas.desempilhar(); }
            'end'

        |  'function'
            nomedafuncao
            // Começa o escopo para a 'function' (empilha tabela)
            {
              pilhaDeTabelas.empilhar( new TabelaDeSimbolos($nomedafuncao.nome) );
              if ( $nomedafuncao.metodo ) {
                pilhaDeTabelas.topo().adicionarSimbolo( "self", "parametro" );
              }
            }
            corpodafuncao
            // Termina o escopo para a 'function' (desempilha tabela)
            { pilhaDeTabelas.desempilhar(); }

        // Criar novo escopo para a função
        |  'local'
            'function'
            NOME
            // Começa o escopo para a 'function' (empilha tabela)
            { pilhaDeTabelas.empilhar( new TabelaDeSimbolos( $NOME.getText() )); }
            corpodafuncao
            // Termina o escopo para a 'function' (desempilha tabela)
            { pilhaDeTabelas.desempilhar(); }

        // Empilhar variaveis locais
        |  'local' listadenomes
            {
              pilhaDeTabelas.topo().adicionarSimbolos( $listadenomes.nomes, "variavel" );
            }
            ('=' listaexp)?
        ;

ultimocomando : 'return' (listaexp)? | 'break'
              ;

// Regra semântica extra
nomedafuncao returns [ String nome, boolean metodo ]
@init { $metodo = false; }
    : n1=NOME { $nome = $n1.getText(); }
      ('.' n2=NOME { $nome += "." + $n2.getText(); })*
      (':' n3=NOME { $metodo = true; $nome += "." + $n3.getText(); })?
    ;

listavar returns [ List<String> nomes ]
@init { $nomes = new ArrayList<String>(); }
    : v1=var { $nomes.add($v1.nome); }
      (',' v2=var { $nomes.add($v2.nome); }
      )*
    ;

var returns [ String nome, int linha, int coluna ]
    :  NOME { $nome = $NOME.getText(); $linha = $NOME.line; $coluna = $NOME.pos; }
    |  expprefixo '[' exp ']'
    |  expprefixo '.' NOME
    ;

listadenomes returns [ List<String> nomes ]
@init{ $nomes = new ArrayList<String>(); }
    : n1=NOME { $nomes.add($n1.getText()); }
      (',' n2=NOME { $nomes.add($n2.getText()); } )*
    ;

listaexp : (exp ',')* exp
         ;

exp :  'nil' | 'false' | 'true' | NUMERO | CADEIA | '...' | funcao |
       expprefixo2 | construtortabela | exp opbin exp | opunaria exp
    ;


expprefixo : NOME ( '[' exp ']' | '.' NOME )*
           ;

expprefixo2 : var
              {
                // Verifica se a variavel que aparece na expressão já foi declarada
                if( !pilhaDeTabelas.existeSimbolo( $var.nome ) ) {
                  Mensagens.erroVariavelNaoExiste( $var.linha, $var.coluna, $var.nome );
                }
              }
              | chamadadefuncao | '(' exp ')'
           ;

chamadadefuncao :  expprefixo args |
                   expprefixo ':' NOME args
                ;

args :  '(' (listaexp)? ')' | construtortabela | CADEIA
     ;

funcao : 'function' corpodafuncao
       ;

corpodafuncao : '(' (listapar)? ')' bloco 'end'
              ;

listapar : listadenomes
            {
              // Adiciona todos os parametros declarados na função
              pilhaDeTabelas.topo().adicionarSimbolos( $listadenomes.nomes, "parametro" );
            }
            (',' '...')?
         | '...'
         ;

construtortabela : '{' (listadecampos)? '}'
                 ;

listadecampos : campo (separadordecampos campo)* (separadordecampos)?
              ;

campo : '[' exp ']' '=' exp | NOME '=' exp | exp
      ;

separadordecampos : ',' | ';'
                  ;

opbin : '+' | '-' | '*' | '/' | '^' | '%' | '..' | '<' |
        '<=' | '>' | '>=' | '==' | '~=' | 'and' | 'or'
      ;

opunaria : '-' | 'not' | '#'
         ;


NOME	:	('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
CADEIA	:	'\'' ~('\n' | '\r' | '\'')* '\'' | '"' ~('\n' | '\r' | '"')* '"';
NUMERO	:	('0'..'9')+ EXPOENTE? | ('0'..'9')+ '.' ('0'..'9')* EXPOENTE?
		| '.' ('0'..'9')+ EXPOENTE?;
fragment
EXPOENTE	:	('e' | 'E') ( '+' | '-')? ('0'..'9')+;
COMENTARIO
	:	'--' ~('\n' | '\r')* '\r'? '\n' {skip();};
WS	:	(' ' | '\t' | '\r' | '\n') {skip();};
