package tech.devin.house.aviacaoapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssentoService {

    List<String> assentos = new ArrayList<>();


     public List<String> obterAssentos() {

        for (int fileira = 1; fileira <= 9; fileira++) {
            for (char poltrona = 'A'; poltrona <= 'F'; poltrona++) {
                String assento = fileira + String.valueOf(poltrona);
                assentos.add(assento);
            }
        }return assentos;
}
}
