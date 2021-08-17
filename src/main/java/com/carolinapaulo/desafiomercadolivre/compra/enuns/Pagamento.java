package com.carolinapaulo.desafiomercadolivre.compra.enuns;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import org.springframework.web.util.UriComponentsBuilder;

public interface Pagamento {

    public String redirectUrl(CompraModel compra, UriComponentsBuilder uriBuilder);
}
