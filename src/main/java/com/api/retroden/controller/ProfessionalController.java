package com.api.retroden.controller;

import com.api.retroden.dto.request.ProfessionalRequest;
import com.api.retroden.dto.response.ProfessionalResponse;
import com.api.retroden.service.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }
    @PostMapping
    public ResponseEntity<?> createProfessional(@RequestBody ProfessionalRequest professionalRequest) {
        this.professionalService.create(professionalRequest);
        return ResponseEntity.accepted().body("Professionel created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.professionalService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<ProfessionalResponse>> findAll() {
        return ResponseEntity.ok().body(this.professionalService.findAll());
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ProfessionalRequest professionelRequest) {
        return ResponseEntity.ok().body(this.professionalService.update(professionelRequest));
    }
    @DeleteMapping
    public void deleteProfesiional(@RequestParam Long id) {
        this.professionalService.delete(id);
    }
}
