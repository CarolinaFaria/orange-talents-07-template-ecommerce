package com.carolinapaulo.desafiomercadolivre.produto;

import com.carolinapaulo.desafiomercadolivre.categoria.CategoriaRepository;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
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
