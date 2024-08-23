package com.projetoXp.ifma.controllers;

import com.projetoXp.ifma.model.NivelAcesso;
import com.projetoXp.ifma.model.Usuario;
import com.projetoXp.ifma.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvar-usuario")
    public ResponseEntity<Map<String, String>> salvarUsuario(@RequestBody Usuario usuario) {

        Map<String, String> response = new HashMap<>();

        // Validação dos dados do usuário antes de salvar
        if (usuario.getEmail() == null || usuario.getSenha() == null) {
            response.put("message", "E-mail ou senha não fornecidos.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Validação de CPF
        if (!usuarioService.isCpfUnico(usuario.getCpf())) {
            response.put("message", "O CPF fornecido já está associado a outro registro de MEI.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Usuario salvarUsuario = usuarioService.salvarUsuario(usuario);

        response.put("message", "Usuário salvo com sucesso.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/validar-email")
    public ResponseEntity<Map<String, String>> validarEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        Map<String, String> response = new HashMap<>();
        if (email == null || email.isEmpty()) {
            response.put("message", "E-mail não fornecido.");
            return ResponseEntity.badRequest().body(response);
        }

        if (usuarioService.validarEmail(email)) {
            response.put("message", "E-mail válido.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "E-mail inválido.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}/atualizar-status")
    public ResponseEntity<Usuario> atualizarNivelAcesso(@PathVariable Long id, @RequestParam String novoStatus) {
        Usuario usuarioValidado = usuarioService.atualizarNivelAcesso(id, novoStatus);
        return new ResponseEntity<>(usuarioValidado, HttpStatus.OK);
    }

    @GetMapping("/filtrar-nivel-acesso")
    public ResponseEntity<List<Usuario>> filtrarUsuarios(@RequestParam NivelAcesso nivelAcesso) {
        List<Usuario> usuarios = usuarioService.buscarUsuariosPorNivelAcesso(nivelAcesso);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
