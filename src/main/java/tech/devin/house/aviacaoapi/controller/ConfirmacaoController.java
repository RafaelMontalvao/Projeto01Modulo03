package tech.devin.house.aviacaoapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devin.house.aviacaoapi.dto.ConfirmacaoRequest;
import tech.devin.house.aviacaoapi.dto.ConfirmacaoResponse;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.service.ConfirmacaoService;

import java.net.URI;


@RestController
@RequestMapping("api/passageiros/confirmacao")
@Data
@AllArgsConstructor
public class ConfirmacaoController {

    private ModelMapper mapper;
    private ConfirmacaoService service;


    @PostMapping
    public ResponseEntity<ConfirmacaoResponse> confirmacao(@RequestBody @Valid ConfirmacaoRequest request) {
        BilheteEmbarque bilhete = mapper.map(request, BilheteEmbarque.class);
        bilhete = service.confirmacao(bilhete);
        ConfirmacaoResponse resp = mapper.map(bilhete, ConfirmacaoResponse.class);
        return ResponseEntity.created(URI.create(resp.getEticket())).body(resp);
    }


}
