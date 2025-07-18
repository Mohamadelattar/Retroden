package com.api.retroden.controller;

import com.api.retroden.dto.request.CompanyRequest;
import com.api.retroden.dto.response.CompanyResponse;
import com.api.retroden.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        this.companyService.create(companyRequest);
        return ResponseEntity.accepted().body("Company created");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }
    @PutMapping
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.update(companyRequest));
    }
    @DeleteMapping
    public void deleteCompany(@RequestParam Long id) {
        this.companyService.delete(id);
    }
}
