package br.com.allur.composador_carbono.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro){
        Map<String, String> mapaDeErros = new HashMap<>();

        List<FieldError> campos = erro.getBindingResult().getFieldErrors();

        for (FieldError campo : campos){
            mapaDeErros.put(campo.getField(), campo.getDefaultMessage());
        }

        return mapaDeErros;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados(){
        Map<String, String> mapaDeErros = new HashMap<>();
        mapaDeErros.put("erro", "Usuário já cadastrado");
        return mapaDeErros;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> manusearRoleNaoEncontrada(){
        Map<String, String> mapaDeErros = new HashMap<>();
        mapaDeErros.put("erro", "Valor inválido para o campo role");
        return mapaDeErros;
    }

}
