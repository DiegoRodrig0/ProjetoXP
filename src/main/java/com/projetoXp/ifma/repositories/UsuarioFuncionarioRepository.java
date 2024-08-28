package com.projetoXp.ifma.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoXp.ifma.model.NivelAcesso;
import com.projetoXp.ifma.model.Usuario;
import com.projetoXp.ifma.model.UsuarioFuncionario;

public interface UsuarioFuncionarioRepository extends JpaRepository<UsuarioFuncionario, Long>{
    Optional<UsuarioFuncionario> findByCpf(String cpf);
    List<Usuario> findByNivelAcesso(NivelAcesso nivelAcesso);
}
