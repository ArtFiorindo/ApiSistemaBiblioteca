package br.com.fiap.CP01.controller;

import br.com.fiap.CP01.Model.GerenciadorBiblioteca;
import br.com.fiap.CP01.controller.dto.ReservaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private GerenciadorBiblioteca gerenciadorBiblioteca;

    @PostMapping("/{isbn}")
    public ResponseEntity<Void> reservarLivro(@PathVariable String isbn, @RequestBody ReservaDTO reservaDTO) {
        gerenciadorBiblioteca.reservarLivro(isbn, reservaDTO.getUserId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{isbn}/{userId}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable String isbn, @PathVariable String userId) {
        gerenciadorBiblioteca.cancelarReserva(isbn, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<List<String>> listarFilaDeEspera(@PathVariable String isbn) {
        List<String> filaDeEspera = gerenciadorBiblioteca.listarFilaDeEspera(isbn);
        return ResponseEntity.ok(filaDeEspera);
    }

}
