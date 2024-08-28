package com.projetoXp.ifma.repositories;

import com.projetoXp.ifma.model.NivelAcesso;
import com.projetoXp.ifma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail (String email);
    Optional<Usuario> findByCpf(String cpf);
    List<Usuario> findByNivelAcesso(NivelAcesso nivelAcesso);
}
