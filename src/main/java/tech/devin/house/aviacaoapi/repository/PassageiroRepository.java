package tech.devin.house.aviacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devin.house.aviacaoapi.model.Passageiro;

import java.util.Optional;

public interface PassageiroRepository extends JpaRepository<Passageiro,Long> {
    Optional<Passageiro> findByCpf(Long cpf);

    boolean existsByCpf(Long cpf);



}
