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

public class Lac {

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

      InputStream casoDeTesteEntrada = new FileInputStream(arquivoDeEntrada);
      FileOutputStream casoDeTesteSaida = new FileOutputStream(arquivoDeSaida);
      PrintWriter printWriter = new PrintWriter(casoDeTesteSaida);

      ANTLRInputStream input = new ANTLRInputStream(casoDeTesteEntrada);
      LaLexer lexer = new LaLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      LaParser parser = new LaParser(tokens);

      Buffer errorBuffer = new Buffer();
      Buffer geradorBuffer = new Buffer();

      LaErrorListener errorListener = new LaErrorListener( errorBuffer );
      lexer.removeErrorListeners();
      lexer.addErrorListener(errorListener);
      parser.removeErrorListeners();
      parser.addErrorListener(errorListener);

      LaErrorStrategy errorStrategy = new LaErrorStrategy();
      parser.setErrorHandler(errorStrategy);

      try {
        parser.programa();
      } catch ( Exception e ) {
        // e.printStackTrace(printWriter);
      }

      if ( !errorBuffer.isEmpty() ) {
        errorBuffer.println("Fim da compilacao");
        printWriter.print( errorBuffer.getTexto() );
      } else {
        printWriter.print( geradorBuffer.getTexto() );
      }
      printWriter.close();
    }
}
