package tech.devin.house.aviacaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devin.house.aviacaoapi.exception.RegistroNaoEncontradoException;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.repository.BilheteEmbarqueRepository;
import tech.devin.house.aviacaoapi.repository.PassageiroRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmacaoService {

      @Autowired
      private BilheteEmbarqueRepository bilheteEmbarqueRepository;
      @Autowired
      private PassageiroRepository passageiroRepository;

      @Autowired
      private AssentoService assentoService;



    public BilheteEmbarque consultarCpf(Long cpf) {
        Optional<BilheteEmbarque> bilheteEmbarqueOpt = bilheteEmbarqueRepository.findByPassageiro_cpf(cpf);
        if(bilheteEmbarqueOpt.isEmpty())
            return null;
        BilheteEmbarque bilhete = bilheteEmbarqueOpt.get();
            return bilhete;
    }

    public BilheteEmbarque confirmacao(BilheteEmbarque bilhete) {
        Long cpf = bilhete.getPassageiro().getCpf();
        String assentos = bilhete.getAssento();
        if(!passageiroRepository.existsByCpf(cpf) || !assentoService.existsAssento(assentos)) {
            throw new RegistroNaoEncontradoException();
        }
        String eticket = UUID.randomUUID().toString();
        bilhete.setEticket(eticket);
        bilhete.setDataHoraConfirmacao(LocalDateTime.now());
        bilheteEmbarqueRepository.save(bilhete);
        return bilhete;
    }
}
