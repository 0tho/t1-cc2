package trabalho1;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;

public class LaErrorStrategy extends DefaultErrorStrategy {

	@Override
	protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
    Saida.println("Pegou um erro");
	}

}
