package com.carolinapaulo.desafiomercadolivre.compra.outros.enuns;

import com.carolinapaulo.desafiomercadolivre.compra.CompraModel;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento implements Pagamento{

    PAYPAL {
        @Override
        public String redirectUrl(CompraModel compra, UriComponentsBuilder uriBuilder) {
            String urlRetorno = uriBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();
            return "paypal.com/"
                    + compra.getId()
                    + "?redirectUrl="
                    +urlRetorno;
        }
    },

    PAGSEGURO{
        @Override
        public String redirectUrl(CompraModel compra, UriComponentsBuilder uriBuilder) {
            String urlRetorno = uriBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/"
                    + compra.getId()
                    + "?redirectUrl="
                    + urlRetorno;
        }
    }

}
