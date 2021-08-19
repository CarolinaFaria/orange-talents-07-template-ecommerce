package com.carolinapaulo.desafiomercadolivre.compra.outros;

import com.carolinapaulo.desafiomercadolivre.compra.outros.nota_fiscal.NovaCompraNFRequest;
import com.carolinapaulo.desafiomercadolivre.compra.outros.ranking.NovoRankingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@RequestBody @Valid NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("Criando nota fiscal para: " + request.toString());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid NovoRankingRequest request) throws InterruptedException {
        System.out.println("Criando ranking para: " + request.toString());
        Thread.sleep(150);
    }
}
