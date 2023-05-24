package tech.devin.house.aviacaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devin.house.aviacaoapi.model.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro,Integer> {
}
