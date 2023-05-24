package tech.devin.house.aviacaoapi.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PassageiroResponse {

    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String classificacao;
    private Integer milhas;

}


