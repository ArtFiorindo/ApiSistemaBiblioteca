package br.com.fiap.CP01.Model;

import br.com.fiap.CP01.controller.dto.LivroDTO;

import java.util.List;

public interface GerenciadorBiblioteca {

    void criarLivro(LivroDTO livroDTO);

    List<LivroDTO> listarLivros(String ordenarPor);

    void deletarLivro(String isbn);

    void reservarLivro(String isbn, String userId);

    List<String> listarFilaDeEspera(String isbn);

    void cancelarReserva(String isbn, String userId);
}

