package dados;

public class InfosCadastro {
    private String nome, idade, telefone, academia;
    private int usuarioId;

    public InfosCadastro(int usuarioId, String nome, String idade, String telefone, String academia) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.academia = academia;
    }

    public InfosCadastro() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
