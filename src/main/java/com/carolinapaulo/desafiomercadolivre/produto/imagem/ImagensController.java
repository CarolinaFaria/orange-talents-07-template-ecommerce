package com.carolinapaulo.desafiomercadolivre.produto.imagem;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRepository;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ImagensController {

    private ProdutoRepository produtoRepository;
    private Uploader uploader;

    @Autowired
    public ImagensController(ProdutoRepository produtoRepository, Uploader uploader) {
        this.produtoRepository = produtoRepository;
        this.uploader = uploader;
    }

    @PostMapping("/{id}/imagens")
    public ResponseEntity<?> adicionarImagens(@PathVariable Long id, @Valid ImagensRequest imagens, @AuthenticationPrincipal UsuarioModel usuario) {
        Optional<ProdutoModel> produtoObj = produtoRepository.findById(id);

        //Checa se o produto existe no banco de dados
        if(produtoObj.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        //Checa se o usuário é o dono do produto
        if(!produtoObj.get().isOwner(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        //Faz o upload das imagens
        Set<String> listaLinks = uploader.enviar(imagens.getImagens());

        //Salva as imagens no produto
        produtoObj.get().adicionaImagens(listaLinks);

        return ResponseEntity.ok().build();
    }
}
