package com.carolinapaulo.desafiomercadolivre.produto.pergunta;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "Perguntas")
public class PerguntaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotBlank
    @ManyToOne
    @Valid
    private UsuarioModel usuarioLogado;

    @NotBlank
    @ManyToOne
    @Valid
    private ProdutoModel produto;

    @Deprecated
    public PerguntaModel() {
    }

    public PerguntaModel(String titulo, UsuarioModel usuarioLogado, ProdutoModel produto) {
        this.titulo = titulo;
        this.usuarioLogado = usuarioLogado;
        this.produto = produto;
        this.dataCriacao = dataCriacao;
    }

    public UsuarioModel getInteressada() {
        return usuarioLogado;

    }

    public UsuarioModel getDono() {
        return produto.getDono();
    }
}
