package com.carolinapaulo.desafiomercadolivre.produto.pergunta;

import com.carolinapaulo.desafiomercadolivre.config.validator.ExistsId;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @Positive
    @ExistsId(domainClass = ProdutoModel.class, fieldName = "id", message = "Não existe esse produto na base de dados")
    private Long idProduto;

    public PerguntaRequest(String titulo) {
        this.titulo = titulo;

    }
    public String getTitulo() {
        return titulo;
    }

    public PerguntaModel converter(ProdutoModel produto, UsuarioModel usuario) {
        return new PerguntaModel(this.titulo, usuario, produto);
    }

    public PerguntaRequest(Long idProduto) {
        this.idProduto = idProduto;
    }

}
