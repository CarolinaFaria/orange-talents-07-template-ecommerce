package com.carolinapaulo.desafiomercadolivre.categoria;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Categoria")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nomeCategoria;

    @ManyToOne
    private CategoriaModel categoriaModel;

    @Deprecated
    public CategoriaModel() {
    }

    public CategoriaModel(String nomeCategoria) {
        Assert.hasLength(nomeCategoria, "O nome não pode estar vazio");
        this.nomeCategoria = nomeCategoria;
    }

    public void setCategoriaModel(CategoriaModel categoriaModel) {
        this.categoriaModel = categoriaModel;
    }
}
