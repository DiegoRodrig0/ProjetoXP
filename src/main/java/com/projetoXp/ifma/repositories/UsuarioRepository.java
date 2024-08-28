package com.projetoXp.ifma.repositories;

import com.projetoXp.ifma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail (String email);
}
