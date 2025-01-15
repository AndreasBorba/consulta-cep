package com.br.projetoAPI.projetoAPI.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.projetoAPI.projetoAPI.Model.CepResponse;
import com.br.projetoAPI.projetoAPI.Service.CepService;
import org.springframework.ui.Model;

@Controller
public class CepController {
    @Autowired
    private CepService cepService;

    @GetMapping("/consultar-cep")
    public String consultarCep(@RequestParam String cep, Model model) {
        //Chama o serviço para consultar o CEP
        CepResponse resposta = cepService.consultarCep(cep);
        //Adiciona o resultado ao modelo
        model.addAttribute("resposta", resposta);
        if(resposta.getCep().equals("CEP inválido")){
            return "erro";  // Redireciona para a página de erro
        }else{
            return "resultado";  // Redireciona para a página de sucesso
        }
   }

    @GetMapping("/resultado")
    public String resultado() {
        return "resultado.html";  // Retorna o HTML da página resultado
    }
    @GetMapping("/")
    public String home() {
        return "index.html";  // Retorna o HTML da página inicial
    }
    @GetMapping("/sobre")
    public String sobre() {
        return "sobre.html";  // Retorna o HTML da página sobre
    }
}
