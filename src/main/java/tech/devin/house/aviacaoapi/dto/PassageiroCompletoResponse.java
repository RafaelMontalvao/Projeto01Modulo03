package tech.devin.house.aviacaoapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassageiroCompletoResponse {


    private Long cpf;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String classificacao;
    private Integer milhas;
    private String eticket;
    private String assento;
    private LocalDateTime DataHoraConfirmacao;



}
