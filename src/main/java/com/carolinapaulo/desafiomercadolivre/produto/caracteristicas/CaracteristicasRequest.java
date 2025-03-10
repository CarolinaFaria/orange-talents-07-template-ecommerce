package com.carolinapaulo.desafiomercadolivre.produto.caracteristicas;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;

import javax.validation.constraints.NotBlank;
import java.util.Objects;


public class CaracteristicasRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @Deprecated
    public CaracteristicasRequest() {
    }

    public CaracteristicasRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicasRequest that = (CaracteristicasRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public CaracteristicaModel converter(ProdutoModel produto) {
        return new CaracteristicaModel(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


}
