package tech.devin.house.aviacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.model.Passageiro;

import java.util.Optional;

public interface BilheteEmbarqueRepository extends JpaRepository<BilheteEmbarque,String> {

    Optional<BilheteEmbarque> findByPassageiro_cpf(Long cpf);
}
