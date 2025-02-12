
package dados;

public class Criar {
    
    private String cemail;
    private String csenha;

    public Criar(String cemail, String csenha) {
        this.cemail = cemail;
        this.csenha = csenha;
        }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCsenha() {
        return csenha;
    }

    public void setCsenha(String csenha) {
        this.csenha = csenha;
    }
  
}
