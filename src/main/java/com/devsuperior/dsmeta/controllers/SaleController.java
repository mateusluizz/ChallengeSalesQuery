package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<SaleMinDTO>> findById(@PathVariable Long id) {
        List<SaleMinDTO> saleMinDTO = service.findById(id);
        return ResponseEntity.ok(saleMinDTO);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleReportDTO>> getReport(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable) {
        Page<SaleReportDTO> saleReportDTOS = service.findReport(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(saleReportDTOS);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<Page<SaleSummaryDTO>> getSummary(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            Pageable pageable) {
        Page<SaleSummaryDTO> saleSummaryDTOS = service.findSummary(minDate, maxDate, pageable);
        return ResponseEntity.ok(saleSummaryDTOS);
    }


}
