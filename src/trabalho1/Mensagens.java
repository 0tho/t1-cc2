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

  private static String header( int linha ) {
    return "Linha " + linha + ": ";
  }

  public static String erroVariavelNaoDeclarada(int linha, String token) {
    return header(linha) + "identificador " + token + " nao declarado";
  }

  public static String erroVariavelJaDeclarada(int linha, String token) {
    return header(linha) + "identificador " + token + " ja declarado anteriormente";
  }

  public static String erroTipoNaoDeclarada(int linha, String token) {
    return header(linha) + "tipo " + token + " nao declarado";
  }

  public static String erroRetorneEmEscopoIncorreto( int linha ) {
    return header(linha) + "comando retorne nao permitido nesse escopo";
  }

  public static String erroAtribuicaoIncompativel( int linha, String text ) {
    return header(linha) + "atribuicao nao compativel para " + text;
  }

  public static String erroIncompatibilidadeDeParametros( int linha, String text ) {
    return header(linha) + "incompatibilidade de parametros na chamada de " + text;
  }

  // Sintatico
  public static String erroSintaticoProximoA( int linha, String token ) {
    return header(linha) + "erro sintatico proximo a " + token;
  }

  public static String erroCaracterNaoIdentificado( int linha, String caracter ) {
    return header(linha) + caracter + " - simbolo nao identificado";
  }

  public static String erroComentarioNaoFechado( int linha ) {
    return header(linha) + "comentario nao fechado";
  }
}
