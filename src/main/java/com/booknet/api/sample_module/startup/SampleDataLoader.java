package com.booknet.api.sample_module.startup;

import com.booknet.api.sample_module.model.SampleModel;
import com.booknet.api.sample_module.payload.request.SampleCreateRequest;
import com.booknet.api.sample_module.repository.SampleRepository;
import com.booknet.api.sample_module.service.SampleService;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements ApplicationRunner {
    SampleService sampleService;
    SampleRepository sampleRepository;

    @Autowired
    public SampleDataLoader(SampleService service, SampleRepository repository) {
        this.sampleService = service;
        this.sampleRepository = repository;
    }

    public void run(ApplicationArguments args) {
        this.sampleRepository.deleteAll();

        final int NUM_GENERATED_MODEL = 10;
        for (int i = 0; i < NUM_GENERATED_MODEL; i++) {
            Integer number = Utils.math.randomInt(0, 10);
            String text = "abcdef";

            SampleCreateRequest req = new SampleCreateRequest();
            req.setNumber(number);
            req.setText(text);
            this.sampleService.createSample(req);
        }
    }
}
