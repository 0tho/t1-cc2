package trabalho1;

public class LaExtendedVisitor extends LaBaseVisitor<Void> {


  @Override
  public Void visitPrograma(LaParser.ProgramaContext ctx) {
    Lac.errorBuffer.println("Oh yis");
    return null;
  }
 }
