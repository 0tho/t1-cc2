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
      Token token = (Token) offendingSymbol;
      int tokenType = token.getType();
      if ( tokenType == LaLexer.WrongComment ) {
        errorBuffer.println( Mensagens.erroComentarioNaoFechado( line ) );
      } else if(tokenType == LaLexer.WrongCharacter) {
        errorBuffer.println( Mensagens.erroCaracterNaoIdentificado( line, token.getText() ) );
      } else {
        String text;

        text  = token.getText();
        if( text == "<EOF>") text = "EOF";

        errorBuffer.println( Mensagens.erroSintaticoProximoA( line, text ) );
      }
    }
}
