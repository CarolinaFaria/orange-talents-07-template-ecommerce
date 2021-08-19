package com.carolinapaulo.desafiomercadolivre.compra.outros.ranking;

import javax.validation.constraints.NotNull;

public class NovoRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idDonoProduto;

    public NovoRankingRequest(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    @Override
    public String toString() {
        return "NovoRankingRequest{" +
                "idCompra=" + idCompra +
                ", idDonoProduto=" + idDonoProduto +
                '}';
    }

}
