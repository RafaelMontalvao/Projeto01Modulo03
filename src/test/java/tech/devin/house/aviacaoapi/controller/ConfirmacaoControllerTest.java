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
import tech.devin.house.aviacaoapi.dto.ConfirmacaoRequest;
import tech.devin.house.aviacaoapi.model.BilheteEmbarque;
import tech.devin.house.aviacaoapi.service.ConfirmacaoService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ConfirmacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // classe que serializa/des-serializa objetos JSON

    @MockBean
    private
    AssentoController assentoController ;


    @MockBean
    private
    PassageiroController passageiroController;


    private ModelMapper modelMapper = new ModelMapper();

    @MockBean  // mock para dependencias da classe de controller
    private ConfirmacaoService service;


    @Test
    @DisplayName("Cria Cofirmacao de Chekin ")
    void confirmacao() throws Exception {
        ConfirmacaoRequest request = new ConfirmacaoRequest(11111111111L,"1A",true);
        BilheteEmbarque bilhete = modelMapper.map(request, BilheteEmbarque.class);
        String requestJson = objectMapper.writeValueAsString(request);
        String eticket = "olatudobom";
        LocalDateTime hoje = LocalDateTime.now();
        bilhete.setEticket(eticket);
        bilhete.setDataHoraConfirmacao(hoje);
        Mockito.when(service.confirmacao(Mockito.any(BilheteEmbarque.class))).thenReturn(bilhete);
        mockMvc.perform(post("/api/passageiros/confirmacao")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eticket", is(eticket)))
                .andExpect(jsonPath("$.dataHoraConfirmacao", is(not(empty()))));
     }
    }
