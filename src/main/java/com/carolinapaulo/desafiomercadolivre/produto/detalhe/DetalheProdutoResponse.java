package com.carolinapaulo.desafiomercadolivre.produto.detalhe;


import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.opinioes.Opinioes;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Set<DetalheCaracteristicaProduto> caracteristicas;
    private Set<String> imagens;
    private SortedSet<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private final int total;

    public DetalheProdutoResponse(ProdutoModel produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
       // this.caracteristicas = produto.getCaracteristicas().stream().map(c -> new DetalheCaracteristicaProduto(c)).collect(Collectors.toSet());
        this.caracteristicas = produto.mapCaracteristicas(DetalheCaracteristicaProduto::new);
        this.imagens = produto.mapImagens(imagens -> imagens.getLink());
        this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

        Opinioes opinioes = produto.getOpinioes();

        this.opinioes = opinioes.mapOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),"descricao", opiniao.getDescricao());
        });

        this.mediaNotas = opinioes.mediaNotas();
        this.total = opinioes.total();

    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Set<DetalheCaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotal() {
        return total;
    }
}
