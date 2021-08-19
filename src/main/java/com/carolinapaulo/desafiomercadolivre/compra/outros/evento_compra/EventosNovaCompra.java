package com.carolinapaulo.desafiomercadolivre.compra.outros.evento_compra;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusTransacao;
import com.carolinapaulo.desafiomercadolivre.email.EmailFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Service
public class EventosNovaCompra {

    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;
    private EmailFake email;

    public StatusTransacao processa(CompraModel compra) {
        if(compra.processadaComSucesso()){
        eventosCompraSucesso.forEach(evento -> evento.processa(compra));
            email.enviaEmailCompraConfirmada(compra);
            return StatusTransacao.SUCESSO;
        }
        else {
            String uri = compra.getGateway().redirectUrl(compra, UriComponentsBuilder.fromHttpUrl("http://localhost:8080/"));
            email.enviarEmailCompraNegada(compra, uri);
            return StatusTransacao.ERRO;
        }
    }

}
