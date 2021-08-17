package com.carolinapaulo.desafiomercadolivre.produto;

import com.carolinapaulo.desafiomercadolivre.categoria.CategoriaModel;
import com.carolinapaulo.desafiomercadolivre.produto.caracteristicas.CaracteristicaModel;
import com.carolinapaulo.desafiomercadolivre.produto.caracteristicas.CaracteristicasRequest;
import com.carolinapaulo.desafiomercadolivre.produto.imagem.ImagemProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.opinioes.OpiniaoModel;
import com.carolinapaulo.desafiomercadolivre.produto.opinioes.Opinioes;
import com.carolinapaulo.desafiomercadolivre.produto.pergunta.PerguntaModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
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
    private Integer quantidadeDisponivel;

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
    private Set<OpiniaoModel> opinioes = new HashSet<>();

    @ManyToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<PerguntaModel> perguntas = new TreeSet<>();

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

    public boolean isOwner(UsuarioModel usuario) {
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

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }


    public <T> Set<T> mapCaracteristicas(Function<CaracteristicaModel, T> funcaoMapeadora) {
        return this.listaCaracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapImagens(Function<ImagemProdutoModel, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());

    }

    public <T extends Comparable<T>> SortedSet<T> mapPerguntas(Function<PerguntaModel, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));

    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }

    public boolean abataEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");

        if (quantidade <= this.quantidadeDisponivel) {
            this.quantidadeDisponivel -= quantidade;
            return true;
        }
        return false;
    }


}


