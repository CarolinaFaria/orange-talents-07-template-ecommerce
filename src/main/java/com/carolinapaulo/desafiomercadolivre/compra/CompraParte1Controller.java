package com.carolinapaulo.desafiomercadolivre.compra;

import com.carolinapaulo.desafiomercadolivre.compra.enuns.GatewayPagamento;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoModel;
import com.carolinapaulo.desafiomercadolivre.produto.ProdutoRepository;
import com.carolinapaulo.desafiomercadolivre.produto.pergunta.email.EmailFake;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioModel;
import com.carolinapaulo.desafiomercadolivre.usuario.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraParte1Controller {

    private ProdutoRepository produtoRepository;

    private UsuarioRepository usuarioRepository;

    private CompraRepository compraRepository;

    private EmailFake email;

    public CompraParte1Controller(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, EmailFake email) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.email = email;
    }

    @PostMapping("/produto/{id}")
    public ResponseEntity<?> realizaCompra(@RequestBody @Valid CompraRequest request, @AuthenticationPrincipal UsuarioModel usuarioLogado,
                                           UriComponentsBuilder uriBuilder) {

       ProdutoModel produtoObj = produtoRepository.findById(request.getIdProduto()).get();

        int quantidade = request.getQuantidade();
        boolean quantidadeAbatida = produtoObj.abataEstoque(quantidade);
        GatewayPagamento gateway = request.getGateway();

        if(quantidadeAbatida){
            CompraModel novaCompra = new CompraModel(produtoObj, quantidade, usuarioLogado, gateway);
            compraRepository.save(novaCompra);

            return  ResponseEntity.ok(gateway.redirectUrl(novaCompra, uriBuilder));
        }
        return ResponseEntity.badRequest().build();
    }
}
