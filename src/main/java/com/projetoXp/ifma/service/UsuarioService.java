package com.projetoXp.ifma.service;

import com.projetoXp.ifma.model.Usuario;
import com.projetoXp.ifma.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Verificação do formato do e-mail
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(email).matches();
    }

    public boolean isCpfUnico(String cpf) {
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
        return usuario.isEmpty(); // Retorna true se o CPF não for encontrado
    }
}
