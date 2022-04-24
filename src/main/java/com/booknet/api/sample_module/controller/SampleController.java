package com.booknet.api.sample_module.controller;

import com.booknet.api.sample_module.model.SampleModel;
import com.booknet.api.sample_module.payload.request.SampleCreateRequest;
import com.booknet.api.sample_module.payload.request.SampleNotifyRequest;
import com.booknet.api.sample_module.payload.request.SampleUpdateRequest;
import com.booknet.api.sample_module.service.SampleService;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/samples")
public class SampleController {
    @Autowired
    SampleService sampleService;

    @GetMapping
    public ResponseEntity<?> getAllSample() {
        Collection<?> samples = sampleService.getAllSamples();
        return ResponseEntity.ok(samples.toArray());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        SampleModel sample = sampleService.getSample(id);
        return ResponseEntity.ok(sample);
    }

    @PostMapping
    public ResponseEntity<?> createSample(@Valid @RequestBody SampleCreateRequest req) {
        SampleModel newModel = sampleService.createSample(req);
        return ResponseEntity.ok(newModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSample(
            @PathVariable("id") String id,
            @Valid @RequestBody SampleUpdateRequest req
    ) {
        SampleModel editedModel = sampleService.updateSample(id, req);
        return ResponseEntity.ok(editedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSample(@PathVariable("id") String id) {
        SampleModel deleteSample = sampleService.removeSample(id);
        return ResponseEntity.ok(deleteSample);
    }

    @PostMapping("/notify")
    public ResponseEntity<?> doNotify() {
        sampleService.doNotify();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/notify-with-args")
    public ResponseEntity<?> doNotifyWithArgument(@Valid @RequestBody SampleNotifyRequest req) {
        sampleService.doNotifyWithArgument(req);
        return ResponseEntity.ok(null);
    }
}
