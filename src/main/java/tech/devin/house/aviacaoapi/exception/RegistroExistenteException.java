package tech.devin.house.aviacaoapi.exception;

public class RegistroExistenteException extends RuntimeException{

    public RegistroExistenteException(String assentos) {
        super(" Assento "  + assentos +   " já existente!");
    }


    public RegistroExistenteException(String aluno, Long cpf) {
        super(aluno +  " com CPF "  + cpf +   " já existente!");
    }
}

