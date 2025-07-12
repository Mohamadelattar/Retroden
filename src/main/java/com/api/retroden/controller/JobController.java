package com.api.retroden.controller;

import com.api.retroden.dto.request.JobRequest;
import com.api.retroden.dto.response.JobResponse;
import com.api.retroden.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<JobResponse>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid JobRequest job) {
        return ResponseEntity.ok(jobService.create(job));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.update(jobRequest));
    }
    @DeleteMapping
   public void delete(@RequestParam Long id) {
        jobService.delete(id);
    }
}
