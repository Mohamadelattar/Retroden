package com.api.retroden.controller;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/certification")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping
    public ResponseEntity<?> createCertification(@RequestBody @Valid CertificationRequest certificationRequest) {
        this.certificationService.create(certificationRequest);
        return ResponseEntity.accepted().body("Certification created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.certificationService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getAll() {
        return ResponseEntity.ok(certificationService.findAll());
    }

    @PutMapping
    public ResponseEntity<?> updateCertification(@RequestBody @Valid CertificationRequest certificationRequest) {
        return ResponseEntity.ok(certificationService.update(certificationRequest));
    }

    @DeleteMapping
    public void deleteCertification(@RequestParam Long id) {
        this.certificationService.delete(id);
    }


}
