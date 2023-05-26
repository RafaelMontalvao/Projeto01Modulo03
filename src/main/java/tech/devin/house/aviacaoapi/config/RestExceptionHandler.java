package tech.devin.house.aviacaoapi.config;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.devin.house.aviacaoapi.dto.ErroResponse;
import tech.devin.house.aviacaoapi.exception.FalhaChekinMaioridadeException;
import tech.devin.house.aviacaoapi.exception.MalasNaosDespachadasException;
import tech.devin.house.aviacaoapi.exception.RegistroExistenteException;
import tech.devin.house.aviacaoapi.exception.RegistroNaoEncontradoException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;




    @ControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(RegistroExistenteException.class)
        public ResponseEntity<Object> handleRegistroExistenteException(RegistroExistenteException e) {
            ErroResponse erro = new ErroResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
        }

        @ExceptionHandler(RegistroNaoEncontradoException.class)
        public ResponseEntity<Object> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
            ErroResponse erro = new ErroResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getConstraintViolations().forEach(e -> {
                Iterator<Path.Node> iterator = e.getPropertyPath().iterator();
                String fieldName = null;
                while (iterator.hasNext()) {
                    fieldName = iterator.next().getName();
                }
                String errorMessage = e.getMessage();
                fieldErrors.put(fieldName, errorMessage);
            });
            ErroResponse erro = new ErroResponse("Erro de validação", fieldErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }


        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                fieldErrors.put(fieldName, errorMessage);
            });
            ErroResponse erro = new ErroResponse("Erro de validação", fieldErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }


        // catch any other exception for standard error message handling
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErroResponse erro = new ErroResponse(ex.getMessage());
            return new ResponseEntity<>(erro, headers, status);
        }

        @ExceptionHandler(FalhaChekinMaioridadeException.class)
        public ResponseEntity<Object> handleFalhaExclusaoVeiculoComMultasException(FalhaChekinMaioridadeException e) {
            Map<String, String> retorno = new HashMap<>();
            retorno.put("erro", "Passageiro menor de idade, nao pode  escolher assentos na fileira 4 e 5");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);

        }

        @ExceptionHandler(MalasNaosDespachadasException.class)
        public ResponseEntity<Object> handleFalhaExclusaoVeiculoComMultasException(MalasNaosDespachadasException e) {
            Map<String, String> retorno = new HashMap<>();
            retorno.put("erro", "nas fileiras 4 e 5 as malas devem ser despachadas");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);

        }
    }
