package com.carolinapaulo.desafiomercadolivre.produto.detalhe;

import com.carolinapaulo.desafiomercadolivre.produto.caracteristicas.CaracteristicaModel;

public class DetalheCaracteristicaProduto {

    private String nome;
    private String descricao;

    public DetalheCaracteristicaProduto(CaracteristicaModel caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();

    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
