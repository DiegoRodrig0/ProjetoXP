package com.projetoXp.ifma.model;

import jakarta.persistence.*;

@Entity
public class ProcessoMei {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String empresa;
    @Column(nullable = false)
    private String categoriaAtividade;
    @Column(nullable = false)
    private String descricaoAtividade;
    
}