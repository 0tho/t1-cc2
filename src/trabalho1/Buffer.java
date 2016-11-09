package trabalho1;

public class Buffer {
    private StringBuffer texto;

    public Buffer() {
      texto = new StringBuffer();
    }

    public void println(String txt) {
        texto.append(txt).append("\n");
    }

    public void clear() {
        texto = new StringBuffer();
    }

    public String getTexto() {
        return texto.toString();
    }

    public Boolean isEmpty() {
      return texto.length() <= 0;
    }
}
