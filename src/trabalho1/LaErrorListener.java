package trabalho1;

// import org.antlr.v4.runtime.BaseErrorListener;
// import org.antlr.v4.runtime.Recognizer;
// import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
// import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.*;

public class LaErrorListener extends BaseErrorListener {

    Buffer errorBuffer;

    public LaErrorListener( Buffer errorBuffer ) {
      super();
      this.errorBuffer = errorBuffer;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        // FailedPredicateException, InputMismatchException, LexerNoViableAltException, NoViableAltException
        if ( e instanceof LexerNoViableAltException ) {
          String erro[] = msg.split("'");
          String caracter = Character.toString(erro[1].charAt(0));

          errorBuffer.println( Mensagens.erroCaracterNaoIdentificado( line, caracter ) );
        } else {
          Token token = (Token) offendingSymbol;
          if ( token.getType() == LaLexer.COMENTARIO_ERRADO ) {
            errorBuffer.println( Mensagens.erroComentarioNaoFechado( line ) );
          } else {
            String text;

            text  = token.getText();
            if( text == "<EOF>") text = "EOF";

            errorBuffer.println( Mensagens.erroSintaticoProximoA( line, text ) );
          }
        }
      throw new ParseCancellationException();
    }
}
