package com.carolinapaulo.desafiomercadolivre.usuario;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioRequest> cadastraUsuario(@RequestBody @Valid UsuarioRequest usuario){
        UsuarioModel usuarioModel = usuario.converter(usuarioRepository);
        usuarioRepository.save(usuarioModel);
        return ResponseEntity.ok().build();

    }


}
