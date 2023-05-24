package tech.devin.house.aviacaoapi.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devin.house.aviacaoapi.service.AssentoService;

import java.util.List;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {

    private final AssentoService assentoService;
    private final ModelMapper modelMapper;

    public AssentoController(AssentoService assentoService, ModelMapper modelMapper) {
        this.assentoService = assentoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity consultarAssentos() {
        List<String> assentos = assentoService.obterAssentos();
        return ResponseEntity.ok(assentos);
    }
}