package tech.devin.house.aviacaoapi.exception;

public class RegistroNaoEncontradoException extends RuntimeException{

    public RegistroNaoEncontradoException( Long cpf) {
        super(" CPF " +    cpf  +  " não encontrado!");
    }
    public RegistroNaoEncontradoException(String assentos){
        super(" Assento "  + assentos +   "Nao Encontrado");
    }
}
