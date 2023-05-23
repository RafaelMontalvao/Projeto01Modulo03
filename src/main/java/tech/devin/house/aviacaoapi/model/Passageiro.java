package tech.devin.house.aviacaoapi.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PASSAGEIRO")
public class Passageiro {

    @Id
    Long cpf;

    String nome;

    LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    Classificacao classificacao;

    Integer milhas = 100;

}
