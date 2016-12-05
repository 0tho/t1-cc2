/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Geradores {

	public static String enterScope() {
		return "{\n";
	}

	public static String leaveEscope() {
		return "\n}";
	}

	public static String whileLoop(String condition, String code) {
		String finalCode = "while ("
						.append(normalize(condition))
						.append(") ")
						.append(Geradores.enterScope())
						.append(code)
						.append(Geradores.leaveEscope());
		return finalCode;
	}
	
	public static String doWhileLoop(String condition, String code) {
		String finalCode = "do "
						.append(Geradores.enterScope())
						.append(code)
						.append(Geradores.leaveEscope())
						.append("while (")
						.append(normalize(condition))
						.append(");");
		return finalCode;
	}
	
	public static String forLoop(String countVariable, String begin, String end, String code) {
		String finalCode = "int "
						.append(countVariable)
						.append(";\n")
						.append("for (")
						.append(countVariable)
						.append("=")
						.append(begin)
						.append("; ")
						.append(countVariable)
						.append("<")
						.append(end)
						.append("; ")
						.append(countVariable)
						.append("++")
						.append(") ")
						.append(Geradores.enterScope())
						.append(code)
						.append(Geradores.leaveEscope());
		return finalCode;
	}

	public static String attribution(String var, String exp) {
        String finalCode = "";
        finalCode.append(var)
        		 .append(" = ")
        		 .append(normalize(exp))
        		 .append(";");
        		 
        return finalCode;
    }

	public static String normalize(String exp) {
        return exp.replace("=", "==")
            	  .replace("ou", "||")
                  .replace("e", "&&")
                  .replace("nao", "!")
                  .replace("verdadeiro", "1")
                  .replace("falso", "0");
    }

	public static String method() {
		return "";
	}

	private static String makeType(String type) {
        switch (type) {
            case "inteiro":
                return = "%d";
            case "real":
                return = "%f";
            case "logico":
                return = "%d";
            case "literal":
                return = "%s";
            default:
                return = "";
        }
    }

	public static String write(String text, ArrayList<String> variables) {
		String finalCode = "";
		finalCode.append("printf(\"")
				 .append(text)
				 .append("\"");

		if (variables.size() > 0) {
			for (String var : variables) {
				finalCode.append(",")
						 .append(var);
			}
		}

		finalCode.append(");");

		return finalCode;
	}

	public static String read(String type, String variable) {
		String finalCode = "";
		finalCode.append("scanf(\"")
				 .append(makeType(type))
				 .append("\"")
				 .append(",&")
				 .append(variable)
				 .append(");");

		return finalCode;
	}

	public static String condition(String condition, String code, String elseCode), {
		String finalCode = "if (";
		finalCode.append(condition)
				 .append(")")
				 .append(Geradores.enterScope())
				 .append(code)
				 .append(Geradores.leaveEscope());

		if (elseCode.length() > 0) {
			finalCode.append("else ")
					 .append(Geradores.enterScope())
					 .append(elseCode)
					 .append(Geradores.leaveEscope());
		}

		return finalCode;
	}

	public static String switchCondition(String condition, ArrayList<String> caseCondition, ArrayList<String> caseCode) {
		String finalCode = "switch (\n"
					    .append(condition)
					    .append(")")
					    .append(Geradores.enterScope())
					    .append(Geradores.switchCases(caseList, caseCode))
					    .append(Geradores.leaveEscope());
	}

	public static String switchCases(ArrayList<String> caseCondition, ArrayList<String> caseCode) {
		String caseFinal = "";

		for (int i=0; i<=caseCondition.length(); i++) {
			caseFinal.append("case")
					 .append(caseCondition.get(i))
					 .append(":\n")
					 .append(caseCode.get(i));
			if (caseCode.get(i).equals("")) {
				caseFinal.append(" break;\n");
			}
		}

		caseFinal+= "default: " + caseCode.get(caseCode.length()-1);

		return caseFinal;
	}
}