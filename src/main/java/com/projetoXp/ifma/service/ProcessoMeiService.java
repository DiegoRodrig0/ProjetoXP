package com.projetoXp.ifma.service;

import com.projetoXp.ifma.model.ProcessoMei;
import com.projetoXp.ifma.model.StatusProcesso;
import com.projetoXp.ifma.repositories.ProcessoMeiRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoMeiService {

    @Autowired
    private ProcessoMeiRepository processoMeiRepository;

    @Autowired
    private EmailService emailService;

    public ProcessoMei salvarProcesso(ProcessoMei processoMei) {
        ProcessoMei processoSalvo = processoMeiRepository.save(processoMei);

        // Enviar e-mail ao usuário após o registro
        String destinatario = processoMei.getUsuarioMei().getEmail();
        String assunto = "Notificação de Registro de Processo";
        String mensagem = String.format("Seu processo de abertura de MEI foi registrado com sucesso. \nStatus inicial: %s", processoMei.getStatusProcesso());
        emailService.enviarEmail(destinatario, assunto, mensagem);

        return processoSalvo;
    }

    public ProcessoMei atualizarStatus(Long id, String novoStatus) {
        ProcessoMei processo = processoMeiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        if(novoStatus.toUpperCase().equals("PENDENTE")){
            processo.setStatusProcesso(StatusProcesso.PENDENTE);
        } else if(novoStatus.toUpperCase().equals("EM_ANDAMENTO")) {
            processo.setStatusProcesso(StatusProcesso.EM_ANDAMENTO);
        } else if(novoStatus.toUpperCase().equals("FINALIZADO")) {
            processo.setStatusProcesso(StatusProcesso.FINALIZADO);
        } else if(novoStatus.toUpperCase().equals("CANCELADO")) {
            processo.setStatusProcesso(StatusProcesso.CANCELADO);
        }

        ProcessoMei processoAtualizado = processoMeiRepository.save(processo);

        // Enviar e-mail ao usuário após a atualização do status
        String destinatario = processo.getUsuarioMei().getEmail();
        String assunto = "Atualização de Status do Processo";
        String mensagem = String.format("O status do seu processo foi atualizado para: %s", processo.getStatusProcesso());
        emailService.enviarEmail(destinatario, assunto, mensagem);

        return processoAtualizado;
    }

    public ProcessoMei verificarPendencias(String nome, String cpf) {
        Optional<ProcessoMei> processoMeiOpt = processoMeiRepository.finByNomeAndCpf(nome, cpf);
        
        if(processoMeiOpt.isPresent()) {
            ProcessoMei processoMei = processoMeiOpt.get();
            if (processoMei.getTemPendencias()) {
                return processoMei;
            } else {
                String assunto = "O processo não possui pendencias";
            } 
        } else {
            throw new IllegalArgumentException("Processo não encontrado para o nome: " + nome + " e CPF: " + cpf);
        }
        return null;
    }
}
