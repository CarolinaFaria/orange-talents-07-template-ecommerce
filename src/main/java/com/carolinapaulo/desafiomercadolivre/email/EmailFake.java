package com.carolinapaulo.desafiomercadolivre.email;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import com.carolinapaulo.desafiomercadolivre.produto.pergunta.PerguntaModel;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EmailFake {

    private EnviaEmail enviaEmail;

    public void novoEmail(@NotNull @Valid PerguntaModel perguntaModel) {

       enviaEmail.send("<html>...</html>",
               "Nova pergunta",
               perguntaModel.getInteressada().getEmail(),
               "noreply@mercadolivre.com.br",
               perguntaModel.getDono().getEmail());

    }

    public void novaCompra(CompraModel novaCompra) {
        enviaEmail.send("nova compra..." + novaCompra, "Você tem uma nova compra",
                novaCompra.getComprador().getEmail(),
                "compras@nossomercadolivre.com",
                novaCompra.getDonoProduto().getEmail());
    }

    public void enviarEmailCompraNegada(CompraModel compra, String uri) {
        enviaEmail.send("<html>...</html>",
                "Compra falhou",
                "compras@nossomercadolivre.com",
                "compras@nossomercadolivre.com",
                compra.getComprador().getEmail());

    }

    public void enviaEmailCompraConfirmada(CompraModel compra) {
        enviaEmail.send("<html>...</html>",
                "Compra realizada com sucesso",
                "compras@nossomercadolivre.com",
                "compras@nossomercadolivre.com",
                compra.getComprador().getEmail());

    }
}
