package tech.devin.house.aviacaoapi.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmacaoRequest {

    @NotNull(message = "{campo.obrigatorio}")
    private Long cpf;

    @NotEmpty(message = "{campo.obrigatorio}")
    private String assento;

    private boolean malasDespachadas;




}
