package com.projetoXp.ifma.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    @OneToMany(mappedBy = "categoriaEmpresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessoMei> processos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<ProcessoMei> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoMei> processos) {
        this.processos = processos;
    }
}
