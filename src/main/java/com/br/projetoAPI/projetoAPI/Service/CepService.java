package com.br.projetoAPI.projetoAPI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.projetoAPI.projetoAPI.Model.CepResponse;

@Service
public class CepService {

    @Autowired
    private RestTemplate restTemplate;

    public CepResponse consultarCep(String cep) {
        // URL da API (exemplo com ViaCEP)
        String url = "https://brasilapi.com.br/api/cep/v2/" + cep;

        try {
            // Fazendo a requisição GET e obtendo a resposta como CepResponse
            ResponseEntity<CepResponse> response = restTemplate.getForEntity(url, CepResponse.class);
            // Retorna o corpo da resposta
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Tratar o erro de requisição HTTP
            if (e.getStatusCode().is4xxClientError()) {
                // Retornar uma resposta de erro personalizada
                CepResponse erroResposta = new CepResponse();
                erroResposta.setCep("CEP inválido");
                return erroResposta;
            }
            throw e; // Re-throw other exceptions
        }
    }
    
}
