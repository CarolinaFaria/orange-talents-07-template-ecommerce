package com.carolinapaulo.desafiomercadolivre.config.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth")
public class AutenticationController {

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    public AutenticationController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> autentica(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.build();

        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String token = tokenService.gerarToken(authentication);

            TokenResponse tokenResponse = new TokenResponse("Bearer", token);
            return ok(tokenResponse);

        } catch (AuthenticationException e) {
            return badRequest().build();

        }

    }
}
