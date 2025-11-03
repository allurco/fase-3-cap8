package br.com.allur.composador_carbono.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FonteNaoEncontradaException extends RuntimeException {

    public FonteNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
