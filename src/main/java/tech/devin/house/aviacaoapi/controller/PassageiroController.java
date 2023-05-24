package tech.devin.house.aviacaoapi.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devin.house.aviacaoapi.dto.PassageiroCompletoResponse;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.service.PassageiroService;

import java.util.List;

@RestController
@RequestMapping("api/passageiros")
@AllArgsConstructor
public class PassageiroController {

    private ModelMapper modelMapper = new ModelMapper();
    private PassageiroService service;

    @GetMapping
    public ResponseEntity<List<PassageiroCompletoResponse>> consultar() {
        List<Passageiro> passageiros = service.listar();
        List<PassageiroCompletoResponse> passageiroCompletoResponse = passageiros.stream()
                .map(v -> modelMapper.map(v, PassageiroCompletoResponse.class)).toList();
        return ResponseEntity.ok(passageiroCompletoResponse);
    }




}
