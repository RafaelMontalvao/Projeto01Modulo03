package tech.devin.house.aviacaoapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tech.devin.house.aviacaoapi.model.Classificacao;

import java.time.LocalDate;

@Data
public class PassageiroCompletoResponse {


    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String classificacao;
    private Integer milhas;
    private String eticket;
    private String assento;
    private LocalDate DataHoraConfirmacao;



}
