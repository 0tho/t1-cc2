package trabalho1;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;


public class LaErrorStrategy extends DefaultErrorStrategy {

  @Override
	public void reportError( Parser recognizer, RecognitionException e ) {
    super.reportError( recognizer, e );
    
  }

}
