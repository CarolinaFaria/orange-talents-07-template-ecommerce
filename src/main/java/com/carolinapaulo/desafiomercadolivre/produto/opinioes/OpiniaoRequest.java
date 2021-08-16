package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import validator.ExistsId;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoRequest {

    @NotBlank
    private String titulo;

    @Max(5)
    @Min(1)
    @NotNull
    private Integer nota;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotBlank
    @ExistsId(domainClass = UsuarioModel.class, fieldName="id")
    @JsonProperty(value="idUsuario")
    private Long idUsuarioLogado;

    @NotBlank
    @ExistsId(domainClass = ProdutoModel.class, fieldName="id")
    @JsonProperty(value="idUsuario")
    private Long idProduto;

    public OpiniaoRequest(String titulo, Integer nota, String descricao, Long idusuarioLogado, Long idProduto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.idUsuarioLogado = idusuarioLogado;
        this.idProduto = idProduto;
    }

    public OpiniaoModel converter(ProdutoModel produto, UsuarioModel usuario) {
        return new OpiniaoModel(this.titulo, this.nota, this.descricao, usuario, produto);
    }
}
