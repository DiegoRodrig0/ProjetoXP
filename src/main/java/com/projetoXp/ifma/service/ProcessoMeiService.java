package com.projetoXp.ifma.service;

import com.projetoXp.ifma.model.ProcessoMei;
import com.projetoXp.ifma.repositories.ProcessoMeiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoMeiService {

    @Autowired
    private ProcessoMeiRepository processoMeiRepository;

    public ProcessoMei salvarProcesso(ProcessoMei processoMei) {
        return processoMeiRepository.save(processoMei);
    }
}
