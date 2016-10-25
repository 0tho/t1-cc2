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

  public static void erroVariavelNaoExiste(int numLinha, int numColuna, String variavel) {
    Saida.println(numLinha+","+(numColuna+1)+":Variavel "+variavel+" nao amarrada");
  }

  public static void erroPertoDe( int numLinha, String token ) {
    Saida.println( "Linha " + numLinha + ": erro sintatico proximo a " + token );
  }
}
