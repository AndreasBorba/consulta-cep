package com.br.projetoAPI.projetoAPI.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CepControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testa a consulta de um CEP válido")
    public void testConsultarCepValido() throws Exception {
        String cep = "01001000";

        mockMvc.perform(get("/consultar-cep").param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(view().name("resultado"));
    }

    @Test
    @DisplayName("Testa a consulta de um CEP inválido")
    public void testConsultarCepInvalido() throws Exception {
        String cep = "00000000";

        mockMvc.perform(get("/consultar-cep").param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(view().name("erro"));
    }

    @Test
    @DisplayName("Testa a exclusão de um CEP inexistente")
    public void testDeleteCep() throws Exception {
        Long id = 60000000L;

        mockMvc.perform(delete("/ceps/{id}", id))
                .andExpect(status().isNotFound());
    }
}