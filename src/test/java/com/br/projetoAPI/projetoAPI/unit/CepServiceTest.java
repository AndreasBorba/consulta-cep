package com.br.projetoAPI.projetoAPI.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.br.projetoAPI.projetoAPI.Model.CepResponse;
import com.br.projetoAPI.projetoAPI.Service.CepService;
import com.br.projetoAPI.projetoAPI.Service.DatabaseService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class CepServiceTest {
    
    @InjectMocks
    private CepService cepService;

    @Mock
    private RestTemplate restTemplate;
    
    @Mock
    private DatabaseService databaseService;

    @Test
    @DisplayName("Testa a consulta de um CEP válido")
    public void testConsultarCepValido() {
        String cep = "01001000";
        CepResponse mockResponse = new CepResponse();
        mockResponse.setCep(cep);
        mockResponse.setState("SP");
        mockResponse.setCity("São Paulo");

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals(cep, resposta.getCep());
        assertEquals("SP", resposta.getState());
        assertEquals("São Paulo", resposta.getCity());
    }

    @Test
    @DisplayName("Testa a consulta de um CEP inválido")
    public void testConsultarCepInvalido() {
        String cep = "00000000";

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }

    @Test
    @DisplayName("Testa a consulta de um CEP inválido com letras")
    public void testConsultarCepInvalidoComLetras() {
        String cep = "ABCDEFGH";

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }

    @Test
    @DisplayName("Testa a consulta de um CEP com erro no servidor")
    public void testConsultarCepErroServidor() {
        String cep = "00000000";

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("Erro no servidor", resposta.getCep());
    }
}
