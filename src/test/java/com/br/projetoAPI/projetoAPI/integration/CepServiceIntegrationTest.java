package com.br.projetoAPI.projetoAPI.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.projetoAPI.projetoAPI.Model.CepResponse;
import com.br.projetoAPI.projetoAPI.Service.CepService;

@SpringBootTest
public class CepServiceIntegrationTest {

    @Autowired
    private CepService cepService;

    @Test
    @DisplayName("Testa a consulta de um CEP válido")
    public void testConsultarCepValido() {
        String cep = "01001000";
        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals(cep, resposta.getCep());
        assertEquals("SP", resposta.getState());
        assertEquals("São Paulo", resposta.getCity());
    }

    @Test
    @DisplayName("Testa a consulta de um CEP inválido")
    public void testConsultarCepInvalido() {
        String cep = "00000000";
        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }

    @Test
    @DisplayName("Testa a consulta de um CEP com letras")
    public void testConsultarCepComLetras() {
        String cep = "ABCDE123";
        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }
}
