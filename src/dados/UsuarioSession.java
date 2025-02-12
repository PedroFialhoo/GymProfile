
package dados;

public class UsuarioSession {
    // Variável estática para armazenar o userId
    private static int userId;

    // Método para obter o userId
    public static int getUserId() {
        return userId;
    }

    // Método para definir o userId
    public static void setUserId(int userId) {
        UsuarioSession.userId = userId;
    }
}