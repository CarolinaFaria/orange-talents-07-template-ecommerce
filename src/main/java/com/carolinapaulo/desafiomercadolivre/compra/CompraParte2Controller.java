package com.carolinapaulo.desafiomercadolivre.compra;

import com.carolinapaulo.desafiomercadolivre.compra.outros.gateway.PagSeguroRequest;
import com.carolinapaulo.desafiomercadolivre.compra.outros.gateway.PayPalRequest;
import com.carolinapaulo.desafiomercadolivre.compra.outros.gateway.RetornoGateway;
import com.carolinapaulo.desafiomercadolivre.compra.outros.evento_compra.EventosNovaCompra;
import com.carolinapaulo.desafiomercadolivre.compra.outros.nota_fiscal.NotaFiscal;
import com.carolinapaulo.desafiomercadolivre.compra.outros.ranking.Ranking;
import com.carolinapaulo.desafiomercadolivre.compra.outros.transacao.TransacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CompraParte2Controller {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private NotaFiscal notaFiscal;
    @Autowired
    private Ranking ranking;
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid PagSeguroRequest request){

        return processa(idCompra,request);
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    public ResponseEntity<?> processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid PayPalRequest request){

        return processa(idCompra,request);
    }

    private ResponseEntity<TransacaoResponse> processa(Long compraId, RetornoGateway retornoGateway) {
        CompraModel compra = compraRepository
                .findById(compraId).get();
        compra.adicionaTransacao(retornoGateway);
        compraRepository.save(compra);

        eventosNovaCompra.processa(compra);

        return ResponseEntity.ok().build();
    }

}
