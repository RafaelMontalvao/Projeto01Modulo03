package tech.devin.house.aviacaoapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
@Data
public class PassageiroResponse {

    private Long cpf;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String classificacao;
    private Integer milhas;

}


