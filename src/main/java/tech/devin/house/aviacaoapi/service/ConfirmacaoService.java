package tech.devin.house.aviacaoapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devin.house.aviacaoapi.exception.FalhaChekinMaioridadeException;
import tech.devin.house.aviacaoapi.exception.MalasNaosDespachadasException;
import tech.devin.house.aviacaoapi.exception.RegistroExistenteException;
import tech.devin.house.aviacaoapi.exception.RegistroNaoEncontradoException;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.model.Classificacao;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.repository.BilheteEmbarqueRepository;
import tech.devin.house.aviacaoapi.repository.PassageiroRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;



@Service
@Slf4j
public class ConfirmacaoService {

      @Autowired
      private BilheteEmbarqueRepository bilheteEmbarqueRepository;
      @Autowired
      private PassageiroRepository passageiroRepository;

      @Autowired
      private AssentoService assentoService;

      @Autowired
      private PassageiroService passageiroService;



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
            throw new RegistroNaoEncontradoException(cpf);
        }
        if (bilheteEmbarqueRepository.existsByAssento(assentos)){
            throw  new RegistroExistenteException(assentos);
        }
        if((assentos.contains("4") || assentos.contains("5")) && passageiroService.verificaMaiorIdade(cpf) == false ){

          throw new FalhaChekinMaioridadeException();
            }
        if((assentos.contains("4") || assentos.contains("5")) && bilhete.getMalas() == false ){

            throw new MalasNaosDespachadasException();
        }

        Passageiro passageiro = passageiroService.obter(cpf);
        Classificacao classificao = passageiro.getClassificacao();
        Integer milhas = passageiro.getMilhas();
        switch (classificao){
            case VIP:
                milhas = milhas +100;
                break;
            case OURO:
                milhas = milhas + 80;
                break;
            case PRATA:
                milhas = milhas+ 50;
                break;
            case BRONZE:
                milhas = milhas +30;
                break;
            case ASSOCIADO:
                milhas = milhas+ 10;
                break;
        }
        passageiro.setMilhas(milhas);
        String eticket = UUID.randomUUID().toString();
        bilhete.setEticket(eticket);
        bilhete.setDataHoraConfirmacao(LocalDateTime.now());
        log.info("Confirmacao feita pelo passageiro de cpf {} com eticket{}", cpf,eticket);
        bilheteEmbarqueRepository.save(bilhete);
        return bilhete;
    }
}
