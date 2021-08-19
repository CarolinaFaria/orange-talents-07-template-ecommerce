package com.carolinapaulo.desafiomercadolivre.compra.outros.evento_compra;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;

public interface EventoCompraSucesso {

    void processa(CompraModel compra);
}
