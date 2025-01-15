package com.br.projetoAPI.projetoAPI.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.projetoAPI.projetoAPI.Model.Cep;
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
        model.addAttribute("resposta", resposta);
        if(resposta.getCep().equals("CEP inválido")){
            return "erro";
        }else{
            return "resultado";
        }
   }
   @GetMapping("/listar-ceps")
    public String listarCeps(Model model) {
        // Obtém a lista de CEPs do serviço
        List<Cep> ceps = cepService.getAllCeps();
        model.addAttribute("ceps", ceps);
        return "listar-ceps"; 
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
