package br.com.autotech.main;

import br.com.autotech.dao.PecaDao;
import br.com.autotech.model.Peca;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        PecaDao dao = new PecaDao();

        // 1. Criar e salvar uma nova peça de teste
        Peca novaPeca = new Peca();
        novaPeca.setCodigo("PT-" + System.currentTimeMillis() % 10000);
        novaPeca.setNome("Amortecedor Dianteiro");
        novaPeca.setPreco(349.90);
        novaPeca.setEstoque(15);

        System.out.println("Salvando nova peça...");
        dao.salvar(novaPeca);
        System.out.println("Peça salva com Sucesso! ID gerado: " + novaPeca.getId());

        // 2. Listar todas para provar a persistência
        System.out.println("\n--- LISTA DE PEÇAS NO BANCO ---");
        List<Peca> pecas = dao.listarTodos();
        for (Peca p : pecas) {
            System.out.println(p.toString());
        }
    }
}
