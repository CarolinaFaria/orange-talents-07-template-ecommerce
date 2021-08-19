package com.carolinapaulo.desafiomercadolivre.compra.outros.gateway;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.transacao.Transacao;

public interface RetornoGateway {
    Transacao toTransacao(CompraModel compra);
}
