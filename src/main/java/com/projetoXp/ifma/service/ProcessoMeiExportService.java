package com.projetoXp.ifma.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.projetoXp.ifma.model.ProcessoMei;
import com.projetoXp.ifma.repositories.ProcessoMeiRepository;

@Service
public class ProcessoMeiExportService {

    private final ProcessoMeiRepository processoMeiRepository;

    public ProcessoMeiExportService(ProcessoMeiRepository processoMeiRepository) {
        this.processoMeiRepository = processoMeiRepository;
    }

    public void writeProcessosToCsv(Writer writer) throws IOException {
        List<ProcessoMei> processos = processoMeiRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (ProcessoMei processo : processos) {
                csvPrinter.printRecord(processo.getId(), processo.getTemPendencias());
            }
        }
    }
    
    public void writeProcessosToExcel(OutputStream outputStream) throws IOException {
        List<ProcessoMei> processos = processoMeiRepository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Processos");

        // Cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("CPF");
        headerRow.createCell(3).setCellValue("Tem Pendências");

        // Dados
        int rowNum = 1;
        for (ProcessoMei processo : processos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(processo.getId());
            row.createCell(3).setCellValue(processo.getTemPendencias());
        }

        workbook.write(outputStream);
        workbook.close();
    }
}