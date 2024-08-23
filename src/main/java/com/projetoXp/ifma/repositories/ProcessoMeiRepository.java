package com.projetoXp.ifma.repositories;

import com.projetoXp.ifma.model.ProcessoMei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoMeiRepository extends JpaRepository<ProcessoMei, Long> {
}
