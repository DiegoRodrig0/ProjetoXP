package com.projetoXp.ifma.service;

import com.projetoXp.ifma.model.NivelAcesso;
import com.projetoXp.ifma.model.Usuario;
import com.projetoXp.ifma.model.UsuarioFuncionario;
import com.projetoXp.ifma.repositories.UsuarioFuncionarioRepository;
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
    @Autowired
    private UsuarioFuncionarioRepository usuarioFuncionarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario registrarUsuário(String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    public Usuario login(String email, String senha) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new Exception("Usuário não encontrado.");
        }

        if (senha != usuario.getSenha()) {
            throw new Exception("Senha incorreta.");
        }

        return usuario;
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
        Optional<UsuarioFuncionario> usuarioFuncionario = usuarioFuncionarioRepository.findByCpf(cpf);
        return usuarioFuncionario.isEmpty(); // Retorna true se o CPF não for encontrado
    }

    public UsuarioFuncionario atualizarNivelAcesso(Long id, String status) {
        // Encontra o usuário pelo ID
        UsuarioFuncionario usuario = usuarioFuncionarioRepository.findById(id)
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
        return usuarioFuncionarioRepository.findByNivelAcesso(nivelAcesso);
    }
}
