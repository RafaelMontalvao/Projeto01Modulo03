package tech.devin.house.aviacaoapi.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devin.house.aviacaoapi.dto.PassageiroCompletoResponse;
import tech.devin.house.aviacaoapi.dto.PassageiroResponse;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.service.ConfirmacaoService;
import tech.devin.house.aviacaoapi.service.PassageiroService;

import java.util.List;

@RestController
@RequestMapping("api/passageiros")
@AllArgsConstructor
public class PassageiroController {

    private ModelMapper modelMapper ;
    private PassageiroService service;
    private ConfirmacaoService confrimacaoService;


    @GetMapping
    public ResponseEntity<List<PassageiroCompletoResponse>> consultar() {
        List<Passageiro> passageiros = service.listar();
        List<PassageiroCompletoResponse> passageiroCompletoResponse = passageiros.stream()
                .map(v -> modelMapper.map(v, PassageiroCompletoResponse.class)).toList();
        for(PassageiroCompletoResponse passageiro : passageiroCompletoResponse){
            BilheteEmbarque bilheteEmbarque = confrimacaoService.consultarCpf(passageiro.getCpf());
            if(bilheteEmbarque != null){
                passageiro.setEticket(bilheteEmbarque.getEticket());
                passageiro.setDataHoraConfirmacao(bilheteEmbarque.getDataHoraConfirmacao());
                passageiro.setAssento((bilheteEmbarque.getAssento()));
            }

        }
        return ResponseEntity.ok(passageiroCompletoResponse);
    }


    @GetMapping("{cpf}")
    public ResponseEntity<PassageiroResponse> consultarCpf(@PathVariable("cpf") Long cpf) {
        Passageiro passageiros = service.obter(cpf);
        PassageiroResponse resp = modelMapper.map(passageiros, PassageiroResponse.class);
        return ResponseEntity.ok(resp);
    }





}
