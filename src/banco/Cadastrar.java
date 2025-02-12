package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dados.Criar;

public class Cadastrar
{
    Connection connection = null;

    public int inserirDados(Criar dados) 
    {
        System.out.println("Inserir Dados");
       
        connection = Conexao.getInstance().getConnection();
        PreparedStatement stmt = null;
        
        try 
        {
            // SQL para inserir os dados do usuário
            String sql = "INSERT INTO usuario (email, senha) VALUES (?, ?)";
            
            // Preparando a instrução e especificando que queremos as chaves geradas automaticamente
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, dados.getCemail());
            stmt.setString(2, dados.getCsenha());
            
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                System.out.println("ID gerado: " + userId);
                return userId; // Retorna o ID gerado
            } else {
                throw new SQLException("Falha ao inserir o usuário, nenhum ID gerado.");
            }

        } 
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return -1;
        } 
        finally 
        {
            try 
            {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e)
            {
                System.out.println("Erro ao desconectar" + e.getMessage());
            }
        }
    }
}
