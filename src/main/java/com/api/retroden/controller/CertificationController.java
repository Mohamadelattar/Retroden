package com.api.retroden.controller;

import com.api.retroden.dto.request.CertificationRequest;
import com.api.retroden.dto.response.CertificationResponse;
import com.api.retroden.model.Certification;
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

    @GetMapping
    public ResponseEntity<CertificationResponse> findById(@RequestParam Long id) {
        return ResponseEntity.of(this.certificationService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CertificationResponse>> findAll() {
        return ResponseEntity.ok(certificationService.findAll());
    }



}
