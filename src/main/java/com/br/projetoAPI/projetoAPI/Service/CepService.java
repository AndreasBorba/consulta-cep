package com.br.projetoAPI.projetoAPI.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.projetoAPI.projetoAPI.Model.Cep;
import com.br.projetoAPI.projetoAPI.Model.CepResponse;
import com.br.projetoAPI.projetoAPI.Repository.CepRepository;

@Service
public class CepService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CepRepository cepRepository;

    @Transactional
    public CepResponse consultarCep(String cep) {
        // URL da API (exemplo com Brasil API)
        String url = "https://brasilapi.com.br/api/cep/v2/" + cep;

        try {
            // Fazendo a requisição GET e obtendo a resposta como CepResponse
            ResponseEntity<CepResponse> response = restTemplate.getForEntity(url, CepResponse.class);
            // Retorna o corpo da resposta
            CepResponse cepResponse = response.getBody();
            databaseService.saveCep(cep);
            return cepResponse;
        } catch (HttpClientErrorException e) {
            // Tratar o erro de requisição HTTP
            if (e.getStatusCode().is4xxClientError()) {
                // Retornar uma resposta de erro personalizada
                CepResponse erroResposta = new CepResponse();
                erroResposta.setCep("CEP inválido");
                return erroResposta;
            }else if(e.getStatusCode().is5xxServerError()){
                // Retornar uma resposta de erro personalizada
                CepResponse erroResposta = new CepResponse();
                erroResposta.setCep("Erro no servidor");
                return erroResposta;
            }
            throw e; // Re-throw other exceptions
        }
    }

    public List<Cep> getAllCeps() {
        return cepRepository.findAll();
    }
}
