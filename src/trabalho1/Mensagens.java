/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

/**
 *
 * @author daniel
 */
public class Mensagens {


  public static void erroVariavelNaoDeclarada(int linha, String token) {
    Saida.println( "Linha " + linha + ": identificador " + token + " nao declarado" );
    CompiladorLa.shouldGenCode = false;
  }

  public static void erroVariavelJaDeclarada(int linha, String token) {
    Saida.println( "Linha " + linha + ": identificador " + token + " ja declarado anteriormente" );
    CompiladorLa.shouldGenCode = false;
  }

  public static void erroTipoNaoDeclarada(int linha, String token) {
    Saida.println( "Linha " + linha + ": tipo " + token + " nao declarado" );
    CompiladorLa.shouldGenCode = false;
  }

  // Sintatico
  public static void erroSintaticoProximoA( int linha, String token ) {
    Saida.println( "Linha " + linha + ": erro sintatico proximo a " + token );
    CompiladorLa.shouldGenCode = false;
  }

  public static void erroCaracterNaoIdentificado( int linha, String caracter ) {
    Saida.println( "Linha " + linha + ": " + caracter + " - simbolo nao identificado" );
  }

  public static void erroComentarioNaoFechado( int linha ) {
    Saida.println( "Linha " + (linha+1) + ": comentario nao fechado" );
  }
}
