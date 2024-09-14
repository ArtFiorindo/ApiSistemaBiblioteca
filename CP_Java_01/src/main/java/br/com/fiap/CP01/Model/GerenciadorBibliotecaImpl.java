package br.com.fiap.CP01.Model;

import br.com.fiap.CP01.controller.dto.LivroDTO;
import br.com.fiap.CP01.exceptions.LivroJaReservadoException;
import br.com.fiap.CP01.exceptions.LivroNaoEncontradoException;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class GerenciadorBibliotecaImpl implements GerenciadorBiblioteca {

    private Map<String, Livro> livros = new HashMap<>();
    private Map<String, Queue<String>> reservas = new HashMap<>();

    @Override
    public void criarLivro(LivroDTO livroDTO) {
        if (livros.containsKey(livroDTO.getIsbn())) {
            throw new IllegalArgumentException("ISBN já existe.");
        }
        Livro livro = new Livro(livroDTO.getIsbn(), livroDTO.getTitulo(), livroDTO.getAutor(), livroDTO.getCategoria());
        livros.put(livro.getIsbn(), livro);
    }

    @Override
    public List<LivroDTO> listarLivros(String ordenarPor) {
        List<LivroDTO> livrosDTO = new ArrayList<>();
        for (Livro livro : livros.values()) {
            livrosDTO.add(new LivroDTO(livro.getIsbn(), livro.getTitulo(), livro.getAutor(), livro.getCategoria()));
        }

        if ("titulo".equals(ordenarPor)) {
            livrosDTO.sort(Comparator.comparing(LivroDTO::getTitulo));
        } else if ("autor".equals(ordenarPor)) {
            livrosDTO.sort(Comparator.comparing(LivroDTO::getAutor));
        }

        return livrosDTO;
    }

    @Override
    public void deletarLivro(String isbn) {
        Livro livro = livros.get(isbn);
        if (livro == null) {
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }
        if (livro.isReservado()) {
            throw new LivroJaReservadoException("Não é possível deletar um livro reservado.");
        }
        livros.remove(isbn);
    }

    @Override
    public void reservarLivro(String isbn, String userId) {
        Livro livro = livros.get(isbn);
        if (livro == null) {
            throw new LivroNaoEncontradoException("Livro com ISBN " + isbn + " não encontrado.");
        }
        if (livro.isReservado()) {
            reservas.putIfAbsent(isbn, new LinkedList<>());
            reservas.get(isbn).add(userId);
            System.out.println("Usuário " + userId + " adicionado à fila de espera do livro " + isbn);
            throw new LivroJaReservadoException("Livro com ISBN " + isbn + " já está reservado. Adicionado à fila de espera.");
        }
        livro.setReservado(true);
    }



    @Override
    public List<String> listarFilaDeEspera(String isbn) {
        if (!reservas.containsKey(isbn)) {
            throw new LivroNaoEncontradoException("Fila de espera não encontrada para o livro com ISBN " + isbn);
        }
        Queue<String> filaDeEspera = reservas.get(isbn);
        List<String> filaDeEsperaList = new ArrayList<>(filaDeEspera);
        System.out.println("Fila de espera para livro " + isbn + ": " + filaDeEsperaList);
        return filaDeEsperaList;
    }


    @Override
    public void cancelarReserva(String isbn, String userId) {
        Queue<String> filaDeEspera = reservas.get(isbn);
        if (filaDeEspera != null && filaDeEspera.contains(userId)) {
            filaDeEspera.remove(userId);
        } else {
            throw new LivroNaoEncontradoException("Usuário não encontrado na fila de espera.");
        }
    }
}
