package br.com.projetovenda.venda.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String message) {
        super(message);
    }

}
