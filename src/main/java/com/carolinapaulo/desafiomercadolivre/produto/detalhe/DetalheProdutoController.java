package com.carolinapaulo.desafiomercadolivre.produto.detalhe;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {

    private ProdutoRepository produtoRepository;

    public DetalheProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/{id}")
    public DetalheProdutoResponse detalhaProduto(@PathVariable("id") Long id){
        Optional<ProdutoModel> produto = produtoRepository.findById(id);

        return new DetalheProdutoResponse(produto.get());

    }
}
