package com.carolinapaulo.desafiomercadolivre.compra.outros.enuns;

public enum StatusRetornoPagSeguro {

    SUCESSO, ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.SUCESSO;
        }
        return StatusTransacao.ERRO;
    }
}
