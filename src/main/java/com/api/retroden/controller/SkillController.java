package com.api.retroden.controller;

import com.api.retroden.dto.request.SkillRequest;
import com.api.retroden.dto.response.SkillResponse;
import com.api.retroden.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponse> findById(@RequestParam Long id) {
        return ResponseEntity.ok(skillService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<SkillResponse>> findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid  SkillRequest skillRequest) {
        this.skillService.create(skillRequest);
        return ResponseEntity.accepted().body("Skill created");
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid SkillRequest skillRequest) {
        return ResponseEntity.ok(skillService.update(skillRequest));
    }
    @DeleteMapping
    public void delete(@RequestParam Long id) {
        this.skillService.delete(id);
    }

}
