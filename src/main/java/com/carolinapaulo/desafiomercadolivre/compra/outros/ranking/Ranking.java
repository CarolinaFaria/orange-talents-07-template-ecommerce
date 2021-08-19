package com.carolinapaulo.desafiomercadolivre.compra.outros.ranking;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.evento_compra.EventoCompraSucesso;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {

    @Override
    public void processa(CompraModel compra) {
        Assert.isTrue(compra.processadaComSucesso(), "compra não processada");

        RestTemplate rt = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idDonoProduto", compra.getDonoProduto().getId());
        try {
            rt.postForEntity("http://localhost:8080/ranking", request, String.class);
        } catch (RestClientException e) {
            throw e;
        }

    }
}
