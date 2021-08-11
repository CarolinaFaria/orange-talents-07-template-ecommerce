package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.hibernate.validator.constraints.Length;

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
    private UsuarioModel usuarioLogado;

    @NotBlank
    private ProdutoModel produto;

    public OpiniaoRequest(String titulo, Integer nota, String descricao, UsuarioModel usuarioLogado, ProdutoModel produto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.usuarioLogado = usuarioLogado;
        this.produto = produto;
    }

    public OpiniaoModel converter(ProdutoModel produto, UsuarioModel usuario) {
        return new OpiniaoModel(this.titulo, this.nota, this.descricao, usuario, produto);
    }
}
