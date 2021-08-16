package com.carolinapaulo.desafiomercadolivre.produto.pergunta;

import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRepository;
import com.carolinapaulo.desafiomercadolivre.produto.pergunta.email.EmailFake;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    private PerguntaRepository perguntaRepository;

    private ProdutoRepository produtoRepository;

    private EmailFake email;

    public PerguntaController(PerguntaRepository perguntaRepository, ProdutoRepository produtoRepository, EmailFake email) {
        this.perguntaRepository = perguntaRepository;
        this.produtoRepository = produtoRepository;
        this.email = email;
    }

    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> cadastraPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest request, @AuthenticationPrincipal UsuarioModel usuario) {

        Optional<ProdutoModel> produtoObj = produtoRepository.findById(id);
        if (produtoObj.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        //Checa se o usuário é o dono do produto
        if (!produtoObj.get().isOwner(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        PerguntaModel perguntaModel = request.converter(produtoObj.get(), usuario);
        perguntaRepository.save(perguntaModel);

        email.novoEmail(perguntaModel);

        return ResponseEntity.ok().build();

    }
}
