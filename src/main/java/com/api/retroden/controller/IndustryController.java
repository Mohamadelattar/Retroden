package com.api.retroden.controller;

import com.api.retroden.dto.request.IndustryRequest;
import com.api.retroden.dto.response.IndustryResponse;
import com.api.retroden.service.IndustryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryController {
    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<IndustryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(industryService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<IndustryResponse>> findAll() {
        return ResponseEntity.ok(industryService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid IndustryRequest industryRequest) {
     this.industryService.create(industryRequest);
     return ResponseEntity.accepted().body("Industry created");
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid IndustryRequest industryRequest) {
        return ResponseEntity.accepted().body(industryService.update(industryRequest));
    }
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        this.industryService.delete(id);
    }
}
