/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.BailErrorStrategy;

public class CompiladorLa {

    public static boolean shouldGenCode = true;

    public static void main(String[] args) throws Exception {
      String arquivoDeEntrada = null;
      String arquivoDeSaida = null;

      if ( args.length != 2 ) {
        System.out.println("Usage:\n Main input output");
        System.out.println("input is the file to be compiled");
        System.out.println("output is the file where the generated code will be stored");
        return;
      }

      arquivoDeEntrada = args[0];
      arquivoDeSaida = args[1];

      //InputStream casoDeTesteEntrada = TestaAnalisadorSemantico.class.getResourceAsStream("casosDeTeste/entrada/" + nomeArquivo);
      InputStream casoDeTesteEntrada = new FileInputStream(arquivoDeEntrada);
      FileOutputStream casoDeTesteSaida = new FileOutputStream(arquivoDeSaida);
      PrintWriter printWriter = new PrintWriter(casoDeTesteSaida);

      ANTLRInputStream input = new ANTLRInputStream(casoDeTesteEntrada);
      laLexer lexer = new laLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      laParser parser = new laParser(tokens);

      lexer.removeErrorListeners();
      lexer.addErrorListener(ErrorListener.INSTANCE);

      // parser.setErrorHandler(new LaErrorStrategy());
      parser.removeErrorListeners();
      parser.addErrorListener(ErrorListener.INSTANCE);

      try {
        parser.programa();
      } catch ( Exception e ) {
        // e.printStackTrace(printWriter);
      }

      if( !shouldGenCode ) {
        Saida.println("Fim da compilacao");
      } else {

      }
      printWriter.print(Saida.getTexto());
      printWriter.close();
    }
}
