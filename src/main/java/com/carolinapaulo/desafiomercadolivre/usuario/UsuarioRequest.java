package com.carolinapaulo.desafiomercadolivre.usuario;

import com.carolinapaulo.desafiomercadolivre.validator.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @ValorUnico(domainClass = UsuarioModel.class, fieldName = "login")
    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsuarioModel converter(UsuarioRepository usuarioRepository) {
            return new UsuarioModel(login, new SenhaHash(senha));
    }
}
