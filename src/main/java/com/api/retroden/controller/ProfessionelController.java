package com.api.retroden.controller;

import com.api.retroden.dto.request.ProfessionelRequest;
import com.api.retroden.dto.response.ProfessionelResponse;
import com.api.retroden.service.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professionel")
public class ProfessionelController {
    private final ProfessionalService professionalService;

    public ProfessionelController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }
    @PostMapping
    public ResponseEntity<?> createCertification(@RequestBody @Valid ProfessionelRequest professionelRequest) {
        this.professionalService.create(professionelRequest);
        return ResponseEntity.accepted().body("Professionel created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionelResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.professionalService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<ProfessionelResponse>> findAll() {
        return ResponseEntity.ok().body(this.professionalService.findAll());
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ProfessionelRequest professionelRequest) {
        return ResponseEntity.ok().body(this.professionalService.update(professionelRequest));
    }
    @DeleteMapping
    public void deleteProfesiional(@RequestParam Long id) {
        this.professionalService.delete(id);
    }
}
