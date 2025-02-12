
package dados;

public class ProcurarFicha {
    private String fichaNome;
    private int id;

    public ProcurarFicha(String fichaNome, int id) {
        this.fichaNome = fichaNome;
        this.id = id;
    }

    public String getFichaNome() {
        return fichaNome;
    }

    public void setFichaNome(String fichaNome) {
        this.fichaNome = fichaNome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
