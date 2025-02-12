package banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dados.Login;
import dados.InfosCadastro;
import dados.Id;
import java.sql.PreparedStatement;
import dados.InfosAtualizar;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dados.UsuarioSession;
import dados.Ficha;


public class Acesso {
    Statement stmt = null;
    Connection connection = null;

    public Integer verificaAcesso(Login login) {
        Integer userId = null;
        connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e verificando acesso");

        try {
            stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM usuario");

            while (res.next()) {
                if (login.getEmail().equals(res.getString("email")) 
                        && login.getSenha().equals(res.getString("senha"))) {
                    userId = res.getInt("id"); // Captura o ID do usuário logado
                    System.out.println("Usuário encontrado com ID: " + userId);
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar acesso: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }

        return userId; // Retorna o ID do usuário ou null se não for encontrado
    }
    
    public boolean excluirConta(String delemail, String delete) {
    boolean status = false;
    System.out.println("Excluir Pessoa");

    connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e pronto para excluir");
    Statement stmt = null;
    ResultSet res = null;

    try {
        stmt = connection.createStatement();

        // Verifica se existe um usuário com o email e senha fornecidos
        String query = "SELECT id FROM usuario WHERE email='" + delemail + "' AND senha='" + delete + "'";
        res = stmt.executeQuery(query);

        // Se encontrar o usuário
        if (res.next()) {
            int usuarioId = res.getInt("id"); // Obtém o ID do usuário

            // Primeiro, exclui os dados relacionados na tabela `ficha` e `info`
            String deleteFicha = "DELETE FROM ficha WHERE id=" + usuarioId;
            int fichaResult = stmt.executeUpdate(deleteFicha);
            System.out.println("Registros excluídos de ficha: " + fichaResult);

            String deleteInfo = "DELETE FROM info WHERE id=" + usuarioId;
            int infoResult = stmt.executeUpdate(deleteInfo);
            System.out.println("Registros excluídos de info: " + infoResult);

            // Agora, exclui o usuário na tabela `usuario`
            String deleteUsuario = "DELETE FROM usuario WHERE id=" + usuarioId;
            System.out.println("SQL: " + deleteUsuario);

            if (stmt.executeUpdate(deleteUsuario) != 0) {
                status = true;
                System.out.println("Conta excluída com sucesso.");
            } else {
                status = false;
                System.out.println("Nenhum registro foi excluído.");
            }
        } else {
            System.out.println("Email e senha não correspondem.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao excluir: " + e.getMessage());
        status = false;
    } finally {
        try {
            if (res != null) res.close();
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }
    return status;
}
    public boolean excluirFicha(String excluiFicha) {
    boolean status = false;
    System.out.println("Excluir Ficha");

    connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e pronto para excluir");
    Statement stmt = null;
    UsuarioSession pegaId = new UsuarioSession();

    try {
        stmt = connection.createStatement();

        int usuarioId = pegaId.getUserId();
        
        // Exclui os dados relacionados na tabela `ficha`
        String deleteFicha = "DELETE FROM ficha WHERE id=" + usuarioId + " AND nome_ficha='" + excluiFicha + "'";
        int fichaResult = stmt.executeUpdate(deleteFicha);

        if (fichaResult != 0) {
            status = true;
            System.out.println("Ficha excluída com sucesso. Registros excluídos de ficha: " + fichaResult);
        } else {
            System.out.println("Nenhum registro foi excluído.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao excluir: " + e.getMessage());
        status = false;
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }
    return status;
}


   
   public boolean acessoEdita(String altera, int userId) {
    boolean status = false;  // Inicializa a variável status como false

    connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e verificando acesso");

    try {
        stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT id, senha FROM usuario");

        while (res.next()) {
            if (userId == res.getInt("id") && altera.equals(res.getString("senha"))) {
                status = true;
                break;  // Interrompe o loop ao encontrar o usuário correto
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao verificar acesso: " + e.getMessage());
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return status;  // Retorna o valor correto do status
}

   
  public boolean atualizarDados(InfosAtualizar infos, int id) {
    connection = Conexao.getInstance().getConnection(); // Adicione esta linha para obter a conexão
    String sql = "UPDATE info SET nome = ?, idade = ?, telefone = ?, academia = ? WHERE id = ?";
   int id2 = id;
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, infos.getNome());
        stmt.setString(2, infos.getIdade());
        stmt.setString(3, infos.getTelefone());
        stmt.setString(4, infos.getAcademia());
        
        stmt.setInt(5, id2);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
  
  public boolean atualizarFicha(Ficha ficha, int id_ficha) {
    connection = Conexao.getInstance().getConnection(); // Adicione esta linha para obter a conexão
    String sql = "UPDATE ficha SET nome_ficha = ?, exercicios = ?, peso = ?, series = ? WHERE id = ? AND id_ficha = ?";
    UsuarioSession pegaId = new UsuarioSession();
    int id = pegaId.getUserId();
   
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, ficha.getNome_ficha());
        stmt.setString(2, ficha.getExercicios());
        stmt.setString(3, ficha.getPeso());
        stmt.setString(4, ficha.getSeries());
        
        stmt.setInt(5, id);
        stmt.setInt(6, id_ficha);

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
 public boolean verificaSenhaAntiga(int userId, String senhaAntiga) {
        boolean senhaCorreta = false;
        connection = Conexao.getInstance().getConnection();

        try {
            String sql = "SELECT senha FROM usuario WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet res = stmt.executeQuery();

            if (res.next() && senhaAntiga.equals(res.getString("senha"))) {
                senhaCorreta = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar senha antiga: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }

        return senhaCorreta;
    }

    // Método para atualizar a senha
    public boolean atualizarSenha(int userId, String novaSenha) {
        boolean sucesso = false;
        connection = Conexao.getInstance().getConnection();

        try {
            String sql = "UPDATE usuario SET senha = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, novaSenha);
            stmt.setInt(2, userId);
            sucesso = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }

        return sucesso;
    }

    public void carregarUsuarios(DefaultTableModel modeloTabela, String nomePesquisa) {
    // Definindo a conexão com o banco de dados
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        conn = Conexao.getInstance().getConnection();  // Aqui você obtém a conexão corretamente
        
        // Consulta SQL com filtro de pesquisa
        String sql = "SELECT id, nome, idade, telefone, academia FROM info";
        
        // Se houver pesquisa, aplicamos o filtro no nome
        if (nomePesquisa != null && !nomePesquisa.trim().isEmpty()) {
            sql += " WHERE nome LIKE ?";
        }
        
        stmt = conn.prepareStatement(sql);
        
        // Se houver um filtro de nome, adicionamos o valor do filtro
        if (nomePesquisa != null && !nomePesquisa.trim().isEmpty()) {
            stmt.setString(1, "%" + nomePesquisa + "%"); // Adiciona o filtro no nome
        }
        
        rs = stmt.executeQuery();
        
        // Limpa a tabela antes de carregar os dados
        modeloTabela.setRowCount(0);
        
        // Preenche a tabela com os resultados
        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String idade = rs.getString("idade");
            String telefone = rs.getString("telefone");
            String academia = rs.getString("academia");
            modeloTabela.addRow(new Object[]{id, nome, idade, telefone, academia});
        }
    } catch (SQLException e) {
        System.out.println("Erro ao carregar usuários: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}

}


