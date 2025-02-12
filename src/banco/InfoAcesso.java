package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dados.UsuarioInfo;
import telas.TelaInfo;
import telas.TelaLogin;
import dados.UsuarioSession;
import java.util.ArrayList;
import java.util.List;
import dados.FichaInfo;
import dados.ProcurarFicha;
import dados.Id;


public class InfoAcesso {

    public UsuarioInfo getUsuarioInfo(int userId) {
        UsuarioInfo usuarioInfo = null;
        Connection connection = Conexao.getInstance().getConnection();
        System.out.println("Conectado e buscando informações do usuário com ID: " + userId);


        if (connection == null) {
            System.out.println("Erro: Conexão é nula.");
            return null;
        }

        System.out.println("Conectado e buscando informações do usuário com ID: " + userId);

        String sql = "SELECT nome, idade, telefone, academia FROM info WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);  // Define o id do usuário na consulta

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String nome = res.getString("nome");
                String idade = res.getString("idade");
                String telefone = res.getString("telefone");
                String academia = res.getString("academia");

                usuarioInfo = new UsuarioInfo(nome, idade, telefone, academia);
                System.out.println("Informações encontradas: " + usuarioInfo);
            } else {
            System.out.println("Nenhum dado encontrado para o ID de usuário fornecido: " + userId);
            TelaInfo info = new TelaInfo(userId); // Passa o userId para o construtor de TelaInfo
            info.setExtendedState(info.MAXIMIZED_BOTH);
            info.setLocationRelativeTo(null);
            info.setVisible(true);
            TelaLogin login = new TelaLogin();
            login.dispose();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar informações do usuário: " + e.getMessage());
            
            
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }

