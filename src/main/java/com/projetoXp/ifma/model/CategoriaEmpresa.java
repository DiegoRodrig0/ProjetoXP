package com.projetoXp.ifma.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoriaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    @OneToMany(mappedBy = "categoriaAtividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessoMei> processos;
}
