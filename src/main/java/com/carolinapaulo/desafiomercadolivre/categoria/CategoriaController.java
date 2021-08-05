package com.carolinapaulo.desafiomercadolivre.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<CategoriaRequest> cadastraCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        CategoriaModel categoria = categoriaRequest.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();

    }
}
