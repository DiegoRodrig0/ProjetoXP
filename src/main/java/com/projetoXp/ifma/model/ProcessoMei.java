package com.projetoXp.ifma.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ProcessoMei {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;

    @ManyToOne
    @JoinColumn(name = "categoria_categoriaEmpresa_id", nullable = false)
    private CategoriaEmpresa categoriaEmpresa;

    private String descricaoAtividade;

    @Lob
    private byte[] documentoIdentidade;

    @Lob
    private byte[] comprovanteEndereco;

    @Lob
    private byte[] declaracaoRegularidadeFiscal;

    @Enumerated(EnumType.STRING)
    private StatusProcesso statusProcesso;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private Date dataInicio;
    private Date dataConclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public CategoriaEmpresa getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(CategoriaEmpresa categoriaEmpresa) {
        this.categoriaEmpresa = categoriaEmpresa;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public byte[] getDocumentoIdentidade() {
        return documentoIdentidade;
    }

    public void setDocumentoIdentidade(byte[] documentoIdentidade) {
        this.documentoIdentidade = documentoIdentidade;
    }

    public byte[] getComprovanteEndereco() {
        return comprovanteEndereco;
    }

    public void setComprovanteEndereco(byte[] comprovanteEndereco) {
        this.comprovanteEndereco = comprovanteEndereco;
    }

    public byte[] getDeclaracaoRegularidadeFiscal() {
        return declaracaoRegularidadeFiscal;
    }

    public void setDeclaracaoRegularidadeFiscal(byte[] declaracaoRegularidadeFiscal) {
        this.declaracaoRegularidadeFiscal = declaracaoRegularidadeFiscal;
    }

    public StatusProcesso getStatusProcesso() {
        return statusProcesso;
    }

    public void setStatusProcesso(StatusProcesso statusProcesso) {
        this.statusProcesso = statusProcesso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
