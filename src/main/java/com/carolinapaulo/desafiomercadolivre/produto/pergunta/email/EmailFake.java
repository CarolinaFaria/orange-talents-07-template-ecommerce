package com.carolinapaulo.desafiomercadolivre.produto.pergunta.email;

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
}
