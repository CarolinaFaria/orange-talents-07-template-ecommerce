package com.carolinapaulo.desafiomercadolivre.compra;

import com.carolinapaulo.desafiomercadolivre.compra.enuns.GatewayPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @Positive
    private int quantidade;

    @NotNull
    private Long idProduto;

    @NotNull
    private GatewayPagamento gateway;

    public CompraRequest(int quantidade, Long idProduto, GatewayPagamento gateway ) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
