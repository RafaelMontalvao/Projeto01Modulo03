package tech.devin.house.aviacaoapi.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PASSAGEIRO")
public class Passageiro {

    @Id
    private Long cpf;

    private String nome;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    private Integer milhas;

}
