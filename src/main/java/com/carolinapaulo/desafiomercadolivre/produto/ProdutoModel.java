package com.carolinapaulo.desafiomercadolivre.produto;

import com.carolinapaulo.desafiomercadolivre.categoria.CategoriaModel;
import com.carolinapaulo.desafiomercadolivre.produto.caracteristicas.CaracteristicaModel;
import com.carolinapaulo.desafiomercadolivre.produto.caracteristicas.CaracteristicasRequest;
import com.carolinapaulo.desafiomercadolivre.produto.imagem.ImagemProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.opinioes.OpiniaoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private final String nome;

    @Positive
    @NotNull
    private final BigDecimal valor;

    @Positive
    @NotNull
    private final Integer quantidadeDisponivel;

    @NotBlank
    @Size(max = 1000)
    private final String descricao;

    @NotNull
    @ManyToOne
    private final CategoriaModel categoria;

    @ManyToOne
    private final UsuarioModel usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProdutoModel> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<CaracteristicaModel> listaCaracteristicas = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<OpiniaoModel> opinioes;

    private final Instant momentoCadastro = Instant.now();

    public ProdutoModel(String nome, BigDecimal valor, Integer quantidadeDisponivel, String descricao,
                        CategoriaModel categoria, UsuarioModel usuario, Set<CaracteristicasRequest> listaCaracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

        listaCaracteristicas.forEach(cr -> this.listaCaracteristicas.add(cr.converter(this)));

        Assert.isTrue(this.listaCaracteristicas.size() >= 3, "Precisa ter no mínimo 3 características");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoModel produto = (ProdutoModel) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public boolean isOwner(UsuarioModel usuario){
        return this.usuario.equals(usuario);
    }


    public void adicionaImagens(Set<String> listaLinks) {
        Set<ImagemProdutoModel> imagens = listaLinks.stream()
                .map(link -> new ImagemProdutoModel(this, link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public UsuarioModel getDono() {
        return this.usuario;
    }
}
