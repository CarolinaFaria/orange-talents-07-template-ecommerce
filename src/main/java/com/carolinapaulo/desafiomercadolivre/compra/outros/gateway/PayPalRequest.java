package com.carolinapaulo.desafiomercadolivre.compra.outros.gateway;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusTransacao;
import com.carolinapaulo.desafiomercadolivre.compra.outros.transacao.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PayPalRequest implements RetornoGateway{

    @NotBlank
    private String idTransacao;

    @Max(1)
    @Min(0)
    private int status;

    public PayPalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(CompraModel compra) {
        StatusTransacao statusCalculado = this.status == 0? StatusTransacao.ERRO
                :StatusTransacao.SUCESSO;
        return new Transacao(statusCalculado,idTransacao, compra);
    }

    public int getStatus() {
        return status;
    }
}
