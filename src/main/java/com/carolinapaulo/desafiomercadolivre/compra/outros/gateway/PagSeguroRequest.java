package com.carolinapaulo.desafiomercadolivre.compra.outros.gateway;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusRetornoPagSeguro;
import com.carolinapaulo.desafiomercadolivre.compra.outros.transacao.Transacao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class PagSeguroRequest implements RetornoGateway{

    @NotBlank
    private String idTransacao;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusRetornoPagSeguro status;

    public PagSeguroRequest(String idTransacao, StatusRetornoPagSeguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(CompraModel compra) {

        return new Transacao(status.normaliza(),idTransacao, compra);
    }

    public StatusRetornoPagSeguro getStatus() {
        return status;
    }
}
