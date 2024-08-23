package com.projetoXp.ifma.service;

import com.projetoXp.ifma.model.NivelAcesso;
import com.projetoXp.ifma.model.StatusProcesso;
import com.projetoXp.ifma.model.Usuario;
import com.projetoXp.ifma.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Usuario atualizarNivelAcesso(Long id, String status) {
        // Encontra o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(status.toUpperCase().equals("ADMINISTRADOR")){
            usuario.setNivelAcesso(NivelAcesso.ADMINISTRADOR);
        } else if (status.toUpperCase().equals("EDITOR")){
            usuario.setNivelAcesso(NivelAcesso.EDITOR);
        } else {
            usuario.setNivelAcesso(NivelAcesso.VISUALIZADOR);
        }

        // Salva as alterações no repositório
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarUsuariosPorNivelAcesso(NivelAcesso nivelAcesso) {
        return usuarioRepository.findByNivelAcesso(nivelAcesso);
    }
}
