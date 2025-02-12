package dados;

import dados.UsuarioSession;

public class UsuarioInfo {
    private int userId;
    private String nome;
    private String idade;
    private String telefone;
    private String academia;

    public UsuarioInfo() {
    }

    public UsuarioInfo(String nome, String idade, String telefone, String academia) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.academia = academia;
    }

    public UsuarioInfo(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    
    UsuarioSession id = new UsuarioSession();

    // Sobrescreve o método toString() para exibir os atributos de forma legível
    @Override
    public String toString() {
        return "UsuarioInfo {" +
                "userId=" + id.getUserId() +
                ", nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", academia='" + academia + '\'' +
                '}';
    }
}

