package br.com.autotech.dao;

import br.com.autotech.factory.ConexaoFactory;
import br.com.autotech.model.Peca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaDao {

    // Fase 2: Gravação parametrizada com retorno de ID
    public void salvar(Peca peca) {
        String sql = "INSERT INTO pecas (codigo, nome, preco, estoque) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, peca.getCodigo());
            pstmt.setString(2, peca.getNome());
            pstmt.setDouble(3, peca.getPreco());
            pstmt.setInt(4, peca.getEstoque());
            pstmt.executeUpdate();

            // Recupera o ID gerado pelo SQLite e atribui ao objeto
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    peca.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar peça no banco", e);
        }
    }

    // Fase 3: Recuperação e mapeamento de dados
    public List<Peca> listarTodos() {
        String sql = "SELECT * FROM pecas";
        List<Peca> lista = new ArrayList<>();

        try (Connection conn = ConexaoFactory.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Peca peca = new Peca();
                peca.setId(rs.getLong("id"));
                peca.setCodigo(rs.getString("codigo"));
                peca.setNome(rs.getString("nome"));
                peca.setPreco(rs.getDouble("preco"));
                peca.setEstoque(rs.getInt("estoque"));
                
                lista.add(peca);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar peças", e);
        }
        return lista;
    }
}
