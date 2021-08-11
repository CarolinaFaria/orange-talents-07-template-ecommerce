package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRepository;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRequest;
import com.carolinapaulo.desafiomercadolivre.produto.imagem.Uploader;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    private ProdutoRepository produtoRepository;
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    public OpiniaoController(ProdutoRepository produtoRepository, Uploader uploader, OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.opiniaoRepository = opiniaoRepository;
    }


    @PostMapping("/{id}/opinioes")
    public ResponseEntity<ProdutoRequest> cadastraOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest opiniao, @AuthenticationPrincipal UsuarioModel usuario) {
        Optional<ProdutoModel> produtoObj = produtoRepository.findById(id);

        //Checa se o produto existe no banco de dados
        if (produtoObj.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        //Converte o DTO para um objeto modelo e salva no banco
        OpiniaoModel opiniaoModel = opiniao.converter(produtoObj.get(), usuario);
        opiniaoRepository.save(opiniaoModel);

        return ResponseEntity.ok().build();
    }
}
