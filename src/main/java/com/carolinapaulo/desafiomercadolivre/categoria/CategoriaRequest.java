package com.carolinapaulo.desafiomercadolivre.categoria;

import com.carolinapaulo.desafiomercadolivre.validator.ValorUnico;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CategoriaRequest {

    @NotNull
    @ValorUnico(domainClass = CategoriaModel.class, fieldName = "nomeCategoria")
    @JsonProperty(value="nomeCategoria")
    private final String nomeCategoria;

    @JsonProperty(value="idCategoriaMae")
    private final Long categoriaMae;

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaRequest(String nome, Long categoriaMae) {
        this.nomeCategoria = nome;
        this.categoriaMae = categoriaMae;
    }

    public CategoriaModel converter(CategoriaRepository categoriaRepository) {
        CategoriaModel categoria = new CategoriaModel(nomeCategoria);

        if(categoriaMae != null) {
            Optional<CategoriaModel> categoriaBanco = categoriaRepository.findById(categoriaMae);
            categoria.setCategoriaModel(categoriaBanco.get());
        }

        return categoria;
    }
}
