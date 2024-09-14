package br.com.fiap.CP01.exceptions;

public class LivroJaReservadoException extends RuntimeException {
    public LivroJaReservadoException(String message) {
        super(message);
    }
}