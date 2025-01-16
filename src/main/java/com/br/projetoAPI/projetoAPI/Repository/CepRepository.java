package com.br.projetoAPI.projetoAPI.Repository;

import com.br.projetoAPI.projetoAPI.Model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {
    void deleteById(@NonNull Long id);
}

