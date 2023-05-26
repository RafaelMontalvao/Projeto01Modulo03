package tech.devin.house.aviacaoapi.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devin.house.aviacaoapi.exception.RegistroNaoEncontradoException;
import tech.devin.house.aviacaoapi.model.Classificacao;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.repository.PassageiroRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PassageiroService {


    @Autowired
    private PassageiroRepository repo;


    public List<Passageiro> listar() {
        return repo.findAll();
    }


    public Passageiro obter(Long cpf) {
        Optional<Passageiro> passageiroOpt = repo.findByCpf(cpf);
        if (passageiroOpt.isEmpty()) {
            throw new RegistroNaoEncontradoException(cpf);
        }
        return passageiroOpt.get();
    }

    public boolean verificaMaiorIdade(Long cpf) {
        Passageiro passageiro = obter(cpf);
        LocalDate Datanascimento = passageiro.getDataNascimento();
        LocalDate DataAtual = LocalDate.now();
        Period idade = Period.between(Datanascimento, DataAtual);
        return idade.getYears() > 18;
    }

}








