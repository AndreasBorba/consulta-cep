package com.br.projetoAPI.projetoAPI.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class CepServiceTest {
    
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CepService cepService;
    
    @Mock
    private DatabaseService databaseService;

    @Test
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
    public void testConsultarCepInvalido() {
        String cep = "00000000";

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }

    @Test
    public void testConsultarCepInvalidoComLetras() {
        String cep = "ABCDEFGH";

        when(restTemplate.getForEntity("https://brasilapi.com.br/api/cep/v2/" + cep, CepResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        CepResponse resposta = cepService.consultarCep(cep);
        assertEquals("CEP inválido", resposta.getCep());
    }
}
