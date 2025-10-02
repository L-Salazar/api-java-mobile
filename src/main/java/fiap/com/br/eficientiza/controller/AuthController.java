package fiap.com.br.eficientiza.controller;

import fiap.com.br.eficientiza.dto.LoginResponseDTO;
import fiap.com.br.eficientiza.entity.Usuario;
import fiap.com.br.eficientiza.exception.CredenciaisInvalidasException;
import fiap.com.br.eficientiza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // DTO para mapear o JSON de login
    public static class LoginDTO {
        private String email;
        private String senha;

        // Getters e setters
        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        String tokenFicticio = "123abc";
        Usuario usuario = usuarioService.buscarUsuarioPorEmailESenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(usuario == null){
            return new ResponseEntity<>("Credenciais inválidas. Verifique seu email e senha.", HttpStatus.BAD_REQUEST);
        }
            LoginResponseDTO loginResponse = new LoginResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getTipo(),
                    "123abc"
            );
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
