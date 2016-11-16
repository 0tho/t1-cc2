package trabalho1;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Token;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.misc.IntervalSet;


public class LaErrorStrategy extends DefaultErrorStrategy {

  @Override
	public void reportError( Parser recognizer, RecognitionException e ) {
    super.reportError( recognizer, e );
    throw new ParseCancellationException();
  }

  @Override
  public void recover(Parser recognizer, RecognitionException e) {
    throw e;
  }

  @Override
  public Token recoverInline(Parser recognizer) throws RecognitionException {
		throw new InputMismatchException(recognizer);
  }

  @Override
  public void sync(Parser recognizer) throws RecognitionException {

  }

}
