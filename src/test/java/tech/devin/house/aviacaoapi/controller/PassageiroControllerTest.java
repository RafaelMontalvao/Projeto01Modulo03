package tech.devin.house.aviacaoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.service.PassageiroService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.devin.house.aviacaoapi.model.Classificacao.VIP;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PassageiroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // classe que serializa/des-serializa objetos JSON

    @MockBean
    private
    AssentoController assentoController ;

    @MockBean
    private ConfirmacaoController confirmacaoController;


    @MockBean
    private
    PassageiroController passageiroController;


    private ModelMapper modelMapper = new ModelMapper();

    @MockBean  // mock para dependencias da classe de controller
    private PassageiroService service;






    @Test
    @DisplayName("Consultar todos passageiros")
    void consultar() throws Exception {
        var passageiros = List.of(
                new Passageiro(11111111111L,"Rafael Brito", LocalDate.of(1986, Month.NOVEMBER, 23),VIP,100, null),
                new Passageiro(2222222222L,"Marcela Teixeira", LocalDate.of(1990, Month.DECEMBER, 10),VIP,100, null)
                );
        Mockito.when(service.listar()).thenReturn(passageiros);
        mockMvc.perform(get("/api/passageiros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));

    }
    @Test
    @DisplayName("Consulta Passageiro por CPF")
    void consultarcpf() throws Exception{
        var passageiro = new Passageiro(11111111111L,"Rafael Brito", LocalDate.of(1986, Month.NOVEMBER, 23),VIP,100, null);
        Mockito.when(service.obter(Mockito.anyLong())).thenReturn(passageiro);
        mockMvc.perform(get("/api/passageiros/11111111111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(passageiro.getNome())));
    }



}