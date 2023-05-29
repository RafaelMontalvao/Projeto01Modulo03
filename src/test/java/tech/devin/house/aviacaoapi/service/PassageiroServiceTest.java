package tech.devin.house.aviacaoapi.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devin.house.aviacaoapi.exception.RegistroNaoEncontradoException;
import tech.devin.house.aviacaoapi.model.Passageiro;
import tech.devin.house.aviacaoapi.repository.PassageiroRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static tech.devin.house.aviacaoapi.model.Classificacao.VIP;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {
    @Mock  // mockando a dependencia da classe que eu quero testar
    private PassageiroRepository repo;

    @InjectMocks  // injentando os mocks como dependencia da classe que eu quero testar
    private PassageiroService service;  // classe que eu quero testar


    @Test
    @DisplayName("Quando tem registros, deve retornar lista com registros")
    void listar() {
        var passageiros = List.of(
                new Passageiro(11111111111L, "Rafael Brito", LocalDate.of(1986, Month.NOVEMBER, 23), VIP, 100, null),
                new Passageiro(2222222222L, "Marcela Teixeira", LocalDate.of(1990, Month.DECEMBER, 10), VIP, 100, null)
        );
        //give
        Mockito.when(repo.findAll()).thenReturn(passageiros);
        // when
        List<Passageiro> resultado = service.listar();
        // then
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
        assertEquals(11111111111L, resultado.get(0).getCpf());
        assertEquals(2222222222L, resultado.get(1).getCpf());
    }

    @Test
    @DisplayName("Quando nao tem registros, deve retornar lista vazia")
    void consultar_listaVazia() {
        // given
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
        // when
        List<Passageiro> resultado = service.listar();
        // then
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Quando nao existe um passageiro com o cpf informado, deve lançar exceção")
    void consultarPorId_naoExistente() {
        Mockito.when(repo.findByCpf(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.obter(1L));
    }


   @Test
    @DisplayName("procurar por Cpf exixtente")
    void obter() {
       var passageiro = new Passageiro(11111111111L,"Rafael Brito", LocalDate.of(1986, Month.NOVEMBER, 23),VIP,100, null);
       Mockito.when(repo.findByCpf(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
       Passageiro resultado = service.obter(passageiro.getCpf());
       assertNotNull(resultado);
       assertEquals(passageiro.getCpf(), resultado.getCpf());
   }
    }


