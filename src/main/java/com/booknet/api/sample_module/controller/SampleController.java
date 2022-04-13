package com.booknet.api.sample_module.controller;

import com.booknet.api.sample_module.model.SampleModel;
import com.booknet.api.sample_module.payload.request.SampleCreateRequest;
import com.booknet.api.sample_module.payload.request.SampleUpdateRequest;
import com.booknet.api.sample_module.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/samples")
public class SampleController {
    @Autowired
    SampleService sampleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<?> getAllSample() {
        Collection<?> samples = sampleService.getAllSamples();
        return ResponseEntity.ok(samples.toArray());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        SampleModel sample = sampleService.getSample(id);
        return ResponseEntity.ok(sample);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<?> createSample(@Valid @RequestBody SampleCreateRequest req) {
        SampleModel newModel = sampleService.createSample(req);
        return ResponseEntity.ok(newModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<?> updateSample(
            @PathVariable("id") String id,
            @Valid @RequestBody SampleUpdateRequest req
    ) {
        SampleModel editedModel = sampleService.updateSample(id, req);
        return ResponseEntity.ok(editedModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<?> removeSample(@PathVariable("id") String id) {
        SampleModel deleteSample = sampleService.removeSample(id);
        return ResponseEntity.ok(deleteSample);
    }
}
