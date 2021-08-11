package com.carolinapaulo.desafiomercadolivre.produto.imagem;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private ProdutoModel produto;

    @URL
    @NotBlank
    private String link;

    public ImagemProdutoModel() {
    }

    public ImagemProdutoModel(ProdutoModel produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
