package trabalho1;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.LexerNoViableAltException;

public class ErrorListener extends BaseErrorListener {
    public static ErrorListener INSTANCE = new ErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        Token token = (Token) offendingSymbol;
        String text;
        if ( token != null) {
          text  = token.getText();
          if( text == "<EOF>") text = "EOF";

          Saida.println("Linha "+line+": erro sintatico proximo a "+ text);
        } else {
          String erro[] = msg.split("'");
          String caracter = Character.toString(erro[1].charAt(0));

          // Saida.println( msg );
          Saida.println( "Linha "+line+": " + caracter + " - simbolo nao identificado" );
        }

        throw new ParseCancellationException();
    }
}
