package com.api.retroden.controller;

import com.api.retroden.dto.request.CvRequest;
import com.api.retroden.dto.response.CVResponse;
import com.api.retroden.service.CVService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv")
public class CvController {
    private final CVService cvService;

    public CvController(CVService cvService) {
        this.cvService = cvService;
    }
    @GetMapping
    public ResponseEntity<CVResponse> findById(@RequestParam Long id) {
        return ResponseEntity.ok(cvService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<CVResponse>> findAll() {
        return ResponseEntity.ok(cvService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> cretaeCV(@RequestBody @Valid CvRequest cvRequest) {
        this.cvService.create(cvRequest);
        return ResponseEntity.accepted().body("CV created");

    }
    @PutMapping
    public ResponseEntity<?> updateCV(@RequestBody @Valid CvRequest cvRequest) {
        return ResponseEntity.ok(cvService.update(cvRequest));
    }
    @DeleteMapping
    public void deleteCV(@RequestParam Long id) {
        this.cvService.delete(id);
    }
}
