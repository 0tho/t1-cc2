/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

public class Geradores {

	public static String enterScope() {
		return "{";
	}

	public static String leaveEscope() {
		return "}";
	}

	public static String whileLoop(String condition, String code) {
		return "while (" + condition + ") " + Geradores.enterScope() + code + Geradores.leaveEscope();
	}
	
	public static String doWhileLoop(String condition, String code) {
		return "do " + Geradores.enterScope() + code + Geradores.leaveEscope() + "while (" + condition + ");"
	}
	
	public static String forLoop(String condition, String code) {
		return "for (" + condition + ") " + Geradores.enterScope() + code + Geradores.leaveEscope();
	}

	public static String attribution() {
		return "";
	}

	public static String normalize() {
		return "";
	}

	public static String method() {
		return "";
	}

	public static String write() {
		return "";
	}

	public static String read() {
		return "";
	}

	public static String condition(String condition, String code, String elseCode), {
		String finalCode = "if (" + condition + ")" + Geradores.enterScope() + code + Geradores.leaveEscope();

		if (elseCode.length() > 0) {
			finalCode+= "else " + Geradores.enterScope() + elseCode + Geradores.leaveEscope();
		}

		return finalCode;
	}

	public static String switchCondition(String condition, ArrayList<String> caseCondition, ArrayList<String> caseCode) {
		return "switch (" + condition + ")" + Geradores.enterScope() + Geradores.switchCases(caseList, caseCode) + Geradores.leaveEscope();
	}

	public static String switchCases(ArrayList<String> caseCondition, ArrayList<String> caseCode) {
		String caseFinal = "";

		for (int i=0; i<=caseCondition.length(); i++) {
			caseFinal+= "case" + caseCondition.get(i) + ":\n" + caseCode.get(i);
			if (caseCode.get(i).equals("")) {
				caseFinal+= " break;\n"
			}
		}

		caseFinal+= "default: " + caseCode.get(caseCode.length()-1);

		return caseFinal;
	}

	public static String beginAlgorithm() {
		return "int main()" + Geradores.enterScope();
	}

	public static String endAlgorithm() {
		return "return 0;" + Geradores.leaveEscope();
	}
}