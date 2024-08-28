package com.projetoXp.ifma.model;

import jakarta.persistence.Entity;

@Entity
public class UsuarioFuncionario extends Usuario{
    
    NivelAcesso nivelAcesso;

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
