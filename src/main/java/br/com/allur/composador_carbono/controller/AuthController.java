package br.com.allur.composador_carbono.controller;

import br.com.allur.composador_carbono.config.security.TokenService;
import br.com.allur.composador_carbono.dto.LoginDto;
import br.com.allur.composador_carbono.dto.TokenDto;
import br.com.allur.composador_carbono.dto.UsuarioCadastroDto;
import br.com.allur.composador_carbono.dto.UsuarioExibicaoDto;
import br.com.allur.composador_carbono.models.Usuario;
import br.com.allur.composador_carbono.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;


    @Autowired
    private TokenService tokenService;




    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha()
                );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return usuarioService.salvarUsuario(usuarioCadastroDto);
    }

}
