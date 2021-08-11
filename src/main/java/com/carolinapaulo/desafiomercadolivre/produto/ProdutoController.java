package com.carolinapaulo.desafiomercadolivre.produto;

import com.carolinapaulo.desafiomercadolivre.categoria.CategoriaRepository;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoRequest> cadastraProduto(@RequestBody @Valid ProdutoRequest produto, @AuthenticationPrincipal UsuarioModel usuario){
        Optional<ProdutoModel> produtoModel = produto.converter(categoriaRepository, usuario);
        if(produtoModel.isPresent()) {
            produtoRepository.save(produtoModel.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
