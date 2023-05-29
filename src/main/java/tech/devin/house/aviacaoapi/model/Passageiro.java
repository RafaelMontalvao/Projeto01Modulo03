package tech.devin.house.aviacaoapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "PASSAGEIRO")
@AllArgsConstructor
public class Passageiro {

    @Id
    private Long cpf;

    private String nome;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    private Integer milhas;

    @OneToOne
    @JoinColumn(name = "eticket", referencedColumnName = "eticket")
    private BilheteEmbarque bilhete;




}

