package br.com.autotech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoFactory {
    private static final String URL = "jdbc:sqlite:autotech.db";

    public static Connection getConexao() throws SQLException {
        Connection conexao = DriverManager.getConnection(URL);
        criarTabelaAutonoma(conexao);
        return conexao;
    }

    private static void criarTabelaAutonoma(Connection conexao) {
        String ddl = "CREATE TABLE IF NOT EXISTS pecas (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "codigo TEXT NOT NULL, " +
                     "nome TEXT NOT NULL, " +
                     "preco REAL NOT NULL, " +
                     "estoque INTEGER NOT NULL" +
                     ");";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(ddl);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela de peças: " + e.getMessage());
        }
    }
}
