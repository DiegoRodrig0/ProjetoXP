package com.projetoXp.ifma.model;

import jakarta.persistence.Entity;

@Entity
public class UsuarioMei extends Usuario{
    
    ProcessoMei processoMei;

    public ProcessoMei getProcessoMei() {
        return processoMei;
    }

    public void setProcessoMei(ProcessoMei processoMei) {
        this.processoMei = processoMei;
    }

}
