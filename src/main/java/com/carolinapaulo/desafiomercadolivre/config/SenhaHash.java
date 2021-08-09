package com.carolinapaulo.desafiomercadolivre.config;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class SenhaHash {


    private final String senha;
    public SenhaHash(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "senha tem que ter valor");
        Assert.isTrue(senha.length()>=6, "senha precisa ter 6 ou mais caracteres");

        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(senha);
    }


}
