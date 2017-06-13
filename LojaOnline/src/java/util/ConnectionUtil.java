package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection connection;

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        /**
         * Verifica se a conexão está ativa
         */
        if (connection == null) {
            // Carregar o Driver JDBC
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            /**
             * Conectar ao Banco de Dados URL:
             * jdbc:nomeDoProduto:servidor:porta:nomeDoBanco
             */
            String url = "jdbc:firebirdsql:192.168.56.101/3050:/databases/lojaonline.fdb";
            String usuario = "SYSDBA";
            String senha = "471017260699088c7187";

            connection = DriverManager.getConnection(url, usuario, senha);
        }
        return connection;
    }
}
