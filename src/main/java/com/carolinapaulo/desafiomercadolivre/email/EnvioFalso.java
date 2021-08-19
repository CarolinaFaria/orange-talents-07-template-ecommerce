package com.carolinapaulo.desafiomercadolivre.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class EnvioFalso implements EnviaEmail {
    
    @Override
    public void send(String corpoEmail, String titulo, String nameFrom, String from, String to) {
        System.out.println(corpoEmail);
        System.out.println(titulo);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);

    }
}
