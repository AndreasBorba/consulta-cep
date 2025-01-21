package com.br.projetoAPI.projetoAPI.Service;

import com.br.projetoAPI.projetoAPI.Model.Cep;
import com.br.projetoAPI.projetoAPI.Model.CepResponse;
import com.br.projetoAPI.projetoAPI.Repository.CepRepository;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

@Service
public class CepService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private CepRepository cepRepository;

    @PostConstruct
    public void init() throws Exception {
        TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                HttpClients.custom()
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                        .build()
        );
        
        this.restTemplate = new RestTemplate(factory);
    }

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
            if (e.getStatusCode().is4xxClientError()) {
                CepResponse erroResposta = new CepResponse();
                erroResposta.setCep("CEP inválido");
                return erroResposta;
            }
        } catch (HttpServerErrorException e) {
            CepResponse erroResposta = new CepResponse();
            erroResposta.setCep("Erro no servidor");
            return erroResposta;
        }
        return null;
    }

    public List<Cep> getAllCeps() {
        return cepRepository.findAll();
    }

    @Transactional
    public void deleteCepById(Long id) {
        Optional<Cep> cep = cepRepository.findById(id);
        if (!cep.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "CEP não encontrado com id: " + id);
        }
        cepRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        Optional<Cep> cep = cepRepository.findById(id);
        if (cep.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}

