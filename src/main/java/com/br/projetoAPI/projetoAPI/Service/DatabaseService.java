package com.br.projetoAPI.projetoAPI.Service;

import com.br.projetoAPI.projetoAPI.Model.Cep;
import com.br.projetoAPI.projetoAPI.Repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private CepRepository cepRepository;

    public void saveCep(String cep) {
        Cep cepEntity = new Cep();
        cepEntity.setCep(cep);
        cepRepository.save(cepEntity);
        System.out.println("CEP salvo com sucesso!");
    }
}