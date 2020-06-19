package br.com.crptecnologia.workshop.usuarios;

public class EmailEmUsoException extends Exception{
    public EmailEmUsoException(String message) {
        super(message);
    }
}
