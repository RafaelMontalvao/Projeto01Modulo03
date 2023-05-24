package tech.devin.house.aviacaoapi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assentos")
public class AssentoController {

    @GetMapping
    public ResponseEntity<List<String>> consultarAssentos() {
        List<String> assentos = obterAssentos();
        return ResponseEntity.ok(assentos);
    }



}
