package br.com.fiap.CP01.controller;

import br.com.fiap.CP01.Model.GerenciadorBiblioteca;
import br.com.fiap.CP01.controller.dto.LivroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private GerenciadorBiblioteca gerenciadorBiblioteca;

    @PostMapping
    public ResponseEntity<Void> criarLivro(@RequestBody LivroDTO livroDTO) {
        gerenciadorBiblioteca.criarLivro(livroDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarLivros(@RequestParam(required = false) String ordenarPor) {
        return ResponseEntity.ok(gerenciadorBiblioteca.listarLivros(ordenarPor));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String isbn) {
        gerenciadorBiblioteca.deletarLivro(isbn);
        return ResponseEntity.noContent().build();
    }
}
