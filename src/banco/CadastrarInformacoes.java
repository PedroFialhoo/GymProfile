package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dados.InfosCadastro;
import dados.Ficha;
import dados.UsuarioSession;

public class CadastrarInformacoes
{
    Connection connection = null;

    public boolean inserirDados(InfosCadastro dados) 
    {
        System.out.println("Inserir Informações");
   
    connection = Conexao.getInstance().getConnection();
    PreparedStatement stmt = null;
    
    try 
    {
        String sql = "INSERT INTO info (id, nome, idade, telefone, academia) VALUES (?, ?, ?, ?, ?)";
        
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, dados.getUsuarioId()); // Usar o ID do usuário do objeto Infos
        stmt.setString(2, dados.getNome());
        stmt.setString(3, dados.getIdade());
        stmt.setString(4, dados.getTelefone());
        stmt.setString(5, dados.getAcademia());

        stmt.executeUpdate();
        return true;

    } 
    catch (SQLException e)
    {
        System.out.println(e.getMessage());
        return false;
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
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }
  }
    
    public boolean criarFicha(Ficha ficha, UsuarioSession mandaId) 
    {
        System.out.println("Criar Ficha");
   
    connection = Conexao.getInstance().getConnection();
    PreparedStatement stmt = null;
    
    try 
    {
        String sql = "INSERT INTO ficha (id, nome_ficha, exercicios, peso, series) VALUES (?, ?, ?, ?, ?)";
        
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, mandaId.getUserId()); // Usar o ID do usuário do objeto Infos
        stmt.setString(2, ficha.getNome_ficha());
        stmt.setString(3, ficha.getExercicios());
        stmt.setString(4, ficha.getPeso());
        stmt.setString(5, ficha.getSeries());

        stmt.executeUpdate();
        return true;

    } 
    catch (SQLException e)
    {
        System.out.println(e.getMessage());
        return false;
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
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }
  }
}
