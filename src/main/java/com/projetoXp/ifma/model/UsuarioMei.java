package com.projetoXp.ifma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class UsuarioMei extends Usuario{
    
    @OneToOne(mappedBy = "usuarioMei")
    ProcessoMei processoMei;

    public ProcessoMei getProcessoMei() {
        return processoMei;
    }

    public void setProcessoMei(ProcessoMei processoMei) {
        this.processoMei = processoMei;
    }

}
