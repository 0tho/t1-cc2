package trabalho1;

import java.util.ArrayList;
import java.util.List;

public class TabelaDeTipos {

  private ArrayList<String> tipos;

  public TabelaDeTipos() {
    tipos = new ArrayList<String>();
  }

  public void adicionarTipo(String nome) {
    tipos.add(nome);
  }

  public void adicionarTipos(List<String> nomes) {
    for(String nome : nomes) {
      tipos.add(nome);
    }
  }

  public boolean existeTipo(String nome) {
    nome = nome.replace("^", "");
    for(String tipo : tipos) {
      if(tipo.equals(nome)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    String ret = "Tipos: ";
    for(String  tipo : tipos) {
      ret += "\n   " + tipo;
    }
    return ret;
    }
}
