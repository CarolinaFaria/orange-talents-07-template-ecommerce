package com.carolinapaulo.desafiomercadolivre.compra.outros.transacao;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.compra.outros.enuns.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CompraModel compra;
    private @NotNull LocalDateTime instante;
    private @NotNull StatusTransacao status;
    private @NotBlank String idTransacao;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, String idTransacao, @NotNull @Valid CompraModel compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
        this.instante = LocalDateTime.now();

    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacao.equals(transacao.idTransacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacao);
    }
}
