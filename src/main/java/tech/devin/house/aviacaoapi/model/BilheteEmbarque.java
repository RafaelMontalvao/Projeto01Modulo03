package tech.devin.house.aviacaoapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "CHECKIN")
    public class BilheteEmbarque {

        @Id
        private String eticket;

        private String assento;

        private Boolean malas;

        private LocalDate DataHoraConfirmacao;

        @OneToOne
        @JoinColumn(name = "cpf", referencedColumnName = "cpf")
        private Passageiro passageiro;


    }
