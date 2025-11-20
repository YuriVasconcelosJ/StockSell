package br.com.projetovenda.venda.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.projetovenda.venda.exception.EstoqueInsuficienteException;
import br.com.projetovenda.venda.exception.ResourceConflictException;
import br.com.projetovenda.venda.exception.ResourceNotFoundException;
import br.com.projetovenda.venda.exception.model.ErroResponse;
import jakarta.servlet.http.HttpServletRequest;

/*
 * Aqui vamos organizar todas exceções
 */
@RestControllerAdvice
public class GlobalExceptionHandle {

    /**
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResponse> handleResourceNotFound(ResourceNotFoundException ex,
            HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<ErroResponse> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex,
            HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErroResponse> handleResourceConflictExceptio(ResourceConflictException ex,
            HttpServletRequest request) {
        ErroResponse erro = new ErroResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

}