        return usuarioInfo;
    }
    
    public List<String> getNomesFichas(UsuarioSession usuarioSession) {
    int userId = usuarioSession.getUserId();
    List<String> nomesFichas = new ArrayList<>();
    Connection connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e buscando todos os nomes de fichas para o usuário com ID: " + userId);

    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return nomesFichas;
    }

    String sql = "SELECT nome_ficha FROM ficha WHERE id = ?"; // Assume que usuario_id é a FK na tabela ficha

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);  // Define o id do usuário na consulta

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            String nomeFicha = res.getString("nome_ficha");
            nomesFichas.add(nomeFicha);
        }

        if (nomesFichas.isEmpty()) {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário fornecido: " + userId);
        } else {
            System.out.println("Nomes das fichas encontradas: " + nomesFichas);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar nomes das fichas: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return nomesFichas;
}
public List<String> getNomesFichas2(int id) {
   
    System.out.println("id recebido- "+id);
    List<String> nomesFichas = new ArrayList<>();
    Connection connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e buscando todos os nomes de fichas para o usuário com ID: " + id);

    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return nomesFichas;
    }

    String sql = "SELECT nome_ficha FROM ficha WHERE id = ?"; // Confirmado como id

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        while (res.next()) {
            String nomeFicha = res.getString("nome_ficha");
            nomesFichas.add(nomeFicha);
        }

        if (nomesFichas.isEmpty()) {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário fornecido: " + id);
        } else {
            System.out.println("Nomes das fichas encontradas: " + nomesFichas);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar nomes das fichas: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return nomesFichas;
}

   public FichaInfo buscarFichaEspecifica(UsuarioSession usuarioSession, String nomeFicha) {
    int userId = usuarioSession.getUserId();
    FichaInfo fichaInfo = null;
    Connection connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e buscando informações da ficha para o usuário com ID: " + userId + " e nome semelhante a: " + nomeFicha);

    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return null;
    }
    
    String sql = "SELECT id_ficha, nome_ficha, exercicios, peso, series FROM ficha WHERE id = ? AND nome_ficha LIKE ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);                // Define o ID do usuário
        stmt.setString(2, "%" + nomeFicha + "%"); // Define o nome da ficha com LIKE para busca parcial

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String nomeEncontrado = res.getString("nome_ficha"); // Renomeado para evitar conflito
            String exercicios = res.getString("exercicios");
            String peso = res.getString("peso");
            String series = res.getString("series");
            int id_ficha = res.getInt("id_ficha");

            fichaInfo = new FichaInfo(nomeEncontrado, exercicios, peso, series);
            fichaInfo.setId_ficha(id_ficha);
            System.out.println("Informações da ficha encontradas: " + fichaInfo);
        } else {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário e nome da ficha fornecidos.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar informações da ficha: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return fichaInfo;
}
   public FichaInfo buscarFichaEspecifica2(int id, String nomeFicha) {
    
    FichaInfo fichaInfo = null;
    Connection connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e buscando informações da ficha para o usuário com ID: " + id + " e nome semelhante a: " + nomeFicha);

    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return null;
    }
    
    String sql = "SELECT nome_ficha, exercicios, peso, series FROM ficha WHERE id = ? AND nome_ficha LIKE ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);                // Define o ID do usuário
        stmt.setString(2, "%" + nomeFicha + "%"); // Define o nome da ficha com LIKE para busca parcial

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String nomeEncontrado = res.getString("nome_ficha"); // Renomeado para evitar conflito
            String exercicios = res.getString("exercicios");
            String peso = res.getString("peso");
            String series = res.getString("series");

            fichaInfo = new FichaInfo(nomeEncontrado, exercicios, peso, series);
            System.out.println("Informações da ficha encontradas: " + fichaInfo);
        } else {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário e nome da ficha fornecidos.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar informações da ficha: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return fichaInfo;
}
      public FichaInfo buscarFichaEspecifica3(UsuarioSession usuarioSession, String nomeFicha) {
    int userId = usuarioSession.getUserId();
    FichaInfo fichaInfo = null;
    Connection connection = Conexao.getInstance().getConnection();
    System.out.println("Conectado e buscando informações da ficha para o usuário com ID: " + userId + " e nome a: " + nomeFicha);

    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return null;
    }
    
    String sql = "SELECT id_ficha, nome_ficha, exercicios, peso, series FROM ficha WHERE id = ? AND nome_ficha = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);                // Define o ID do usuário
        stmt.setString(2, nomeFicha);

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String nomeEncontrado = res.getString("nome_ficha"); // Renomeado para evitar conflito
            String exercicios = res.getString("exercicios");
            String peso = res.getString("peso");
            String series = res.getString("series");
            int id_ficha = res.getInt("id_ficha");

            fichaInfo = new FichaInfo(nomeEncontrado, exercicios, peso, series);
            fichaInfo.setId_ficha(id_ficha);
            System.out.println("Informações da ficha encontradas: " + fichaInfo);
        } else {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário e nome da ficha fornecidos.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar informações da ficha: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return fichaInfo;
}

   public FichaInfo buscarFichaDeOutros(ProcurarFicha procurar) {
    
    FichaInfo fichaInfo = null;
    Connection connection = Conexao.getInstance().getConnection();
 
    if (connection == null) {
        System.out.println("Erro: Conexão é nula.");
        return null;
    }
    
    String sql = "SELECT nome_ficha, exercicios, peso, series FROM ficha WHERE id = ? AND nome_ficha LIKE ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, procurar.getId());                // Define o ID do usuário
        stmt.setString(2, "%" + procurar.getFichaNome() + "%"); // Define o nome da ficha com LIKE para busca parcial

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            String nomeEncontrado = res.getString("nome_ficha"); // Renomeado para evitar conflito
            String exercicios = res.getString("exercicios");
            String peso = res.getString("peso");
            String series = res.getString("series");

            fichaInfo = new FichaInfo(nomeEncontrado, exercicios, peso, series);
            System.out.println("Informações da ficha encontradas: " + fichaInfo);
        } else {
            System.out.println("Nenhuma ficha encontrada para o ID de usuário e nome da ficha fornecidos.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar informações da ficha: " + e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
        }
    }

    return fichaInfo;
}
}
