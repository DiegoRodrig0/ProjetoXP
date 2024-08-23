package com.projetoXp.ifma.controllers;

import com.projetoXp.ifma.model.ProcessoMei;
import com.projetoXp.ifma.service.ProcessoMeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processos")
public class ProcessoMeiController {

    @Autowired
    private ProcessoMeiService processoMeiService;

    @PostMapping("/salvar-processos")
    public ResponseEntity<ProcessoMei> salvarProcessoMei(@RequestBody ProcessoMei processoMei) {
        ProcessoMei processo = processoMeiService.salvarProcesso(processoMei);
        return new ResponseEntity<>(processo, HttpStatus.CREATED);
    }
}
