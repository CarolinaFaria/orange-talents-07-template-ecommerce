package com.carolinapaulo.desafiomercadolivre.compra.outros.transacao;

import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusTransacao;
import com.fasterxml.jackson.annotation.JsonCreator;

public class TransacaoResponse {

    private StatusTransacao statusTransacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransacaoResponse(StatusTransacao statusTransacao) {
        this.statusTransacao = statusTransacao;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}
