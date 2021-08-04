package com.carolinapaulo.desafiomercadolivre.usuario;


import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cliente")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    @NotNull
    @PastOrPresent
    private LocalDateTime momentoCriacao;


    public UsuarioModel(String login, SenhaHash senhaHash) {
        Assert.isTrue(StringUtils.hasLength(login),"email não pode ser em branco");
        Assert.notNull(senhaHash,"o objeto do tipo senha limpa nao pode ser nulo");


        this.login = login;
        this.senha = senhaHash.hash();
        this.momentoCriacao = LocalDateTime.now();
    }



}
