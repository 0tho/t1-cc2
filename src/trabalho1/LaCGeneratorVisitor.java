package trabalho1;

public class LaCGeneratorVisitor extends LaParserBaseVisitor<String> {
  public LaCGeneratorVisitor() {
    super();
  }

  private int tabCount = 0;

  private String tab() {
    String tabs = "";
    for (int i = 0; i < tabCount; i++ ) {
      tabs += "\t";
    }
    return tabs;
  }

  @Override
  public String visitPrograma(LaParser.ProgramaContext ctx) {
    Lac.geradorBuffer.println(tab() + "#include <stdio.h>");
    Lac.geradorBuffer.println(tab() + "");
    Lac.geradorBuffer.println(tab() + "int main() {");
    tabCount++;
    visitChildren(ctx);
    Lac.geradorBuffer.println(tab() + "return 0;");
    tabCount--;
    Lac.geradorBuffer.println(tab() + "}");
    return null;
  }
}
