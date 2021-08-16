package com.carolinapaulo.desafiomercadolivre.produto.caracteristicas;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;

import javax.persistence.*;

@Entity
@Table(name = "Caracteristicas")
public class CaracteristicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProdutoModel produto;

    private String nome;
    private String descricao;

    @Deprecated
    public CaracteristicaModel() {
    }

    public CaracteristicaModel(String nome, String descricao, ProdutoModel produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
