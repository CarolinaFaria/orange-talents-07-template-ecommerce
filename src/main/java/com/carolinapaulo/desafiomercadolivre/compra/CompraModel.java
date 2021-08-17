package com.carolinapaulo.desafiomercadolivre.compra;


import com.carolinapaulo.desafiomercadolivre.compra.enuns.GatewayPagamento;
import com.carolinapaulo.desafiomercadolivre.compra.enuns.StatusCompra;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

    public CompraModel(@NotNull @Valid ProdutoModel produtoEscolhido,@Positive int quantidade, @NotNull @Valid UsuarioModel comprador, GatewayPagamento gatewayPagamento) {
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
}
