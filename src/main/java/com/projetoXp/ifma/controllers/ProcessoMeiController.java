package com.projetoXp.ifma.controllers;

import com.projetoXp.ifma.model.ProcessoMei;
import com.projetoXp.ifma.service.ProcessoMeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processos")
public class ProcessoMeiController {

    @Autowired
    private ProcessoMeiService processoMeiService;

    @PostMapping("/salvar-processo")
    public ResponseEntity<ProcessoMei> salvarProcessoMei(@RequestBody ProcessoMei processoMei) {
        ProcessoMei processo = processoMeiService.salvarProcesso(processoMei);
        return new ResponseEntity<>(processo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/statusProcesso")
    public ResponseEntity<ProcessoMei> atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus) {
        ProcessoMei processoAtualizado = processoMeiService.atualizarStatus(id, novoStatus);
        return new ResponseEntity<>(processoAtualizado, HttpStatus.OK);
    }
}
