package tech.devin.house.aviacaoapi.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.repository.PassageiroRepository;

import java.util.List;

@Service
@Slf4j
public class PassageiroService {

    @Autowired
    private PassageiroRepository repo;

    public List<Passageiro> listar() {
        return repo.findAll();
    }



}
