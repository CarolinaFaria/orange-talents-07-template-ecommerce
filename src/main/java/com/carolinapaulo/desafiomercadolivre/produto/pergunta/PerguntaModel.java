package com.carolinapaulo.desafiomercadolivre.produto.pergunta;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Perguntas")
public class PerguntaModel implements Comparable<PerguntaModel> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime dataCriacao;


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
        this.dataCriacao = dataCriacao = LocalDateTime.now();
    }

    public UsuarioModel getInteressada() {
        return usuarioLogado;

    }

    public UsuarioModel getDono() {
        return produto.getDono();
    }

    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaModel that = (PerguntaModel) o;
        return titulo.equals(that.titulo) && usuarioLogado.equals(that.usuarioLogado) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, usuarioLogado, produto);
    }

    @Override
    public int compareTo(PerguntaModel o) {
        return this.titulo.compareTo(o.getTitulo());
    }
}
