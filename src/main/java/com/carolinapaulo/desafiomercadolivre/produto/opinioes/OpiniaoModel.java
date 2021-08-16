package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Opinioes")
public class OpiniaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    private UsuarioModel usuarioLogado;

    @NotBlank
    @ManyToOne
    private ProdutoModel produto;

    public OpiniaoModel(String titulo, Integer nota, String descricao, UsuarioModel usuarioLogado, ProdutoModel produto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.usuarioLogado = usuarioLogado;
        this.produto = produto;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }
}
