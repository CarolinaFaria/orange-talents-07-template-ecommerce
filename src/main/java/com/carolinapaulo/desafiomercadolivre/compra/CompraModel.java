package com.carolinapaulo.desafiomercadolivre.compra;


import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.GatewayPagamento;
import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusCompra;
import com.carolinapaulo.desafiomercadolivre.compra.outros.gateway.RetornoGateway;
import com.carolinapaulo.desafiomercadolivre.compra.outros.transacao.Transacao;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Compras")
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private ProdutoModel produtoEscolhido;

    @Positive
    @NotNull
    private int quantidade;

    @ManyToOne
    @NotNull
    @Valid
    private UsuarioModel comprador;

    @Enumerated(value = EnumType.STRING)
    private StatusCompra status;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private GatewayPagamento gateway;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public CompraModel() {
    }

    public CompraModel(@NotNull @Valid ProdutoModel produtoEscolhido, @Positive int quantidade, @NotNull @Valid UsuarioModel comprador, GatewayPagamento gatewayPagamento) {
        this.produtoEscolhido = produtoEscolhido;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gatewayPagamento;
        this.status = StatusCompra.INICIADA;
    }



    public UsuarioModel getComprador() {
        return comprador;
    }

    public UsuarioModel getDonoProduto() {
        return produtoEscolhido.getDono();
    }

    public Long getId() {
        return id;
    }

    public ProdutoModel getProdutoEscolhido() {
        return produtoEscolhido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public void adicionaTransacao(RetornoGateway request) {
        Transacao novaTransacao =request.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida");
        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao>transacoesConcluidasComSucesso (){
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <=1,
                "Erro, tem mais de uma transacao concluida com sucesso");

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }
}
